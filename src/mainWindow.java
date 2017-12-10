import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import java.awt.datatransfer.*;
import java.awt.Toolkit;

public class mainWindow implements ActionListener {
	private JFrame f;
	private JPanel p, p1;
	private JButton b1;
	private JScrollPane text;
	private JTextArea editArea;
	private JLabel label;
	private StringSelection selection;
	private Clipboard clpbrd = Toolkit.getDefaultToolkit().getSystemClipboard();
	public mainWindow() {
		gui();
	}
	
	public void gui() {
		f = new JFrame("Spongebob Meme Maker");
		f.setVisible(true);
		f.setSize(640,480);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		p = new JPanel();
		p1 = new JPanel();
		//Sets text box
		editArea = new JTextArea(5,40);
		Font font = new Font(Font.MONOSPACED, Font.PLAIN, editArea.getFont().getSize());
        editArea.setFont(font);
        editArea.setLineWrap(true);
        editArea.setWrapStyleWord(true);
		text = new JScrollPane(editArea, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);

		p.add(text);
		
		//Sets button
		b1 = new JButton("Convert");
		b1.addActionListener(this);
		
		p.add(b1);
		
		label = new JLabel();
		p1.add(label);
		
		Border padding = BorderFactory.createEmptyBorder(0, 0, 15, 0);
		p.setBorder(padding);
		
		f.add(p);
		f.add(p1,BorderLayout.SOUTH);
		f.pack();

	}
	
	public static void main(String[] args) {
		new mainWindow();
	}
	

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == b1) {
			String paragraph = editArea.getText();
			selection = new StringSelection(memeify(paragraph));
			clpbrd.setContents(selection, null);
			System.out.println(memeify(paragraph));
			label.setText("Copied to Clipboard!");
			editArea.setText("");
		}
	}

	private String memeify(String paragraph) {
		Random rand = new Random();
		char[] chars = paragraph.toCharArray();
		for(int i = 0; i < paragraph.length(); i++) {
			int randNum = rand.nextInt(3) + 1;
			if (i%randNum == 0) {
				chars[i] = Character.toUpperCase(paragraph.charAt(i));
			}
			else {
				chars[i] = Character.toLowerCase(paragraph.charAt(i));
			}
		}
		paragraph = String.valueOf(chars);
		return paragraph;
	}
}
