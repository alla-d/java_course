package pl.stqa.java_course.addressbook.tests;

import org.testng.annotations.Test;
import pl.stqa.java_course.addressbook.model.ContactData;
import pl.stqa.java_course.addressbook.model.Contacts;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class CreateNewContact extends TestBase{

  @Test
  public void testCreateNewContact() {
    Contacts before = app.contact().all();
    ContactData contact = new ContactData().withContactName("name").withLastName("surname").withEmail("uuuu@oo.pp").withGroup("[none]");
    app.contact().create((contact), true);
    Contacts after = app.contact().all();
    assertThat(after.size(), equalTo(before.size() + 1));
    assertThat(after, equalTo(before.withAdded(
            contact.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt()))));

  }

}
