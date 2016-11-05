package ali.object.strcture;

import java.util.HashMap;
import java.io.InputStreamReader;
import java.io.FileInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.util.Map;
import org.json.JSONArray;

/**
 * @根据json数据的格式与类型构造
 * @author Administrator
 *
 */
public class StructureObject {
    private HashMap<String,JSONArray> arrayMap = new HashMap<String,JSONArray>();
    private HashMap<String,Map> jsonMap = new HashMap<String,Map>();
    private HashMap<String,String> elementMap = new HashMap<String,String>();
    
    public void putArray(String key,JSONArray array)  {
    	arrayMap.put(key, array);
    }
    
    public void putJson(String key,Map map)  {
    	jsonMap.put(key, map);
    }
    
    public void putElement(String key,String value)  {
    	elementMap.put(key, value);
    }
    
    public HashMap<String,JSONArray> getArray() {
    	return this.arrayMap;
    }
    
    public HashMap<String,Map> getJson() {
    	return this.jsonMap;
    }
    
    public HashMap<String,String> getElement() {
    	return this.elementMap;
    }
}
