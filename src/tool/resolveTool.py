#coding=utf-8

def jsonResolve(dbTool,jsonList,itemId):
    """
    对json进行解析并入库
    :param dbTool:
    :param jsonList:
    :return:
    """

    if len(jsonList) == 0:
        return ;

    for js in jsonList:
        rateList = js["rateDetail"]["rateList"]

        if len(rateList) == 0:
            continue

        #解析消费者的初始评论
        userName = js["displayUserNick"]  #评论者名称
        userTmallLevel = js["tamllSweetLevel"]  #评论者天猫商城等级
        userVipLevel = js["userVipLevel"]   #评论者vip等级

        picNum = len(js["pics"])   #评论中图片数量
        content = js["rateContent"]   #评论内容
        date = js["rateDate"]  #评论日期
        reviewId = js["id"]   #评论ID

        #
