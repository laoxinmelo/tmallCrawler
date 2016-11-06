#coding=utf-8

def jsonResolve(dbTool,jsonObject,itemId):
    """
    对json进行解析并入库
    :param dbTool:
    :param jsonList:
    :return:
    """

    rateList = jsonObject["rateDetail"]["rateList"]

    if len(rateList) == 0:
         return ;

    commentSql = "insert into review values";
    appendSql = "insert into appendreview values";
    replySql = "insert into reply values";

    for js in rateList:
        #解析消费者的初始评论
        userName = js["displayUserNick"]  #评论者名称
        userTmallLevel = js["tamllSweetLevel"]  #评论者天猫商城等级
        userVipLevel = js["userVipLevel"]   #评论者vip等级

        picNum = len(js["pics"])   #评论中图片数量
        content = js["rateContent"]   #评论内容
        content = content.replace("<b>","").replace("</b>","")
        date = js["rateDate"]  #评论日期
        reviewId = js["id"]   #评论ID

        commentValue = "(\"%s\",\"%s\",\"%s\",%d,%d,\"%s\",\"%s\",%d);" % (itemId,reviewId,userName,userTmallLevel,userVipLevel,content,date,picNum);
        dbTool.insert(commentSql + commentValue)

        #解析消费者所受到的商家反馈
        reply = js["reply"].replace("<b>","").replace("</b>","")
        if reply != "":
            replyValue = "(\"%s\",\"%s\",\"%s\");" % (itemId,reviewId,reply);
            dbTool.insert(replySql + replyValue)

        #解析消费者的追加评论
        append = js["appendComment"]
        if append != "":
            appendDate = append["commentTime"]
            appendContent = append["content"].replace("<b>","").replace("</b>","")
            appendPicNum = len(append["pics"])
            appendDays = append["days"]

            appendValue = "(\"%s\",\"%s\",\"%s\",\"%s\",%d,%d);" % (itemId,reviewId,appendContent,appendDate,appendDays,appendPicNum)
            dbTool.insert(appendSql + appendValue)

            appendReply = append["reply"]
            if appendReply != "":
                replyValue = "(\"%s\",\"%s\",\"%s\");" % (itemId,reviewId,appendReply);
                dbTool.insert(replySql + replyValue)