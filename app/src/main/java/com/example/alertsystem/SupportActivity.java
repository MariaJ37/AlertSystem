package com.example.alertsystem;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class SupportActivity extends AppCompatActivity {
    String email = "armita471@yahoo.com";
    String subject = "Alert System Subject";
    String body = "";
    String chooserTitle = "Alert System Title";
    String number = "+15144308598";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_support);
        getSupportActionBar().hide();


        findViewById(R.id.back1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
                finish();
            }
        });

        findViewById(R.id.callImg).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String phone = "+15144308598";
                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", phone, null));
                startActivity(intent);
            }
        });
        findViewById(R.id.emailImg).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.parse("mailto:" + email));
                emailIntent.putExtra(Intent.EXTRA_SUBJECT, subject);
                emailIntent.putExtra(Intent.EXTRA_TEXT, body);
                //emailIntent.putExtra(Intent.EXTRA_HTML_TEXT, body); //If you are using HTML in your body text
                startActivity(Intent.createChooser(emailIntent, chooserTitle));
            }
        });



    }

}
