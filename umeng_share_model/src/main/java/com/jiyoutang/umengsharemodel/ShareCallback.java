package com.jiyoutang.umengsharemodel;


import com.umeng.socialize.bean.SHARE_MEDIA;

/**
 * Created by OFFICE on 2016/8/9.
 */
public interface ShareCallback {

    void onResult(SHARE_MEDIA platform);
    void onError(SHARE_MEDIA platform, Throwable t);
    void onCancel(SHARE_MEDIA platform);

    /**
     * 目前只有微信需要判断
     */
    void onApkNotInstalled();

}
