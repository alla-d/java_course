package pl.stqa.java_course.sandbox;

public class Distance {

  public static void main(String[] args) {
    Point p1 = new Point(5.5, 7.9);
    Point p2 = new Point(3.2, 5.3);
   // System.out.println("The distance between Point p1 to Point p2 (using static function) = " + Point.distance(p1, p2));
    System.out.println("The distance between Point p1 to Point p2 (using method) = " + p1.distance2(p2));
  }
}
