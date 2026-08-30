# WebView
-keepclassmembers class com.nexus.v3.JavaScriptBridge {
   public *;
}

# Android
-keep public class android.webkit.** { *; }
-keepnames class android.webkit.**

# Preserve line numbers for debugging
-keepattributes SourceFile,LineNumberTable
-renamesourcefileattribute SourceFile
