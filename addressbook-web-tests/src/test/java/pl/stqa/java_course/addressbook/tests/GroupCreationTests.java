package pl.stqa.java_course.addressbook.tests;

import org.testng.annotations.*;
import pl.stqa.java_course.addressbook.model.GroupData;

public class GroupCreationTests extends TestBase {

  @Test
  public void testGroupCreation() throws Exception {
    app.getNavigationHelper().gotoGroupPage();
    app.getGroupHelper().createGroup(new GroupData("test1", "test2", null));
    app.getNavigationHelper().gotoHomePage();
  }


}
