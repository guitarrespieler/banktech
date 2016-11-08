package model.gameObjects;
public class Submarine {

	/**
	 * A tengeralattjárót mozgathatjuk vele. Paraméterül megadhatjuk neki az elfordulás szögét és a gyorsítás / lassítás mértékét ( tehát nem a kívánt sebességre állítjuk, hanem növeljük vagy csökkentjük azt!)
	 * @param speed
	 * @param turnAngle
	 */
	public void move(double speed, double turnAngle) {
		// TODO - implement Submarine.move
		throw new UnsupportedOperationException();
	}

	/**
	 * A megadott irány felé indít egy torpedót. A megadott irány fok-ban értendő, ahol a:  
	 * Keleti irány a 0 fok, Északi 90, Nyugati 180, Déli 270.  
	 *     
	 * Irányt, szám érték megadásával állíthatunk:  
	 * angle: 90.0 (Körök végi kiértékeléskor, a megadott irányba torpedót indít)  
	 * @param shootAngle
	 */
	public void shoot(double shootAngle) {
		// TODO - implement Submarine.shoot
		throw new UnsupportedOperationException();
	}

	public void usePassiveSonar() {
		// TODO - implement Submarine.usePassiveSonar
		throw new UnsupportedOperationException();
	}

	public void activateSonar() {
		// TODO - implement Submarine.activateSonar
		throw new UnsupportedOperationException();
	}

}