package pl.stqa.java_course.addressbook;

import org.testng.annotations.Test;

public class CreateNewContact extends TestBase{

  @Test
  public void testCreateNewContact() throws Exception {
    gotoNewContactPage();
    fillContactForm(new GroupDataContact("Name", "Middle name", "Surname", "test1", "Mr", "Test", "test, test street, 01000", "0123456", "1234567", "01234568", "0123", "test@test.com", "test, test, 01000", "1", "January", "1990"));
    submitContactCreation();
  }

}
