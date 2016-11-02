package ali.taobao;

import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;

import ali.object.strcture.EndNum;
import ali.object.strcture.StructureObject;
import ali.taobao.object.review.AppendObject;
import ali.taobao.object.review.CommentObject;
import ali.taobao.object.review.ReplyObject;
import ali.taobao.object.review.ReviewObject;

import org.json.JSONArray;

import com.json.JsonHelper;

/**
 * ����һ����Ʒ����������ҳ�棬��Append/Comment/Reply-Object����ΪReviewObject�ļ���ArrayList
 * @author Administrator
 *
 */
public class getOutput {


	/**
	 * @Purpose:��StructureObjectת��ΪReviewObject
	 * @param structureList
	 * @return
	 */
	public ArrayList<ReviewObject> getReviewList(String text,String productID) throws Exception
	{
		ArrayList<StructureObject> structureList = getOriginalData(text);

		getData getdata = new getData();
		ArrayList<ReviewObject> reviewList = new ArrayList<ReviewObject>();
		for(int i=0;i<structureList.size();i++)
		{
			ReviewObject review = new ReviewObject();

			StructureObject structure = structureList.get(i);
			CommentObject comment = new CommentObject();
			ReplyObject reply = new ReplyObject();
			comment.SETproductID(productID);

			HashMap<String,String> elementMap = structure.getElement();
			JSONArray appendArray = structure.getArray().get("appendList");
			JSONArray photosArray = structure.getArray().get("photos");
			HashMap<String,Map> jsonMap = structure.getJson();

			getdata.getElementData(elementMap, comment);
			getdata.getJSONData(jsonMap, comment, reply);
			getdata.getPhotos(photosArray, comment);
			ArrayList<AppendObject> appendList = getdata.getAppendList(appendArray, comment, productID);

			review.setAppendObject(appendList);
			review.setCommentObject(comment);
			review.setReplyObject(reply);

			reviewList.add(review);
		}

		return reviewList;
	}

	/**
	 * @Purpose:�������ݵĽṹ��ʽ�Լ��������ۣ���ҳ���ԭʼ�ı�json���й���
	 * @param text
	 * @return
	 * @throws Exception
	 */
	private ArrayList<StructureObject> getOriginalData(String text) throws Exception
	{
		ArrayList<StructureObject> reviewList = new ArrayList<StructureObject>();
		String[] array = text.split("\"auction\":");

		for(int i=1;i<array.length;i++)
		{
			StructureObject ro = new StructureObject();
			String temp = "\"auction\":" + array[i].substring(0, array[i].lastIndexOf("}"));

			String[] tempArray = temp.split(",\"");
			for(int j=0;j<tempArray.length;j++)
			{
				if(!tempArray[j].startsWith("\""))
					tempArray[j] = "\"" + tempArray[j];

				//System.out.println(tempArray[j]);

				if(tempArray[j].contains("[")&&tempArray[j].contains("{"))
				{					
					if(tempArray[j].indexOf("[")<tempArray[j].indexOf("{"))
					{
						EndNum en = getComplex(tempArray,"[","]",j);
						String data = en.getData();
						String key = data.substring(0, data.indexOf(":")).replaceAll("\"", "");
						String value = data.substring(data.indexOf(":")+1);

						//System.out.println(array[i]);
						JSONArray Jarray = new JSONArray(value);
						ro.putArray(key, Jarray);
						j = en.getNum();
					}
					else if(tempArray[j].indexOf("[")>tempArray[j].indexOf("{"))
					{
						EndNum en = getComplex(tempArray,"{","}",j);
						String data = en.getData();;
						String key = data.substring(0, data.indexOf(":")).replaceAll("\"", "");
						String value = data.substring(data.indexOf(":")+1);

						value = new String(value.getBytes());
						Map map = JsonHelper.toMap(value);
						ro.putJson(key, map);
						j = en.getNum();
					}
				}
				else if(tempArray[j].contains("["))
				{
					EndNum en = getComplex(tempArray,"[","]",j);
					String data = en.getData();
					String key = data.substring(0, data.indexOf(":")).replaceAll("\"", "");
					String value = data.substring(data.indexOf(":")+1);

					if(!value.startsWith("["))
						value = "[" + value;
					if(!value.endsWith("]"))
						value = value + "]";
					//System.out.println(value);
					JSONArray Jarray = new JSONArray(value);
					ro.putArray(key, Jarray);
					j = en.getNum();
				}
				else if(tempArray[j].contains("{"))
				{
					EndNum en = getComplex(tempArray,"{","}",j);
					String data = en.getData();
					//System.out.println(data);
					String key = data.substring(0, data.indexOf(":")).replaceAll("\"", "");
					String value = data.substring(data.indexOf(":")+1);
					value = new String(value.getBytes());
					Map map = JsonHelper.toMap(value);
					ro.putJson(key, map);
					j = en.getNum();
				}
				else if(tempArray[j].contains(":"))
				{
					String data = tempArray[j];
					String key = data.substring(0, data.indexOf(":")).replaceAll("\"", "");
					String value = data.substring(data.indexOf(":")+1);
					value = value.replaceAll("}]", "");
					//System.out.println(data);
					ro.putElement(key, value);
				}
			}
			reviewList.add(ro);
		}
		return reviewList;
	}

	/**
	 * @Purpose:��ȡ�����ͣ�����[]��{}���ţ�������
	 * @param symbol
	 * @return
	 * @throws Exception
	 */
	private static EndNum getComplex(String[] array,String start,String end,int index) throws Exception
	{
		int count = charNum(array[index],end);
		int num = charNum(array[index],start);
		String output = array[index];
		index = index+1;

		while(count!=num)
		{
			if(array[index].contains(end) || array[index].contains(start))
			{
				count += charNum(array[index],end);
				num += charNum(array[index],start);
			}

			output += ",\"" + array[index];
			index++;
		}



		index = index - 1;
		EndNum en = new EndNum();
		en.setData(output); en.setNum(index);
		return en;
	}

	/**
	 * @Purpose:�����ַ�����ĳ���ַ�������
	 * @param symbol
	 * @return
	 * @throws Exception
	 */
	private static int charNum(String string,String symbol) throws Exception
	{
		int num = 0;
		String[] array = string.split("");
		for(int i=0;i<array.length;i++)
		{
			if(array[i].equals(symbol))
				num++;
		}
		return num;
	}
}
