package pl.stqa.java_course.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.*;
import pl.stqa.java_course.addressbook.model.GroupData;

import java.util.HashSet;
import java.util.List;

public class GroupCreationTests extends TestBase {

  @Test
  public void testGroupCreation() throws Exception {
    app.getNavigationHelper().gotoGroupPage();
    List<GroupData> before = app.getGroupHelper().getGroupList();
    GroupData group = new GroupData("test1", "test2", null);
    app.getGroupHelper().createGroup(group);
    List<GroupData> after = app.getGroupHelper().getGroupList();
    Assert.assertEquals(after.size(), before.size() + 1);
    app.getNavigationHelper().gotoHomePage();

    before.add(group);
    int max = 0;
    for (GroupData g : after){
      if (g.getId() > max){
        max = g.getId();
      }
    }
    group.setId(max);
    Assert.assertEquals(new HashSet<Object>(before), new HashSet<Object>(after));
  }
}
