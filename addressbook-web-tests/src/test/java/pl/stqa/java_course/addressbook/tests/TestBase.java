package pl.stqa.java_course.addressbook.tests;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import pl.stqa.java_course.addressbook.appmanager.ApplicationManager;
import org.openqa.selenium.remote.BrowserType;
import pl.stqa.java_course.addressbook.model.ContactData;
import pl.stqa.java_course.addressbook.model.Contacts;
import pl.stqa.java_course.addressbook.model.GroupData;
import pl.stqa.java_course.addressbook.model.Groups;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;

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

  public void verifyGroupListInUi() {
    if(Boolean.getBoolean("veryfyUI")){
      Groups dbGroups = app.db().groups();
      Groups uiGroups = app.group().all();
      assertThat(uiGroups, equalTo(dbGroups.stream()
              .map((g) -> new GroupData().withId(g.getId()).withName(g.getName()))
              .collect(Collectors.toSet())));
    }
  }

  public void verifyContactListInUI() {
    // get system property
    if (Boolean.getBoolean("verifyUI")) {
      Contacts dbContacts = app.db().contacts();
      Contacts uiContacts = app.contact().all();
      assertThat(uiContacts, equalTo(dbContacts.stream().map((c) -> new ContactData()
              .withId(c.getId()).withContactName(c.getContactName()).withLastName(c.getLastName())
              .withAllPhones(String.format("%s\n%s\n%s",
                      c.getHomePhone(), c.getMobilePhone(), c.getWorkPhone()))
              .withEmail(c.getEmail())
              .withAddress(c.getAddress()))
              .collect(Collectors.toSet())));
    }
  }
}
