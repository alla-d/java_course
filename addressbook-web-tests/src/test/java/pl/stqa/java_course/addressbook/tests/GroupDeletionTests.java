package pl.stqa.java_course.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pl.stqa.java_course.addressbook.model.GroupData;
import pl.stqa.java_course.addressbook.model.Groups;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

public class GroupDeletionTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    if (app.db().groups().size() == 0) {
      app.goTo().groupPage();
      app.group().create(new GroupData().withName("test1"));
    }
    app.goTo().homePage();
  }

  @Test
  public void testGroupDeletion() {
    Groups before = app.db().groups();
    GroupData deleteGroup = before.iterator().next();
    app.goTo().groupPage();
    app.group().delete(deleteGroup);
    assertEquals(app.contact().count(), before.size() - 1);
    Groups after = app.db().groups();
    assertThat(after, equalTo(before.without(deleteGroup)));
  }

}
