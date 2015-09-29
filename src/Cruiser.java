import java.util.ArrayList;


public class Cruiser extends Starship
	{
		ArrayList<Spice> cargo;
		int speed;
		int deuterium;
		int gasMileage;
		String name;
		
		public Cruiser(String name, int s, int d, int g)
		{
			this.name = name;
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