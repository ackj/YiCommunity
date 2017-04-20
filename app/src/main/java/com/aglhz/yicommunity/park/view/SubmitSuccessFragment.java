//package com.aglhz.yicommunity.park.view;
//
//import android.os.Bundle;
//import android.support.annotation.Nullable;
//import android.support.v7.widget.Toolbar;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.TextView;
//
//import com.aglhz.yicommunity.R;
//
//import butterknife.BindView;
//import butterknife.ButterKnife;
//import me.yokeyword.fragmentation.SupportFragment;
//
///**
// * Created by Administrator on 2017/4/19 9:43.
// */
//public class SubmitSuccessFragment extends SupportFragment {
//
//    private String mTitle;
//
//    @BindView(R.id.toolbar_title)
//    TextView toolbarTitle;
//    @BindView(R.id.toolbar)
//    Toolbar toolbar;
//
//    public static SubmitSuccessFragment newInstance(String title) {
//        Bundle args = new Bundle();
//        args.putString(Constants.TITLE, title);
//
//        SubmitSuccessFragment fragment = new SubmitSuccessFragment();
//        fragment.setArguments(args);
//        return fragment;
//    }
//
//    @Nullable
//    @Override
//    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        View view = inflater.inflate(R.layout.fragment_submit_success, container, false);
//        ButterKnife.bind(this,view);
//        return view;
//    }
//    @Override
//    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
//        super.onViewCreated(view, savedInstanceState);
//        initToolbar();
//    }
//
//    @Override
//    public void onCreate(@Nullable Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        Bundle args = getArguments();
//        if (args != null) {
//            mTitle = args.getString(Constants.TITLE);
//        }
//    }
//
//    private void initToolbar() {
//        toolbarTitle.setText(mTitle);
//        toolbar.setNavigationIcon(R.mipmap.ic_chevron_left_white_24dp);
//        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                _mActivity.onBackPressedSupport();
//            }
//        });
//    }
//}
