/*
 * Student Name: Tri Doan
 * Course: CIS 146 SJSU Fall 2019
 * Project: 2
 * Description: create methods to read in data from file and find max sub array 
 * using three algorithms
 */
package cs146F19.Doan.project2;

import java.util.*;
import java.io.*;
public class Maximum_Subarray {

	//run this method in main() to test if all the data read in and algorithms works correctly 
	public void ReadInFromFile_CreateArray_And_TestAlgorithms()
	{
		try {
			//Use BufferedReader and StringTokenizer to find array of the array 
			BufferedReader buffer = new BufferedReader(new FileReader("C:\\Users\\Tri Doan\\eclipse-workspace\\Project2_CS146\\maxSumtest.txt")); 
			String line = buffer.readLine();
			StringTokenizer tokenize;
				int arraysize = 0;
				tokenize = new StringTokenizer(line, "  ");
				while(tokenize.hasMoreTokens())
				{
					tokenize.nextToken();
					arraysize++;
				}
			buffer.close();
			arraysize -=3;

			//create array
			int [] arr = new int[arraysize];
			
			//read in data to the array newly created
			BufferedReader buffer1 = new BufferedReader(new FileReader("C:\\Users\\Tri Doan\\eclipse-workspace\\Project2_CS146\\maxSumtest.txt")); 
			String line1 = buffer1.readLine();
			StringTokenizer tokenize1;
			int counter = 1;
			while(line1 != null)
			{
				tokenize1 = new StringTokenizer(line1, "  ");
				int index = 0;
				if(counter == 1)
				{
					while(tokenize1.hasMoreTokens() && index < arraysize)
					{
						arr[index] = Integer.parseInt(tokenize1.nextToken());
						index++;
					}
					counter = 0;
					
					//CALL METHODS HERE
					FreeLancerInfo Person = new FreeLancerInfo();
					//Person = BruteForce(arr);
					//Person = DivideAndConquer(arr, 0, arr.length-1);
					//Person = Kadane_Algorithm(arr);
					Person.FreeLancerDisplayInfo();	
				}
				else 
				{
					counter = 1;
				}
				line1 = buffer1.readLine();
			}
			buffer1.close();
		}catch(IOException E)
			{
				System.out.println("Can't find the file!");
			}
	}
	
	/*
	 * This method run two for loop 
	 * Run the array from first to last, second to last, third to last and so on 
	 * Each iteration, add up the element value to val, if val is greater than max 
	 * update val to max 
	 * Return an object stores departing, arrival, and max 
	 */

	public static FreeLancerInfo BruteForce(int [] array) 
	{	
		FreeLancerInfo Person1 = new FreeLancerInfo();
		int max = array[0];
		int keepi = 0;
		int keepj = 0;
		for(int i = 0; i < array.length; i++)
		{
			int val = 0;
			for(int j = i ; j < array.length; j++)
			{
				val += array[j];
				if(val > max)
				{
					max = val;
					keepi = i;
					keepj = j;
				}
			}
		}
		Person1.setDeparting_date(keepi);
		Person1.setArrive_date(keepj);
		Person1.setMax_earning(max);
		return Person1;
	}
	
	/*
	 * This method divide the array into two halves
	 * Use recursive to return the maximum sub array in left half, right half
	 * and cross midpoint
	 * And then return one of the three that are highest
	 * Return an object stores departing, arrival, and max 
	 */
	public static FreeLancerInfo DivideAndConquer(int [] array, int low, int high)
	{
		FreeLancerInfo PersonLeft = new FreeLancerInfo();
		FreeLancerInfo PersonRight = new FreeLancerInfo();
		FreeLancerInfo PersonCross = new FreeLancerInfo();

		//base case wit only one element in the array
		if(low ==  high)
		{
			FreeLancerInfo Person1 = new FreeLancerInfo();
			Person1.setMax_earning(array[low]);
			Person1.setDeparting_date(low);
			Person1.setArrive_date(high);
			return Person1;
		}
		else 
		{
			int mid = (low + high)/2;
			PersonLeft = DivideAndConquer(array, low, mid);
			PersonRight = DivideAndConquer(array, mid+1, high);
			PersonCross = Find_Max_Crossing_Subarray(array, low, mid, high);
		}
		if(PersonLeft.getMax_earning() >= PersonRight.getMax_earning() && PersonLeft.getMax_earning() >= PersonCross.getMax_earning())
		{
			return PersonLeft;
		}
		else if(PersonRight.getMax_earning() >= PersonLeft.getMax_earning() && PersonRight.getMax_earning() >= PersonCross.getMax_earning())
		{
			return PersonRight;
		}
		else
		{
			return PersonCross;
		}
	}
	
	/*
	 * Method inside DivideAndConquer which to find the max crossing sub array 
	 * Run a for loop to find the highest sub array to the left and to the right 
	 * Return an object stores departing, arrival, and max 
	 */
	public static FreeLancerInfo Find_Max_Crossing_Subarray(int [] array, int low, int mid, int high)
	{
		FreeLancerInfo Person = new FreeLancerInfo();
		int left_sum = Integer.MIN_VALUE;
		int sum = 0; 
		for(int i = mid ; i >= low; i--)
		{
			sum += array[i];
			if(sum > left_sum)
			{
				left_sum = sum;
				Person.setDeparting_date(i);
			}
		}
		
		int right_sum = Integer.MIN_VALUE;
		sum = 0;
		for(int j = mid+1; j <= high; j++)
		{
			sum += array[j];
			if(sum > right_sum)
			{
				right_sum = sum;
				Person.setArrive_date(j);
			}
		}
		Person.setMax_earning(left_sum+right_sum);
		return Person; 
	}
	
	/*
	 * This methods run in O(n) time which take the sum of all combination of the array.
	 * If the sum is less than zero. Reset the sum, and if not add the sum to the final sum 
	 */
	public static FreeLancerInfo Kadane_Algorithm(int [] array)
	{
		FreeLancerInfo Person = new FreeLancerInfo();
		/*
		 * Loop through the entire array
		 * Add up maxTemp is element is greater than zero
		 * If maxTemp is greater than maxSum, set maxSum equal to maxTemp
		 * update the arrive index when maxTemp is less than zero
		 * update the depart index when maxTemp is greater than maxSum
		 */
		int maxSum = 0;
		int maxTemp = 0;
		int depart = -1;
		int arrive = -1;  
		int tempArrive = 0;

		for(int i = 0; i < array.length; i++)
		{
			maxTemp += array[i];
			if(maxTemp < 0)
			{
				maxTemp = 0;
				depart = i + 1; 
			}
			if(maxTemp > maxSum)
			{
				maxSum = maxTemp;
				arrive = i;
				tempArrive = depart;
			}
		}
		depart = tempArrive;
		Person.setMax_earning(maxSum);
		Person.setDeparting_date(depart);
		Person.setArrive_date(arrive);
		return Person;
	}
}