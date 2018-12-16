package pl.stqa.java_course.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pl.stqa.java_course.addressbook.model.ContactData;
import java.util.List;

public class ContactsDeletionTest extends TestBase {

  @BeforeMethod
  public void ensurePreconditions(){
    if (app.contact().list().size() == 0){
      app.contact().create(new ContactData().withContactName("Name").withLastName("Surname").withMobilePhone("1234567").withEmail("test@test.com").withGroup("[none]"), true);
    }
    app.goTo().homePage();
  }

  @Test
  public void testContactDeletion(){
    List<ContactData> before = app.contact().list();
    int index = before.size() - 1;
    app.contact().delete(index);
    app.goTo().homePage();
    List<ContactData> after = app.contact().list();
    Assert.assertEquals(after.size(), before.size() - 1);

    before.remove(index);
    Assert.assertEquals(before, after);
  }


}
