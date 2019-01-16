
package ru.stqa.pft.mantis.tests;

import org.openqa.selenium.remote.BrowserType;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import ru.stqa.pft.mantis.appmanager.ApplicationManager;

import java.io.File;
import java.io.IOException;
import java.rmi.RemoteException;
import java.net.MalformedURLException;
import org.testng.SkipException;
import javax.xml.rpc.ServiceException;

public class TestBase {

  //protected final ApplicationManager app = new ApplicationManager(BrowserType.FIREFOX);
  // CHROME
  protected final ApplicationManager app = new ApplicationManager(System.getProperty("browser", BrowserType.CHROME));
  // IE
  // protected final ApplicationManager app = new ApplicationManager(BrowserType.IE);

  @BeforeSuite
  public void setUp() throws Exception {
    app.init();
    app.ftp().upload(new File("src/test/resources/config_inc.php"), "config_inc.php", "config_inc.php.bak");
  }

  @AfterSuite (alwaysRun = true)
  public void tearDown() throws IOException {
    app.ftp().restore("config_inc.php.bak", "config_inc.php");
    app.stop();
  }
  public void skipIfNotFixed(int issueId) throws RemoteException, ServiceException, MalformedURLException {
    if (app.soap().isIssueOpen(issueId) == true) {
      throw new SkipException("Ignored because of issue " + issueId);
    } else {
      System.out.println("TEst started");
    }
  }

 }