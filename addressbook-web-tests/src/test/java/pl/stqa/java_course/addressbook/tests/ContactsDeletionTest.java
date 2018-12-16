package pl.stqa.java_course.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pl.stqa.java_course.addressbook.model.ContactData;
import java.util.List;
import java.util.Set;

public class ContactsDeletionTest extends TestBase {

  @BeforeMethod
  public void ensurePreconditions(){
    if (app.contact().all().size() == 0){
      app.contact().create(new ContactData().withContactName("Name").withLastName("Surname").withMobilePhone("1234567").withEmail("test@test.com").withGroup("[none]"), true);
    }
    app.goTo().homePage();
  }

  @Test
  public void testContactDeletion(){
    Set<ContactData> before = app.contact().all();
    ContactData deleteContact = before.iterator().next();
    app.contact().delete(deleteContact);
    Set<ContactData> after = app.contact().all();
    Assert.assertEquals(after.size(), before.size() - 1);

    before.remove(deleteContact);
    Assert.assertEquals(before, after);
  }


}
