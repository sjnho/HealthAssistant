package com.sjn.healthassistant.util;

/**
 * Created by sjn on 16/5/8.
 */
public class DrugStringFormat {
    public static String formatDrug(String string) {
        if(string.contains("【")&&string.contains("】")){
            String newStr = string.replace("【", "\n【");
            newStr = newStr.replace("】", "】\n");
            return newStr;
        }else {
            return null;
        }

    }
}
