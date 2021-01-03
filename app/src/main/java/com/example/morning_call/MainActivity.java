package com.example.morning_call;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.Service;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.UserHandle;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.example.morning_call.ListenToPhoneState;

public class MainActivity extends AppCompatActivity {

    private static final int PERMISSION_REQUEST_CODE = 123;
    private static final String TAG = "test";
    EditText phoneNumber;
    Button call;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        phoneNumber = findViewById(R.id.phoneNumber);
        call = findViewById(R.id.call);

        call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //要權限
                if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    if (ActivityCompat.shouldShowRequestPermissionRationale(MainActivity.this, Manifest.permission.CALL_PHONE)) {
                        // sees the explanation, try again to request the permission.
                        ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.CALL_PHONE}, PERMISSION_REQUEST_CODE);
                    } else {
                        // No explanation needed; request the permission
                        Toast.makeText(MainActivity.this, "請給權限!",Toast.LENGTH_SHORT).show();
                        ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.CALL_PHONE}, PERMISSION_REQUEST_CODE);
                    }
                } else {
                    //Toast.makeText(MainActivity.this, String.format("tel:%1$s", phoneNumber.getText()),Toast.LENGTH_SHORT).show();
                    // Permission has already been granted
//                    Intent intent = new Intent(Intent.ACTION_CALL);
//                    Uri data = Uri.parse(String.format("tel:%1$s", phoneNumber.getText()));
//                    intent.setData(data);
//                    startActivity(intent);

                    new ListenToPhoneState(MainActivity.this,phoneNumber.getText().toString());


                }

            }
        });


    }


}

