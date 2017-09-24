/*********** JOB SEQUENCING WITH DEADLINES  by:" ADITYA MANDHARE "*****************/
// PROGRAM:
import java.util.*;
class job
{
	int p;    //.............for profit of a job
	int d;    //.............for deadline of a job
	int v;    //.............for checking if that job has been selected
	/*************default constructor**************/
	job()
	{
		p=0;
		d=0;
		v=0;
	}
	job(int x,int y,int z) // parameterised constructor
	{
		p=x;
		d=y;
		v=z;
	}
}
class js
{
	static int n;
	static int out(job jb[],int x)
	{
		for(int i=0;i<n;++i)
			if(jb[i].p==x)
				return i;
		return 0;
	}
	public static void main(String args[])
	{
		Scanner scr=new Scanner(System.in);
		System.out.println("Enter the number of jobs");
		n=scr.nextInt();
		int max=0;   // this is to find the maximum deadline
		job jb[]=new job[n];
		/***********************Accepting job from user*************************/
		for(int i=0;i<n;++i)
		{
			System.out.println("Enter profit and deadline(p d)");
			int p=scr.nextInt();
			int d=scr.nextInt();
			if(max<d)
				max=d;     // assign maximum value of deadline to "max" variable
			jb[i]=new job(p,d,0);  //zero as third parameter to mark that initially it is unvisited
		}
		//accepted jobs from user

		/*************************Sorting in increasing orser of deadlines*************************/
		for(int i=0;i<=n-2;++i)
		{
			for(int j=i;j<=n-1;++j)
			{
				if(jb[i].d>jb[j].d)
				{
					job temp=jb[i];
					jb[i]=jb[j];
					jb[j]=temp;
				}
			}
		}
		// sorting process ends

		/******************************Displaying the jobs to the user***********************/
		System.out.println("The jobs are as follows ");
		for(int i=0;i<n;++i)
			System.out.println("Job "+i+" Profit = "+jb[i].p+" Deadline = "+jb[i].d);
		// jobs displayed to the user

		int count;
		int hold[]=new int[max];
		for(int i=0;i<max;++i)
			hold[i]=0;
		/*****************************Process of job sequencing begins*************************/
		for(int i=0;i<n;++i)
		{
			count=0;
			for(int j=0;j<n;++j)
			{

				if(count<jb[j].d && jb[j].v==0 && count<max && jb[j].p>hold[count])
				{
					int ch=0;

					if(hold[count]!=0)
					{
						ch=out(jb,hold[count]);
						jb[ch].v=0;
					}

					hold[count]=jb[j].p;
					jb[j].v=1;
					++count;

				} // end of if

			} //end of inner for
		}// end of outer for
		/******************************job sequencing process ends******************************/

		/******************************calculating max profit**********************************/
		int profit=0;
		for(int i=0;i<max;++i)
			profit+=hold[i];
		System.out.println("The maximum profit is "+profit);

	}//end main method
}//end class

/****************SAMPLE OUTPUT *********************/
/*
   Enter the number of jobs
   4
   Enter profit and deadline(p d)
   70 2
   Enter profit and deadline(p d)
   12 1
   Enter profit and deadline(p d)
   18 2
   Enter profit and deadline(p d)
   35 1
   The jobs are as follows
   Job 0 Profit = 12 Deadline = 1
   Job 1 Profit = 35 Deadline = 1
   Job 2 Profit = 18 Deadline = 2
   Job 3 Profit = 70 Deadline = 2

   The maximum profit is 105

*/
