package pl.stqa.java_course.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pl.stqa.java_course.addressbook.model.ContactData;
import java.util.Comparator;
import java.util.List;

public class ContactModificationTest extends TestBase {

  @Test
  public void testContactModification(){

    if (! app.getContactHelper().isThereAContact()){
      app.getContactHelper().createContact(new ContactData("Name", "Surname", null,  null, null,
              null, "0123456", "1234567",
              "01234568", "0123", "test@test.com", null, "[none]"), true);
    }
    app.getNavigationHelper().gotoHomePage();

    List<ContactData> before = app.getContactHelper().getContactList();
    app.getContactHelper().editContact(before.size() - 1);
    ContactData contact = new ContactData(before.get(before.size() - 1).getId(), "update",
            "update", null, null, null,
            null, "(+11)0123456", "1234567",
            "01234568", "0123", "test@test.com", null, null);
    app.getContactHelper().fillContactForm(contact, false);
    app.getContactHelper().submitContactModification();
    app.getNavigationHelper().gotoHomePage();
    List<ContactData> after = app.getContactHelper().getContactList();
    Assert.assertEquals(after.size(), before.size());

    before.remove(before.size() - 1);
    before.add(contact);
    Comparator<? super ContactData> byId = (c1, c2) -> Integer.compare(c1.getId(), c2.getId());
    before.sort(byId);
    after.sort(byId);
    Assert.assertEquals(before, after);
      }

}
