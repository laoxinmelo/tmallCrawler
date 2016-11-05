package test;


import com.raul.bupt.dbtool.DBTool;
import com.raul.bupt.dbtool.impl.DBToolImpl;
import com.raul.bupt.object.Product;
import com.raul.bupt.request.Crawler;
import com.raul.bupt.request.impl.CrawlerImpl;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/11/5.
 */
public class CrawleTest {

    static final Crawler crawler = new CrawlerImpl();
    static final DBTool dbTool = new DBToolImpl();


    /**
     * 将抓取的商品信息保存到数据库当中
     * @param itemList
     */
    static void saveItemList(ArrayList<Product> itemList) {

        for(Product product : itemList) {
            String sql = String.format("insert into product values(\"%s\",\"%s\",\"%s\",\"%s\",%f,%d)" ,
                    product.getProductId(),product.getProductName(),product.getSellerId(),product.getSellerName(),
                    product.getPrice(),product.getTradeNum());

            dbTool.execute(sql);
        }
    }

    public static void main(String[] args) {

        int count = 0;
        while(true) {

            count += 1;
            int startNum = 60*(count-1) - 1;
            if(startNum<0) {
                startNum = 0;
            }

            String url = "https://list.tmall.com/search_product.htm?cat=53320010&s="+startNum+"&sort=s&style=g&search_condition=7&from=sn_1_rightnav&industryCatId=53320010&type=pc#J_Filter";
            ArrayList itemList = crawler.itemCrawle(url);

            if(itemList.size() > 0) {
                 saveItemList(itemList);
                System.out.println(count);
            }else {
                break;
            }

//            break;
        }
    }
}
