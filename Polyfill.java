import java.util.HashSet;

public class Polyfill {
	static HashSet<Point> getPolyPixels(Polygon p) {
		Point p1 = p.p1;
		Point p2 = p.p2;
		Point p3 = p.p3;
		HashSet<Point> l1P = calculateBresenhamLine(p1.x, p1.y, p2.x, p2.y);
		HashSet<Point> l2P = calculateBresenhamLine(p2.x, p2.y, p3.x, p3.y);
		HashSet<Point> l3P = calculateBresenhamLine(p3.x, p3.y, p1.x, p1.y);
		HashSet<Point> fillP = fillForm(p, l1P, l2P, l3P);
		fillP.addAll(l1P);
		fillP.addAll(l2P);
		fillP.addAll(l3P);
		return fillP;
	}

	private static HashSet<Point> fillForm(Polygon p, HashSet<Point> l1p, HashSet<Point> l2p, HashSet<Point> l3p) {
		HashSet<Point> points = new HashSet<Point>();
		fillFormInternal(points, p.getCenter().x, p.getCenter().y, l1p, l2p, l3p);
		return points;
	}

	private static void fillFormInternal(HashSet<Point> points, int x, int y, HashSet<Point> l1p, HashSet<Point> l2p, HashSet<Point> l3p) {
		Point curPoint = new Point(x, y);
		if(!points.contains(curPoint) && !pointInsideLine(x, y, l1p) && !pointInsideLine(x, y, l2p) && !pointInsideLine(x, y, l3p)){
			points.add(curPoint);		
			fillFormInternal(points, x-1, y  , l1p, l2p, l3p);
			fillFormInternal(points, x  , y+1, l1p, l2p, l3p);
			fillFormInternal(points, x+1, y  , l1p, l2p, l3p);
			fillFormInternal(points, x  , y-1, l1p, l2p, l3p);
		}
	}

	private static boolean pointInsideLine(int x, int y, HashSet<Point> l1p) {
		for (Point point : l1p) {
			if(x==point.x && y==point.y) {
				return true;
			}
		}
		return false;
	}

	private static int sign (int x) {
        return (x > 0) ? 1 : (x < 0) ? -1 : 0;
}
	
	public static HashSet<Point> calculateBresenhamLine(int xstart, int ystart, int xend, int yend) {
		HashSet<Point> linePixels = new HashSet<Point>();
		int x, y, dx, dy, incx, incy, pdx, pdy, es, el, err;

		dx = xend - xstart;
		dy = yend - ystart;

		incx = sign(dx);
		incy = sign(dy);

		if (dx < 0)
			dx = -dx;
		if (dy < 0)
			dy = -dy;

		if (dx > dy) {
			pdx = incx;
			pdy = 0;
			es = dy;
			el = dx;
		} else {
			pdx = 0;
			pdy = incy;
			es = dx;
			el = dy;
		}

		x = xstart;
		y = ystart;
		err = el / 2;
		linePixels.add(new Point(x, y));

		for (int t = 0; t < el; t++) {
			err -= es;
			if (err < 0) {
				err += el;
				x += incx;
				y += incy;
			} else {
				x += pdx;
				y += pdy;
			}
			linePixels.add(new Point(x, y));
		}
		return linePixels;
	}

}
