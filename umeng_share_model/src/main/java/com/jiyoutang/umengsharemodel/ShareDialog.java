package com.jiyoutang.umengsharemodel;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.sina.weibo.sdk.auth.sso.SsoHandler;
import com.umeng.socialize.Config;
import com.umeng.socialize.PlatformConfig;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;


/**
 * Created by OFFICE on 2016/8/9.
 */
public class ShareDialog {

    private Activity mActivity;
    private UMImage image;
    private Context mContext;
    private Dialog customDialog;
    private String mTitle;
    private String mContent;
    private String mImageUrl;
    private String mTargetUrl;
    private UMShareListener umShareListener;
    private ShareCallback mShareCallback;
    private ShareAction mShareAction;
    private Dialog mDialog;
    private Dialog dialog;
    private static String QQ_PACKAGE_NAME;
    private static String WX_PACKAGE_NAME;

    private ShareDialog(Activity activity) {
        this.mContext = activity;
        this.mActivity = activity;
        QQ_PACKAGE_NAME = mContext.getString(R.string.qq_package_name);
        WX_PACKAGE_NAME = mContext.getString(R.string.wx_package_name);
        initDefaultDialog(activity);
        mShareAction = new ShareAction(activity);
    }

    /**
     * 设置的loadingdialog不顶用
     * @param activity
     */
    private void initDefaultDialog(Activity activity) {
        ProgressDialogView dialogView = new ProgressDialogView(activity);
        Config.dialog = new Dialog(activity, R.style.loading_dialog);
        Config.dialog.setCancelable(true);
        Config.dialog.setCanceledOnTouchOutside(false);
        Config.dialog.setContentView(dialogView);
    }

    /**
     * 初始化分享
     * @param appKey
     * @param appSecret
     */
    public static void setWeixin(String appKey, String appSecret) {
        PlatformConfig.setWeixin(appKey, appSecret);
    }

    public static void setSina(String appKey, String appSecret) {
        PlatformConfig.setSinaWeibo(appKey, appSecret);
    }

    public static void setQQ(String appKey, String appSecret) {
        PlatformConfig.setQQZone(appKey, appSecret);
    }

    public static void isDebug(boolean isDebug) {
        com.umeng.socialize.utils.Log.LOG = isDebug;
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        UMShareAPI.get(mContext).onActivityResult( requestCode, resultCode, data);
    }

    public void destory() {
        mActivity = null;
        image = null;
        mContext = null;
        customDialog = null;
        mTitle = null;
        mContent = null;
        mImageUrl = null;
        mTargetUrl = null;
        umShareListener = null;
        mShareCallback = null;
        mShareAction = null;
        mDialog = null;
        dialog = null;
    }

    private void init() {
        Config.dialog = mDialog;
        initListener();
        View dialogView = LayoutInflater.from(mContext).inflate(
                R.layout.view_share_pop, null);
        customDialog = new Dialog(mContext, R.style.ShareDialogStyle);
        customDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        customDialog.setCancelable(false);
        customDialog.getWindow().setContentView(dialogView);
        WindowManager.LayoutParams lp = customDialog.getWindow().getAttributes();
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.gravity = Gravity.BOTTOM | Gravity.LEFT;
        customDialog.getWindow().setAttributes(lp);
        LinearLayout Share_QQ, Share_wechat, Share_sina, Share_friend_circle, share_qq_space;
        TextView cancleButton;
        Share_QQ = (LinearLayout) dialogView.findViewById(R.id.share_QQ);
        Share_wechat = (LinearLayout) dialogView.findViewById(R.id.share_wechat);
        Share_sina = (LinearLayout) dialogView.findViewById(R.id.share_sina);
        cancleButton = (TextView) dialogView.findViewById(R.id.cancle);
        Share_friend_circle = (LinearLayout) dialogView.findViewById(R.id.share_friend_cicle);
        share_qq_space = (LinearLayout) dialogView.findViewById(R.id.share_qq_space);

        share_qq_space.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                share(SHARE_MEDIA.QZONE, false);
                customDialog.cancel();
            }
        });

        Share_QQ.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chekoutIsApkInstalled(QQ_PACKAGE_NAME, SHARE_MEDIA.QQ);
//                share(SHARE_MEDIA.QQ);
                customDialog.cancel();
            }
        });
        Share_wechat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chekoutIsApkInstalled(WX_PACKAGE_NAME, SHARE_MEDIA.WEIXIN);
                customDialog.cancel();
            }
        });

        Share_sina.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                chekoutIsApkInstalled("com.sina.weibo", SHARE_MEDIA.SINA);
                share(SHARE_MEDIA.SINA, true);
                customDialog.cancel();
            }
        });

        Share_friend_circle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chekoutIsApkInstalled(WX_PACKAGE_NAME, SHARE_MEDIA.WEIXIN_CIRCLE);
                customDialog.cancel();
            }
        });
        cancleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                customDialog.cancel();
            }
        });
        customDialog.setCanceledOnTouchOutside(true);
    }

    private void chekoutIsApkInstalled(String packageName, SHARE_MEDIA media) {
        if (isApkInstalled(mContext, packageName)) {
            share(media, false);
        } else {
            if (mShareCallback == null) {
                String toast = null;
                switch (packageName) {
                    case "com.tencent.mobileqq":
                        toast = mContext.getString(R.string.qq_not_installed);
                        break;
                    case "com.tencent.mm":
                        toast = mContext.getString(R.string.wx_not_installed);
                        break;
                }
                Toast.makeText(mContext, toast, Toast.LENGTH_SHORT).show();
            } else {
                mShareCallback.onApkNotInstalled();
            }
        }
    }

    private void initListener() {
        umShareListener = new UMShareListener() {
            @Override
            public void onResult(SHARE_MEDIA platform) {
                if (mShareCallback == null) {
                    Toast.makeText(mContext, platform + mContext.getString(R.string.share_successed), Toast.LENGTH_SHORT).show();
                } else {
                    mShareCallback.onResult(platform);
                }

            }

            @Override
            public void onError(SHARE_MEDIA platform, Throwable t) {

                if (mShareCallback == null) {
//                    if (("WechatClientNotExistException").equals(t.getClass().getSimpleName())) {
//                        Toast.makeText(mContext, platform + " 微信客户端为安装", Toast.LENGTH_SHORT).show();
//                    } else {
                        Toast.makeText(mContext, platform + mContext.getString(R.string.share_failed), Toast.LENGTH_SHORT).show();
//                    }
                } else {
                    mShareCallback.onError(platform, t);
                }

            }

            @Override
            public void onCancel(SHARE_MEDIA platform) {

                if (mShareCallback == null) {
                    Toast.makeText(mContext, platform + mContext.getString(R.string.share_cancel), Toast.LENGTH_SHORT).show();
                } else {
                    mShareCallback.onCancel(platform);
                }

            }
        };

        if (mImageUrl != null) {
            image = new UMImage(mContext, mImageUrl);
        }
    }

    private void setContentTitle(String title) {
        this.mTitle = title;
    }

    private void setContentText(String contentText) {
        this.mContent = contentText;
    }

    private void setUrl(String Url) {
        this.mTargetUrl = Url;
    }

    private void setImageUrl(String ImageUrl) {
        this.mImageUrl = ImageUrl;
    }

    private void setOnShareCallback(ShareCallback callback) {
        this.mShareCallback = callback;
    }

    private void setDialog(Dialog dialog) {
        this.mDialog = dialog;
    }

    public void showDialog() {
        customDialog.show();
    }

    public void hideDialog() {
        customDialog.cancel();
    }


    private boolean isApkInstalled(Context context, String packageName) {
        try {
            context.getPackageManager().getApplicationInfo(packageName, PackageManager.GET_UNINSTALLED_PACKAGES);
            return true;
        } catch (PackageManager.NameNotFoundException e) {
            return false;
        }
    }

    private void share(SHARE_MEDIA media, boolean isSina) {
               mShareAction.setPlatform(media)
                .setCallback(umShareListener)
                .withTitle(isSina ? null : mTitle)
                .withText(mContent)
                .withTargetUrl(mTargetUrl)
                .withMedia(image)
                .share();
    }

    public static class Build {
        private Context context;
        private Activity activity;
        private ShareDialog shareDialog;

        public Build(Activity activity) {
            shareDialog = new ShareDialog(activity);
        }

        public Build setTitle(String title) {
            shareDialog.setContentTitle(title);
            return this;
        }

        public Build setContent(String content) {
            shareDialog.setContentText(content);
            return this;
        }

        public Build setTargetUrl(String targetUrl) {
            shareDialog.setUrl(targetUrl);
            return this;
        }

        public Build setImageUrl(String imageUrl) {
            shareDialog.setImageUrl(imageUrl);
            return this;
        }

        public Build setOnShareCallback(ShareCallback callback) {
            shareDialog.setOnShareCallback(callback);
            return this;
        }

        public Build setDialog(Dialog dialog) {
            shareDialog.setDialog(dialog);
            return this;
        }

        public ShareDialog build() {
            shareDialog.init();
            return shareDialog;
        }
    }

}

