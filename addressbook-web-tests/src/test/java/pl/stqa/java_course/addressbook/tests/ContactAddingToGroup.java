package pl.stqa.java_course.addressbook.tests;

import org.openqa.selenium.By;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pl.stqa.java_course.addressbook.model.ContactData;
import pl.stqa.java_course.addressbook.model.GroupData;
import pl.stqa.java_course.addressbook.model.Groups;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;


public class ContactAddingToGroup extends TestBase {

  @BeforeMethod
  public void ensurePreconditions(){
    // go to group page and create group if there is no groups
    if (app.db().groups().size() == 0) {
      app.goTo().groupPage();
      app.group().create(new GroupData().withName("test1"));
    }

    // if there are no contacts - create one
    if (app.db().contacts().size() == 0){
      app.goTo().homePage();
      app.contact().create(new ContactData()
              .withContactName("Name").withLastName("Surname")
              .withMobilePhone("222").withEmail("aaa"), true);
    }
  }

  @Test
  public void testContactAddingToGroup() {
    app.goTo().homePage();
    Integer id = selectRandomContactID();
    ContactData contact = app.db().getContact(id);
    Groups groups = app.db().groups();

    List<GroupData> available = new ArrayList<>();
    available.addAll(groups);
    available.removeAll(contact.getGroups());

    if(available.size()>0){
      app.contact().selectGroupAdd(available.get(0).getName());
      app.contact().addGroup();
      assertThat(app.db().getContact(contact.getId()).getGroups(),
              equalTo(contact.getGroups().withAdded(available.get(0))));
    }
    else{
      app.goTo().groupPage();
      app.group().create(new GroupData().withName("newgroup "+ new Date()));
      testContactAddingToGroup();
    }
  }

  private int selectRandomContactID() {
    return app.contact().selectContactAndReturnID(Math.abs(new Random().nextInt())%app.db().getContactListSize());
  }
}
