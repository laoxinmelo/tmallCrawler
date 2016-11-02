#coding=utf-8

import MySQLdb
from DBUtils.PooledDB import *


host = "localhost"
user = "root"
passwd = "lx920618"
dbName = "tmall"
port = 3306

mincached = 10
maxcached = 30
maxconnections = 20
blocking = True

class DBPool:
    def __init__(self):
        """
        构建数据库连接池
        :return:
        """
        self.pool = PooledDB(MySQLdb,host=host,user=user,passwd=passwd,db=dbName,port=port,
                             mincached=mincached,maxcached=maxcached,maxconnections=maxconnections,blocking=blocking)

        self.conn = self.pool.connection()
        self.cur = self.conn.cursor()

    def select(self,sql):
        """
        数据查询
        :param sql:
        :return:
        """
        try:
            self.cur.execute(sql);
            return self.cur.fetchall();
        except Exception as msg:
            print msg

    def update(self,sql):
        """
        数据更新
        :param sql:
        :return:
        """
        try:
            self.cur.execute(sql);
            self.conn.commit()
        except Exception as msg:
            print msg

    def insert(self,sql):
        """
        数据插入
        :param sql:
        :return:
        """
        try:
            self.cur.execute(sql);
            self.conn.commit()
        except Exception as msg:
            print msg


    def delete(self,sql):
        """
        数据删除
        :param sql:
        :return:
        """

        try:
            self.cur.execute(sql);
            self.conn.commit()
        except Exception as msg:
            print msg

    def close(self):
        """
        关闭连接池的连接
        :return:
        """
        self.conn.close()
        self.cur.close()
