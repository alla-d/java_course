package pl.stqa.java_course.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pl.stqa.java_course.addressbook.model.ContactData;

import java.util.Comparator;
import java.util.List;

public class CreateNewContact extends TestBase{

  @Test
  public void testCreateNewContact() throws Exception {
    List<ContactData> before = app.getContactHelper().getContactList();
    ContactData contact = new ContactData( "name", "surname", null, null, null, null, null, null, null, null, null, null, "[none]");
    app.getContactHelper().createContact((contact), true);
    app.getNavigationHelper().gotoHomePage();
    List<ContactData> after = app.getContactHelper().getContactList();
    Assert.assertEquals(after.size(), before.size() + 1);

    before.add(contact);
    Comparator<? super ContactData> byId = (g1, g2) -> Integer.compare(g1.getId(), g2.getId());
    before.sort(byId);
    after.sort(byId);
    Assert.assertEquals(after, before);

  }

}
