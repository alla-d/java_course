package pl.stqa.java_course.addressbook.tests;

import org.testng.annotations.Test;

public class ContactsDeletionTest extends TestBase {

  @Test
  public void testContactDeletion(){
    app.getContactHelper().selectContacts();
    app.getContactHelper().deleteContacts();
    app.getContactHelper().alertDelete();
  }
}
