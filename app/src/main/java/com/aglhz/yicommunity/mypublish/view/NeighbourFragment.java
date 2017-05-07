package com.aglhz.yicommunity.mypublish.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.aglhz.abase.mvp.view.base.BaseFragment;
import com.aglhz.yicommunity.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Author: LiuJia on 2017/4/21 19:49.
 * Email: liujia95me@126.com
 * <p>
 * 邻里邻居
 */
public class NeighbourFragment extends BaseFragment {
    private static final String TAG = NeighbourFragment.class.getSimpleName();

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    public static NeighbourFragment newInstance() {
        return new NeighbourFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.recyclerview, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

}
