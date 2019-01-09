package pl.stqa.java_course.addressbook.tests;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import com.thoughtworks.xstream.XStream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pl.stqa.java_course.addressbook.model.ContactData;
import pl.stqa.java_course.addressbook.model.Contacts;
import pl.stqa.java_course.addressbook.model.GroupData;
import pl.stqa.java_course.addressbook.model.Groups;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class CreateNewContact extends TestBase {

  @BeforeMethod
  public void ensurePreconditions(){
    if (app.db().groups().size()==0){
      app.goTo().groupPage();
      app.group().create(new GroupData().withName("test1"));
    }
  }

/*
  @DataProvider
  public Iterator<Object[]> validContactsFromCsv() throws IOException {
    List<Object[]> list = new ArrayList<Object[]>();
    try (BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/contacts.csv")))) {
      String line = reader.readLine();
      while (line != null) {
        String[] split = line.split(";");
        list.add(new Object[]{new ContactData().withContactName(split[0]).withLastName(split[1])
                .withAddress(split[2]).withHomePhone(split[3])
                .withEmail(split[4]).withGroup(split[5])});
        line = reader.readLine();
      }
      return list.iterator();
    }
  }
*/

  @DataProvider
  public Iterator<Object[]> validContactsFromXml() throws IOException {
    try (BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/contacts.xml")))) {
      String xml = "";
      String line = reader.readLine();
      while (line != null) {
        xml += line;
        line = reader.readLine();
      }
      XStream xstream = new XStream();
      xstream.processAnnotations(ContactData.class);
      List<ContactData> contacts = (List<ContactData>) xstream.fromXML(xml);
      return contacts.stream().map((g) -> new Object[]{g}).collect(Collectors.toList()).iterator();
    }
  }

  @DataProvider
  public Iterator<Object[]> validContactsFromJson() throws IOException {
    try (BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/contacts.json")))) {
      String json = "";
      String line = reader.readLine();
      while (line != null) {
        json += line;
        line = reader.readLine();
      }
      Gson gson = new Gson();
      List<ContactData> contacts = gson.fromJson(json, new TypeToken<List<ContactData>>() {
      }.getType());
      return contacts.stream().map((g) -> new Object[]{g}).collect(Collectors.toList()).iterator();
    }
  }

//    @Test  (dataProvider = "validContactsFromXml")
//  public void testCreateNewContactFromXml(ContactData contact) {
//    Contacts before = app.db().contacts();
//    app.contact().create(contact, true);
//    assertThat(app.contact().count(), equalTo(before.size() + 1));
//    Contacts after = app.db().contacts();
//    assertThat(after, equalTo(before.withAdded(
//            contact.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt()))));
//  }

  @Test(dataProvider = "validContactsFromJson")
  public void testCreateNewContactFromJson(ContactData contact) {
    Groups groups = app.db().groups();
    app.goTo().homePage();
    Contacts before = app.db().contacts();
    File photo = new File("src/test/resources/test.png");
    contact.withPhoto(photo).inGroup(groups.iterator().next());
    app.contact().create((contact), true);
    assertThat(app.contact().count(), equalTo(before.size() + 1));
    Contacts after = app.contact().all();
    assertThat(after, equalTo(before.withAdded(
            contact.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt()))));
    verifyContactListInUI();
  }

 /* @Test(dataProvider = "validContactsFromCsv")
  public void testCreateNewContactFromCsv(ContactData contact) {
    Contacts before = app.contact().all();
// File photo = new File("src/test/resources/test.png");
//    ContactData contact = new ContactData().withContactName("photo").withLastName("surname")
//            .withAddress("address").withHomePhone("111").withWorkPhone("333").withMobilePhone("222")
//            .withEmail("aaa").withEmail2("bbb").withEmail3("ccc").withGroup("[none]").withPhoto(photo);
    app.contact().create((contact), true);
    assertThat(app.contact().count(), equalTo(before.size() + 1));
    Contacts after = app.contact().all();
    assertThat(after, equalTo(before.withAdded(
            contact.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt()))));
  }
*/
//  @Test // path to photo
//  public void testCurrentDir(){
//    File currentDir = new File(".");
//    System.out.println(currentDir.getAbsoluteFile());
//    File photo = new File("src/test/resources/test.png");
//    System.out.println(photo.getAbsolutePath());
//    System.out.println(photo.exists());
//  }
}
