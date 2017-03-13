package com.raul.bupt.request.impl;

import com.raul.bupt.object.Product;
import com.raul.bupt.request.Crawler;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.util.ArrayList;

/**
 * Created by Administrator on 2016/11/5.
 */
public class CrawlerImpl implements Crawler{

    private final String itemIndex = "product  ";//"product item";
    private final String idIndex = "data-id";
    private final String priceIndex = "productPrice";
    private final String tradeIndex = "productStatus";
    private final String sellerIndex = "productShop-name";

    /**
     * 获取url对应的doc文件
     * @param url
     * @return
     */
    private Document getDoc(String url) {
        try{

            Document doc = Jsoup.connect(url)
                    .ignoreContentType(true)
                    .userAgent("Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.31 (KHTML, like Gecko) Chrome/26.0.1410.64 Safari/537.31")
                    .timeout(20000)
                    .get();

            return doc;

        }catch(SocketTimeoutException e)
        {
            Document doc = getDoc(url);
            return doc;
        }
        catch(SocketException e)
        {
            Document doc = getDoc(url);
            return doc;
        }
        catch(Exception e)
        {
            System.err.println("出现以下异常：");
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 对网页所对应的url界面进行解析
     * @param doc
     * @return
     */
    private ArrayList docResolve(Document doc) {
        if(doc == null) {
            throw new NullPointerException("The Url Document is NULL....");
        }


       ArrayList itemList = new ArrayList<Product>();  //用来保存当前页面下各个商品的对应信息，如名称，价格，销量，卖家信息等

        Elements elements = doc.select("div");
        for(Element element:elements) {
            if(element.attr("class").contains(itemIndex)) {
                String itemId = element.attr(idIndex);   //获取商品对应的id
                Elements itemElements = element.getAllElements();

                Product product = new Product();
                product.setProductId(itemId);

                for(Element itemElement:itemElements) {
                    //获取商品对应的价格
                    if (itemElement.attr("class").equals(priceIndex)) {
                        String priceStr = itemElement.text().trim().replaceAll("[^0-9.]","");
                        product.setPrice(Double.valueOf(priceStr));
                    }
                    //获取商品对应的交易记录
                    if(itemElement.attr("class").equals(tradeIndex)) {
                        String tradeNumStr = itemElement.text().trim().replaceAll("[^0-9]","");
                        product.setTradeNum(Integer.valueOf(tradeNumStr));
                    }
                    //获取商品对应的卖家名称及ID
                    if(itemElement.attr("class").equals(sellerIndex)) {
                        String sellerName = itemElement.text().trim();
                        product.setSellerName(sellerName);

                        String sellerId = itemElement.attr("href");
                        sellerId = sellerId.substring(sellerId.indexOf("id=")+3,sellerId.indexOf("&"));
                        product.setSellerId(sellerId);
                    }
                    //获取商品对应的名称
                    if(itemElement.tagName().equals("a") && itemElement.hasAttr("href")
                            && itemElement.hasAttr("target") && itemElement.hasAttr("title")
                            && itemElement.hasAttr("data-p") && (!itemElement.hasAttr("class"))) {
                        String itemName = itemElement.text().trim();
                        product.setProductName(itemName);
                    }

                }
                itemList.add(product);
            }
        }

        return itemList;
    }

    /**
     * 抓取商品列表页面，同时对各个商品信息进行解析，将其保存在数据库当中
     * @param url
     */
    public ArrayList itemCrawle(String url) {

        Document doc = this.getDoc(url);
        while(doc == null) {
            System.err.println("The HTML DOC is NULL!!!!!");
            try{
                Thread.sleep(5 * 1000);
            }catch (InterruptedException e) {
                ;
            }
            doc = this.getDoc(url);
        }

        ArrayList itemList = this.docResolve(doc);
        return itemList;

    }

}
