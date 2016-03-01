package com.android.opencv.androidstudio_opencv_readpicture;

import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

import org.opencv.android.BaseLoaderCallback;
import org.opencv.android.LoaderCallbackInterface;
import org.opencv.android.OpenCVLoader;
import org.opencv.android.Utils;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "ReadPicture::Activity";
    private ImageView iv;

    void imageProcess() {
        // load image from file
        Mat mat = Imgcodecs.imread("/mnt/sdcard/Download/lena.jpg", Imgcodecs.CV_LOAD_IMAGE_COLOR);

        // covert RGBA to BGRA
        Mat mat_show = new Mat();
        Imgproc.cvtColor(mat, mat_show, Imgproc.COLOR_RGBA2BGRA);

        // covert Mat to bitmap
        Bitmap bmp = Bitmap.createBitmap(mat_show.cols(), mat_show.rows(), Bitmap.Config.ARGB_8888);
        Utils.matToBitmap(mat_show, bmp);

        iv.setImageBitmap(bmp);

        // resize to small image
        Mat mat_small = new Mat(100, 100, CvType.CV_8UC3);
        Imgproc.resize(mat, mat_small, mat_small.size());
        Imgcodecs.imwrite("/mnt/sdcard/Download/lena_small.jpg", mat_small);
    }

    private BaseLoaderCallback mLoaderCallback = new BaseLoaderCallback(this) {
        @Override
        public void onManagerConnected(int status) {
            switch (status) {
                case LoaderCallbackInterface.SUCCESS: {
                    Log.i(TAG, "OpenCV loaded successfully");

                    imageProcess();

                } break;
                default:
                {
                    super.onManagerConnected(status);
                } break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        iv = new ImageView(this);
        setContentView(iv);

        Log.i(TAG, "call stringFromJNI(), return string: " + stringFromJNI());
    }

    @Override
    public void onResume() {
        super.onResume();
        if (!OpenCVLoader.initDebug()) {
            Log.d(TAG, "Internal OpenCV library not found. Using OpenCV Manager for initialization");
            OpenCVLoader.initAsync(OpenCVLoader.OPENCV_VERSION_3_0_0, this, mLoaderCallback);
        } else {
            Log.d(TAG, "OpenCV library found inside package. Using it!");
            mLoaderCallback.onManagerConnected(LoaderCallbackInterface.SUCCESS);
        }
    }

    public native String stringFromJNI();

    static {
        System.loadLibrary("myjni");
    }
}
