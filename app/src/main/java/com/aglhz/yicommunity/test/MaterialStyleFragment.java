package com.aglhz.yicommunity.test;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.aglhz.abase.log.ALog;
import com.aglhz.abase.mvp.view.base.BaseFragment;
import com.aglhz.abase.utils.DensityUtils;
import com.aglhz.yicommunity.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrDefaultHandler2;
import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.PtrHandler;
import in.srain.cube.views.ptr.PtrHandler2;
import in.srain.cube.views.ptr.header.MaterialHeader;
import in.srain.cube.views.ptr.header.StoreHouseHeader;

import static com.aglhz.yicommunity.R.id.ptrFrameLayout;

public class MaterialStyleFragment extends BaseFragment {

    @BindView(R.id.material_style_ptr_frame)
    PtrFrameLayout mPtrFrameLayout;


    public static MaterialStyleFragment newInstance() {
        return new MaterialStyleFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_test, container, false);

        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        view.findViewById(R.id.bt).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView tv=new TextView(getContext());
                tv.setText("111111111111111111111111111111111111111111111111");

                mPtrFrameLayout.setFooterView(tv);
            }
        });

        // header
        final MaterialHeader header = new MaterialHeader(getContext());
        int[] colors = getResources().getIntArray(R.array.google_colors);
        header.setColorSchemeColors(colors);
        header.setLayoutParams(new PtrFrameLayout.LayoutParams(-1, -2));
        header.setPadding(0, DensityUtils.dp2px(_mActivity, 15), 0, DensityUtils.dp2px(_mActivity, 10));
        header.setPtrFrameLayout(mPtrFrameLayout);


        // header
        final MaterialHeader header1 = new MaterialHeader(getContext());
        int[] colors1 = getResources().getIntArray(R.array.google_colors);
        header1.setColorSchemeColors(colors);
        header1.setLayoutParams(new PtrFrameLayout.LayoutParams(-1, -2));
        header1.setPadding(0, DensityUtils.dp2px(_mActivity, 15), 0, DensityUtils.dp2px(_mActivity, 10));
        header1.setPtrFrameLayout(mPtrFrameLayout);


        

        mPtrFrameLayout.setDurationToCloseHeader(1500);
        mPtrFrameLayout.setHeaderView(header);
        mPtrFrameLayout.addPtrUIHandler(header);

        mPtrFrameLayout.setMode(PtrFrameLayout.Mode.BOTH);

        StoreHouseHeader footer = new StoreHouseHeader(getContext());
        footer.setPadding(0, DensityUtils.dp2px(_mActivity, 15), 0, DensityUtils.dp2px(_mActivity, 10));
        footer.initWithString("Ultra Footer");

        mPtrFrameLayout.setFooterView(header1);
        mPtrFrameLayout.addPtrUIHandler(header1);
        mPtrFrameLayout.setPtrHandler(new PtrHandler2() {
            @Override
            public boolean checkCanDoRefresh(PtrFrameLayout frame, View content, View header) {
                return true;
            }

            @Override
            public boolean checkCanDoLoadMore(PtrFrameLayout frame, View content, View footer) {
                return true;
            }

            @Override
            public void onLoadMoreBegin(PtrFrameLayout frame) {
                ALog.e("加载更多加载更多加载更多加载更多加载更多加载更多加载更多加载更多");
                mPtrFrameLayout.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mPtrFrameLayout.refreshComplete();
                    }
                }, 1500);
            }

            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
                ALog.e("*************************************");

                mPtrFrameLayout.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mPtrFrameLayout.refreshComplete();
                    }
                }, 1500);
            }
        });


    }
}
