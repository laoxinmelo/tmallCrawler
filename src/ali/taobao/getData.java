package ali.taobao;

import org.json.JSONArray;
import org.json.JSONObject;

import com.json.JsonHelper;

import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

import ali.taobao.object.review.AppendObject;
import ali.taobao.object.review.CommentObject;
import ali.taobao.object.review.ReplyObject;
import ali.taobao.symbol.getSymbol;

/**
 * 将Structure转换为Append/Comment/Reply-Object
 * @author Administrator
 *
 */
public class getData {
	/**
	 * @Purpose:获取element类型的有效数据标签
	 * @return
	 * @throws Exception
	 */
	private ArrayList<String> elementSymbol() throws Exception
	{
		return getSymbol.getElementSymbol();
	}

	/**
	 * @Purpose:获取json类型的有效数据标签
	 * @return
	 * @throws Exception
	 */
	private ArrayList<String> jsonSymbol() throws Exception
	{
		return getSymbol.getJsonSymbol();
	}

	/**
	 * @Purpose:获取Jarray类型中appendList的有效数据标签
	 * @return
	 * @throws Exception
	 */
	private ArrayList<String> appendSymbol() throws Exception
	{
		return getSymbol.getappendListSymbol();
	}

	/**
	 * @Purpose:获取ReviewObject中elementMap的有效对象值
	 * @param elementMap
	 * @throws Exception
	 */
	public void getElementData(HashMap<String,String> elementMap,CommentObject co) throws Exception
	{
		ArrayList<String> elementSymbol = elementSymbol();

		//获取评论的内容、评论极性等相关信息
		for(int i=0;i<elementSymbol.size();i++)
		{
			String key = elementSymbol.get(i);
			if(elementMap.containsKey(key))
			{
				String temp = elementMap.get(key);
				//System.out.println(key +":"+ temp);
				if(temp.startsWith("\"")&&temp.endsWith("\""))
					temp = temp.substring(temp.indexOf("\"")+1, temp.lastIndexOf("\""));
				co.SETothers(key, temp);
			}
		}
	}

	/**
	 * @Purpose:获取ReviewObject中jsonMap的有效对象值
	 * @param jsonMap
	 * @throws Exception
	 */
	public void getJSONData(HashMap<String,Map> jsonMap,CommentObject co,ReplyObject ro) throws Exception
	{
		ArrayList<String> jsonSymbol = jsonSymbol();

		for(int i=0;i<jsonSymbol.size();i++)
		{
			String temp = jsonSymbol.get(i);
			String key = temp.substring(0, temp.indexOf("	")); 
			String mapKey = temp.substring(temp.indexOf("	")+1);

			//商家回复
			if(jsonMap.containsKey("reply")&&key.equals("reply"))
			{
				String reply = String.valueOf(jsonMap.get("reply").get("content"));
				ro.setReply(reply);
			}
			//评论用户个人信息
			else if(jsonMap.containsKey(key))
			{
				Object value = jsonMap.get(key).get(mapKey);
				//System.out.println(mapKey + ":" + value);
				co.SETothers(mapKey, value);
			}
		}
		ro.setProductID(co.getProductID());
		ro.setRateID(co.getRateID());
	}

	/**
	 * @Purpose:获取ReviewObject中arrayMap所对应appendList中的有效对象值(追加评论)
	 * @param Jarray
	 * @throws Exception
	 */
	public ArrayList<AppendObject> getAppendList(JSONArray Jarray,CommentObject co,String productID) throws Exception
	{
		ArrayList<String> appendSymbol = appendSymbol();
		ArrayList<AppendObject> appendList = new ArrayList<AppendObject>(); 

		for(int i=0;i<Jarray.length();i++)
		{
			AppendObject ao = new AppendObject();
			JSONObject jo = Jarray.getJSONObject(i);
			Map joMap = JsonHelper.toMap(String.valueOf(Jarray.get(i)));
			for(int j=0;j<appendSymbol.size();j++)
			{
				String key = appendSymbol.get(j);
				//追加评论的图片数量
				if(key.equals("photos"))
				{
					JSONArray tempArray = new JSONArray(jo.getString(key));
					ao.setOthers(key, tempArray.length());
				}
				//追加评论的其他数据
				else 
					ao.setOthers(key, jo.get(key));
			}
			ao.setInitialDate(co.getDate());
			ao.setProductID(productID);
			ao.setRateID(co.getRateID());

			if(ao.belong())
				appendList.add(ao);
		}
		return appendList;
	}

	/**
	 * @Purpose:获取评论中附带图片的数量
	 * @param Jarray
	 * @throws Exception
	 */
	public void getPhotos(JSONArray Jarray,CommentObject co) throws Exception
	{
		co.SETphotoNum(String.valueOf(Jarray.length()));
	}

}
