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
      app.contact().create(new ContactData().withContactName("Name").withLastName("Surname").withMobilePhone("01234568").withEmail("test@test.com").withGroup("[none]"), true);
    }
    app.goTo().homePage();
  }

  @Test
  public void testContactModification(){
    List<ContactData> before = app.contact().list();
    int index = before.size() - 1;
    app.contact().editContact(index);
    ContactData contact = new ContactData().withId(before.get(index).getId()).withContactName("update").withLastName("update").withAddress("adres").withMobilePhone("(+11)0123456").withEmail("test@test.com");
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
