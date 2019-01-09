package pl.stqa.java_course.addressbook.tests;

import org.hamcrest.MatcherAssert;
import org.openqa.selenium.By;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pl.stqa.java_course.addressbook.model.ContactData;
import pl.stqa.java_course.addressbook.model.GroupData;
import pl.stqa.java_course.addressbook.model.Groups;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;

public class ContactRemoveFromGroup extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    // if there are no contacts - create one
    if (app.db().contacts().size() == 0) {
      app.goTo().homePage();
      app.contact().create(new ContactData()
              .withContactName("Name").withLastName("Surname")
              .withMobilePhone("222").withEmail("aaa"), true);
      // go to group page and create group if there is no groups
      if (app.db().groups().size() == 0) {
        app.goTo().groupPage();
        app.group().create(new GroupData().withName("test1"));
      }
      // if group
      List<GroupData> list = app.db().groups().stream().collect(Collectors.toList());
      boolean condition = false;

      for (GroupData g : list) {
        if (g.getContacts().size() > 0) {
          condition = true;
        }
      }

      if (condition == false) {
        app.contact().create(new ContactData().withContactName("Joanna").withLastName("Test").inGroup(list.get(0)), true);
      }
    }
  }
    @Test
    public void testContactRemoveFromGroup (){
      app.goTo().homePage();
      Groups groups = app.db().groups();
      contactRemoval(groups.size());
    }

    public void contactRemoval (int x){

      Groups groups = app.db().groups();
      GroupData selectedGroup = groups.stream().collect(Collectors.toList()).get(x - 1);

      if (selectedGroup.getContacts().size() > 0) {
        app.contact().selectGroupRemove(selectedGroup.getName());
        Integer id = app.contact().selectContactAndReturnID(0);
        ContactData contact = app.db().getContact(id);
        app.contact().click(By.name("remove"));
        app.goTo().homePage();
        Set<GroupData> after = contact.getGroups();
        after.remove(selectedGroup);
        MatcherAssert.assertThat(app.db().getContact(id).getGroups(),
                equalTo(contact.getGroups().without(selectedGroup)));
      } else {
        x--;
        contactRemoval(x);
      }
    }
  }




