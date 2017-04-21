package com.aglhz.yicommunity.property.view;

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
import com.aglhz.yicommunity.common.bean.RepairBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/4/19 14:27.
 * <p>
 * 物业保修列表
 */
public class RepairRecordFragment extends BaseFragment {
    private final String TAG = RepairRecordFragment.class.getSimpleName();

    @BindView(R.id.toolbar_title)
    TextView toolbarTitle;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.toolbar_menu)
    TextView toolbarMenu;
    @BindView(R.id.rv_property_repair_activity)
    RecyclerView recyclerview;

    public static RepairRecordFragment newInstance() {
        return new RepairRecordFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_repair_record, container, false);
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
        toolbarTitle.setText("物业保修");
        toolbarMenu.setText("我要报修");
        toolbar.setNavigationIcon(R.drawable.ic_chevron_left_white_24dp);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _mActivity.onBackPressedSupport();
            }
        });
    }

    public void initData() {

        recyclerview.setLayoutManager(new LinearLayoutManager(_mActivity));
        List<RepairBean> datas = new ArrayList<>();
        datas.add(new RepairBean("公共报修", "2017/08/05", "小区纱窗的沙破裂，前门门吸坏419 公共报修账户 2016/12/07 17:26:44 未维修 161116103825", R.drawable.ic_property_repair_untreated_172px));
        datas.add(new RepairBean("私人报修", "2017/08/05", "小区纱窗的沙破裂，前门门吸坏419 公共报修账户 2016/12/07 17:26:44 未维修 161116103825", R.drawable.ic_property_repair_processing_172px));
        datas.add(new RepairBean("私人报修", "2017/08/05", "小区纱窗的沙破裂，前门门吸坏419 公共报修账户 2016/12/07 17:26:44 未维修 161116103825", R.drawable.ic_property_repair_finished_172px));
        recyclerview.setAdapter(new RepairRecordRVAdapter(datas));
    }

}
