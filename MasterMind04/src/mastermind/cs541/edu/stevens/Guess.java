package mastermind.cs541.edu.stevens;

public class Guess {
	int a;
	int b;
	int[] numbs;
	public int getA() {
		return a;
	}
	public void setA(int a) {
		this.a = a;
	}
	public int getB() {
		return b;
	}
	public void setB(int b) {
		this.b = b;
	}
	public void setNumbs(int[] numbs) {
		this.numbs = numbs;
	}
	public String getOutPut() {
		String r = "";
		for (int i = 0; i < numbs.length; i++) {
			r += numbs[i];
		}
		r = r+"["+a+"A"+b+"B"+"]";
		return r;
	}
	public int[] getNumbs() {
		return numbs;
	}
}
