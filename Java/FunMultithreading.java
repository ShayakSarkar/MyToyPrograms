import java.lang.Thread;
import java.io.*;
class Lock
{	Thread lock;
	String name;
	public Lock(String s)
	{	lock=null;
		name=s;
	}
	public String getName()
	{	return name;
	}	
	public void grabLock()
	{	while(lock!=Thread.currentThread())
		{	synchronized(this)
			{	if(lock==null)
				{	lock=Thread.currentThread();
					System.out.println("\t\t"+Thread.currentThread().getName()+" "+name+" lock grabbed");
					return;
				}

			}
			if(lock!=Thread.currentThread())
			{	synchronized(this)
				{	try
					{	this.wait();
					}
					catch(Exception e)
					{	System.out.println(Thread.currentThread().getName()+" Interrupted");
						e.printStackTrace();
						return;
					}
				}
			}
		}
	}
	public synchronized void freeLock()
	{	lock=null;
		this.notifyAll();
		System.out.println("\t\t"+Thread.currentThread().getName()+" "+name+" Lock freed");
	}
	public synchronized Thread getOwner()
	{	return lock;
	}
	public synchronized boolean isFree()
	{	if(lock==null)
			return true;
		else
			return false;
	}			
};
public class FunMultithreading
{			

	static int ctr=0;	
	public static void main(String args[])throws IOException
	{	BufferedReader in=new BufferedReader(new InputStreamReader(System.in));
		Lock runnerLock=new Lock("RunnerLock");	
		Lock gfLock=new Lock("GfLock");
		
		class Girlfriend extends Thread
		{	public int id;
			public boolean isBad;
			public Girlfriend g[];
			Girlfriend(String s,int i,boolean b,Girlfriend arr[])
			{	this.setName(s);
				id=i;
				isBad=b;
				g=arr;
			}
			public void run()
			{	while(gfLock.getOwner()!=Thread.currentThread())
				{	if(ctr==id && id==0 && isBad)
						gfLock.grabLock();
					try
					{	if(ctr==id && isBad && (g[id-1].isBad||!g[id-1].isAlive()))
							gfLock.grabLock();
					}
					catch(Exception e)
					{}
					if(ctr-1==id && !isBad)
						gfLock.grabLock();
				}
				try
				{	System.out.println(this.getName()+" is calling");
					this.sleep(800);	
					System.out.println(this.getName()+" is calling");
					this.sleep(800);
					System.out.println(this.getName()+" is calling");
					this.sleep(800);
					System.out.println(this.getName()+" is calling");
					this.sleep(800);
				}
				catch(Exception e)
				{	e.printStackTrace();
				}
				
				gfLock.freeLock();
				runnerLock.freeLock();
				
			}
		};
		class Runner extends Thread
		{	int id;
			Runner r[];
			Girlfriend g[];
			Runner(String s,int i,Runner arr[],Girlfriend arr2[])
			{	this.setName(s);
				id=i;
				r=arr;
				g=arr2;
			}
			public void run()
			{	while(runnerLock.getOwner()!=Thread.currentThread())
				{	if(id==ctr)
						runnerLock.grabLock();
				}		
				if(gfLock.isFree() && !g[id].isBad)
					gfLock.grabLock();
				System.out.println(this.getName()+" is Running");
				int i;
				for(i=1;i<=4;i++)
				{	System.out.println((i*100)/4+" % completed");
					try
					{	this.sleep(800);
					}
					catch(Exception e)
					{	e.printStackTrace();
					}
				}
				gfLock.freeLock();
				ctr++;
			}
		};
		Runner r[]=new Runner[4];
		Girlfriend g[]=new Girlfriend[4];
		r[0]=new Runner("Runner 1",0,r,g);r[1]=new Runner("Runner 2",1,r,g);r[2]=new Runner("Runner 3",2,r,g);r[3]=new Runner("Runner 4",3,r,g);
		boolean b[]=new boolean[4];
		int i;
		
		
		System.out.println("This is a supreme example of concurrent programming:\n\nHere we try to simulate a 4 X 100 m relay race\n\nThere are 4 runners all of which are fully alive and capable of running their 100m\n\nsince they are on separater threads.\n\nThey communicate with each other in real time to find out which of the runners should run next.\n\nIn order to make things a bit more complicated, we introduce girlfriends all these three runners\n\nNow it is the users choice to decide if the girlfriend of these runners is a good girlfriend or a bad girlfriend\n\nA good girlfriend will not call the runner when he is busy running whereas a bad girlfriend will call the runner only when he is running\n\n\n\n");
		for(i=0;i<4;i++)
		{	System.out.println("Girlfriend "+(i+1)+": (0 for bad 1 for good): ");
			int ch=Integer.parseInt(in.readLine());
			if(ch==1)
				b[i]=false;
			else
				b[i]=true;
		}
						
		g[0]=new Girlfriend("girlfriend 1",0,b[0],g);g[1]=new Girlfriend("girlfriend 2",1,b[1],g);g[2]=new Girlfriend("girlfriend 3",2,b[2],g);g[3]=new Girlfriend("girlfriend 4",3,b[3],g);
		
		g[0].start();g[1].start();g[2].start();g[3].start();
		r[0].start();r[1].start();r[2].start();r[3].start();
		/*
		try
		{	Thread.currentThread().sleep(200);
		}
		catch(Exception e)
		{	e.printStackTrace();
		}
		*/
		
	}
}	
		
			
