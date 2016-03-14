package jwang81.sstevens.edu;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.swing.JFileChooser;

public class main {
	public static Map<String,String> stopWords = new HashMap<String, String>();
	public static void main(String[] args) {
		//Load the stop words
		StopWords.loadStopWords();
		//Chose the directory
		System.out.println("Please chose the directry.");
		JFileChooser chooser = new JFileChooser();
		chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		int returnVal = chooser.showOpenDialog(null);
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			System.out.println("You chose to open this file: "
					+ chooser.getSelectedFile().getName());
		}
		System.out.println("Program is crawling the files and constroct the dic trie, please wait...");
		File dir = chooser.getSelectedFile();
		//Text Crawler, get a dictionary trie
		try {
			Util.readDir(dir);
		} catch (IOException e) {
			e.printStackTrace();
			return;
		}
		System.out.println("Processing succeed!");
		//execute search 
		Search s = new Search();
		s.init();
	}

}
