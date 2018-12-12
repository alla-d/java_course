package pl.stqa.java_course.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pl.stqa.java_course.addressbook.model.ContactData;

import java.util.List;

public class ContactsDeletionTest extends TestBase {

  @Test
  public void testContactDeletion(){
    app.getNavigationHelper().gotoHomePage();
    List<ContactData> before = app.getContactHelper().getContactList();
    if (! app.getContactHelper().isThereAContact()){
      app.getContactHelper().createContact(new ContactData("Name", "Surname", "test1", "Mr", null, "test, test street, 01000", "0123456", "1234567", "01234568", "0123", "test@test.com", "test, test, 01000", "[none]"), true);
    }
    app.getContactHelper().selectContact(before.size() - 1);
    app.getContactHelper().deleteContacts();
    app.getContactHelper().alertDelete();
    app.getNavigationHelper().gotoHomePage();
    List<ContactData> after = app.getContactHelper().getContactList();
    Assert.assertEquals(after.size(), before.size() - 1);
    before.remove(before.size() - 1);
    Assert.assertEquals(before, after);
  }

}
