package com.base.game.ship;

public abstract class Starship 
{
	ShipClass shipClass;
	String name;
	float dockingSpeed;
	float dockingMult;
	
	public Starship(ShipClass sc, String n, float ds)
	{
		name = n;
		shipClass = sc;
		dockingMult = sc.getSize();
		dockingSpeed = ds;
	}
	
	public float dockSpeed()
	{
		return dockingSpeed * dockingMult;
	}
	
	protected enum ShipClass
	{
		Fighter			(1),
		Frigate 		(2),
		Destroyer		(2),
		Cruiser 		(3),
		BattleCruiser 	(3),
		BattleShip		(5),
		CapitalShip		(10);
		
		private float size;
		
		ShipClass(float size)
		{
			this.size = size;
		}
		
		public float getSize()
		{
			return size;
		}
	}
}
