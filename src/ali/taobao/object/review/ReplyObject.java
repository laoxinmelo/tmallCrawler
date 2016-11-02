package ali.taobao.object.review;

import ali.object.strcture.OriginalObject;

import com.json.JsonHelper;

import java.util.Map;

/**
 * 表示一条评论的商家回复部分
 * @author Administrator
 *
 */
public class ReplyObject extends OriginalObject{
	String productID;
	String rateID;
	String reply;
	
	public void setProductID(String productID) {
		this.productID = productID;
	}

	public void setRateID(String rateID) {
		this.rateID = rateID;
	}
	
	public void setReply (String reply) {
		this.reply = reply;
	}
	
	public boolean belong() {
		return this.reply!=null;
	}
	
	public String toString() {
		String output = this.productID + "	" + this.rateID + "	" +  this.reply;
		return output;
	}
}
