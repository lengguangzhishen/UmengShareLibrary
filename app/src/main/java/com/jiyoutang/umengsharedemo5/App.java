package com.jiyoutang.umengsharedemo5;

import android.app.Application;

import com.jiyoutang.umengsharemodel.ShareDialog;

/**
 * Created by OFFICE on 2016/8/9.
 */
public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

//        ShareDialog.isDebug(AppSettings.isDebug);
//        ShareDialog.setWeixin("wx967daebe835fbeac", "5bb696d9ccd75a38c8a0bfe0675559b3");
//        ShareDialog.setSina("468502476", "988c564f4ba960cfce1e1710d971a35a");
//        ShareDialog.setQQ("1105164242", "N2KYGNBo0RXADls0");

        ShareDialog.isDebug(true);
        ShareDialog.setWeixin("wx967daebe835fbeac", "5bb696d9ccd75a38c8a0bfe0675559b3");
        ShareDialog.setSina("3921700954", "04b48b094faeb16683c32669824ebdad");
        ShareDialog.setQQ("100424468", "c7394704798a158208a74ab60104f0ba");
    }
}
