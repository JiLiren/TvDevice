
import 'package:flutter/services.dart';
import 'dart:async';
import 'dart:io';

class TvDevice {

  static const MethodChannel _channel = const MethodChannel('TvDevice');

  static Future<bool> checkTvDevice() async {
    if(Platform.isAndroid){
      final bool tvDevice = await _channel.invokeMethod('getPlatformVersion');
      return tvDevice;
    }else{
      return false;
    }
  }

}
