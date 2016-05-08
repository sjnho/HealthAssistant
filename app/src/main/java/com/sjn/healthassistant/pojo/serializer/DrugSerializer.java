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
        jsonObject.addProperty("name", src.getName());
        jsonObject.addProperty("img", src.getImg());
        jsonObject.addProperty("message", src.getMessage());
        jsonObject.addProperty("keywords", src.getKeywords());
        jsonObject.addProperty("description", src.getDescription());
        jsonObject.addProperty("tag", src.getTag());
        jsonObject.addProperty("type", src.getType());
        return jsonObject;
    }
}
