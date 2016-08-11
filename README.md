# UmengShareLibrary
封装的友盟分享依赖包

初始化:

        ShareDialog.isDebug(true);
        ShareDialog.setWeixin("wx967daebe835fbeac", "5bb696d9ccd75a38c8a0bfe0675559b3");
        ShareDialog.setSina("3921700954", "04b48b094faeb16683c32669824ebdad");
        ShareDialog.setQQ("100424468", "c7394704798a158208a74ab60104f0ba");

调用:

    public class MainActivity extends AppCompatActivity {

        private ShareDialog mShareDialog;
    
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);
    
            initShareDialog();
        }
    
        private void initShareDialog() {
            mShareDialog = new ShareDialog.Build(this)
                    .setTitle("title")
                    .setContent("content")
                    .setTargetUrl("http://www.baidu.com")
                    .setImageUrl("http://www.umeng.com/images/pic/social/integrated_3.png")
                    .build();
        }
    
        public void click(View view) {
            mShareDialog.showDialog();
        }
    
        @Override
        protected void onActivityResult(int requestCode, int resultCode, Intent data) {
            super.onActivityResult(requestCode, resultCode, data);
            mShareDialog.onActivityResult(requestCode, resultCode, data);
        }
    
        @Override
        protected void onDestroy() {
            super.onDestroy();
            mShareDialog.destory();
        }
    }


清单文件和混淆设置和友盟官网一样, 或者直接赋值该项目中的...

        <?xml version="1.0" encoding="utf-8"?>
        <manifest xmlns:android="http://schemas.android.com/apk/res/android"
                  package="com.jiyoutang.umengsharedemo5">
        
            <uses-permission android:name="android.permission.ACCESS_NETWORK_STATE" />
        
            <uses-permission android:name="android.permission.ACCESS_WIFI_STATE" />
        
            <uses-permission android:name="android.permission.READ_PHONE_STATE"/>
        
            <uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE" />
        
            <uses-permission android:name="android.permission.INTERNET" />
        
            <uses-permission android:name="android.permission.READ_LOGS" />
        
            <uses-permission android:name="android.permission.CALL_PHONE" />
        
            <uses-permission android:name="android.permission.ACCESS_FINE_LOCATION" />
        
            <uses-permission android:name="android.permission.ACCESS_COARSE_LOCATION" />
        
            <uses-permission android:name="android.permission.GET_TASKS" />
        
            <uses-permission android:name="android.permission.SET_DEBUG_APP" />
        
            <uses-permission android:name="android.permission.SYSTEM_ALERT_WINDOW" />
        
            <uses-permission android:name="android.permission.GET_ACCOUNTS" />
        
            <uses-permission android:name="android.permission.USE_CREDENTIALS"/>
        
            <uses-permission android:name="android.permission.MANAGE_ACCOUNTS"/>
        
            <application
                android:name=".App"
                android:allowBackup="true"
                android:icon="@mipmap/ic_launcher"
                android:label="@string/app_name"
                android:supportsRtl="true"
                android:theme="@style/AppTheme">
                <activity android:name=".MainActivity">
                    <intent-filter>
                        <action android:name="android.intent.action.MAIN"/>
        
                        <category android:name="android.intent.category.LAUNCHER"/>
                    </intent-filter>
                </activity>
        
                <meta-data
                    android:name="UMENG_APPKEY"
                    android:value="561cae6ae0f55abd990035bf" >
                </meta-data>
        
                <activity
                    android:name="com.jiyoutang.teacherplatform.wxapi.WXEntryActivity"
                    android:configChanges="keyboardHidden|orientation|screenSize"
                    android:exported="true"
                    android:launchMode="singleTop"
                    android:theme="@android:style/Theme.Translucent.NoTitleBar" >
                </activity>
        
                <activity
                    android:name="com.tencent.tauth.AuthActivity"
                    android:launchMode="singleTask"
                    android:noHistory="true" >
                    <intent-filter>
                        <action android:name="android.intent.action.VIEW" />
        
                        <category android:name="android.intent.category.DEFAULT" />
                        <category android:name="android.intent.category.BROWSABLE" />
        
                        <data android:scheme="tencent100424468" />
                    </intent-filter>
                </activity>
                <activity
                    android:name="com.tencent.connect.common.AssistActivity"
                    android:screenOrientation="portrait"
                    android:theme="@android:style/Theme.Translucent.NoTitleBar" />
        
                <activity
                    android:name="com.jiyoutang.teacherplatform.WBShareActivity"
                    android:configChanges="keyboardHidden|orientation"
                    android:screenOrientation="portrait" >
                    <intent-filter>
                        <action android:name="com.sina.weibo.sdk.action.ACTION_SDK_REQ_ACTIVITY" />
                        <category android:name="android.intent.category.DEFAULT" />
                    </intent-filter>
                </activity>
        
                <activity
                    android:name="com.sina.weibo.sdk.component.WeiboSdkBrowser"
                    android:configChanges="keyboardHidden|orientation"
                    android:windowSoftInputMode="adjustResize"
                    android:exported="false" >
                </activity>
            </application>
        
        </manifest>


        -dontshrink
         -dontoptimize
         -dontwarn com.google.android.maps.**
         -dontwarn android.webkit.WebView
         -dontwarn com.umeng.**
         -dontwarn com.tencent.weibo.sdk.**
         -dontwarn com.facebook.**
         -keep public class javax.**
         -keep public class android.webkit.**
         -dontwarn android.support.v4.**
         -keep enum com.facebook.**
         -keepattributes Exceptions,InnerClasses,Signature
         -keepattributes *Annotation*
         -keepattributes SourceFile,LineNumberTable
        
         -keep public interface com.facebook.**
         -keep public interface com.tencent.**
         -keep public interface com.umeng.socialize.**
         -keep public interface com.umeng.socialize.sensor.**
         -keep public interface com.umeng.scrshot.**
        
         -keep public class com.umeng.socialize.* {*;}
        
        
         -keep class com.facebook.**
         -keep class com.facebook.** { *; }
         -keep class com.umeng.scrshot.**
         -keep public class com.tencent.** {*;}
         -keep class com.umeng.socialize.sensor.**
         -keep class com.umeng.socialize.handler.**
         -keep class com.umeng.socialize.handler.*
         -keep class com.tencent.mm.sdk.modelmsg.WXMediaMessage {*;}
         -keep class com.tencent.mm.sdk.modelmsg.** implements com.tencent.mm.sdk.modelmsg.WXMediaMessage$IMediaObject {*;}
        
         -keep class im.yixin.sdk.api.YXMessage {*;}
         -keep class im.yixin.sdk.api.** implements im.yixin.sdk.api.YXMessage$YXMessageData{*;}
        
         -dontwarn twitter4j.**
         -keep class twitter4j.** { *; }
        
         -keep class com.tencent.** {*;}
         -dontwarn com.tencent.**
         -keep public class com.umeng.soexample.R$*{
             public static final int *;
         }
         -keep public class com.umeng.soexample.R$*{
             public static final int *;
         }
         -keep class com.tencent.open.TDialog$*
         -keep class com.tencent.open.TDialog$* {*;}
         -keep class com.tencent.open.PKDialog
         -keep class com.tencent.open.PKDialog {*;}
         -keep class com.tencent.open.PKDialog$*
         -keep class com.tencent.open.PKDialog$* {*;}
        
         -keep class com.sina.** {*;}
         -dontwarn com.sina.**
         -keep class  com.alipay.share.sdk.** {
            *;
         }
         -keepnames class * implements android.os.Parcelable {
             public static final ** CREATOR;
         }
        
         -keep class com.linkedin.** { *; }
         -keepattributes Signature


注意: 清单文件中的腾讯号要写成实际的, 项目要依赖v4包.
