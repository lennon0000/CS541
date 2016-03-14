package jwang81.sstevens.edu;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class Search extends JFrame implements ActionListener {

	private JPanel p = new JPanel();
	private JTextArea res;
	JTextField inputField;
	
	JButton search;
	
	public void init() {
		
		this.setTitle("Text Crawler");
		this.setLocationRelativeTo(null);
		this.getContentPane().setBackground(java.awt.Color.WHITE);
		this.setLayout(new BorderLayout());
	
		search = new JButton("Search");
		search.setActionCommand("search");
		search.addActionListener(this);
		
		inputField = new JTextField(15);
		inputField.setSize(17, 2);
		p.add(inputField);
		p.add(search);
		
		res = new JTextArea(20, 30);
		
		p.add(res);
		
		this.add(p);
		this.setSize(400, 450);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("search")) {
			Set<Integer> finalIndex = new HashSet<Integer>();
			res = new JTextArea(20, 30);
			p.removeAll();
			
			p.add(inputField);
			p.add(search);
			p.add(res);
			this.setVisible(true);
			String inputValue = inputField.getText();
			
			List<Integer> results = new ArrayList<Integer>();
			if (inputValue.contains(" ")) {
				String[] words = inputValue.split(" ");
				
				Map<Integer,Integer> check = new HashMap<Integer,Integer>();
				int num = words.length;
				for (int i = 0; i < words.length; i++) {
					Set<Integer> result = Util.tree.getIndexList(words[i]);
					for(Integer temp:result){
						if (check.containsKey(temp)) {
							int count = check.get(temp) +1;
							
							check.put(temp, count++);
						}else {
							check.put(temp, 1);
						}
					}
				}
				
				for (Integer index:check.keySet()) {
					if (check.get(index) == num) {
						finalIndex.add(index);
					}
				}
				
			}else {
				finalIndex = Util.tree.getIndexList(inputValue);
			}
			
			res.append("The results set is : \r\n\r\n");
			// for iterate all the results， output feedback.
			for (Integer index: finalIndex) {
				String title = "";
				try {
					title = Util.getTitle(index);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				res.append(title + "\r\n\r\n");
			}
		}
	}
}
