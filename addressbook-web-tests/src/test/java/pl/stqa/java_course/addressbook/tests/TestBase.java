package pl.stqa.java_course.addressbook.tests;

import org.openqa.selenium.remote.BrowserType;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import pl.stqa.java_course.addressbook.appmanager.ApplicationManager;

public class TestBase {

  // Firefox
  //protected static final ApplicationManager app = new ApplicationManager(BrowserType.FIREFOX);
  // CHROME
  protected static final ApplicationManager app = new ApplicationManager(BrowserType.CHROME);
  // IE
  // protected static final ApplicationManager app = new ApplicationManager(BrowserType.IE);

  @BeforeSuite
  public void setUp() throws Exception {
    app.init();
  }

  @AfterSuite
  public void tearDown() throws Exception {
    app.stop();
  }

}
