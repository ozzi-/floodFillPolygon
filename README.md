# floodFillPolygon
Java Flood Fill Polygon implementation

```java
Polygon p = new Polygon(new Point(100, 100), new Point(200, 200), new Point(100, 150));

// instead of g2d.fillPolygon() - we use this handmade implementation which is about 35x slower ;)
HashSet<Point> polyPixels = Polyfill.getPolyPixels(p);
for (Point point : polyPixels) {
	g2d.drawRect(point.x, point.y, 0,0);
}
```
![result](https://i.imgur.com/5EBRQ6e.png)
