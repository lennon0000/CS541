package mastermind.cs541.edu.stevens;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.TextEvent;
import java.awt.event.TextListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class PanelView extends JFrame implements ActionListener, ItemListener,
		DocumentListener {
	private JPasswordField jpf;
	private JPanel p = new JPanel();
	private JPanel textPanel = new JPanel();
	private JTextArea res;
	
	public void init() {
		this.setTitle("MasterMind");
		this.setLocationRelativeTo(null);
		this.getContentPane().setBackground(java.awt.Color.WHITE);
		this.setLayout(new BorderLayout());

		JComboBox jcb = new JComboBox();
		jcb.addItem("Please choose");
		jcb.addItem("Generate Random");
		jcb.addItem("Define by user");
		jcb.setSelectedItem("Please choose");

		jcb.addItemListener(this);

		p.add(jcb);
		this.add(p);
		this.setSize(310, 450);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("start")) {
			textPanel.removeAll();
			JButton re = new JButton("Restart");
			re.setActionCommand("restart");
			re.addActionListener(this);
			p.add(re);
			this.setVisible(true);
			int[] genePass = new int[4];
			if (jpf != null) {
				char[] pass = jpf.getPassword();
				for (int i = 0; i < pass.length; i++) {
					genePass[i] = Integer.parseInt(pass[i] + "");
				}
			} else {
				Random random = new Random();
				int j = 0;
				genePass[0] = -1;
				genePass[1] = -1;
				genePass[2] = -1;
				genePass[3] = -1;
				while (true) {
					int temp = random.nextInt(10);
					boolean add = true;
					for (int i = 0; i < genePass.length; i++) {
						if (genePass[i] == temp) {
							add = false;
						}
					}
					if (add) {
						genePass[j] = temp;
						j++;
						if (j == 4) {
							break;
						}
					}
				}
			}
			for (int i = 0; i < genePass.length; i++) {
				System.out.print(genePass[i]);
			}
			System.out.println();
			List<Guess> result = new ArrayList<Guess>();
			Compute compute = new Compute();
			result = compute.computing(genePass);

			res = new JTextArea(20, 20);

			res.append("The results set is : \r\n");
			// for iterate all the results， output feedback.
			for (int i = 1; i <= result.size(); i++) {
				Guess temp = result.get(i-1);
				if (i < 10) {
					res.append("0");
				}
				res.append(i + ": " + temp.getOutPut() + "\r\n");
			}

			JScrollPane scroll = new JScrollPane(res);
			scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
			textPanel.add(scroll);
			this.add(p, BorderLayout.NORTH);
			this.add(textPanel, BorderLayout.CENTER);
			this.setVisible(true);

		}
		if (e.getActionCommand().equals("restart")) {
			p.removeAll();
			textPanel.removeAll();
			jpf = null;
			this.init();
			this.setVisible(true);
		}
	}

	@Override
	public void itemStateChanged(ItemEvent e) {
		if (e.getStateChange() == e.SELECTED) {
			if (e.getItem().toString() == "Generate Random") {
				JButton jb = new JButton("Generate and Start");
				jb.setActionCommand("start");
				jb.addActionListener(this);
				p.removeAll();
				p.add(jb);
				this.add(p, BorderLayout.NORTH);
				this.add(textPanel, BorderLayout.CENTER);
				this.setVisible(true);

			} else {
				jpf = new JPasswordField(5);
				jpf.getDocument().addDocumentListener(this);
				JButton start = new JButton("Set and Start");
				start.setActionCommand("start");
				start.addActionListener(this);
				p.removeAll();
				p.add(jpf);
				p.add(start);
				this.add(p, BorderLayout.NORTH);
				this.add(textPanel, BorderLayout.CENTER);
				this.setVisible(true);
			}
		}

	}

	@Override
	public void insertUpdate(DocumentEvent e) {
	}

	@Override
	public void removeUpdate(DocumentEvent e) {
		textPanel.removeAll();
		this.repaint();
		this.setVisible(true);
	}

	@Override
	public void changedUpdate(DocumentEvent e) {
	}
}
