package com.raul.bupt.request;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import javax.print.Doc;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Administrator on 2016/11/2.
 */
public interface Crawler {

    public ArrayList itemCrawle(String url);
}

