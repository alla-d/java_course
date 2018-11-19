package pl.stqa.java_course.sandbox;

public class Point {

  public double x;
  public double y;

  public Point (double x, double y){
    this.x = x;
    this.y = y;
  }

  public static double distance (Point p1, Point p2){
    double dist = Math.sqrt(Math.pow(p1.x - p2.x, 2.0) + Math.pow(p1.y - p2.y, 2.0));
    return dist;
   }
   public static void main (String[] args){
    Point p1 = new Point(3.5, 5.9);
    Point p2 = new Point(3.2, 5.3);
     System.out.println("The distance between Point p1 to Point p2 = " + Point.distance(p1, p2));
   }

  }

