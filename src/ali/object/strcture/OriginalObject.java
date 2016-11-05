package ali.object.strcture;

import java.lang.reflect.Field;

public class OriginalObject {
	/**
     * @Purpose:返回该类的变量名
     * @return
     */
    public String variableName() {
  	  Field[] fields = this.getClass().getDeclaredFields();
  	  String names = "";
  	  for(int i=0;i<fields.length;i++)
  		  names += fields[i].getName() + "	";
  	 
  	  return names;
    }
}
