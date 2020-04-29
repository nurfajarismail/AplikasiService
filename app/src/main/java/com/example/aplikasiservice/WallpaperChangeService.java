package com.example.aplikasiservice;

import android.app.Service;
import android.app.WallpaperManager;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.IBinder;
import java.io.IOException;

import androidx.annotation.Nullable;

public class WallpaperChangeService extends Service implements Runnable {
    private int wallpaperID[] = {R.drawable.kmti, R.drawable.umy};
    private int waktu;
    private int FLAG = 0;
    private Thread t;
    private Bitmap gambar1;
    private Bitmap gambar2;


    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        super.onStartCommand(intent, flags, startId);
        Bundle bundle = intent.getExtras();
        waktu = bundle.getInt("durasi");
        gambar1 = BitmapFactory.decodeResource(getResources(), wallpaperID[0]);
        gambar2 = BitmapFactory.decodeResource(getResources(), wallpaperID[1]);
        t = new Thread(WallpaperChangeService.this);
        t.start();
        return START_STICKY_COMPATIBILITY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        System.exit(0);
    }

    @Override
    public void run() {
        WallpaperManager myWallpaper;
        myWallpaper = WallpaperManager.getInstance(this);
        try {
            while (true) {
                if (FLAG == 0) {
                    myWallpaper.setBitmap(gambar1);
                    FLAG--;
                } else {
                    myWallpaper.setBitmap(gambar2);
                    FLAG--;
                }
                Thread.sleep(100 * waktu);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
