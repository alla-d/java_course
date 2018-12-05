package pl.stqa.java_course.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pl.stqa.java_course.addressbook.model.GroupData;

public class GroupModificationTests extends TestBase {

  @Test
  public void testGroupModification(){
    app.getNavigationHelper().gotoGroupPage();
    int before = app.getGroupHelper().getGroupCount();
    if (! app.getGroupHelper().isThereAGroup()) {
      app.getGroupHelper().createGroup(new GroupData("test1", "test2", null));
    }
    app.getGroupHelper().selectGroup(before - 1);
    app.getGroupHelper().initGroupModification();
    app.getGroupHelper().fillGroupForm(new GroupData("update", "test2", "test3"));
    app.getGroupHelper().submitGroupModificaton();
    app.getGroupHelper().returnToGroupPage();
    int after = app.getGroupHelper().getGroupCount();
    Assert.assertEquals(after, before);
  }
}
