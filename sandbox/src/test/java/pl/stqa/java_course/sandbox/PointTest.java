package pl.stqa.java_course.sandbox;


import org.testng.Assert;
import org.testng.annotations.Test;

public class PointTest {

  // test for the method
  @Test
  public void distanceTrue() {
    Point p1 = new Point(5.5, 7.9);
    Point p2 = new Point(3.2, 5.3);
    Assert.assertEquals(p1.distance2(p2), 3.4713109915419564);
  }

  // test for the function
  @Test
  public void distanceNotTrue() {
    Point p1 = new Point(5.5, 7.9);
    Point p2 = new Point(3.2, 5.3);
    Assert.assertNotEquals(Point.distance(p1, p2), 5.4713109915419564);
  }

}
