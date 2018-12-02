package pl.stqa.java_course.addressbook.tests;

import org.testng.annotations.Test;
import pl.stqa.java_course.addressbook.model.GroupDataContact;

public class ContactModificationTest extends TestBase {

  @Test
  public void testContactModification(){
    if (! app.getContactHelper().isThereAContact()){
      app.getContactHelper().createContact(new GroupDataContact("Name", "Middle name", "Surname", "test1", "Mr", null, "test, test street, 01000", "0123456", "1234567", "01234568", "0123", "test@test.com", "test, test, 01000", "test1"), true);
    }
    app.getContactHelper().selectContact();
    app.getContactHelper().fillContactForm(new GroupDataContact("update", "Middle name2", "Surname update", "test update", "Mrs", "Test update", "test, update street, 01000", "(+11)0123456", "1234567", "01234568", "0123", "test@test.com", "test, test, 01000", null), false);
    app.getContactHelper().submitContactModification();
  }
}
