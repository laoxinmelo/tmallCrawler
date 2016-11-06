#coding=utf-8

from tool.dbTool import *
from tool.Crawler import *
from tool.resolveTool import *
import Queue
import threading

#数据库连接池
dbTool = DBPool()

itemQueue = Queue.Queue()

def getItemQueue():
    """
    从数据库中获取所有商品id的信息
    :return:
    """
    itemSet = dbTool.select("select itemId,sellerId from product");
    for item in itemSet:
        itemQueue.put((item[0],item[1]))


class crawleThread(threading.Thread):
    def __init__(self,num):
        threading.Thread.__init__(self)
        self.threadID = num
        self.itemQueue = itemQueue
        self.db = dbTool

    def run(self):
        while self.itemQueue.qsize() > 0:
            item = self.itemQueue.get()
            itemId = item[0]
            sellerId = item[1]
            crawler = ItemCrawler(itemId,sellerId,self.db)
            crawler.crawle(self.threadID)

            # print "There are " + str(self.itemQueue.qsize()) + " items left..."

if __name__ == "__main__":

     getItemQueue()

     threadList = []
     for i in range(3):
         newThread = crawleThread(i+1)
         threadList.append(newThread)

     for i in range(3):
        threadList[i].start()

