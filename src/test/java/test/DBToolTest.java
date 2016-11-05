package test;

import com.raul.bupt.dbtool.DBTool;
import com.raul.bupt.dbtool.impl.DBToolImpl;

/**
 * Created by Administrator on 2016/11/5.
 */
public class DBToolTest {

    static final DBTool dbTool = new DBToolImpl();

    public static void main(String[] args) {
        dbTool.query("select * from review limit 1");
    }
}
