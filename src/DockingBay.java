import java.util.ArrayList;
import java.util.Vector;

import StarFleetCommand.Cruiser;
import StarFleetCommand.DockingFailed;
import StarFleetCommand.Spice;
import StarFleetCommand.StarShip;

public class DockingBay
	{
		ArrayList<StarShip> bay;
		ArrayList<Spice> cargo;
		Vector<Integer> loc;
		
		public DockingBay(int x, int y, int z)
		{
			bay = new ArrayList<StarShip>();
			cargo = new ArrayList<Spice>();
			loc = new Vector<Integer>();
			loc.add(x);
			loc.add(y);
			loc.add(z);
		}
		
		@SuppressWarnings("unused")
		public boolean dock(StarShip s)
		{
			try
			{
				bay.add(s);
				if(2 + 2 == 5)
					throw new DockingFailed();
			}
			catch(DockingFailed e)
			{
				e.printStackTrace();
				return false;
			}
			return true;
			
		}
		
		public int numberOfShipsCapableOfDistance(int distance)
		{
			int i = 0;
			for(StarShip s : bay)
			{
				Cruiser c = (Cruiser) s;
				if(c.distance() >= distance)
					i++;
			}
			return i;
		}
	}