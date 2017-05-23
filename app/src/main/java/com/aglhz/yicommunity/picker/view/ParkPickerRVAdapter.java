package com.aglhz.yicommunity.picker.view;

import android.graphics.Color;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;

import com.aglhz.abase.mvp.view.base.BaseRecyclerViewAdapter;
import com.aglhz.yicommunity.bean.ParkSelectBean;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

/**
 * Author: LiuJia on 2017/5/23 0023 15:09.
 * Email: liujia95me@126.com
 */

public class ParkPickerRVAdapter extends BaseRecyclerViewAdapter<ParkSelectBean.DataBean.ParkPlaceListBean,BaseViewHolder>{
    private String searchKey = "";

    public ParkPickerRVAdapter(List<ParkSelectBean.DataBean.ParkPlaceListBean> data) {
        super(android.R.layout.simple_list_item_1, data);
    }

    public void setSearchKey(String searchKey) {
        this.searchKey = searchKey;
    }

    @Override
    protected void convert(BaseViewHolder helper, ParkSelectBean.DataBean.ParkPlaceListBean bean) {
        if (bean == null) {
            return;
        }
        int nameIndex = bean.getName().indexOf(searchKey);
        SpannableString nameSpan = new SpannableString(bean.getName());
        if (nameIndex != -1) {
            nameSpan.setSpan(new ForegroundColorSpan(Color.RED), nameIndex, nameIndex + searchKey.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        }
        helper.setText(android.R.id.text1, nameSpan);
    }
}
