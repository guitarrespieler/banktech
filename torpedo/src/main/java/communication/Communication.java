package communication;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;


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

	/**
	 * A paraméterül kapott TAG-et hozzáfűzi az URL címhez és erre a címre posztol. Visszatérési értékként adja a szerver válaszát.
	 * @param url_tag Ezt a TAG-et fűzi hozzá az URL címhez.
	 * @throws IOException 
	 */
	public static String post(String urlTag) throws IOException {
		String url = mainURL+urlTag;
		URL targetURL = new URL(url);
		HttpURLConnection connection = null;
		connection = (HttpURLConnection) targetURL.openConnection();
		connection.setRequestMethod("POST");
		connection.setRequestProperty("TEAMTOKEN", TEAM_TOKEN);
		connection.setUseCaches(false);
	    connection.setDoOutput(true);

	    //Send request
	    //try (OutputStream output = connection.getOutputStream()) {
	    //    output.write(query.getBytes(charset));
	   // }

	    InputStream is = connection.getInputStream();
	    BufferedReader rd = new BufferedReader(new InputStreamReader(is));
	    StringBuilder response = new StringBuilder(); // or StringBuffer if Java version 5+
	    String line;
	    while ((line = rd.readLine()) != null) {
	      response.append(line);
	      response.append('\r');
	    }
	    rd.close();
	    return response.toString();
	}

	/**
	 * Get kérést intéz a szerver felé. A paraméterül kapott URL TAG-et hozzáfűzi az url-hez. Visszatérési értékként megadja a szerver válaszát.
	 * @param urlTag
	 */
	public static String get(String urlTag) {
		String query = mainURL+urlTag;
		// TODO - implement Communication.get
		throw new UnsupportedOperationException();
	}

}