package com.aglhz.yicommunity.main.guide;

import android.content.Context;
import android.content.Intent;

import com.aglhz.abase.cache.SPCache;
import com.aglhz.yicommunity.R;
import com.aglhz.yicommunity.common.Constants;

/**
 * Author: LiuJia on 2017/6/8 0008 10:21.
 * Email: liujia95me@126.com
 * 指引页的帮助类
 */

public class GuideHelper {

    /**
     * 首页的指引
     * 指引从添加房屋，设置一键开门到一键开门的整个流程
     * @param context
     */
    public static void showHomeGuide(Context context) {
        boolean showed = (boolean) SPCache.get(context, Constants.SP_KEY_GUIDE_MAIN, false);
        if (!showed) {
            Intent intent = new Intent(context, GuideActivity.class);
            int[] guides = new int[]{R.drawable.bg_guide_01_1242px_2208px, R.drawable.bg_guide_02_1242px_2208px, R.drawable.bg_guide_03_1242px_2208px};
            intent.putExtra("guide", guides);
            context.startActivity(intent);
            SPCache.put(context, Constants.SP_KEY_GUIDE_MAIN, true);
        }
    }

    /**
     * 月卡缴费的用户指引
     * @param context
     */
    public static void showCardPaydGuide(Context context) {
        boolean showed = (boolean) SPCache.get(context, Constants.SP_KEY_GUIDE_CARD_PAY, false);
        if (!showed) {
            Intent intent = new Intent(context, GuideActivity.class);
            int[] guides = new int[]{R.drawable.bg_guide_05_1242px_2208px};
            intent.putExtra("guide", guides);
            context.startActivity(intent);
            SPCache.put(context, Constants.SP_KEY_GUIDE_CARD_PAY, true);
        }
    }

    /**
     * 我的车卡用户指引
     * @param context
     */
    public static void showMyCardGuide(Context context) {
        boolean showed = (boolean) SPCache.get(context, Constants.SP_KEY_GUIDE_MY_CARD, false);
        if (!showed) {
            Intent intent = new Intent(context, GuideActivity.class);
            int[] guides = new int[]{R.drawable.bg_guide_06_1242px_2208px};
            intent.putExtra("guide", guides);
            context.startActivity(intent);
            SPCache.put(context, Constants.SP_KEY_GUIDE_MY_CARD, true);
        }
    }

    public static boolean showed(Context context) {
        return (boolean) SPCache.get(context, Constants.SP_KEY_GUIDE_MY_CARD, false);
    }

    /**
     * 临停缴费的用户指引
     * @param context
     */
    public static void showTempporaryCardPayGuide(Context context) {
        boolean showed = (boolean) SPCache.get(context, Constants.SP_KEY_GUIDE_TEMPPORARY_PARK_PAY, false);
        if (!showed) {
            Intent intent = new Intent(context, GuideActivity.class);
            int[] guides = new int[]{R.drawable.bg_guide_04_1242px_2208px};
            intent.putExtra("guide", guides);
            context.startActivity(intent);
            SPCache.put(context, Constants.SP_KEY_GUIDE_TEMPPORARY_PARK_PAY, true);
        }
    }
}
