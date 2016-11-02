package ali.taobao.object.review;

import java.util.ArrayList;

public class ReviewObject {
     ArrayList<AppendObject> appendList;
     CommentObject comment;
     ReplyObject reply;
     
     public void setAppendObject(ArrayList<AppendObject> appendList) {
    	 this.appendList = appendList;
     }
     
     public void setCommentObject(CommentObject comment) {
    	 this.comment = comment;
     }
     
     public void setReplyObject(ReplyObject reply) {
    		 this.reply = reply;
     }
     
     public boolean appendBelong() {
    	 return appendList.size()!=0;
     }
     
     public boolean commentBelong() {
    	 return this.comment.belong();
     }
     
     public boolean replyBelong() {
    	 return this.reply.belong();
     }
     
     /**
      * @Purpose:返回追加评论
      * @return
      */
     public ArrayList<String> getAppends() {
    	 ArrayList<String> strList = new ArrayList<String>();
    	 for(int i=0;i<this.appendList.size();i++)
    		 strList.add(this.appendList.get(i).toString());
    	 return strList;
     }
     
     /**
      * @Purpose:返回评论
      * @return
      */
     public String getComment() {
    	 return this.comment.toString();
     }
     
     /**
      * @Purpose:返回商家回复
      * @return
      */
     public String getReply() {
    	 return this.reply.toString();
     }
}
