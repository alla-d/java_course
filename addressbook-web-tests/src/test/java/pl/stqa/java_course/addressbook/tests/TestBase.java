package pl.stqa.java_course.addressbook.tests;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import pl.stqa.java_course.addressbook.appmanager.ApplicationManager;
import org.openqa.selenium.remote.BrowserType;

public class TestBase {
  // Firefox
  //protected final ApplicationManager app = new ApplicationManager(BrowserType.FIREFOX);
  // CHROME
   protected final ApplicationManager app = new ApplicationManager(BrowserType.CHROME);
  // IE
  // protected final ApplicationManager app = new ApplicationManager(BrowserType.IE);

  @BeforeMethod
  public void setUp() throws Exception {
    app.init();
  }

  @AfterMethod
  public void tearDown() throws Exception {
    app.stop();
  }

}
