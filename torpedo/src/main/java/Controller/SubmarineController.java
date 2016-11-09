package Controller;

import communication.CommException;
import model.gameObjects.Position;
import model.gameObjects.Submarine;
import model.gameconfig.GameInfoDataHolder;

public class SubmarineController {
	
	public static void moveSubmarine(Submarine submarine,GameInfoDataHolder gameInfo) throws CommException
	{
		//submarine.move(gameInfo.getMapConfiguration().getMaxAccelerationPerRound(), 0.3);
		double maxAcceleration = gameInfo.getMapConfiguration().getMaxAccelerationPerRound();
		
		if(isCollideWithStaticMapElement(submarine,gameInfo,maxAcceleration))
			submarine.move(-maxAcceleration, 0.0);
		else
			submarine.move(maxAcceleration, 0.0);
	}
	
	private static boolean isCollideWithStaticMapElement(Submarine submarine,GameInfoDataHolder gameInfo,double maxAcceleration)
	{
		Position stopPosition =  getStopPosition(submarine,maxAcceleration);
		if(submarine.getDataHolder().getVelocity()>0)
		{
		if(isCollideWithMapBorder(stopPosition,gameInfo))
			return true;
		}
		return false;
	}
	
	private static Position getStopPosition(Submarine submarine,double maxAcceleration)
	{
		Position stopPosition= new Position(0,0);
		double currentVelocity = submarine.getDataHolder().getVelocity();
		double angle = submarine.getDataHolder().getAngle();
		
		while(currentVelocity==0)
		{
			stopPosition = getNextRoundPosition(stopPosition,currentVelocity,angle);
			if(currentVelocity-maxAcceleration<0)
				currentVelocity=0;
			currentVelocity-=maxAcceleration;
			
		}
		
		return stopPosition;
	}
	
	private static Position getNextRoundPosition(Position current,double velocity,double angle)
	{
		Position nextPosition = new Position(current.getX()+velocity*Math.cos(angle),
											current.getY()+velocity*Math.sin(angle));
		return nextPosition;
	}
	
	private static boolean isCollideWithMapBorder(Position subMarinePosInFuture,GameInfoDataHolder gameInfo)
	{
		
		Position mapsRightTop = new Position(gameInfo.getMapConfiguration().getWidth(),gameInfo.getMapConfiguration().getHeight());
		
		if(subMarinePosInFuture.getX()<=0||subMarinePosInFuture.getY()<=0||
				subMarinePosInFuture.getX()>=mapsRightTop.getX()||subMarinePosInFuture.getY()>=mapsRightTop.getY())
		return true;
		
		return false;
	}
	
	

}
