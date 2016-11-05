package ali.taobao;

import java.util.Map;
import java.util.Random;

import org.apache.commons.lang3.StringEscapeUtils;
import org.jsoup.Jsoup;
import org.jsoup.Connection.Method;
import org.jsoup.Connection.Response;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.net.SocketTimeoutException;
import java.net.ConnectException;
import com.json.JsonHelper;

/**
 * ��ȡÿ������ҳ���json�ַ���
 * @author Administrator
 *
 */
public class getCODE {

	/**
	 * @Purpose:��ȡ����Ʒ���۵�����
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public String getReviewLink(String id) throws Exception
	{
		String url = "https://item.taobao.com/item.htm?id=" + id;
		String Link = "";

		Document doc = Jsoup.connect(url)
				.ignoreContentType(true)
				.userAgent("Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.31 (KHTML, like Gecko) Chrome/26.0.1410.64 Safari/537.31")
				.timeout(20000)
				.get();

		Elements elements = doc.select("div");
		for(Element element:elements)
		{
			if(element.attr("id").equals("reviews"))
			{
				Link = "http:"+element.attr("data-listApi");
				break;
			}
		}

		return Link;
	}

	/**
	 * @Purpose:
	 * @param link
	 * @throws Exception
	 */
	public String getCode(String link,int num) throws Exception
	{
		link = link + "&Typ&currentPageNum=" + num;
		//System.err.println(link);
		String text = getJsonCode(link);
		return text;
	}

	public static String escape(String htmlcode) 
	{
		htmlcode = StringEscapeUtils.unescapeHtml4(htmlcode); //����ת��
		htmlcode = htmlcode.replaceAll("\r\n", ""); //�ַ�ת��
		return htmlcode;
	}

	/**
	 * @Purpose:��ȡjson�ַ���
	 * @param link
	 * @return
	 */
	public static String getJsonCode(String link) throws Exception
	{
		String text = "";
		try{
			Response res = Jsoup.connect(link)
					.method(Method.GET)
					.timeout(10000)
					.execute();		
			Map<String,String> cookies = null;
			cookies = res.cookies();
			
			//System.err.println(cookies.size());
			Document doc = Jsoup.connect(link)
					.ignoreContentType(true)
					.cookies(cookies)
					.userAgent("Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/42.0.2311.152 Safari/537.36")
					.timeout(20000)
					.get();


			text = escape(doc.toString()).replaceAll("<[^>]+>", "").replaceAll("[\r\n]", "").trim();
			return text;
		}
		catch(SocketTimeoutException | ConnectException e)
		{
			System.err.println("���ӳ�ʱ");
			return getJsonCode(link);
		}
	}
}
