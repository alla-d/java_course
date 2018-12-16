package pl.stqa.java_course.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pl.stqa.java_course.addressbook.model.ContactData;
import java.util.Comparator;
import java.util.List;
import java.util.Set;

public class ContactModificationTest extends TestBase {

  @BeforeMethod
  public void ensurePreconditions(){
    if (app.contact().all().size() == 0){
      app.contact().create(new ContactData()
              .withContactName("Name").withLastName("Surname")
              .withMobilePhone("01234568").withEmail("test@test.com")
              .withGroup("[none]"), true);
    }
    app.goTo().homePage();
  }

  @Test
  public void testContactModification(){
    Set<ContactData> before = app.contact().all();
    ContactData modifiedContact = before.iterator().next();
    ContactData contact = new ContactData()
            .withId(modifiedContact.getId()).withContactName("update").withLastName("update")
            .withAddress("adres").withMobilePhone("(+11)0123456").withEmail("test@test.com");
    app.contact().modify(contact);
    Set<ContactData> after = app.contact().all();
    Assert.assertEquals(after.size(), before.size());

    before.remove(modifiedContact);
    before.add(contact);
    Assert.assertEquals(before, after);
      }



}
