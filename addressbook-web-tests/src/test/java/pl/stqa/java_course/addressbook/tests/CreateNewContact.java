package pl.stqa.java_course.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pl.stqa.java_course.addressbook.model.ContactData;
import java.util.Set;

public class CreateNewContact extends TestBase{

  @Test
  public void testCreateNewContact() {
    Set<ContactData> before = app.contact().all();
    ContactData contact = new ContactData().withContactName("name").withLastName("surname").withGroup("[none]");
    app.contact().create((contact), true);
    app.goTo().homePage();
    Set<ContactData> after = app.contact().all();
    Assert.assertEquals(after.size(), before.size() + 1);

    contact.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt());
    before.add(contact);
    Assert.assertEquals(after, before);

  }

}
