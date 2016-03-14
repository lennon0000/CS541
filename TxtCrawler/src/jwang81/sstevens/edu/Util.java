package jwang81.sstevens.edu;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;


public class Util {
	public static Trie tree;
	public static String path;
	/*
	 * input a directory and read all the file under that directory
	 * */
	public static void readDir(File dir) throws IOException {
		tree = new Trie();
		File f = new File("");
		path = f.getAbsolutePath();
		
		File[] files = dir.listFiles();
//		String writePath = dir.getAbsolutePath();//
		/*
		 * save the index and title into a local file, the file name will be index.txt
		 * */
		path += "/index/index.txt";
		File writename = new File(path);
		writename.createNewFile();
		BufferedWriter out = new BufferedWriter(new FileWriter(writename));

		int index = 0;
		for (File temp : files) {
			String title = temp.getName();
			out.write(index + " " + title + "\r\n"); // \r\n change line
			
			readFile(temp, index);
			index++;
			out.flush();
		}
		out.close();
	}
	/*
	 * read all the words from the file and insert it into a tree
	 * */
	private static void readFile(File file, int index) throws IOException {

		BufferedReader reader = new BufferedReader(new FileReader(file));
		String tempString = null;
		while ((tempString = reader.readLine()) != null) {
			String[] line = tempString.split("[^a-zA-Z]+");
			for (int i = 0; i < line.length; i++) {
				String w = line[i];
				String word = upperToLower(w);
				if (!checkStopWords(word)) {
					tree.insert(word, index);
				}
			}
		}
		reader.close();
	}

	private static boolean checkStopWords(String word) {
		if (main.stopWords.containsKey(word)) {
			return true;
		} else {
			return false;
		}
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
	/*
	 * Get the title by the index, search the title from the file which contain the index and title
	 * */
	public static String getTitle(Integer index) throws IOException {
		BufferedReader reader = new BufferedReader(new FileReader(Util.path));
		String tempString = "";
		while ((tempString = reader.readLine()) != null) {
			String[] titleDetail = tempString.split(" ");
			
			if (titleDetail[0].equals(index+"")) {
				tempString = tempString.substring(1);
				break;
			}
		}
		reader.close();
		return tempString;
	}
}
