#coding=utf-8

import requests
import traceback

def getHttpResponse(url,headers,proxies,timeout):
    """
    构造http/https的请求，并返回请求结果
    :param url:
    :param header:
    :param proxies:
    :param timeout:
    :return:
    """
    try:
        return requests.get(url=url,headers=headers,proxies=proxies,timeout=timeout);
    except(requests.exceptions.ReadTimeout,requests.exceptions.SSLError,requests.exceptions.ConnectTimeout,requests.exceptions.ConnectionError):
        # print traceback.format_exc()
        return None;