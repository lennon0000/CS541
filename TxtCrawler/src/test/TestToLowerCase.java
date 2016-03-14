package test;

import java.util.Locale;

public class TestToLowerCase {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String r = "AAbb";
		String rr = upperToLower(r);
		System.out.println("AAAA".toLowerCase());
		System.out.println(r.toLowerCase());
		System.out.println(rr);
	}
	public static String upperToLower(String str)
    {
    	char[] ch = str.toCharArray();
    	for(int i=0;i<ch.length;i++)
    	{
    		if(((int)ch[i]>64) && ((int)ch[i]<91))
    		{
    			ch[i] = (char)((int)ch[i]+32);
    		}
    	}
    	
    	String childStr = String.valueOf(ch);
    	
    	return childStr;
    }

}
