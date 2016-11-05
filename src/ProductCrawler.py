#coding=utf-8

from tool.dbTool import *
from tool.requestTool import *
from tool.userAgentTool import *

from HTMLParser import HTMLParser
import urllib

def strEscape(word):
    """
    将汉字转换成百分号格式
    :param word:
    :return:
    """
    return urllib.quote(word)


class MyHTMLParser(HTMLParser):
    def __init__(self):
        HTMLParser.__init__(self)
        self.content = ""

    def handle_startendtag(self, tag, attrs):
        """

        :param tag:
        :param attrs:元组形式，表示标签及对应的值
        :return:
        """
        if tag == "a":
            pass

    def handle_data(self, data):
        print data


#数据库连接池
dbTool = DBPool()

if __name__ == "__main__":
      url = "https://list.tmall.com/search_product.htm?q=%s&type=p&vmarket=&from=mallfp..pc_1_searchbutton" % strEscape("电子烟")
      req = getHttpResponse(url=url,headers=None,proxies=None,timeout=1)

      myHTMLParser = MyHTMLParser()

      if req != None:
           content = req.text
           # print content
           myHTMLParser.feed(content)