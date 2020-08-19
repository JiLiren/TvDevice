package com.awesome.TvDevice;

import androidx.annotation.NonNull;

import io.flutter.embedding.engine.plugins.FlutterPlugin;
import io.flutter.plugin.common.MethodCall;
import io.flutter.plugin.common.MethodChannel;
import io.flutter.plugin.common.MethodChannel.MethodCallHandler;
import io.flutter.plugin.common.MethodChannel.Result;
import io.flutter.plugin.common.PluginRegistry;
import io.flutter.plugin.common.PluginRegistry.Registrar;

/** TvDevicePlugin */
public class TvDevicePlugin implements FlutterPlugin, MethodCallHandler {
  private MethodChannel channel;

  private static PluginRegistry.Registrar registrar;


  @Override
  public void onAttachedToEngine(@NonNull FlutterPluginBinding flutterPluginBinding) {
    channel = new MethodChannel(flutterPluginBinding.getFlutterEngine().getDartExecutor(), "com.awesome.TvDevice");
    channel.setMethodCallHandler(this);
  }

  public static void registerWith(Registrar registrar) {
    TvDevicePlugin.registrar = registrar;
    final MethodChannel channel = new MethodChannel(registrar.messenger(), "com.awesome.TvDevice");
    channel.setMethodCallHandler(new TvDevicePlugin());
  }

  @Override
  public void onMethodCall(@NonNull MethodCall call, @NonNull Result result) {
    if (call.method.equals("com.awesome.checkDevice")) {
      boolean tvDevice = DeviceUtil.checkDevice(registrar.context());
      result.success(tvDevice);
    } else {
      result.notImplemented();
    }
  }

  @Override
  public void onDetachedFromEngine(@NonNull FlutterPluginBinding binding) {
    channel.setMethodCallHandler(null);
  }
}
