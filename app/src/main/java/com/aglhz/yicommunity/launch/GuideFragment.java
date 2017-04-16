package com.aglhz.yicommunity.launch;

import android.animation.ArgbEvaluator;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;

import com.aglhz.abase.mvp.view.base.BaseFragment;
import com.aglhz.yicommunity.R;
import com.aglhz.yicommunity.login.view.LoginFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Author：leguang on 2016/10/9 0009 15:49
 * Email：langmanleguang@qq.com
 */
public class GuideFragment extends BaseFragment {

    private static final String TAG = GuideFragment.class.getSimpleName();

    public static GuideFragment newInstance() {
        return new GuideFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        getFragmentManager().beginTransaction()
                .show(getPreFragment())
                .commit();
        View view = inflater.inflate(R.layout.fragment_guide, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        initData();
    }

    private void initData() {
    }



    class GuideViewPagerAdapter extends PagerAdapter {

        @Override
        public int getCount() {
//            if (arrayResouces != null) {
//                return arrayResouces.length;
//            }
            return 0;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {

//            View view = View.inflate(_mActivity, R.layout.item_vp_guide_fragment, null);
//            FrameLayout fl_container = (FrameLayout) view.findViewById(R.id.fl_container_item_vp_guide_fragment);
//            fl_container.setBackgroundResource(arrayResouces[position]);
//            container.addView(view);
            return null;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }

    }
}
