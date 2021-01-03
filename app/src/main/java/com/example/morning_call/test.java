package com.example.morning_call;


import java.lang.reflect.Method;
import android.app.Activity;
import android.app.Dialog;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.SystemClock;
import android.telephony.TelephonyManager;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.Chronometer;
import android.widget.ImageView;
import android.widget.TextView;
class PhoneBroadcastReceiver extends BroadcastReceiver
{
    private static final String TAG = "message";
    private static boolean mIncomingFlag = false;
    private static String mIncomingNumber = null;
    private static boolean callover = false;
    @Override public void onReceive(Context context, Intent intent)
    {
        if (intent.getAction().equals(Intent.ACTION_CALL))
        {
            // 如果是撥打電話
            mIncomingFlag = false; String phoneNumber = intent.getStringExtra(Intent.EXTRA_PHONE_NUMBER);
        }
        else {
            TelephonyManager tManager = (TelephonyManager) context.getSystemService(Service.TELEPHONY_SERVICE);
            switch (tManager.getCallState())
            {
                case TelephonyManager.CALL_STATE_RINGING:
                //來電狀態
                    mIncomingNumber = intent.getStringExtra("incoming_number");
                    break;
                case TelephonyManager.CALL_STATE_OFFHOOK:
                    //摘機狀態(接聽)
                    mIncomingNumber = intent.getStringExtra("incoming_number");
                    callover = true;
                    break;
                case TelephonyManager.CALL_STATE_IDLE:
                    // 空閑狀態，沒有任何活動。(掛斷)
                    android.util.Log.d("fangc", " mIncomingNumber " + "guaduan");
                    if (mIncomingFlag||callover)
                    {
                        mIncomingFlag = false;
                        callover = false;
                    }
                    break;
            }
        }
    }


    public class test {
            }

}