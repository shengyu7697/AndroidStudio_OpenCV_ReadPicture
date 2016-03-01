package com.android.opencv.androidstudio_opencv_readpicture;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    private ImageView iv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        iv = new ImageView(this);
        setContentView(iv);

        Bitmap bmp;
        bmp = Bitmap.createBitmap(640, 480, Bitmap.Config.ARGB_8888);
        bmp.eraseColor(Color.GRAY);
        iv.setImageBitmap(bmp);
    }
}
