package pl.stqa.java_course.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pl.stqa.java_course.addressbook.model.ContactData;
import pl.stqa.java_course.addressbook.model.Contacts;
import pl.stqa.java_course.addressbook.model.GroupData;

import java.util.Set;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactsDeletionTest extends TestBase {

  @BeforeMethod
  public void ensurePreconditions(){
    if (app.db().contacts().size() == 0) {
      app.goTo().homePage();
      app.contact().create(new ContactData()
              .withContactName("Name").withLastName("Surname")
              .withMobilePhone("222").withEmail("aaa")
              .withGroup("[none]"), true);
    }
  }

  @Test
  public void testContactDeletion(){
    Contacts before = app.db().contacts();
    ContactData deleteContact = before.iterator().next();
    app.contact().delete(deleteContact);
    Assert.assertEquals(app.contact().count(), before.size() - 1);
    Contacts after = app.db().contacts();
    assertThat(after, equalTo(before.without(deleteContact)));
  }


}
