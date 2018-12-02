package pl.stqa.java_course.addressbook.tests;

import org.testng.annotations.Test;
import pl.stqa.java_course.addressbook.model.GroupData;

public class GroupDeletionTests extends TestBase{

  @Test
  public void testGroupDeletion() throws Exception {
    app.getNavigationHelper().gotoGroupPage();
    if (! app.getGroupHelper().isThereAGroup()) {
      app.getGroupHelper().createGroup(new GroupData("test1", "test2", null));
    }
    app.getGroupHelper().selectGroup();
    app.getGroupHelper().deleteSelectedGroups();
    app.getGroupHelper().returnToGroupPage();
  }

}
