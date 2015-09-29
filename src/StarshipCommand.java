import java.util.ArrayList;
import java.util.Vector;


public class StarshipCommand 
{
	public static void main(String [] args)
	{
		DockingBay mainBay = new DockingBay(1,1,1);
		mainBay.dock(new Cruiser("Highwind", 52, 75, 10));
		mainBay.dock(new Cruiser("Enterprise",88, 13, 44));
		mainBay.dock(new Cruiser("Millenium Falcon", 56, 9, 5));
		
		System.out.println(mainBay.numberOfShipsCapableOfDistance(100));
		
		DockingBay subBay = new DockingBay(1,1,1);
		mainBay.dock(new Cruiser("The BBC", 950, 950, 1));
		mainBay.dock(new Cruiser("The Calculator",943, 976, 1));
		System.out.println(mainBay.numberOfShipsCapableOfDistance(920368));
	}
}
