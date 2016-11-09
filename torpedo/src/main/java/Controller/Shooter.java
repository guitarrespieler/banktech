package Controller;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.http.entity.EntityTemplate;

import model.gameObjects.Position;
import model.gameObjects.Submarine;
import model.gameObjects.entities.EntityDataHolder;
import model.gameObjects.entities.EntityType;
import model.gameconfig.GameInfoDataHolder;
import model.gameconfig.MapConfigDataHolder;

/**
 * Lövési logikáért felelős osztály
 * @author tibor
 *
 */
public class Shooter {
	
	public static double COLLISION_RADIUS = 100.0;
	
	/**
	 * Kulcs: submarine ID
	 * Érték: hátralévő cooldown idő
	 */
	public static Map<Integer, Integer> torpedoCooldown = new HashMap<Integer, Integer>();
	
//	/**
//	 * Megmondja, hogy szabad-e lőni.
//	 * @param nearbyEntities a közelben lévő érzékelt entity-k
//	 * @return
//	 */
//	public static boolean isShootEnabled(Submarine controlledUnit, List<EntityDataHolder> nearbyEntities,MapConfigDataHolder mapData){
//		COLLISION_RADIUS += mapData.getSubmarineSize();
//		
//		List<Position> islands = mapData.getPositions();
//		
//		return false;
//		
//	}

	/**
	 * Megmondja, hogy merre lőjön a hajó.
	 * @return irány fokban megadva
	 */
	public static double angleToShoot(Submarine controlledUnit, Map<EntityType, List<EntityDataHolder>> nearbyEntities){
		double angle = controlledUnit.getDataHolder().getAngle();
		
		//ha nincs a közelben ellenfél 
		if(nearbyEntities.isEmpty()){			
			return angle;
		}
		EntityDataHolder enemySubmarine = null;
		
		//keresünk egy célpontot
		if(nearbyEntities.containsKey(EntityType.Submarine))
			if(!nearbyEntities.get(EntityType.Submarine).isEmpty())
				enemySubmarine = nearbyEntities.get(EntityType.Submarine).get(0);
		
		//ha a közelben nem ellenfél hajó, hanem torpedó van
		if(enemySubmarine == null)
			return angle;
		//ha a közelben van egy ellenséges hajó
		return calculateAngle(controlledUnit, enemySubmarine);
	}

	private static double calculateAngle(Submarine controlledUnit, EntityDataHolder enemy) {
		Position subPos = controlledUnit.getDataHolder().getPosition();
		Position enemyPos = enemy.getPosition();
		
	    double angle = Math.toDegrees(Math.atan2(enemyPos.getY() - subPos.getY(), enemyPos.getX() - subPos.getX()));

	    if(angle < 0){
	        angle += 360;
	    }

		return angle;
	}
}
