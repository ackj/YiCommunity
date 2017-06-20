package com.aglhz.yicommunity.main.picker.view;

import android.graphics.Color;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;

import com.aglhz.abase.mvp.view.base.BaseRecyclerViewAdapter;
import com.aglhz.yicommunity.R;
import com.aglhz.yicommunity.entity.bean.CommunitySelectBean;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;


/**
 * Created by Administrator on 2017/4/29 0029.
 */
public class CommunityPickerRVAdapter extends BaseRecyclerViewAdapter<CommunitySelectBean.DataBean.CommunitiesBean, BaseViewHolder> {

    private String searchKey = "";

    public CommunityPickerRVAdapter(List<CommunitySelectBean.DataBean.CommunitiesBean> data) {
        super(R.layout.item_community_address, data);
    }

    public void setSearchKey(String searchKey) {
        this.searchKey = searchKey;
    }

    @Override
    protected void convert(BaseViewHolder helper, CommunitySelectBean.DataBean.CommunitiesBean bean) {
        if (bean == null) {
            return;
        }

        int nameIndex = bean.getName().indexOf(searchKey);
        SpannableString nameSpan = new SpannableString(bean.getName());
        if (nameIndex != -1) {
            nameSpan.setSpan(new ForegroundColorSpan(Color.RED), nameIndex, nameIndex + searchKey.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        }

        int addressIndex = bean.getPosition().getAddress().indexOf(searchKey);
        SpannableString addressSpan = new SpannableString(bean.getPosition().getAddress());
        if (addressIndex != -1) {
            addressSpan.setSpan(new ForegroundColorSpan(Color.RED), addressIndex, addressIndex + searchKey.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        }
        helper.setText(R.id.tv_community_name, nameSpan)
                .setText(R.id.tv_community_address, addressSpan)
                .addOnClickListener(R.id.ll_community_layout);
    }
}
