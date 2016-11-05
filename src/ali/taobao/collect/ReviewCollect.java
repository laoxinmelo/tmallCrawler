package ali.taobao.collect;

import ali.taobao.getCODE;
import ali.taobao.getOutput;
import ali.taobao.object.review.*;

import java.util.ArrayList;
import java.util.Random;
import java.io.BufferedWriter;
import java.io.FileWriter;

import org.jsoup.Jsoup;

public class ReviewCollect {
	public static void main(String[] args) throws Exception
	{		
		getCODE getcode = new getCODE();
		getOutput getoutput = new getOutput();

		String productID = "522572063558";
		String link = getcode.getReviewLink(productID);
		int num = 1;

		AppendObject ao = new AppendObject();
		CommentObject co = new CommentObject();
		ReplyObject ro = new ReplyObject();

		BufferedWriter cbw = new BufferedWriter(new FileWriter("comment/" + productID + ".txt"));
		BufferedWriter abw = new BufferedWriter(new FileWriter("append/" + productID + ".txt"));
		BufferedWriter rbw = new BufferedWriter(new FileWriter("reply/" + productID + ".txt"));

		ArrayList<String> cList = new ArrayList<String>();
		ArrayList<String> rList = new ArrayList<String>();
		ArrayList<String> aList = new ArrayList<String>();

		cbw.write(co.variableName() + "\r\n");
		abw.write(ao.variableName() + "\r\n");
		rbw.write(ro.variableName() + "\r\n");

		while(true)
		{
			String text = getcode.getCode(link, num);
			if(text.startsWith("(")&&text.endsWith(")"))
				text = text.substring(text.indexOf("(")+1, text.lastIndexOf(")"));
			System.out.println(text);
			if(text.contains("\"comments\":null,"))
				break;	
			else if(text.contains("https://sec.taobao.com/query.htm?")||text.startsWith("亲，访问受限了"))
			{
				System.err.println("网页已经被封，现休息20分钟");
				Thread.sleep(20*60*1000);
			}

			ArrayList<ReviewObject> reviewList = getoutput.getReviewList(text,productID);

			for(int i=0;i<reviewList.size();i++)
			{
				ReviewObject review = reviewList.get(i);
				if(review.commentBelong())
				{
					String temp = review.getComment();
					if(!cList.contains(temp))
					{
						System.out.println(num + "	" + temp);
						cbw.write(temp + "\r\n");
						//count ++;
						cList.add(temp);
					}
				}
				if(review.replyBelong())
				{
					String temp = review.getReply();
					if(!rList.contains(temp))
					{
						System.err.println(temp);
						rbw.write(temp + "\r\n");
						rList.add(temp);
					}
				}
				if(review.appendBelong())
				{
					ArrayList<String> appendList = review.getAppends();
					for(int j=0;j<appendList.size();j++)
					{
						String temp = appendList.get(j);
						if(!aList.contains(temp))
						{
							System.err.println(temp);
							abw.write(temp + "\r\n");
							aList.add(temp);
						}
					}
				}
			}
			
			Random random = new Random();
			int time = random.nextInt(10);
			while(time<5)
				time = random.nextInt(10);
			
			System.err.println("______第" + num + "页评论抓取完成____现停止" + time + "秒");
			Thread.sleep(time*1000);

			num++;
		}
		cbw.flush(); cbw.close();
		rbw.flush(); rbw.close();
		abw.flush(); abw.close();
	}
}
