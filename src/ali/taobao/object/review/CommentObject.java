package ali.taobao.object.review;

import ali.object.strcture.OriginalObject;

/**
 * 表示一条评论的评论正文、商品信息、用户信息等
 * @author Administrator
 *
 */
public class CommentObject extends OriginalObject{
	private String productID;//商品id
	private String title;
	private String sku;
	private String amount;
	private String nick;
	private String vipLevel;
	private String anony;
	private String rateID;//评论id
	private String rate;//评论内容
	private String content;//评论日期
	private String date;//评论极性
	private String buyAmount = "";//购买数量
	private String dayAfterConfirm;//收货日期与评论日期只之差
	private String photoNum;//评论中图片的数量

	public void SETproductID(String id) {
		this.productID = id;
	}

	public void SETphotoNum(String num) {
		this.photoNum = num;
	}

	public String getProductID() {
		return this.productID;
	}

	public String getRateID() {
		return this.rateID;
	}

	public String getDate() {
		return this.date;
	}

	public boolean belong() {
		return (!this.content.equals("评价方未及时做出评价,系统默认好评!"));
	}

	/**
	 * @Purpose:设置其他变量的值
	 * @param key
	 * @param value
	 */
	public void SETothers(String key,Object value)
	{
		String Value = String.valueOf(value);
		if(key.equals("rateId"))
			this.rateID = Value;
		else if(key.equals("content"))
			this.content = Value;
		else if(key.equals("date"))
			this.date = Value;
		else if(key.equals("rate"))
			this.rate = Value;
		else if(key.equals("buyAmount"))
			this.buyAmount = Value;
		else if(key.equals("dayAfterConfirm"))
			this.dayAfterConfirm = Value;
		else if(key.equals("title"))
			this.title = Value;
		else if(key.equals("sku"))
			this.sku = Value;
		else if(key.equals("amount"))
			this.amount = Value;
		else if(key.equals("nick"))
			this.nick = Value;
		else if(key.equals("vipLevel"))
			this.vipLevel = Value;
		else if(key.equals("anony"))
			this.anony = Value;
	}


	public String toString() {
		String output = this.productID + "	" +this.title + "	" + this.sku + "	" + 
				this.amount + "	" + this.nick + "	" + this.vipLevel + "	" + 
				this.anony + "	" + this.rateID + "	" + this.rate + "	" +
				this.content + "	" + this.date + "	" + this.buyAmount + "	" +
				this.dayAfterConfirm + "	" + this.photoNum;

		return output;
	}
}
