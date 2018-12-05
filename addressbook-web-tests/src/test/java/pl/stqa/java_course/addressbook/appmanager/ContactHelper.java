package pl.stqa.java_course.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import pl.stqa.java_course.addressbook.model.GroupDataContact;

public class ContactHelper extends HelperBase {

  public ContactHelper(WebDriver wd) {
    super(wd);
  }

  public void gotoNewContactPage() {
    wd.get("http://localhost/addressbook/edit.php");
  }

  public void fillContactForm(GroupDataContact groupDataContact, boolean creation) {
    type(By.name("firstname"), groupDataContact.getContactName());
    type(By.name("middlename"), groupDataContact.getMiddleName());
    type(By.name("lastname"), groupDataContact.getLastName());
    type(By.name("nickname"), groupDataContact.getNickname());
    type(By.name("title"), groupDataContact.getTitle());
    type(By.name("company"), groupDataContact.getCompany());
    type(By.name("address"), groupDataContact.getAddress());
    type(By.name("home"), groupDataContact.getHomePhone());
    type(By.name("mobile"), groupDataContact.getMobilePhone());
    type(By.name("work"), groupDataContact.getWorkPhone());
    type(By.name("fax"), groupDataContact.getFax());
    type(By.name("email"), groupDataContact.getEmail());
    type(By.name("address2"), groupDataContact.getAddress2());

    if (creation) {
      new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(groupDataContact.getGroup());
    } else {
      Assert.assertFalse(isElementPresent(By.name("new_group")));
    }
  }

  public void submitContactCreation() {
    click(By.name("submit"));
  }

  public void selectContact() {
    click(By.name("selected[]"));
  }

  public void editContact() {
    click(By.xpath("//img[@alt='Edit']"));
  }

  public void submitContactModification() {
    click(By.name("update"));
  }

  public void selectContacts() {
    click(By.id("MassCB"));
  }

  public void deleteContacts() {
    click(By.xpath("//input[@value='Delete']"));
  }

  public void alertDelete () {
    wd.switchTo().alert().accept();
  }

  public void createContact(GroupDataContact contact, boolean creation) {
  gotoNewContactPage();
  fillContactForm(contact, creation);
  submitContactCreation();
  }

  public boolean isThereAContact() {
    return isElementPresent(By.name("selected[]"));
  }

  public int getContactCount() {
    return wd.findElements(By.name("selected[]")).size();
  }
}
