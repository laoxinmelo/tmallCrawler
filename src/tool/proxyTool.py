#coding=utf-8

import random

def reflushProxy():
    """
    设置动态随机代理
    :return:
    """
    proxies = ["122.114.173.129:16816","122.114.137.89:16816","123.207.12.49:16816","121.42.140.113:16816","122.114.173.129:16816"]
    # proxies = ["192.168.6.90:8088","192.168.6.97:8088","192.168.6.98:8088","192.168.6.103:8088"]
    proxy = proxies[random.randint(0,len(proxies)-1)]

    return proxy;