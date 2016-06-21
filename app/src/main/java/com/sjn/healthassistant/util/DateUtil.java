package com.sjn.healthassistant.util;

import android.widget.SimpleAdapter;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by sjn on 16/6/15.
 */
public class DateUtil {
    public static String getDateText(Date date){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy年MM月dd日 HH时mm分", Locale.CHINA);
        return simpleDateFormat.format(date);

    }
}
