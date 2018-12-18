package pl.stqa.java_course.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pl.stqa.java_course.addressbook.model.ContactData;
import java.util.Arrays;
import java.util.stream.Collectors;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;


public class ContactEmailAddressTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    app.goTo().homePage();
    if (app.contact().all().size() == 0) {
      app.contact().create(new ContactData().withContactName("Test").withLastName("Surname").withHomePhone("111").
              withMobilePhone("222").withWorkPhone("333")
              .withEmail("aaa").withEmail2("bbb").withEmail3("ccc"), true);
    }
  }

  @Test(enabled = false)
  public void testEmailAddress() {
    app.goTo().homePage();
    ContactData contact = app.contact().all().iterator().next();
    ContactData contactInfoFromEditForm = app.contact().infoFromEditForm(contact);
    assertThat(contact.getAllEmails(), equalTo(mergeEmails(contactInfoFromEditForm)));
    assertThat(contact.getAddress(), equalTo(mergeAddress(contactInfoFromEditForm)));
  }

  private String mergeEmails(ContactData contact) {
    return Arrays.asList(contact.getEmail(), contact.getEmail2(), contact.getEmail3())
            .stream().filter((s) -> !s.equals(""))
            .map(ContactEmailAddressTests::cleanedTxt)
            .collect(Collectors.joining("\n"));

  }
  private String mergeAddress(ContactData contact) {
    return Arrays.asList(contact.getAddress())
            .stream().filter((s) -> !s.equals(""))
            .map(ContactEmailAddressTests::cleanedTxt)
            .collect(Collectors.joining("\n"));
  }


  public static String cleanedTxt(String txt) {

    return txt.replaceAll("\\s", "").replaceAll("[-()]", "");
  }
}


