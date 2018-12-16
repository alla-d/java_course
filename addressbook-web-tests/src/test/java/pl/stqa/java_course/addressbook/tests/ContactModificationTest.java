package pl.stqa.java_course.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pl.stqa.java_course.addressbook.model.ContactData;
import java.util.Comparator;
import java.util.List;

public class ContactModificationTest extends TestBase {

  @BeforeMethod
  public void ensurePreconditions(){
    if (app.contact().list().size() == 0){
      app.contact().create(new ContactData("Name", "Surname",
              null,  null, null,
              null, "0123456", "1234567",
              "01234568", "0123", "test@test.com", null, "[none]"), true);
    }
    app.goTo().homePage();
  }

  @Test
  public void testContactModification(){
    List<ContactData> before = app.contact().list();
    int index = before.size() - 1;
    app.contact().editContact(index);
    ContactData contact = new ContactData(before.get(index).getId(), "update",
            "update", null, null, null,
            null, "(+11)0123456", "1234567",
            "01234568", "0123", "test@test.com", null, null);
    app.contact().modify(contact);
    app.goTo().homePage();
    List<ContactData> after = app.contact().list();
    Assert.assertEquals(after.size(), before.size());

    before.remove(index);
    before.add(contact);
    Comparator<? super ContactData> byId = (c1, c2) -> Integer.compare(c1.getId(), c2.getId());
    before.sort(byId);
    after.sort(byId);
    Assert.assertEquals(before, after);
      }



}
