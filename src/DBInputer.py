#-*_coding:utf8-*-

from tool.dbTool import DBPool
import os
import sys

reload(sys)
sys.setdefaultencoding("utf8")

dbTool = DBPool()

if __name__ == "__main__":

    basicPath = u"E:\\毕业论文\\数据\\正式实验\\"

    productList = os.listdir(basicPath)
    for product in productList:
        productPath = basicPath + product
        for txt in os.listdir(productPath):
            filePath = productPath + "\\" + txt
            # print filePath
            reader = file(filePath,"r")
            line = reader.readline().decode("gbk").strip("\r\n")

            while len(line) > 0:
                tempList = line.split("\t")
                itemId = tempList[0]  #商品Id
                reviewId = tempList[1]  #评论Id

                #追加评论
                if txt.count("append") > 0:
                    content = tempList[2].encode("utf-8")
                    date = tempList[3].replace("2015","2016").encode("utf-8")
                    days = int(tempList[4])
                    picNum = int(tempList[5])

                    # sql = "insert into appendreview VALUES(\"%s\",\"%s\",\"%s\",\"%s\",%d,%d)" % (itemId,reviewId,content,date,days,picNum)
                    # print sql
                    # dbTool.insert(sql)
                #原始评论
                if txt.count("comment") > 0:
                    userName = tempList[2].encode("utf-8")
                    tmallLevel = int(tempList[3])

                    isVip = tempList[4]
                    vip = 0
                    if isVip == u"1":
                        vip = 1

                    content = tempList[5].encode("utf-8") + tempList[6].encode("utf-8")
                    content = content.replace("\"","_")

                    date = tempList[7].encode("utf-8")
                    picNum = int(tempList[8])

                    sql = "insert into review values(\"%s\",\"%s\",\"%s\",%d,%d,\"%s\",\"%s\",%d)" % (itemId,reviewId,userName,tmallLevel,vip,content,date,picNum);
                    # print sql
                    dbTool.insert(sql)




                #商家反馈
                if txt.count("reply") > 0:
                    content = tempList[2]

                    # sql = "insert into reply VALUES(\"%s\",\"%s\",\"%s\")" % (itemId,reviewId,content)
                    # print sql
                    # dbTool.insert(sql)

                try:
                    line = reader.readline().strip("\r\n").decode("gbk")
                except UnicodeDecodeError:
                    line = reader.readline().decode("gbk").strip("\r\n")