package com.example.aplikasiservice;

import androidx.appcompat.app.AppCompatActivity;

import android.app.WallpaperManager;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class MainActivity extends AppCompatActivity {
    private Button mSetBtn, munSetBtn;
    private RadioButton mMenitradio, mlimaradio, mPuluhRadio, mtigapuluhradio, mjamradio;
    private RadioGroup mTimeRadiogroup;
    public int mChangeTime=60;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mSetBtn=(Button) findViewById(R.id.btnset);
        munSetBtn=(Button) findViewById(R.id.btnunset);
        mMenitradio=(RadioButton) findViewById(R.id.radio0);
        mlimaradio=(RadioButton) findViewById(R.id.radio1);
        mtigapuluhradio=(RadioButton) findViewById(R.id.radio2);
        mjamradio=(RadioButton) findViewById(R.id.radio3);
        mTimeRadiogroup=(RadioGroup) findViewById(R.id.radbut);

        munSetBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent mDisable =new Intent(MainActivity.this, WallpaperChangeService.class);
                stopService(mDisable);
                finish();
            }
        });
        mSetBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int mRadioID=mTimeRadiogroup.getCheckedRadioButtonId();
                if (mMenitradio.getId()==mRadioID){mChangeTime=60;}
                else if (mlimaradio.getId()==mRadioID){mChangeTime=60*5;}
                else if (mtigapuluhradio.getId()==mRadioID){mChangeTime=30*60;}
                else if(mjamradio.getId()==mRadioID){mChangeTime=60*60;}

                Intent mService= new Intent(MainActivity.this, WallpaperChangeService.class);
                Bundle mBundleTime = new Bundle();
                mBundleTime.putInt("durasi", mChangeTime);
                mService.putExtras(mBundleTime);
                startService(mService);
                finish();
            }
        });
    }
}
