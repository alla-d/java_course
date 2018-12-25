package pl.stqa.java_course.addressbook.tests;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import pl.stqa.java_course.addressbook.appmanager.ApplicationManager;
import org.openqa.selenium.remote.BrowserType;

import java.lang.reflect.Method;
import java.util.Arrays;

public class TestBase {

  Logger logger = LoggerFactory.getLogger(TestBase.class);

  //protected final ApplicationManager app = new ApplicationManager(BrowserType.FIREFOX);
  // CHROME
  protected final ApplicationManager app = new ApplicationManager(System.getProperty("browser",BrowserType.CHROME));
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

  @BeforeMethod (alwaysRun = false)
  public void logTestStart(Method m, Object[] p){
    logger.info("Start test " + m.getName() + "with paramiters " + Arrays.asList(p));
  }

  @AfterMethod (alwaysRun = false)
  public void LogTestStop(Method m, Object[] p){
    logger.info("Stop test " + m.getName() + "with paramiters " + Arrays.asList(p));

  }
}
