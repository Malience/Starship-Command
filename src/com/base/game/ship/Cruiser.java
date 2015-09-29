import java.util.ArrayList;


public class Cruiser extends Starship
	{
		ArrayList<Spice> cargo;
		int speed;
		int deuterium;
		int gasMileage;
		
		public Cruiser(String n, int s, int d, int g)
		{
			super(ShipClass.Cruiser, n, 1.0f);
			speed = s;
			deuterium = d;
			gasMileage = g;
		}
		
		public int distance()
		{
			return (int) (speed * (double) deuterium / (double) gasMileage);
		}
		
		public String getName()
		{
			return name;
		}
	}