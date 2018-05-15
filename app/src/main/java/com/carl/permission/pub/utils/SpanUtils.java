package com.carl.permission.pub.utils;

import android.support.v4.content.ContextCompat;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;

import com.carl.permission.BaseApplication;
import com.carl.permission.R;


/**
 * Created by ruiqin.shen
 * 类说明：
 */

public class SpanUtils {
    /**
     * 更新字体颜色
     *
     * @return
     */
    public static SpannableString updateFontColor(String content, String[] redTipFonts) {
        SpannableString spannableString = new SpannableString(content);
        for (int i = 0; i < redTipFonts.length; i++) {
            int startIndex = content.lastIndexOf(redTipFonts[i]);
            int endIndex = startIndex + redTipFonts[i].length();
            spannableString.setSpan(new ForegroundColorSpan(ContextCompat.getColor(BaseApplication.getContext(), R.color.text_red)), startIndex, endIndex, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        }
        return spannableString;
    }

}
