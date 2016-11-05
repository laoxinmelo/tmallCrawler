package ali.taobao.object.review;

import ali.object.strcture.OriginalObject;

import com.json.JsonHelper;

/**
 * 表示一条商品评论的追加评论部分
 * @author Administrator
 *
 */
public class AppendObject extends OriginalObject{
	private String productID;
	private String rateID; 
	private String initialDate;
	private String content;
	private String photoNum;
	private String dayAfterConfirm;
	private String reply;


	public void setProductID(String productID) {
		this.productID = productID;
	}

	public void setRateID(String rateID) {
		this.rateID = rateID;
	}

	public void setInitialDate(String date) {
		this.initialDate = date;
	}
	
	public boolean belong() {
		return this.content!=null;
	}

	/**
	 * @Purpose:设置其他变量的值
	 * @param key
	 * @param value
	 */
	public void setOthers(String key,Object value) throws Exception
	{
		String Value = String.valueOf(value);
		if(key.equals("photos"))
			this.photoNum = Value;
		else if(key.equals("content"))
			this.content = Value;
		else if(key.equals("dayAfterConfirm"))
			this.dayAfterConfirm = Value;
		else if(key.equals("reply"))
		{
			//System.out.println(Value);
			if(!Value.equals("null"))
				Value = String.valueOf(JsonHelper.toMap(Value).get("content"));
			this.reply = Value;
		}
	}

	public String toString() {
		String output = this.productID + "	" + this.rateID + "	" + this.initialDate + "	" + 
				this.content + "	" + this.photoNum + "	" + this.dayAfterConfirm + "	" + 
				this.reply;
		return output;
	}
}
