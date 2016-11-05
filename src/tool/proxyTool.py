#coding=utf-8

import random

def reflushProxy():
    """
    设置动态随机代理
    :return:
    """
    proxies = ["10.64.18.210:8411","192.168.6.90:8088","192.168.6.97:8088","192.168.6.98:8088","192.168.6.103:8088"]
    # proxies = ["192.168.6.90:8088","192.168.6.97:8088","192.168.6.98:8088","192.168.6.103:8088"]
    proxy = proxies[random.randint(0,len(proxies)-1)]

    return {"https" : "https://" + proxy};