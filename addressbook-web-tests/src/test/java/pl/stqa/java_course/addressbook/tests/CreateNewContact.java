package pl.stqa.java_course.addressbook.tests;

import org.testng.annotations.Test;
import pl.stqa.java_course.addressbook.model.ContactData;
import pl.stqa.java_course.addressbook.model.Contacts;

import java.io.File;
import java.util.Set;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class CreateNewContact extends TestBase{

  @Test
  public void testCreateNewContact() {
    Contacts before = app.contact().all();
    File photo = new File("src/test/resources/test.png");
    ContactData contact = new ContactData().withContactName("photo").withLastName("surname")
            .withAddress("address").withHomePhone("111").withWorkPhone("333").withMobilePhone("222").
                    withEmail("aaa").withEmail2("bbb").withEmail3("ccc").withGroup("[none]").withPhoto(photo);
    app.contact().create((contact), true);
    assertThat(app.contact().count(), equalTo(before.size() + 1));
    Contacts after = app.contact().all();
    assertThat(after, equalTo(before.withAdded(
            contact.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt()))));

  }

//  @Test
//  public void testCurrentDir(){
//    File currentDir = new File(".");
//    System.out.println(currentDir.getAbsoluteFile());
//    File photo = new File("src/test/resources/test.png");
//    System.out.println(photo.getAbsolutePath());
//    System.out.println(photo.exists());
//  }
}
