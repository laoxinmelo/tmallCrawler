#coding=utf-8

from tool import dbTool

if __name__ == "__main__":

    DBPool = dbTool.DBPool()

    #插入
    DBPool.delete("delete from product;")

    print DBPool.select("select * from product;")
