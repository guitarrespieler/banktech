package communication;
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
	 */
	public static String post(String url_tag) {
		// TODO - implement Communication.post
		throw new UnsupportedOperationException();
	}

	/**
	 * Get kérést intéz a szerver felé. A paraméterül kapott URL TAG-et hozzáfűzi az url-hez. Visszatérési értékként megadja a szerver válaszát.
	 * @param urlTag
	 */
	public static String get(String urlTag) {
		// TODO - implement Communication.get
		throw new UnsupportedOperationException();
	}

}