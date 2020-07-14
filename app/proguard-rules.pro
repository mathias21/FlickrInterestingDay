# Add project specific ProGuard rules here.
# You can control the set of applied configuration files using the
# proguardFiles setting in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

# Uncomment this to preserve the line number information for
# debugging stack traces.
#-keepattributes SourceFile,LineNumberTable

# If you keep the line number information, uncomment this to
# hide the original source file name.
#-renamesourcefileattribute SourceFile
### OKHTTP
-keep class com.squareup.okhttp3.** { *; }
-keep class okhttp3.** { *; }
-dontwarn okio.**
-dontwarn okhttp3.**
-dontwarn com.squareup.okhttp3.**
-keep interface com.squareup.okhttp3.** { *; }
-dontwarn retrofit2.Platform$Java8
-dontnote retrofit2.Platform
-dontwarn javax.annotation.**
-dontwarn retrofit2.Platform$Java8adapters.
-dontwarn org.codehaus.mojo.animal_sniffer.IgnoreJRERequirement

### SUPPORT
-keep class android.support.v7.** { *; }
-keep interface android.support.v7.** { *; }

# KOIN This is a configuration file for ProGuard.
# http://proguard.sourceforge.net/index.html#manual/usage.html
-dontusemixedcaseclassnames
-dontskipnonpubliclibraryclasses
-verbose

### PICASSO
# Platform calls Class.forName on types which do not exist on Android to determine platform.
-dontnote okhttp3.internal.Platform

##Others should be placed here if Proguard is used. This is just an example

