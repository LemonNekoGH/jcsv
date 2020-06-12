package moe.lemonneko.jcsv;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class CSVObject {
    private Map<String, String> csvMapping = new HashMap<>();

    public String get(String key) {
        String value = csvMapping.get(key);
        if (value == null) {
            throw new NullPointerException("Key with name [" + key + "] not found.");
        }
        return value;
    }

    public void set(String key, String value) {
        csvMapping.put(key, value);
    }

    public boolean containsKey(String key){
        return csvMapping.containsKey(key);
    }

    public Set<String> keySet(){
        return csvMapping.keySet();
    }
}
