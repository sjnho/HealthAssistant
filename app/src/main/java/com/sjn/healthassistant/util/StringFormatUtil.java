package com.sjn.healthassistant.util;

import com.sjn.healthassistant.pojo.Drug;
import com.sjn.healthassistant.pojo.Symptom;

/**
 * Created by sjn on 16/5/8.
 */
public class StringFormatUtil {

    public static String formatSymptom(Symptom symptom) {
        return "简单介绍:\n" + symptom.getDescription() + "详细信息:\n" + symptom.getDetailtext() + "引发病因" + symptom.getCausetext();

    }


    public static String formatDrug(Drug drug) {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("【 适应症 】\n");
        stringBuffer.append(drug.getSyz()).append("\n");
        stringBuffer.append("【 用法用量 】\n");
        stringBuffer.append(drug.getYfyl()).append("\n");
        stringBuffer.append("【 主要成分 】\n");
        stringBuffer.append(drug.getZycf()).append("\n");
        stringBuffer.append("【 注意事项 】\n");
        stringBuffer.append(drug.getZysx()).append("\n");
        stringBuffer.append("【 禁忌 】\n");
        stringBuffer.append(drug.getJj()).append("\n");
        stringBuffer.append("【 药品类别 】\n");
        stringBuffer.append(drug.getType()).append("\n");
        stringBuffer.append("【 生产企业 】\n");
        stringBuffer.append(drug.getManu()).append("\n");
        stringBuffer.append("【 药物互相作用 】\n");
        stringBuffer.append(drug.getYwxhzy()).append("\n");

        return stringBuffer.toString();
    }
}
