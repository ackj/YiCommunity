package com.aglhz.yicommunity.park.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.aglhz.abase.mvp.view.base.BaseFragment;
import com.aglhz.yicommunity.R;
import com.aglhz.yicommunity.common.bean.CarCardManageBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/4/19 9:39.
 */
public class CarCardFragment extends BaseFragment {
    @BindView(R.id.toolbar_title)
    TextView toolbarTitle;
    @BindView(R.id.rv_car_card_manage)
    RecyclerView rvCarCardManage;
    @BindView(R.id.toolbar)
    Toolbar toolbar;

    public static CarCardFragment newInstance() {
        return new CarCardFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_car_card_manage, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initToolbar();
        initData();
    }

    private void initToolbar() {
        initStateBar(toolbar);
        toolbarTitle.setText("我的车卡");
        toolbar.setNavigationIcon(R.drawable.ic_chevron_left_white_24dp);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _mActivity.onBackPressedSupport();
            }
        });
    }

    private void initData() {
        rvCarCardManage.setLayoutManager(new LinearLayoutManager(_mActivity, LinearLayoutManager.VERTICAL, false));
        List<CarCardManageBean> lists = new ArrayList<>();
        lists.add(new CarCardManageBean("粤L23212", 25, "张三", "12321332132", "凯宾斯基地下车库", "2016/07/08", "2016/07/08", 0));
        lists.add(new CarCardManageBean("粤L23212", 25, "张三", "12321332132", "凯宾斯基地下车库", "2016/07/08", "2016/07/08", 1));
        lists.add(new CarCardManageBean("粤L23212", 25, "张三", "12321332132", "凯宾斯基地下车库", "2016/07/08", "2016/07/08", 2));
        lists.add(new CarCardManageBean("粤L23212", 25, "张三", "12321332132", "凯宾斯基地下车库", "2016/07/08", "2016/07/08", 0));
        lists.add(new CarCardManageBean("粤L23212", 25, "张三", "12321332132", "凯宾斯基地下车库", "2016/07/08", "2016/07/08", 1));
        rvCarCardManage.setAdapter(new CarCardRVAdapter(lists));
    }
}
