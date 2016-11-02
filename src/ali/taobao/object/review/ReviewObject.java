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
      * @Purpose:����׷������
      * @return
      */
     public ArrayList<String> getAppends() {
    	 ArrayList<String> strList = new ArrayList<String>();
    	 for(int i=0;i<this.appendList.size();i++)
    		 strList.add(this.appendList.get(i).toString());
    	 return strList;
     }
     
     /**
      * @Purpose:��������
      * @return
      */
     public String getComment() {
    	 return this.comment.toString();
     }
     
     /**
      * @Purpose:�����̼һظ�
      * @return
      */
     public String getReply() {
    	 return this.reply.toString();
     }
}
