package org.kingtec.utils.Base;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by SolutionsBricks Mobile Dev. Team.
 */
public class GsonParser {
    /**
     * Value,Key
     */
    private HashMap<String, String> dataList;


    public HashMap<String, String> objectLooper(JsonObject mainJsonObject, String levelOneObject, Boolean keyFirst) {
        dataList = new HashMap<>();
        if (levelOneObject != null && mainJsonObject != null)
            mainJsonObject = mainJsonObject.getAsJsonObject(levelOneObject);
        if (mainJsonObject != null) {
            for (Map.Entry<String, JsonElement> entry : mainJsonObject.entrySet()) {
                if (keyFirst)
                    dataList.put(Concurrent.repairJsonValueQuotes(entry.getKey()), Concurrent.repairJsonValueQuotes(entry.getValue().toString()));
                else
                    dataList.put(Concurrent.repairJsonValueQuotes(entry.getValue().toString()), Concurrent.repairJsonValueQuotes(entry.getKey()));
            }
        }
        return dataList;
    }

    public HashMap<Object, Object> getItemsFromJsonObject(JsonObject mainJsonObject, String levelOneObject) {
        HashMap<Object, Object> dataList = new HashMap<>();
        if (levelOneObject != null && mainJsonObject != null)
            mainJsonObject = mainJsonObject.getAsJsonObject(levelOneObject);
        if (mainJsonObject != null) {
            for (Map.Entry<String, JsonElement> entry : mainJsonObject.entrySet()) {
                dataList.put(Concurrent.repairJsonValueQuotes(entry.getKey()), entry.getValue());
            }

        }
        return dataList;
    }

    public ArrayList<Object> getItemsFromJsonArray(JsonArray mainJsonArray) {
        ArrayList<Object> dataList = new ArrayList<>();
        if (mainJsonArray.size() > 0) {
            Iterator<JsonElement> ValsIter = mainJsonArray.iterator();
            while (ValsIter.hasNext()) {
                dataList.add(ValsIter.next().getAsJsonObject());
            }
        }
        return dataList;
    }

    public ArrayList<String> getListOfMap(HashMap<String, String> masterMap, Boolean getKey) {
        ArrayList<String> listOfMap = new ArrayList<>();
        for (Map.Entry<String, String> entry : masterMap.entrySet()) {
            if (getKey) listOfMap.add(entry.getKey());
            else listOfMap.add(entry.getValue());
        }
        return listOfMap;
    }
}
