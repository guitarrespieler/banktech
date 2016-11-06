package communication;
/**
 * Új saját játék létrehozásáért felelős osztály.
 * Létrehoz egy új játékot a csapat számára. A létrehozott játék azonosítója a válasz "id" mezőjében található.  
 * Egyszerre egy csapat csak egy játékot hozhat létre. Ha már létezik játék akkor annak az azonosítóját adja vissza
 */
public class OwnGameCreator {

	/**
	 * Ebben az attribútumban mentjük el a létrehozott játék ID-ját.
	 */
	private long ID = 0;
	/**
	 * Ezen a címen érjük el a szerver oldalt.
	 */
	private static String URL_TAG = "game";

	public long getID() {
		return this.ID;
	}

	public void setID(long ID) {
		this.ID = ID;
	}

	/**
	 * Saját játékot hoz létre. Az új játék ID-ját elmenti.
	 */
	public void createOwnGame() {
		// TODO - implement OwnGameCreator.createOwnGame
		throw new UnsupportedOperationException();
	}

}