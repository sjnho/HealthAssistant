package com.sjn.healthassistant.pojo.serializer;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.sjn.healthassistant.pojo.HealthClassify;

import java.lang.reflect.Type;

/**
 * Created by sjn on 16/5/4.
 */
public class HealthClassifySerializer implements JsonSerializer<HealthClassify> {
    @Override
    public JsonElement serialize(HealthClassify src, Type typeOfSrc, JsonSerializationContext context) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("id", src.getId());
        jsonObject.addProperty("description", src.getDescription());
        jsonObject.addProperty("keywords", src.getKeywords());
        jsonObject.addProperty("seq", src.getSeq());
        jsonObject.addProperty("title", src.getTitle());
        return jsonObject;
    }
}
