import java.awt.*;
import javax.swing.*;

public class Run {
	public static void main(String[] args) {
		JFrame frame = new JFrame();
		Play play = new Play();

		frame.setBounds(10, 10, 700, 600);
		frame.setTitle("Breakout Game");
		frame.setResizable(false);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(play);
    frame.setVisible(true);
	}
}
