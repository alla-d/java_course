
package ru.stqa.pft.mantis.tests;

import org.openqa.selenium.remote.BrowserType;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import ru.stqa.pft.mantis.appmanager.ApplicationManager;

public class TestBase {


  //protected final ApplicationManager app = new ApplicationManager(BrowserType.FIREFOX);
  // CHROME
  protected final ApplicationManager app = new ApplicationManager(System.getProperty("browser", BrowserType.CHROME));
  // IE
  // protected final ApplicationManager app = new ApplicationManager(BrowserType.IE);

  @BeforeSuite(alwaysRun = true)
  public void setUp() throws Exception {
    app.init();
  }

  @AfterSuite(alwaysRun = true)
  public void tearDown()  {
    app.stop();
  }

}