package com.aglhz.yicommunity.picker.view;

import android.graphics.Color;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;

import com.aglhz.abase.mvp.view.base.BaseRecyclerViewAdapter;
import com.aglhz.yicommunity.R;
import com.aglhz.yicommunity.bean.CommunityPickerBean;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;


/**
 * Created by Administrator on 2017/4/29 0029.
 */
public class CommunityListAdapter extends BaseRecyclerViewAdapter<CommunityPickerBean, BaseViewHolder> {

    private String searchKey;

    public CommunityListAdapter(List<CommunityPickerBean> data) {
        super(R.layout.item_community_address, data);
    }

    public void setSearchKey(String searchKey) {
        this.searchKey = searchKey;
    }

    @Override
    protected void convert(BaseViewHolder helper, CommunityPickerBean bean) {
//        int index = str.indexOf(searchKey);
//        SpannableString span = new SpannableString(str);
//        span.setSpan(new ForegroundColorSpan(Color.RED), index, index+searchKey.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

        int nameIndex = bean.name.indexOf(searchKey);
        SpannableString nameSpan = new SpannableString(bean.name);
        if (nameIndex != -1) {
            nameSpan.setSpan(new ForegroundColorSpan(Color.RED), nameIndex, nameIndex + searchKey.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        }

        int addressIndex = bean.address.indexOf(searchKey);
        SpannableString addressSpan = new SpannableString(bean.address);
        if (addressIndex != -1) {
            addressSpan.setSpan(new ForegroundColorSpan(Color.RED), addressIndex, addressIndex + searchKey.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        }
        helper.setText(R.id.tv_community_name, bean.name)
                .setText(R.id.tv_community_name, nameSpan)
                .setText(R.id.tv_community_address, bean.address)
                .setText(R.id.tv_community_address, addressSpan);
    }
}
