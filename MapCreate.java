import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;

public class MapCreate{
	public int m[][];
	public int brickW;
	public int brickH;

	public MapCreate (int r, int c){
		m = new int[r][c];
		for(int i = 0; i<m.length; i++)
		{
			for(int j =0; j<m[0].length; j++)
			{
				m[i][j] = 1;
			}
		}

		brickW = 540/c;
		brickH = 150/r;
	}

	public void draw(Graphics2D g)
	{
		for(int i = 0; i<m.length; i++)
		{
			for(int j =0; j<m[0].length; j++)
			{
				if(m[i][j] > 0)
				{
					g.setColor(new Color(215, 41, 121));
					g.fillRect(j * brickW + 80, i * brickH + 50, brickW, brickH);

					g.setStroke(new BasicStroke(5));
					g.setColor(new Color(247, 224, 107));
					g.drawRect(j * brickW + 80, i * brickH + 50, brickW, brickH);
				}
			}
		}
	}

	public void setBrickValue(int v, int r, int c)
	{
		m[r][c] = v;
	}
}
