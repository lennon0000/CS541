package mastermind.cs541.edu.stevens;

import java.util.LinkedList;
import java.util.List;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		List<Guess> linkedList = new LinkedList<Guess>();
		Guess g;
		int[] digOfN;
		Compute c = new Compute();
		//List rest = new LinkedList<Integer>();
		//int j1 = 0,j2= 0,j3= 0,j4= 0,j5= 0,j6= 0,j7= 0,j8= 0,j9= 0,j10= 0;
		int[] array = new int[10];
		double res = 0;
		int i = 0;
		int max = 0;
		int min = 10;
		for (int j = 0; j < 10; j++) {
			for (int i0 = 0; i0 < 10; i0++) {
				for (int i1 = 0; i1 < 10; i1++) {
					if (i1 != i0) {
						for (int i2 = 0; i2 < 10; i2++) {
							if (i0 != i2 && i1 != i2) {
								for (int i3 = 0; i3 < 10; i3++) {
									if (i0 != i3 && i1 != i3 && i2 != i3) {
										g = new Guess();
										digOfN = new int[4];
										digOfN[0] = i0;
										digOfN[1] = i1;
										digOfN[2] = i2;
										digOfN[3] = i3;
										List l = c.computing(digOfN);
										res += l.size();
										if (max<l.size()) {
											max = l.size();
										}
										if (min>=l.size()) {
											min = l.size();
										}
										g.setNumbs(digOfN);
										linkedList.add(g);
										i++;
										switch (l.size()) {
										case 1:
											array[0]++;
											break;
										case 2:
											array[1]++;
											break;
										case 3:
											array[2]++;
											break;
										case 4:
											array[3]++;
											break;
										case 5:
											array[4]++;
											break;
										case 6:
											array[5]++;
											break;
										case 7:
											array[6]++;
											break;
										case 8:
											array[7]++;
											break;
										case 9:
											array[8]++;
											break;
										case 10:
											array[9]++;
											break;

										default:
											break;
										}
									}
								}
							}
						}
					}
				}
			}
		}
		
		double d = res/i;
		System.out.println("Avg times for 50400 result: "+d);
		System.out.println("Max times for 50400 result: "+max);
		System.out.println("Min times for 50400 result: "+min);
		for (int j = 0; j < 10; j++) {
			System.out.println("Guess for "+(j+1)+" times : "+array[j]);
		}
	}

}
