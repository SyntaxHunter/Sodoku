import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class BoardGUI extends JPanel{
	
	JFrame frame = new JFrame("Sudoku");
	JButton button;
	JTextField[][] nums = new JTextField[9][9];
	int[][] values;
	
	public BoardGUI(){
		setUpGUI();
	}

	public void setUpGUI(){
		frame.setSize(400, 400);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(new BorderLayout());
		
		button = new JButton("Finished!");
		button.addActionListener(new Sodoku2());
		frame.add(button, BorderLayout.SOUTH);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.DARK_GRAY);
		panel.setLayout(new GridLayout(3,3,8,8));
		frame.add(panel, BorderLayout.CENTER);
		
		for(int i = 0; i < 3; i++){
			for(int j = 0; j < 3; j++){
				JPanel p = new JPanel();
				p.setBackground(Color.DARK_GRAY);
				p.setLayout(new GridLayout(3,3,2,2));
				panel.add(p);
				for(int k = 0; k < 9; k++){
					JTextField text = new JTextField();
					text.setHorizontalAlignment(SwingConstants.CENTER);
					p.add(text);
					nums[i * 3 + (k / 3)][j * 3 + (k % 3)] = text;
				}
			}
		}
		
		JTextField prev = nums[nums.length - 1][nums[nums.length - 1].length - 1];
		for(int i = nums.length - 1; i >= 0; i--){
			for(int j = nums[i].length - ((i == (nums.length - 1))? 2 : 1); j >= 0; j--){
				final JTextField p = prev;
				nums[i][j].addKeyListener(new KeyListener() {
					public void keyReleased(KeyEvent e) {}
					public void keyPressed(KeyEvent e){}
					public void keyTyped(KeyEvent e){
						if(!(e.getExtendedKeyCode() == KeyEvent.VK_BACK_SPACE || e.getExtendedKeyCode() == KeyEvent.VK_DELETE));
							p.requestFocusInWindow();  
					}
				});
				prev = nums[i][j];
			}
		}
		
		frame.setVisible(true);
	}
	
}
