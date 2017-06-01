package com.aglhz.yicommunity.main.message;

import android.os.Bundle;

import com.aglhz.abase.mvp.view.base.BaseActivity;
import com.aglhz.yicommunity.R;
import com.aglhz.yicommunity.main.message.view.MessageCenterFragment;

/**
 * Author：leguang on 2017/5/20 0009 08:54
 * Email：langmanleguang@qq.com
 * <p>
 * 消息模块的容器，负责装载一个MessageCenterFragment，在消息推送的时候启动该页面。
 */

public class MessageActivity extends BaseActivity {
    private static final String TAG = MessageActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState == null) {
            loadRootFragment(R.id.fl_main_activity, MessageCenterFragment.newInstance());
        }
    }
}
