package pl.stqa.java_course.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.*;
import pl.stqa.java_course.addressbook.model.GroupData;

import java.util.List;

public class GroupCreationTests extends TestBase {

  @Test
  public void testGroupCreation() throws Exception {
    app.getNavigationHelper().gotoGroupPage();
    List<GroupData> before = app.getGroupHelper().getGroupList();
    app.getGroupHelper().createGroup(new GroupData("test1", "test2", null));
    List<GroupData> after = app.getGroupHelper().getGroupList();
    Assert.assertEquals(after.size(), before.size() + 1);
    app.getNavigationHelper().gotoHomePage();
  }
}
