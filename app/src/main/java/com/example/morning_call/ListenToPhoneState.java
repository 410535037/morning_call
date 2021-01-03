package com.example.morning_call;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Handler;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;

import androidx.core.app.ActivityCompat;

public class ListenToPhoneState extends PhoneStateListener {
    private Context context;
    String contact_mobile;
    int call_status=0,in,geshu;
    int a= 11111;

    public ListenToPhoneState(Context context, String contact_mobile) {
        this.context = context;
        this.geshu = 1;
        this.contact_mobile = contact_mobile;
    }

    public void onCallStateChanged(int state, String incomingNumber) {
        switch (state) {
            case TelephonyManager. CALL_STATE_OFFHOOK://通話中
                //做一些音樂關閉等操作
                break;
            case TelephonyManager.CALL_STATE_IDLE ://無操作
                if (geshu==5){
                    geshu = 2;
                    break;
                    //abac
                }
                else {
                    if (call_status == 0 ){
                        Handler handler = new Handler();
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                Intent intent = new Intent(Intent.ACTION_CALL);
                                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                intent.setData(Uri.parse("tel:" + contact_mobile));
                                if (ActivityCompat.checkSelfPermission(context, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                                    return;
                                }
                                context.startActivity(intent);
                            }
                        }, 3000);//5秒後會自動執行Runnable中的run方法
                        break;
                    }
                }
                break;
            case TelephonyManager.CALL_STATE_RINGING ://來電話
                //來電免打擾程序要處理的關鍵事務
                break;
            default :
                break;
        }
        super.onCallStateChanged(state, incomingNumber);
    }
}
