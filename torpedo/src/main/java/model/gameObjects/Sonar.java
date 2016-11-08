package model.gameObjects;

import java.util.List;

public class Sonar {

	/**
	 * Felderíti a környező hajókat és torpedókat. A szervertől kapott választ feldolgozza: típus alapján készít a lista elemekből egy dataholder objektumot, majd a típus alapján a submarines vagy torpedos listába elmenti.
	 * 
	 * Hiba esetén előre definiált hibakóddal exception-t dob.
	 */
	public void scan() {
		// TODO - implement Sonar.scan
		throw new UnsupportedOperationException();
	}

	/**
	 * Visszaadja a környező tengeralattjárók listáját.
	 */
	public List<Entity> getNearbySubmarines() {
		// TODO - implement Sonar.getNearbySubmarines
		throw new UnsupportedOperationException();
	}

	/**
	 * Visszaadja a környéken lévő torpedók listáját.
	 */
	public List<Entity> getNearbyTorpedos() {
		// TODO - implement Sonar.getNearbyTorpedos
		throw new UnsupportedOperationException();
	}

	/**
	 * Ez a metódus megmondja, hogy a hívó tengeralattjárótól milyen szögben van a paraméterül kapott ID-jú tengeralattjáró.
	 * @param submarineID
	 */
	public double getAngleForThisShip(long submarineID) {
		// TODO - implement Sonar.getAngleForThisShip
		throw new UnsupportedOperationException();
	}

	/**
	 * Bekapcsolja az aktív szonárt, ezáltal pár kör erejéig megnövekszik a szonár hatósugara. Mellékhatása, hogy a hajónkat "az aktív szonár hatósugarával megegyező távolságról" észlelni tudja a többi hajó.
	 */
	public void activate() {
		// TODO - implement Sonar.activate
		throw new UnsupportedOperationException();
	}

}