package pl.stqa.java_course.sandbox;

public class Point {

  public double x;
  public double y;

  public Point(double x, double y) {
    this.x = x;
    this.y = y;
  }
// commit because of feedback
//  public static double distance(Point p1, Point p2) {
//    double dist = Math.sqrt(Math.pow(p1.x - p2.x, 2.0) + Math.pow(p1.y - p2.y, 2.0));
//    return dist;
//  }


  public double distance2(Point p2) {
    double dist = Math.sqrt(Math.pow(this.x - p2.x, 2.0) + Math.pow(this.y - p2.y, 2.0));
    return dist;
  }

}
