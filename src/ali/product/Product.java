package ali.product;

import ali.object.strcture.OriginalObject;

public class Product extends OriginalObject{
	private String id;
	private String name;
	private String storeName;
	private String price;
	private String sellNum;
	private String commentNum;

	public void setID(String id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}

	public void setSellNum(String sellNum) {
		this.sellNum = sellNum;
	}

	public void setCommentNum(String commentNum) {
		this.commentNum = commentNum;
	}

	public String getID() {
		return this.id;
	}

	public String getName() {
		return this.name;
	}

	public String getPrice() {
		return this.price;
	}

	public String getStoreName() {
		return this.storeName;
	}

	public String getSellNum() {
		return this.sellNum;
	}

	public String getCommentNum() {
		return this.commentNum;
	}

	public String toString() {
		String output = this.id + "	" + this.name + "	" + this.storeName + "	" + 
				this.price + "	" + this.sellNum + "	" + this.commentNum;

		return output;
	}
}
