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
 * ��Structureת��ΪAppend/Comment/Reply-Object
 * @author Administrator
 *
 */
public class getData {
	/**
	 * @Purpose:��ȡelement���͵���Ч���ݱ�ǩ
	 * @return
	 * @throws Exception
	 */
	private ArrayList<String> elementSymbol() throws Exception
	{
		return getSymbol.getElementSymbol();
	}

	/**
	 * @Purpose:��ȡjson���͵���Ч���ݱ�ǩ
	 * @return
	 * @throws Exception
	 */
	private ArrayList<String> jsonSymbol() throws Exception
	{
		return getSymbol.getJsonSymbol();
	}

	/**
	 * @Purpose:��ȡJarray������appendList����Ч���ݱ�ǩ
	 * @return
	 * @throws Exception
	 */
	private ArrayList<String> appendSymbol() throws Exception
	{
		return getSymbol.getappendListSymbol();
	}

	/**
	 * @Purpose:��ȡReviewObject��elementMap����Ч����ֵ
	 * @param elementMap
	 * @throws Exception
	 */
	public void getElementData(HashMap<String,String> elementMap,CommentObject co) throws Exception
	{
		ArrayList<String> elementSymbol = elementSymbol();

		//��ȡ���۵����ݡ����ۼ��Ե������Ϣ
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
	 * @Purpose:��ȡReviewObject��jsonMap����Ч����ֵ
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

			//�̼һظ�
			if(jsonMap.containsKey("reply")&&key.equals("reply"))
			{
				String reply = String.valueOf(jsonMap.get("reply").get("content"));
				ro.setReply(reply);
			}
			//�����û�������Ϣ
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
	 * @Purpose:��ȡReviewObject��arrayMap����ӦappendList�е���Ч����ֵ(׷������)
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
				//׷�����۵�ͼƬ����
				if(key.equals("photos"))
				{
					JSONArray tempArray = new JSONArray(jo.getString(key));
					ao.setOthers(key, tempArray.length());
				}
				//׷�����۵���������
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
	 * @Purpose:��ȡ�����и���ͼƬ������
	 * @param Jarray
	 * @throws Exception
	 */
	public void getPhotos(JSONArray Jarray,CommentObject co) throws Exception
	{
		co.SETphotoNum(String.valueOf(Jarray.length()));
	}

}
