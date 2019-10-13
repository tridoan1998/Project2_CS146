/*
 * Student Name: Tri Doan
 * Course: CIS 146 SJSU Fall 2019
 * Project: 2
 * Description: Create Junit test to find the max sub array using three algorithms
 */
package cs146F19.Doan.project2;

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;

import org.junit.Test;

public class Maximum_SubarrayTest {

	@Test
	public void test() throws IOException
	{
		//use to read in data from file
		BufferedReader buffer1 = new BufferedReader(new FileReader("C:\\Users\\Tri Doan\\eclipse-workspace\\Project2_CS146\\maxSumtest.txt")); 
		String line1 = buffer1.readLine();
		StringTokenizer tokenize1;
		
		//use to jump in between empty line
		int counter = 0;		
		boolean skipline = true;
		
		//index of the result array and input array
		int index_for_result_array = 0;
		int index_for_input_array = 0;
		System.out.println("running");
		int [] result_array = new int[3];
		int [] input_array = new int[100];

		//walk through one line at a time 
		while(line1 != null)
		{
			FreeLancerInfo Person = new FreeLancerInfo();
			index_for_result_array = 0;
			index_for_input_array = 0;
			tokenize1 = new StringTokenizer(line1, "  ");
			//only walk through all the line contains elements
			if(skipline== true)
			{
				//read through the whole line
				while(tokenize1.hasMoreTokens())
				{
					//at position 101 to 103 do these
					if(counter >= 100)
					{
						result_array[index_for_result_array] = Integer.parseInt(tokenize1.nextToken());
						switch(counter)
						{
						case 100:
							Person.setMax_earning(result_array[index_for_result_array]);
							break;
						case 101:
							Person.setDeparting_date(result_array[index_for_result_array]);
						case 102:
							Person.setArrive_date(result_array[index_for_result_array]);
							break;
						default:
							break;
						}
						index_for_result_array++;
					}
					//at position from 1 to 100
					else
					{
						input_array[index_for_input_array] = Integer.parseInt(tokenize1.nextToken());
						index_for_input_array++;
						
					}
				counter++;
				}
				skipline = false;	
				/*
				 * Run all three algorithms in the Maximum_Subarray class 
				 * and set it to Person object
				 * Then compare Person object with the result data from the text file 
				 */
				Person = Maximum_Subarray.BruteForce(input_array);				
				assertEquals(Person.getMax_earning(),result_array[0]);
				assertEquals(Person.getDeparting_date(),result_array[1]);
				assertEquals(Person.getArrive_date(),result_array[2]);
				
				Person = Maximum_Subarray.DivideAndConquer(input_array, 0, 99);
				assertEquals(Person.getMax_earning(),result_array[0]);
				assertEquals(Person.getDeparting_date(),result_array[1]);
				assertEquals(Person.getArrive_date(),result_array[2]);
				
				Person = Maximum_Subarray.Kadane_Algorithm(input_array);
				assertEquals(Person.getMax_earning(),result_array[0]);
				assertEquals(Person.getDeparting_date(),result_array[1]);
				assertEquals(Person.getArrive_date(),result_array[2]);
			}
			else
			{
				skipline = true;
			}
			counter = 0;
			line1 = buffer1.readLine();
		}
		buffer1.close();
	}
}