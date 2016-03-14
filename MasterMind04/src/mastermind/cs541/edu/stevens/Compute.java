package mastermind.cs541.edu.stevens;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

/**
 * @author wangjingxu
 *
 */

public class Compute {

	public List<Guess> computing(int[] genePass) {
		List<Guess> results = new ArrayList<Guess>();
		// pick a value from the array
		List<Guess> checkList = new LinkedList<Guess>();
		//if the size of check list is 0, we need to generate all the 5040 possibilities
		if (checkList.size() == 0) {
			checkList = initCheckList();
		}

		boolean notFinish = true;
		while (notFinish) {
			Guess currentG = pick(checkList);//

			int[] abValue = compare(currentG, genePass);// get xAyB value
			currentG.setA(abValue[0]);
			currentG.setB(abValue[1]);
			results.add(currentG);
			//if the feedback is 4A0B,then abort the while loop
			if (abValue[0] == 4) {
				notFinish = false;
			} else {
				checkList.remove(currentG);
			}
			
			update(checkList, abValue, currentG);// by using xAyB value,
													// decrease the size
			                                    // of checkList
		}
		return results;
	}
/*
 * generate all the 5040 possibilities
 * 
 * */
	public List<Guess> initCheckList() {
		List<Guess> linkedList = new LinkedList<Guess>();
		Guess g;
		int[] digOfN;
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
									g.setNumbs(digOfN);
									linkedList.add(g);
								}
							}
						}
					}
				}
			}
		}

		return linkedList;
	}
//update the list by the feedback and guess value
	private void update(List<Guess> checkList, int[] abValue, Guess currentG) {
		Iterator<Guess> it = checkList.iterator();
		while (it.hasNext()) {
			Guess g = it.next();
			int[] diff = compare(currentG, g.getNumbs());
			if (!(abValue[0] == diff[0] && abValue[1] == diff[1])) {
				it.remove();
			}
		}
	}
// pick a element randomly
	private Guess pick(List<Guess> checkList) {
		// TODO if checkList is null then init 5040
		Random random = new Random();
		int r = random.nextInt(checkList.size());
		Guess g = checkList.get(r);
		return g;
	}
//compare the code and guess number
	private int[] compare(Guess g, int[] genePass) {
		int[] abValue = new int[2];
		int a = 0;
		int b = 0;

		int[] temp = g.getNumbs();
		for (int i = 0; i < genePass.length; i++) {
			for (int j = 0; j < temp.length; j++) {
				if (temp[j] == genePass[i]) {
					if (i == j) {
						a++;
					} else {
						b++;
					}
				}
			}
		}
		abValue[0] = a;
		abValue[1] = b;
		return abValue;
	}

	private Guess generateNum() {
		return null;
	}
//generate a random number
	public void geneRandNum() {
		List<Integer> numPool = new ArrayList<Integer>();
		for (int i = 0; i < 10; i++) {
			numPool.add(i);
		}
		Guess g = new Guess();

		int[] digsOfG = new int[4];
		for (int i = 0; i < 4; i++) {
			Set<Integer> checkAllN = new HashSet<Integer>();

			Random random = new Random();
			int r = random.nextInt(numPool.size());

			digsOfG[i] = numPool.get(r);

			checkAllN.add(digsOfG[i]);

			numPool.remove(r);
		}
		g.setNumbs(digsOfG);
	}
}
