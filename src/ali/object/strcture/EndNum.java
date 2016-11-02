package ali.object.strcture;

/**
 * 因为json的原始数据结构当中存在一些 用[]、{}封装起来的数据，需要对其进行识别
 * @author Administrator
 *
 */

public class EndNum {
     int num;
     String data;
     
     public void setNum(int num) {
    	 this.num = num;
     }
     
     public void setData(String data) {
    	 this.data = data;
     }
     
     public int getNum() {
    	 return this.num;
     }
     
     public String getData() {
    	 return this.data;
     }
}
