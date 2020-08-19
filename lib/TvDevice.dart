
import 'package:flutter/services.dart';
import 'dart:async';
import 'dart:io';

class TvDevice {

  static const MethodChannel _channel = const MethodChannel('com.awesome.TvDevice');

  static Future<bool> checkTvDevice() async {
    if(Platform.isAndroid){
      final bool tvDevice = await _channel.invokeMethod('com.awesome.checkDevice');
      return tvDevice;
    }else{
      return false;
    }
  }

}
