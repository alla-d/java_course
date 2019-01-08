package pl.stqa.java_course.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pl.stqa.java_course.addressbook.model.ContactData;
import pl.stqa.java_course.addressbook.model.Contacts;

import java.util.Set;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.*;
import static org.testng.Assert.assertEquals;

public class ContactModificationTest extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    if (app.db().contacts().size() == 0) {
      app.goTo().homePage();
      app.contact().create(new ContactData()
              .withContactName("Name").withLastName("Surname")
              .withMobilePhone("222").withEmail("aaa"), true);
    }
  }

    @Test
    public void testContactModification(){
      Contacts before = app.db().contacts();
      ContactData modifiedContact = before.iterator().next();
      ContactData contact = new ContactData()
              .withId(modifiedContact.getId()).withContactName("update").withLastName("update")
              .withAddress("adres").withMobilePhone("222").withEmail("test@test.com");
      app.contact().modify(contact);
      assertEquals(app.contact().count(), before.size());
      Contacts after = app.db().contacts();
      assertThat(after, equalTo(before.without(modifiedContact).withAdded(contact)));
      verifyContactListInUI();
    }
  }
