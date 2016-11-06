#coding=utf-8

import MySQLdb
import traceback
from DBUtils.PooledDB import *

host = "localhost"
user = "root"
passwd = "lx920618"
dbName = "tmall"
port = 3306

mincached = 10
maxcached = 30
maxconnections = 20
blocking = False

class DBPool:
    def __init__(self):
        """
        构建数据库连接池
        :return:
        """
        self.pool = PooledDB(MySQLdb,host=host,user=user,passwd=passwd,db=dbName,port=port,
                             mincached=mincached,maxcached=maxcached,maxconnections=maxconnections,blocking=blocking,charset="utf8")


    def getConn(self):
        return self.pool.connection();


    def select(self,sql):

        """
        数据查询
        :param sql:
        :return:
        """
        conn = self.getConn()
        cur = self.getConn().cursor()

        try:
            cur.execute(sql);
            return cur.fetchall();
        except Exception as msg:
            print msg
        finally:
            conn.close()
            cur.close()

    def update(self,sql):

        """
        数据更新
        :param sql:
        :return:
        """
        conn = self.getConn()
        cur = self.conn.cursor()

        try:
            cur.execute(sql);
            conn.commit()
        except Exception as msg:
            print msg
        finally:
            conn.close()
            cur.close()


    def insert(self,sql):
        """
        数据插入
        :param sql:
        :return:
        """
        conn = self.getConn()
        cur = self.getConn().cursor()

        try:
            cur.execute(sql);
            conn.commit()
        except Exception as msg:
            print sql
            print msg
        finally:
            conn.close()
            cur.close()


    def delete(self,sql):
        """
        数据删除
        :param sql:
        :return:
        """
        conn = self.getConn()
        cur = self.getConn().cursor()

        try:
            cur.execute(sql);
            conn.commit()
        except Exception as msg:
            print msg
        finally:
            conn.close()
            cur.close()
