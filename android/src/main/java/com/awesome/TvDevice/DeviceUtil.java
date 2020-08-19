package com.awesome.TvDevice;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.BatteryManager;
import android.telephony.TelephonyManager;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;


public class DeviceUtil {


    /**
     * 判断设备是否是电视机
     */
    static boolean checkDevice(Context context){
        boolean screenStatus = checkScreenInfo(context);
        boolean batterStatus = checkScreenInfo(context);
        return  screenStatus && batterStatus;
    }

    /**
    * 判断屏幕是否大于20英寸
    */
    static private boolean checkScreenInfo(Context context){
        WindowManager manager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Display display = manager.getDefaultDisplay();
        DisplayMetrics metrics = new DisplayMetrics();
        display.getMetrics(metrics);
        double width = Math.pow(metrics.widthPixels / metrics.xdpi,2);
        double height = Math.pow(metrics.heightPixels / metrics.ydpi,2);
        double screenSize = Math.sqrt(width + height);
        return  screenSize >= 20;
    }


    /**
     * 判断设备是否没有SIM卡
     */
    static private boolean checkSIMCard(Context context){
        TelephonyManager manager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        return manager.getPhoneType() == TelephonyManager.PHONE_TYPE_NONE;
    }


    /**
     * 判断设备是否是满电 && 交流电
     */
    static private boolean checkAlternatingCurrent(Context context){
        IntentFilter filter = new IntentFilter(Intent.ACTION_BATTERY_CHANGED);
        Intent batterStatus = context.registerReceiver(null,filter);
        if(batterStatus == null){
            return false;
        }
        int status = batterStatus.getIntExtra(BatteryManager.EXTRA_STATUS,-1);
        boolean isCharging = status == BatteryManager.BATTERY_STATUS_FULL;
        int chargePlug = batterStatus.getIntExtra(BatteryManager.EXTRA_PLUGGED,-1);
        boolean acCharge = chargePlug == BatteryManager.BATTERY_PLUGGED_AC;
        return isCharging && acCharge;
    }
}
