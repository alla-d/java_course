package pl.stqa.java_course.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import pl.stqa.java_course.addressbook.model.ContactData;

import java.util.ArrayList;
import java.util.List;

public class ContactHelper extends HelperBase {

  public ContactHelper(WebDriver wd) {
    super(wd);
  }

  public void gotoNewContactPage() {
    wd.get("http://localhost/addressbook/edit.php");
  }

  public void fillContactForm(ContactData ContactData, boolean creation) {
    type(By.name("firstname"), ContactData.getContactName());
    type(By.name("lastname"), ContactData.getLastName());
    type(By.name("nickname"), ContactData.getNickname());
    type(By.name("title"), ContactData.getTitle());
    type(By.name("company"), ContactData.getCompany());
    type(By.name("address"), ContactData.getAddress());
    type(By.name("home"), ContactData.getHomePhone());
    type(By.name("mobile"), ContactData.getMobilePhone());
    type(By.name("work"), ContactData.getWorkPhone());
    type(By.name("fax"), ContactData.getFax());
    type(By.name("email"), ContactData.getEmail());
    type(By.name("address2"), ContactData.getAddress2());

    if (creation) {
      new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(ContactData.getGroup());
    } else {
      Assert.assertFalse(isElementPresent(By.name("new_group")));
    }
  }
  public void create(ContactData contact, boolean creation) {
    gotoNewContactPage();
    fillContactForm(contact, creation);
    submitContactCreation();
  }

  public void delete(int index) {
    selectContact(index);
    deleteContacts();
    alertDelete();
  }


  public void modify(ContactData contact) {
    fillContactForm(contact, false);
    submitContactModification();
  }

  public void submitContactCreation() {
    click(By.name("submit"));
  }

  public void selectContact(int index) {

    wd.findElements(By.name("selected[]")).get(index).click();
  }

  public void editContact(int index) {
    wd.findElements(By.xpath("//img[@alt='Edit']")).get(index).click();
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

  public void alertDelete() {
    wd.switchTo().alert().accept();
  }



  public boolean isThereAContact() {

    return isElementPresent(By.name("selected[]"));
  }

  public int getContactCount() {

    return wd.findElements(By.name("selected[]")).size();
  }


  public List<ContactData> list() {
    List<ContactData> contacts = new ArrayList<ContactData>();
    List<WebElement> elements = wd.findElements(By.name("entry"));
    for (WebElement element : elements) {
      String contactName = element.findElement(By.cssSelector("td:nth-child(3)")).getText();
      String lastName = element.findElement(By.cssSelector("td:nth-child(2)")).getText();
      int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
      ContactData contact = new ContactData(id, contactName, lastName, null, null, null, null, null, null, null, null, null, null, null);
      contacts.add(contact);
     }
    return contacts;
  }
}