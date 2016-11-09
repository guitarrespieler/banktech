package Controller;

import java.util.List;
import communication.CommException;
import model.gameObjects.Position;
import model.gameObjects.Submarine;
import model.gameconfig.GameInfoDataHolder;

public class SubmarineController {
	
	private static double COLLIDEOFFSET = 5;
	
	

	public static void moveSubmarine(Submarine submarine,GameInfoDataHolder gameInfo) throws CommException
	{
		//submarine.move(gameInfo.getMapConfiguration().getMaxAccelerationPerRound(), 0.3);
//		Random rand = new Random();
		double acceleration = gameInfo.getMapConfiguration().getMaxAccelerationPerRound();
//		double angle = rand.nextInt((int)gameInfo.getMapConfiguration().getMaxSteeringPerRound());
		
		if(isCollideWithStaticMapElement(submarine,gameInfo,gameInfo.getMapConfiguration().getMaxAccelerationPerRound()))
			acceleration *=-1;	
					
		submarine.move(acceleration, 2.0);
	}
	
	private static boolean isCollideWithStaticMapElement(Submarine submarine,GameInfoDataHolder gameInfo,double maxAcceleration)
	{
		if(submarine.getDataHolder().getVelocity()>0)
		{
		Position stopPosition =  getStopPosition(submarine,maxAcceleration);
				
		if(isCollideWithMapBorder(stopPosition,gameInfo))
			return true;
		if(isCollideWithIsland(stopPosition, gameInfo))
			return true;
		}
		return false;
	}
	
	private static Position getStopPosition(Submarine submarine,double maxAcceleration)
	{
		Position stopPosition= submarine.getDataHolder().getPosition();
		double currentVelocity = submarine.getDataHolder().getVelocity();
		double angle = submarine.getDataHolder().getAngle();
		
		while(currentVelocity!=0)
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
		Position nextPosition = new Position(current.getX()+COLLIDEOFFSET*velocity*Math.cos(Math.toRadians(angle)),
											current.getY()+COLLIDEOFFSET*velocity*Math.sin(Math.toRadians(angle)));
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
	
	private static boolean isCollideWithIsland(Position subMarinePosInFuture,GameInfoDataHolder gameInfo)
	{
		double islandRadius = gameInfo.getMapConfiguration().getIslandSize();
		List<Position> positions = gameInfo.getMapConfiguration().getPositions();
		for (Position position : positions) {
			if(isCollideWithThatIsland(subMarinePosInFuture,position,islandRadius))
				return true;
		}
		return false;
		
	}
	
	private static boolean isCollideWithThatIsland(Position stopPosition,Position islandPosition, double radius)
	{
		if(Math.pow(stopPosition.getX()-islandPosition.getX(),2)+Math.pow(stopPosition.getY()-islandPosition.getY(),2)<=Math.pow(radius, 2))
			return true;
		return false;
	}

}
