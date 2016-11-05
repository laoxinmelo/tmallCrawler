#coding=utf-8

from userAgentTool import *
from requestTool import *
from proxyTool import *
from resolveTool import *

import time
import json

class ItemCrawler:
    def __init__(self,itemId,sellerId,dbTool):
        """
        对象初始化
        :param itemId:
        :param sellerId:
        :return:
        """
        self.itemId = itemId
        self.sellerId = sellerId
        self.dbTool = dbTool

    def getHeaders(self):
        """
        构造header请求文件
        :return:
        """
        Headers = {}
        Headers["Accept"] = "*/*"
        Headers["Accept-Encoding"] = "gzip, deflate, sdch, br"
        Headers["Accept-Language"] = "zh-CN,zh;q=0.8"
        Headers["cookie"] = self.getCookie()
        Headers["User-Agent"] = getUserAgent()
        Headers["Referer"] = "https://detail.tmall.com/item.htm?id=" + self.itemId

        return Headers

    def getCookie(self):
        """
        获取M相关的cookie
        :return:
        """
        url = "http://api.m.taobao.com/rest/h5ApiUpdate.do?"
        api = "mtop.trip.travel.scenicSearch"
        v = "1.0"
        appKey = "12574478"
        queryString = "api="+api+"&v="+v+"&appKey="+appKey

        proxies = reflushProxy()

        try:
                 r = getHttpResponse(url+queryString,{},proxies,5)
                 cookies = r.cookies
                 cookieDict = requests.utils.dict_from_cookiejar(r.cookies)
                 cookie = "_m_h5_tk=" + cookieDict["_m_h5_tk"] + "; _m_h5_tk_enc=" + cookieDict["_m_h5_tk_enc"]
                 return cookie
        except(AttributeError,Exception):
                 print "在获取cookie中request出现异常，需重新访问..."
                 return None

    def crawle(self,threadNum):
        """
        抓取所有页面的json字符串
        :return:
        """
        pageNum = 1
        totalNum = 1
        t = str(int(time.time()*1000))

        while(pageNum<=totalNum):
            url = "https://rate.tmall.com/list_detail_rate.htm?itemId=" + self.itemId\
              +"&sellerId=" + self.sellerId + "&order=3&currentPage=" + str(pageNum) + "&append=0&content=1&tagId=&posi=&picture=&needFold=0&_ksTS=" + t + "_1819&callback=jsonp1820"

            Headers = self.getHeaders()
            req = getHttpResponse(url,Headers,{},1)
            count = 1
            if req != None:
                content = req.text.strip("\r\n")
                content = content[content.index("(")+1:content.rindex(")")]

                js = json.loads(content)
                if js.has_key("url"):
                    pageNum = pageNum - 1
                    count += 1
                elif isinstance(js["rateDetail"]["paginator"],dict):
                    totalNum = int(js["rateDetail"]["paginator"]["lastPage"])
                    # print threadNum,count,content
                    count = 1
                    jsonResolve(self.dbTool,js,self.itemId)
                    print "ThreadID:%d; PageNum:%d; TotalPageNum:%d; ItemId:%s" % (threadNum,pageNum,totalNum,self.itemId)
            else:
                pageNum = pageNum - 1
                count += 1

            pageNum += 1