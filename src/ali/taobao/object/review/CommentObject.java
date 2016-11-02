package ali.taobao.object.review;

import ali.object.strcture.OriginalObject;

/**
 * ��ʾһ�����۵��������ġ���Ʒ��Ϣ���û���Ϣ��
 * @author Administrator
 *
 */
public class CommentObject extends OriginalObject{
	private String productID;//��Ʒid
	private String title;
	private String sku;
	private String amount;
	private String nick;
	private String vipLevel;
	private String anony;
	private String rateID;//����id
	private String rate;//��������
	private String content;//��������
	private String date;//���ۼ���
	private String buyAmount = "";//��������
	private String dayAfterConfirm;//�ջ���������������ֻ֮��
	private String photoNum;//������ͼƬ������

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
		return (!this.content.equals("���۷�δ��ʱ��������,ϵͳĬ�Ϻ���!"));
	}

	/**
	 * @Purpose:��������������ֵ
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
