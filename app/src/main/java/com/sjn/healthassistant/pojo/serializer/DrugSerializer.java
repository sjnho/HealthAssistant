package com.sjn.healthassistant.pojo.serializer;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.sjn.healthassistant.pojo.Drug;

import java.lang.reflect.Type;

/**
 * Created by sjn on 16/5/8.
 */
public class DrugSerializer implements JsonSerializer<Drug> {
    @Override
    public JsonElement serialize(Drug src, Type typeOfSrc, JsonSerializationContext context) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("id", src.getId());
        jsonObject.addProperty("blfy", src.getBlfy());
        jsonObject.addProperty("drugName", src.getDrugName());
        jsonObject.addProperty("jj", src.getJj());
        jsonObject.addProperty("manu", src.getManu());
        jsonObject.addProperty("pzwh", src.getPzwh());
        jsonObject.addProperty("syz", src.getSyz());
        jsonObject.addProperty("ywxhzy", src.getYwxhzy());
        jsonObject.addProperty("yfyl", src.getYfyl());
        jsonObject.addProperty("img", src.getImg());
        jsonObject.addProperty("type", src.getType());
        jsonObject.addProperty("zc", src.getZc());
        jsonObject.addProperty("zycf", src.getZycf());
        jsonObject.addProperty("zysx", src.getZysx());
        jsonObject.addProperty("zzjb", src.getZzjb());
        return jsonObject;
    }
}
