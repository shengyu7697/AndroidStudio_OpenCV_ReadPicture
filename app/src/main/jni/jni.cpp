#include "com_android_opencv_androidstudio_opencv_readpicture_MainActivity.h"
#include <jni.h>
#include <string.h>
#include <android/log.h>
#include "opencv2/opencv.hpp"

using namespace cv;

#define LOGI(...) ((void)__android_log_print(ANDROID_LOG_INFO, "ReadPicture::Native", __VA_ARGS__))
#define LOGW(...) ((void)__android_log_print(ANDROID_LOG_WARN, "ReadPicture::Native", __VA_ARGS__))

JNIEXPORT jstring JNICALL Java_com_android_opencv_androidstudio_1opencv_1readpicture_MainActivity_stringFromJNI
        (JNIEnv *env, jobject obj)
{
    LOGW("Java_com_android_opencv_androidstudio_1opencv_1readpicture_MainActivity_stringFromJNI()");

    return env->NewStringUTF("Hello from JNI !");
}

JNIEXPORT void JNICALL Java_com_android_opencv_androidstudio_1opencv_1readpicture_MainActivity_imageProcessJNI
        (JNIEnv *env, jobject obj)
{
    LOGW("Java_com_android_opencv_androidstudio_1opencv_1readpicture_MainActivity_imageProcessJNI()");

    Mat mat = imread("/mnt/sdcard/Download/lena.jpg", IMREAD_COLOR);
    Mat mat_small(100, 100, CV_8UC3);
    resize(mat, mat_small, mat_small.size());
    imwrite("/mnt/sdcard/Download/lena_small2.jpg", mat_small);
}
