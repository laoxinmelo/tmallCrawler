package ali.object.strcture;

import java.lang.reflect.Field;

public class OriginalObject {
	/**
     * @Purpose:���ظ���ı�����
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
