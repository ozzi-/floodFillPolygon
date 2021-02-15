import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.HashSet;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class Render extends JFrame {

	private static final long serialVersionUID = 1L;

	public Render() {
		super("Draw Poly Demo");
		setSize(480, 400);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
	}

	void drawLines(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;

		Polygon p = new Polygon(new Point(100, 100), new Point(200, 200), new Point(100, 150));

        long startTime = System.nanoTime();
        
//		This would be the g2d fill polygon implementation, it takes about 200-500 NS on my device		
//		java.awt.Polygon pl = new java.awt.Polygon();
//		pl.addPoint(100, 100);
//		pl.addPoint(200, 200);
//		pl.addPoint(100, 150);
//		g2d.fillPolygon(pl);

//		This implementation is about 35 times slower
		HashSet<Point> polyPixels = Polyfill.getPolyPixels(p);
		for (Point point : polyPixels) {
			g2d.drawRect(point.x, point.y, 0,0);
		}
        long elapsedTime = System.nanoTime() - startTime;
        
        System.out.println("μs TIME " + elapsedTime/1000000);
	}


	public void paint(Graphics g) {
		super.paint(g);
		drawLines(g);
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				new Render().setVisible(true);
			}
		});
	}
}