import java.util.ArrayList;
import java.util.Vector;

public class DockingBay
	{
		ArrayList<Starship> bay;
		ArrayList<Spice> cargo;
		Vector<Integer> loc;
		DockingQueue dockingQueue;
		float dockingSpeed;
		float dockingMultiplier;
		
		public DockingBay(int x, int y, int z)
		{
			this(x,y,z,1.0f);
		}
		
		public DockingBay(int x, int y, int z, float dmult)
		{
			bay = new ArrayList<Starship>();
			cargo = new ArrayList<Spice>();
			loc = new Vector<Integer>();
			loc.add(x);
			loc.add(y);
			loc.add(z);
			dockingQueue = new DockingQueue();
			dockingMultiplier = dmult;
		}
		
		public void requestToDock(Starship s)
		{
			dockingQueue.enqueue(s);
		}
		
		@SuppressWarnings("unused")
		public boolean dock(Starship s)
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
			catch(NullPointerException e)
			{
				return false;
			}
			return true;
		}
		
		public boolean dockNext()
		{
			return dock(dockingQueue.dequeue());
		}
		
		public int numberOfShipsCapableOfDistance(int distance)
		{
			int i = 0;
			for(Starship s : bay)
			{
				Cruiser c = (Cruiser) s;
				if(c.distance() >= distance)
					i++;
			}
			return i;
		}
		
		public void update(float delta)
		{
			dockingQueue.update(delta);
			while(dockingQueue.nextReady())
				dock(dockingQueue.dequeue());
		}
		
		private class DockingQueue
		{
			private Request first;
			private Request last;
			
			public DockingQueue()
			{
				first = null;
				last = null;
			}
			
			public void enqueue(Starship s)
			{
				enqueue(s, 0.0f, 1.0f);
			}
			
			public void enqueue(Starship s, float baySpeed, float bayMult)
			{
				Request r = new Request(s, (s.dockingSpeed + baySpeed) * bayMult);
				if(last != null)
					last.setNext(r);
				last = r;
				if(first == null)
					first = r;
			}
			
			public Starship dequeue()
			{
				if(first == null)
					return null;
				Request r = first;
				first = r.getNext();
				return r.getShip();
			}
			
			public boolean nextReady()
			{
				return first.getDockingTime() <= 0;
			}
			
			public void update(float delta)
			{
				Request current = first;
				while(current != null)
				{
					current.update(delta);
					if(current.getDockingTime() >= 0)
						break;
					delta += current.getDockingTime();
					current = current.getNext();
				}
			}
			
			private class Request
			{
				private Starship ship;
				private Request next;
				private float dockTime;
				
				public Request(Starship s, float time)
				{
					ship = s;
					next = null;
					dockTime = time;
				}
				
				public Starship getShip()
				{
					return ship;
				}
				
				public void setNext(Request r)
				{
					next = r;
				}
				
				public Request getNext()
				{
					return next;
				}
				
				public float getDockingTime()
				{
					return dockTime;
				}
				
				public void update(float delta)
				{
					dockTime -= delta;
				}
			}
		}
	}