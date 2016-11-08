package communication;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;


/**
 * A szerverrel való kommunikálásért felelős osztály.
 */
public class Communication {

	/**
	 * Ezen az URL címen érjük el a szervert. Ehhez fűzzük hozzá az URL TAG-et a post és get metódusokban.
	 */
	private static String mainURL = "http://195.228.45.100:8080/jc16-srv/";
	/**
	 * Ezt a tokent kell minden kéréskor a header-ben megadni.
	 */
	private static String TEAM_TOKEN = "33A0AE6A32DA0DE431035A9D3521C7AF";

	enum httptype{
		POST,GET
	}
	/**
	 * A paraméterül kapott TAG-et hozzáfűzi az URL címhez és erre a címre posztol. Visszatérési értékként adja a szerver válaszát.
	 * @param url_tag Ezt a TAG-et fűzi hozzá az URL címhez.
	 * @throws IOException 
	 */
	public static String post(String urlTag) {
		return apicall(urlTag,httptype.POST);
	    
	}
	/**
	 * Get kérést intéz a szerver felé. A paraméterül kapott URL TAG-et hozzáfűzi az url-hez. Visszatérési értékként megadja a szerver válaszát.
	 * @param urlTag
	 */
	public static String get(String urlTag) {
		return apicall(urlTag,httptype.GET);
	}

	private static String apicall(String urlTag, httptype type) {
		BufferedReader rd = null;
		StringBuilder strbuilder = null;
		try {
			String url = mainURL + urlTag;
			
			HttpClient httpclient = HttpClients.createDefault();
			HttpResponse response = null;
			if(httptype.POST.equals(type)){
				response = initpost(url, httpclient);
			}else{
				response = initget(url, httpclient);
			}
			HttpEntity entity = response.getEntity();

			InputStream is = entity.getContent();
			rd = new BufferedReader(new InputStreamReader(is));
			strbuilder = new StringBuilder(); // or StringBuffer if Java version
												// 5+
			String line;
			while ((line = rd.readLine()) != null) {
				strbuilder.append(line);
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				rd.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return strbuilder.toString();
	}
	private static HttpResponse initget(String url, HttpClient httpclient) throws IOException, ClientProtocolException {
		HttpResponse response;
		HttpGet httpget = new HttpGet(url);
		httpget.addHeader("TEAMTOKEN", TEAM_TOKEN);
		response = httpclient.execute(httpget);
		return response;
	}
	private static HttpResponse initpost(String url, HttpClient httpclient)
			throws IOException, ClientProtocolException {
		HttpResponse response;
		HttpPost httppost = new HttpPost(url);
		httppost.addHeader("TEAMTOKEN", TEAM_TOKEN);
		response = httpclient.execute(httppost);
		return response;
	}
}