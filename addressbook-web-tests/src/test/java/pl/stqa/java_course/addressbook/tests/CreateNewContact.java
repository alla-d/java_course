package pl.stqa.java_course.addressbook.tests;

import org.testng.annotations.Test;
import pl.stqa.java_course.addressbook.model.ContactData;
import pl.stqa.java_course.addressbook.model.Contacts;

import java.util.Set;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class CreateNewContact extends TestBase{

  @Test
  public void testCreateNewContact() {
    Set<ContactData> before = app.contact().all();
    ContactData contact = new ContactData().withContactName("name").withLastName("surname")
            .withAddress("address").withHomePhone("111").withWorkPhone("333").withMobilePhone("222").
                    withEmail("aaa").withEmail2("bbb").withEmail3("ccc").withGroup("[none]");
    app.contact().create((contact), true);
    assertThat(app.contact().count(), equalTo(before.size() + 1));
    Set<ContactData> after = app.contact().all();
   // assertThat(after, equalTo(before.withAdded(
   //         contact.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt()))));

  }

}
