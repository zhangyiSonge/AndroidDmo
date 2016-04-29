#include <jni.h>

JNIEXPORT jstring JNICALL
Java_com_android_zy_androiddmo_MainActivity_getName(JNIEnv *env, jobject instance) {

    // TODO


    return (*env)->NewStringUTF(env, "hello");
}