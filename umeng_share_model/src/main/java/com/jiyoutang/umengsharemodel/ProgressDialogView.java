package com.jiyoutang.umengsharemodel;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by zhangshili on 15/12/8.
 */
public class ProgressDialogView extends LinearLayout {

    private String title;

    private TextView tvTitle;

    public ProgressDialogView(Context context) {
        this(context, null);
    }

    public ProgressDialogView(String title, Context context) {
        super(context, null);
        this.title = title;
        init();
    }

    public ProgressDialogView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        LayoutInflater.from(getContext()).inflate(R.layout.view_ttkj_progress_dialog, this, true);
        tvTitle = (TextView) findViewById(R.id.tv_title);
        if (!TextUtils.isEmpty(title)) {
            tvTitle.setText(title);
        }
    }

    public void setText(String title) {
        this.title = title;
        this.tvTitle.setText(title);
    }
}
