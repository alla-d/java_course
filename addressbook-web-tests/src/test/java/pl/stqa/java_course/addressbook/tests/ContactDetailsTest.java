package pl.stqa.java_course.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pl.stqa.java_course.addressbook.model.ContactData;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactDetailsTest extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    app.goTo().homePage();
    if (app.contact().all().size() == 0) {
      app.contact().create(new ContactData()
              .withContactName("name").withLastName("surname")
              .withAddress("address").withHomePhone("111").withWorkPhone("333").withMobilePhone("222")
              .withEmail("aaa"), true);
    }
    app.goTo().homePage();
  }

  @Test (enabled = false)
  public void testContactDetails() {
    app.goTo().homePage();
    ContactData contact = app.contact().all().iterator().next();
    ContactData contactInfoFromEditForm = app.contact().infoFromEditForm(contact);
    ContactData contactInfoFromDetailsPage = app.contact().infoFromDetailsPage(contact);
    assertThat(contactInfoFromEditForm.mergeDetails(), equalTo(contactInfoFromDetailsPage.getAllDetails()));
  }

}
