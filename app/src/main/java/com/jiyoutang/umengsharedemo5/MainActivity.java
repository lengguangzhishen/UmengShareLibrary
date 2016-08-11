package com.jiyoutang.umengsharedemo5;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.jiyoutang.umengsharemodel.ShareDialog;

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
