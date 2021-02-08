package lab8;

import java.util.Scanner;

// James Gibb u0915889 lab8

public class PT1 {
	public static void main(String[] args) {
	System.out.println(triple(2));
	System.out.println(determineSpeed(100));
	System.out.println(determineSpeed(60));
	System.out.println(determineSpeed(20));
	System.out.println(determineSpeed(0));
	System.out.println(makeExpression(2,1));
	System.out.println(first("1 Hi Joe 2 Joe 4"));
	int [] myArr = {1,3,5,13,5,-3};
	System.out.println(count5and3(myArr));
	}
	
	public static int triple(int number) {
		return number*3;
	}
	
	public static String determineSpeed(double time) {
		String speedMeasurement = "";
		if(time >= 100) {
			speedMeasurement = "Slow";
		}
		else if(time < 100 && time >= 50) {
			speedMeasurement = "Medium";
		}
		else if(time >= 20 && time < 50) {
			speedMeasurement = "Speedy";
		}
		else if(time < 20) {
			speedMeasurement = "Fast";
		}
		return speedMeasurement; 
	}
	
	public static int makeExpression(int num1, int num2) {
		num1 = num1+2;
		num2 = triple(num2);
		int total = num1*num2;
		return total;
	}


	public static int first(String string) {
		String test = string;
		Scanner s = new Scanner(test);
		int first = s.nextInt();
		while(s.hasNextInt()) {
				if(first>2) {
				first = s.nextInt();
			}
		}
		s.close();
		return first;
	}
	
	public static int count5and3(int[] in) {
		int fiveCount = 0;
		int threeCount = 0;
		int count = 0;
		for(int i = 0; i<in.length; i++) {
			if(in[i]==5) {
				fiveCount++;
			}
			if(in[i]==3) {
				threeCount++;
			}
		}
		count = fiveCount - threeCount;
		return count;
	}
}
