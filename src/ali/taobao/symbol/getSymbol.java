package ali.taobao.symbol;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class getSymbol {
    
    /**
     * 获取ElementMap中的有效数据
     * @return
     * @throws Exception
     */
    public static ArrayList<String> getElementSymbol() throws Exception {
    	ArrayList<String> elementList = new ArrayList<String>();
    	
    	File f = new File("key/taobaoList_element.txt");
    	InputStreamReader isr = new InputStreamReader(new FileInputStream(f));
        BufferedReader br = new BufferedReader(isr);
        String temp = br.readLine();
        
        while(temp != null)
        {
        	elementList.add(temp);
        	temp = br.readLine();
        }
        return elementList;
    }
    
    /**
     * 获取JsonMap中的有效数据
     * @return
     * @throws Exception
     */
    public static ArrayList<String> getJsonSymbol() throws Exception {
    	ArrayList<String> jsonList = new ArrayList<String>();
    	
    	File f = new File("key/taobaoList_json.txt");
    	InputStreamReader isr = new InputStreamReader(new FileInputStream(f));
        BufferedReader br = new BufferedReader(isr);
        String temp = br.readLine();
        
        while(temp != null)
        {
        	jsonList.add(temp);
        	temp = br.readLine();
        }
        return jsonList;
    }
    
    /**
     * 获取arrayMap中appendList的有效数据
     * @return
     * @throws Exception
     */
    public static ArrayList<String> getappendListSymbol() throws Exception {
    	ArrayList<String> appendList = new ArrayList<String>();
    	
    	File f = new File("key/taobaoList_append.txt");
    	InputStreamReader isr = new InputStreamReader(new FileInputStream(f));
        BufferedReader br = new BufferedReader(isr);
        String temp = br.readLine();
        
        while(temp != null)
        {
        	appendList.add(temp);
        	temp = br.readLine();
        }
        return appendList;
    }
}
