import java.util.*;
import java.awt.event.*;
import java.awt.*;
import javax.swing.*;
import javax.swing.Timer;


public class Play extends JPanel implements KeyListener, ActionListener
{
	private boolean p = false;
	private int sco = 0;

	private int tBricks = 36;

	private Timer ti;
	private int de = 8;

	private int plX = 300;

	private int baposX = 340;
	private int baposY = 530;
	private int baXdir = -1;
	private int baYdir = -2;
	private MapCreate m;

	public Play()
	{
		m = new MapCreate(4,9);
		addKeyListener(this);
		setFocusable(true);
		setFocusTraversalKeysEnabled(false);
    ti = new Timer(de,this);
		ti.start();
	}

	public void paint(Graphics g){

		g.setColor(new Color(247, 224, 107));
		g.fillRect(1, 1, 692, 592);


		m.draw((Graphics2D) g);

		g.setColor(new Color(247, 224, 107));
		g.fillRect(0, 0, 3, 592);
		g.fillRect(0, 0, 692, 3);
		g.fillRect(691, 0, 3, 592);

		g.setColor(new Color(60, 140, 95));
		g.setFont(new Font("SansSerif",Font.BOLD, 25));
		g.drawString(""+sco,590,30);
		Image s = new ImageIcon("score1.gif").getImage();
		g.drawImage(s, 20, 10, this);

		g.setColor(new Color(215, 41, 121));
		g.fillRect(plX, 550, 100, 8);

		g.setColor(new Color(60, 140, 95));
		g.fillOval(baposX, baposY, 20, 20);

		if(tBricks <= 0){
			 p = false;
       baXdir = 0;
     	 baYdir = 0;
			 Image icon1 = new ImageIcon("won1.gif").getImage();
			 g.drawImage(icon1, 60, 198, this);
		}

		if(baposY > 570){
			 p = false;
       baXdir = 0;
     	 baYdir = 0;
			 Image icon2 = new ImageIcon("over1.gif").getImage();
			 g.drawImage(icon2, 60, 198, this);
    }
		g.dispose();
	}

	public void keyPressed(KeyEvent e)
	{
		if (e.getKeyCode() == KeyEvent.VK_RIGHT)
		{
			if(plX >= 585)
			{
				plX = 585;
			}
			else
			{
				moveRight();
			}
        }

		if (e.getKeyCode() == KeyEvent.VK_LEFT){
			if(plX < 15){
				plX = 15;
			}

			else{
				moveLeft();
			}
    }

		if (e.getKeyCode() == KeyEvent.VK_SPACE){
			if(!p){

				p = true;
				baposX = 340;
				baposY = 530;
				baXdir = -1;
				baYdir = -2;
				plX = 300;
				sco = 0;
				tBricks = 36;
				m = new MapCreate(4,9);

				repaint();
			}
    }
		if (e.getKeyCode() == KeyEvent.VK_ENTER){
			if(!p){

				p = true;
				baposX = 340;
				baposY = 530;
				baXdir = -1;
				baYdir = -2;
				plX = 300;
				sco = 0;
				tBricks = 48;
				m = new MapCreate(4,12);

				repaint();
			}
    }
	}

	public void keyReleased(KeyEvent e) {}
	public void keyTyped(KeyEvent e) {}

	public void moveRight(){
		p = true;
		plX+=20;
	}

	public void moveLeft(){
		p = true;
		plX-=20;
	}

	public void actionPerformed(ActionEvent e){
		ti.start();
		if(p){
			if(new Rectangle(baposX,baposY, 20, 20).intersects(new Rectangle(plX, 550, 30, 8))) {

				baYdir = -baYdir;
				baXdir = -2;
			}

			else if(new Rectangle(baposX,baposY, 20, 20).intersects(new Rectangle(plX + 70, 550, 30, 8))) {

				baYdir = -baYdir;
				baXdir = baXdir +2;
			}

			else if(new Rectangle(baposX,baposY, 20, 20).intersects(new Rectangle(plX + 30, 550, 40, 8))) {

				baYdir = -baYdir;
			}

			A: for(int i = 0; i<m.m.length; i++)
			{
				for(int j =0; j<m.m[0].length; j++)
				{
					if(m.m[i][j] > 0) {

						int brickX = j * m.brickW + 80;
						int brickY = i * m.brickH + 50;
						int brickW = m.brickW;
						int brickH = m.brickH;

						Rectangle rect = new Rectangle(brickX, brickY, brickW, brickH);
						Rectangle baRect = new Rectangle(baposX, baposY, 20, 20);
						Rectangle brickRect = rect;

						if(baRect.intersects(brickRect)) {

							m.setBrickValue(0, i, j);
							sco+=5;
							tBricks--;

							if(baposX + 19 <= brickRect.x || baposX + 1 >= brickRect.x + brickRect.width) {

								baXdir = -baXdir;
							}

							else {

								baYdir = -baYdir;
							}
							break A;
						}
					}
				}
			}

			baposX += baXdir;
			baposY += baYdir;

			if(baposX < 0) {

				baXdir = -baXdir;
			}
			if(baposY < 0) {

				baYdir = -baYdir;
			}
			if(baposX > 670) {

				baXdir = -baXdir;
			}

			repaint();
		}
	}
}
