package pl.stqa.java_course.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pl.stqa.java_course.addressbook.model.ContactData;
import pl.stqa.java_course.addressbook.model.Contacts;

import java.util.Set;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactsDeletionTest extends TestBase {

  @BeforeMethod
  public void ensurePreconditions(){
    if (app.contact().all().size() == 0){
      app.contact().create(new ContactData().withContactName("test").withLastName("Surname")
              .withMobilePhone("1234567").withEmail("test@test.com").withGroup("[none]"), true);
    }
    app.goTo().homePage();
  }

  @Test
  public void testContactDeletion(){
    Contacts before = app.contact().all();
    ContactData deleteContact = before.iterator().next();
    app.contact().delete(deleteContact);
    Assert.assertEquals(app.contact().count(), before.size() - 1);
    Contacts after = app.contact().all();
    assertThat(after, equalTo(before.without(deleteContact)));
  }


}
