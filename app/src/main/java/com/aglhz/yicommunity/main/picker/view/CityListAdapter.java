package com.aglhz.yicommunity.main.picker.view;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.aglhz.abase.log.ALog;
import com.aglhz.yicommunity.R;
import com.aglhz.yicommunity.main.picker.model.LocateState;
import com.aglhz.yicommunity.main.picker.model.City;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Pattern;

/**
 * author zaaach on 2016/1/26.
 */
public class CityListAdapter extends BaseAdapter {
    private static final int VIEW_TYPE_COUNT = 4;
    private Context mContext;
    private LayoutInflater inflater;
    private List<City> mCities;
    private HashMap<String, Integer> letterIndexes;
    private String[] sections;
    private OnCityClickListener onCityClickListener;
    private int locateState = LocateState.LOCATING;
    private String locatedCity;

    public CityListAdapter(Context mContext, List<City> mCities) {
        this.mContext = mContext;
        this.mCities = mCities;
        this.inflater = LayoutInflater.from(mContext);
        if (mCities == null) {
            mCities = new ArrayList<>();
        }
        mCities.add(0, new City("定位", "0"));
        mCities.add(1, new City("最近访问的城市", "1"));
        mCities.add(2, new City("热门", "2"));
        int size = mCities.size();
        letterIndexes = new HashMap<>();
        sections = new String[size];
        for (int index = 0; index < size; index++) {
            //当前城市拼音首字母
            String currentLetter = PinyinUtils.getFirstLetter(mCities.get(index).getPinyin());
            //上个首字母，如果不存在设为""
            String previousLetter = index >= 1 ? PinyinUtils.getFirstLetter(mCities.get(index - 1).getPinyin()) : "";
            if (!TextUtils.equals(currentLetter, previousLetter)) {
                letterIndexes.put(currentLetter, index);
                sections[index] = currentLetter;
            }
        }
    }

    /**
     * 更新定位状态
     *
     * @param state
     */
    public void updateLocateState(int state, String city) {
        this.locateState = state;
        this.locatedCity = city;
        notifyDataSetChanged();
    }

    /**
     * 获取字母索引的位置
     *
     * @param letter
     * @return
     */
    public int getLetterPosition(String letter) {
        Integer integer = letterIndexes.get(letter);
        return integer == null ? -1 : integer;
    }

    @Override
    public int getViewTypeCount() {
        return VIEW_TYPE_COUNT;
    }

    @Override
    public int getItemViewType(int position) {
        return position < VIEW_TYPE_COUNT - 1 ? position : VIEW_TYPE_COUNT - 1;
    }

    @Override
    public int getCount() {
        return mCities == null ? 0 : mCities.size();
    }

    @Override
    public City getItem(int position) {
        return mCities == null ? null : mCities.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View view, ViewGroup parent) {
        CityViewHolder holder;
        int viewType = getItemViewType(position);
        switch (viewType) {
            case 0:     //定位
                view = inflater.inflate(R.layout.cp_view_locate_city, parent, false);
                ViewGroup container = (ViewGroup) view.findViewById(R.id.layout_locate);
                TextView state = (TextView) view.findViewById(R.id.tv_located_city);
                switch (locateState) {
                    case LocateState.LOCATING:
                        state.setText(mContext.getString(R.string.cp_locating));
                        break;
                    case LocateState.FAILED:
                        state.setText(R.string.cp_located_failed);
                        break;
                    case LocateState.SUCCESS:
                        state.setText(locatedCity);
                        break;
                }
                container.setOnClickListener(v -> {
                    if (locateState == LocateState.FAILED) {
                        ALog.e("  if (locateState == LocateState.FAILED) {");

                        //重新定位
                        if (onCityClickListener != null) {

                            ALog.e("  if (onCityClickListener != null) {");

                            onCityClickListener.onLocateClick();
                        }
                    } else if (locateState == LocateState.SUCCESS) {
                        //返回定位城市
                        if (onCityClickListener != null) {
                            onCityClickListener.onCityClick(locatedCity);
                        }
                    }
                });
                break;
            case 1:    //最近访问的
                view = inflater.inflate(R.layout.cp_view_used_city, parent, false);
                WrapHeightGridView usedGridview = (WrapHeightGridView) view.findViewById(R.id.gridview_used_city);
                final UsedCityGridAdapter usedCityGridAdapter = new UsedCityGridAdapter(mContext);
                usedGridview.setAdapter(usedCityGridAdapter);
                usedGridview.setOnItemClickListener((parent12, view12, position12, id) -> {
                    if (onCityClickListener != null) {
                        onCityClickListener.onCityClick(usedCityGridAdapter.getItem(position12));
                    }
                });
                break;

            case 2:     //热门
                view = inflater.inflate(R.layout.cp_view_hot_city, parent, false);
                WrapHeightGridView gridView = (WrapHeightGridView) view.findViewById(R.id.gridview_hot_city);
                final HotCityGridAdapter hotCityGridAdapter = new HotCityGridAdapter(mContext);
                gridView.setAdapter(hotCityGridAdapter);
                gridView.setOnItemClickListener((parent1, view1, position1, id) -> {
                    if (onCityClickListener != null) {
                        onCityClickListener.onCityClick(hotCityGridAdapter.getItem(position1));
                    }
                });
                break;
            case 3:     //所有
                if (view == null) {
                    view = inflater.inflate(R.layout.cp_item_city_listview, parent, false);
                    holder = new CityViewHolder();
                    holder.letter = (TextView) view.findViewById(R.id.tv_item_city_listview_letter);
                    holder.name = (TextView) view.findViewById(R.id.tv_item_city_listview_name);
                    view.setTag(holder);
                } else {
                    holder = (CityViewHolder) view.getTag();
                }
                if (position >= 1) {
                    final String city = mCities.get(position).getName();
                    holder.name.setText(city);
                    String currentLetter = PinyinUtils.getFirstLetter(mCities.get(position).getPinyin());
                    String previousLetter = position >= 1 ? PinyinUtils.getFirstLetter(mCities.get(position - 1).getPinyin()) : "";
                    if (!TextUtils.equals(currentLetter, previousLetter)) {
                        holder.letter.setVisibility(View.VISIBLE);
                        holder.letter.setText(currentLetter);
                    } else {
                        holder.letter.setVisibility(View.GONE);
                    }
                    holder.name.setOnClickListener(v -> {
                        if (onCityClickListener != null) {
                            onCityClickListener.onCityClick(city);
                        }
                    });
                }
                break;
        }
        return view;
    }

    public static class CityViewHolder {
        TextView letter;
        TextView name;
    }

    public void setOnCityClickListener(OnCityClickListener listener) {
        this.onCityClickListener = listener;
    }

    public interface OnCityClickListener {
        void onCityClick(String name);

        void onLocateClick();
    }

    public static class PinyinUtils {
        /**
         * 获取拼音的首字母（大写）
         *
         * @param pinyin
         * @return
         */
        public static String getFirstLetter(final String pinyin) {
            if (TextUtils.isEmpty(pinyin)) return "定位";
            String c = pinyin.substring(0, 1);
            Pattern pattern = Pattern.compile("^[A-Za-z]+$");
            if (pattern.matcher(c).matches()) {
                return c.toUpperCase();
            } else if ("0".equals(c)) {
                return "定位";
            } else if ("1".equals(c)) {
                return "常用";
            } else if ("2".equals(c)) {
                return "热门";
            }
            return "定位";
        }
    }
}
