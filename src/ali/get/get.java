package ali.get;

import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.util.Random;

import org.jsoup.HttpStatusException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class get {

	static int connectNum = 0;

	/**
	 * @Purpose: To ali.get the webpage document
	 * @param url
	 * @return
	 * @throws Exception
	 */
	public static Document getDoc(String url) throws Exception	{
		try{

			Document doc = Jsoup.connect(url)
					.ignoreContentType(true)
					.userAgent("Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.31 (KHTML, like Gecko) Chrome/26.0.1410.64 Safari/537.31")
					.timeout(30000)
					.get();

			connectNum = 0;
			return doc;

		}catch(SocketTimeoutException |javax.net.ssl.SSLException |java.net.ConnectException e)
		{
			connectNum++;

			if(connectNum <10)
			{
				System.err.println(url+"Connection timeout! Reconnecting again!");
				Document doc = getDoc(url);
				return doc;
			}
			else 
			{
				System.err.println("The number of Reconnection is over 10 times! Please ensure your network connection condition before further connection!");
				Document doc = null;
				return doc;
			}
		}catch(HttpStatusException e)
		{
			String temp = e.toString();
			System.err.println(url);
			if(temp.contains("Status=503"))
			{
				System.err.println("Because of the exception, we have to take a break for 40 minutes...");
				Thread.sleep(40*60*1000);
			}

			Document doc = getDoc(url);
			return doc;
		}
	}
}
