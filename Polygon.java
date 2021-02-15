
public class Polygon {
	Point p1;
	Point p2;
	Point p3;
	
	public Polygon(Point p1, Point p2, Point p3) {
		this.p1=p1;
		this.p2=p2;
		this.p3=p3;
	}
	
	public Point getCenter() {
		int x = (p1.x + p2.x + p3.x) / 3;
		int y = (p1.y + p2.y + p3.y) / 3;
		return new Point(x, y);
	}
}
