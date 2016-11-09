package Controller;

import communication.CommException;
import model.gameObjects.Position;
import model.gameObjects.Submarine;
import model.gameconfig.GameInfoDataHolder;

public class SubmarineController {
	
	public static void moveSubmarine(Submarine submarine,GameInfoDataHolder gameInfo) throws CommException
	{
		submarine.move(gameInfo.getMapConfiguration().getMaxAccelerationPerRound(), 0.3);
		isCollideWithStaticMapElement(submarine,gameInfo);
	}
	
	private static boolean isCollideWithStaticMapElement(Submarine submarine,GameInfoDataHolder gameInfo)
	{
		if(isCollideWithMapBorder(submarine.getDataHolder().getPosition(),gameInfo))
			return true;
		
		return false;
	}
	
	private static boolean isCollideWithMapBorder(Position subMarinePosInFuture,GameInfoDataHolder gameInfo)
	{
		
		Position mapsRightTop = new Position(gameInfo.getMapConfiguration().getWidth(),gameInfo.getMapConfiguration().getHeight());
		
		if(subMarinePosInFuture.getX()<=0||subMarinePosInFuture.getY()<=0||
				subMarinePosInFuture.getX()>=mapsRightTop.getX()||subMarinePosInFuture.getY()>=mapsRightTop.getY())
		return true;
		
		return false;
	}
	
//	private static Position getStopPosition(Submarine submarine)
//	{
//		
//	}
	
//	private static Position getNextRoundPosition(Position current,double velocity,double angle)
//	{
//		Position nextPosition = new Position(current.getX().)
//	}

}
