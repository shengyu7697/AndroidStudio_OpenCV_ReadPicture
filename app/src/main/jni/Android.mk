LOCAL_PATH := $(call my-dir)

include $(CLEAR_VARS)

# opencv
OPENCVROOT:= ~/OpenCV-android-sdk-3.1.0/
OPENCV_CAMERA_MODULES:=on
OPENCV_INSTALL_MODULES:=on
OPENCV_LIB_TYPE:=SHARED
include ${OPENCVROOT}/sdk/native/jni/OpenCV.mk

LOCAL_MODULE := myjni
LOCAL_SRC_FILES := jni.cpp
LOCAL_LDLIBS += -llog

include $(BUILD_SHARED_LIBRARY)