package com.aglhz.yicommunity.main.publish;

import android.os.Bundle;

import com.aglhz.abase.mvp.view.base.BaseActivity;
import com.aglhz.yicommunity.R;
import com.aglhz.yicommunity.main.publish.view.CommentFragment;

/**
 * Author: LiuJia on 2017/5/16 0016 17:55.
 * Email: liujia95me@126.com
 */

public class CommentActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_main);
        super.onCreate(savedInstanceState);
        if (savedInstanceState == null) {
            String fid = getIntent().getStringExtra("fid");
            int type = getIntent().getIntExtra("type", 0);
            loadRootFragment(R.id.fl_main_activity, CommentFragment.newInstance(fid, type));
        }
    }
}
