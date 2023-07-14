package com.xlntsmmr.xlnt_phone;

import static android.content.Context.BATTERY_SERVICE;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.BatteryManager;

public class BatteryInfo {

    IntentFilter ifilter;
    Intent batteryStatus;

    int batteryHealth;

    BatteryManager batteryManager;

    public BatteryInfo(Context context) {
        ifilter = new IntentFilter(Intent.ACTION_BATTERY_CHANGED);
        batteryStatus = context.registerReceiver(null, ifilter);
        batteryHealth = batteryStatus.getIntExtra(BatteryManager.EXTRA_HEALTH, -1);
        batteryManager = (BatteryManager) context.getSystemService(BATTERY_SERVICE);
    }

    // Are we charging / charged?
    public boolean isCharging() {
        int status = batteryStatus.getIntExtra(BatteryManager.EXTRA_STATUS, -1);
        boolean isCharging = status == BatteryManager.BATTERY_STATUS_CHARGING ||
                status == BatteryManager.BATTERY_STATUS_FULL;

        return isCharging;
    }

    // How are we charging?
    public String chargingKind() {
        int chargePlug = batteryStatus.getIntExtra(BatteryManager.EXTRA_PLUGGED, -1);
        boolean usbCharge = chargePlug == BatteryManager.BATTERY_PLUGGED_USB;
        boolean acCharge = chargePlug == BatteryManager.BATTERY_PLUGGED_AC;

        if (usbCharge) {
            return "USB";
        } else if (acCharge) {
            return "AC";
        } else {
            return "ETC";
        }
    }

    public float batteryPct() {
        int level = batteryStatus.getIntExtra(BatteryManager.EXTRA_LEVEL, -1);
        int scale = batteryStatus.getIntExtra(BatteryManager.EXTRA_SCALE, -1);

        float batteryPct = level * 100 / (float) scale;

        return batteryPct;
    }

    public String batteryHealth() {
        String healthStatus;
        switch (batteryHealth) {
            case BatteryManager.BATTERY_HEALTH_GOOD:
                healthStatus = "Good";
                break;
            case BatteryManager.BATTERY_HEALTH_OVERHEAT:
                healthStatus = "Overheat";
                break;
            case BatteryManager.BATTERY_HEALTH_DEAD:
                healthStatus = "Dead";
                break;
            case BatteryManager.BATTERY_HEALTH_UNSPECIFIED_FAILURE:
                healthStatus = "Failure";
                break;
            default:
                healthStatus = "Unknown";
                break;
        }

        return healthStatus;
    }


}
