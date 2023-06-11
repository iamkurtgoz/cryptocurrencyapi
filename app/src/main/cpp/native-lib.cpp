#include <jni.h>
#include <string>

extern "C" JNIEXPORT jstring JNICALL
Java_com_iamkurtgoz_contract_sensitive_SensitiveKeys_baseAddress(JNIEnv *env, jobject object) {
    return env->NewStringUTF("https://api.coingecko.com/api/v3/");
}