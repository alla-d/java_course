package pl.stqa.java_course.addressbook.tests;

import org.testng.annotations.Test;
import pl.stqa.java_course.addressbook.model.GroupDataContact;

public class ContactModificationTest extends TestBase {

  @Test
  public void testContactModification(){
    app.getContactHelper().selectContact();
    app.getContactHelper().fillContactForm(new GroupDataContact("update", "Middle name2", "Surname update", "test update", "Mrs", "Test update", "test, update street, 01000", "(+11)0123456", "1234567", "01234568", "0123", "test@test.com", "test, test, 01000"));
    app.getContactHelper().submitContactModification();
  }
}
