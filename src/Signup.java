import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;


public class Signup
{
	static HashMap<String, Integer> organization;
	static HashMap<String, String> joined;
	static ArrayList<String> orgs;
	static ArrayList<String> dupes;
	
	public static void main(String [] args) throws FileNotFoundException
	{
		Scanner k = new Scanner(new File("./in.txt"));
		
		organization = new HashMap<String, Integer>();
		joined = new HashMap<String, String>();
		orgs = new ArrayList<String>();
		dupes = new ArrayList<String>();
		
		String current = null;
		
		while(k.hasNext())
		{
			String s = k.nextLine();
			if(!Character.isLowerCase(s.charAt(0)))
			{
				current = s;
				organization.put(current, new Integer(0));
				orgs.add(s);
			}
			else if(checkDupe(s))
			{
				dupes.add(s);
				joined.put(s, current);
				Integer i = organization.get(current);
				organization.replace(current, i + 1);
			}
			else
			{
				if(!joined.get(s).equals(current))
				{
					organization.replace(joined.get(s), organization.get(joined.get(s)) - 1);
					joined.remove(s);
				}
			}
		}
		
		sortOrgs();
		
		for(int i = 0; i < organization.size(); i++)
		{
			System.out.println(orgs.get(i) + " " + organization.get(orgs.get(i)));
		}
	}
	
	public static void sortOrgs()
	{
		
		for(int i = 0; i < orgs.size() - 1; i++)
		{
			for(int j = i + 1; j < orgs.size(); j++)
			{
				if(organization.get(orgs.get(i)) < organization.get(orgs.get(j)))
				{
					String swap = orgs.get(i);
					orgs.set(i, orgs.get(j));
					orgs.set(j, swap);
				}
			}
		}
		
		for(int i = 0; i < orgs.size() - 1; i++)
		{
			for(int j = i + 1; j < orgs.size(); j++)
			{
				if(organization.get(orgs.get(i)) == organization.get(orgs.get(j)))
					if(orgs.get(i).compareTo(orgs.get(j)) > 0)
					{
						String swap = orgs.get(i);
						orgs.set(i, orgs.get(j));
						orgs.set(j, swap);
					}
			}
		}
	}
	
	public static String checkOrgs(String s)
	{
		for(String t : orgs)
			if(s.equals(t))
				return t;
		return null;
		
	}
	
	public static boolean checkDupe(String s)
	{
		for(String t : dupes)
			if(s.equals(t))
				return false;
		return true;
		
	}
}
