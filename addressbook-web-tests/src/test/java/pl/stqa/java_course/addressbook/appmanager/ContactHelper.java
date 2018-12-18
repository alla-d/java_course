package pl.stqa.java_course.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import pl.stqa.java_course.addressbook.model.ContactData;
import pl.stqa.java_course.addressbook.model.Contacts;
import pl.stqa.java_course.addressbook.model.Groups;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


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
    type(By.name("address"), ContactData.getAddress());
    type(By.name("home"), ContactData.getHomePhone());
    type(By.name("mobile"), ContactData.getMobilePhone());
    type(By.name("work"), ContactData.getWorkPhone());
    type(By.name("email"), ContactData.getEmail());
    type(By.name("email2"), ContactData.getEmail2());
    type(By.name("email3"), ContactData.getEmail3());

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
    contactCache = null;
    returnToHomePage();
  }

  public void delete(ContactData contact) {
    selectContactById(contact.getId());
    deleteContacts();
    alertDelete();
    contactCache = null;
    returnToHomePage();
  }

  public void modify(ContactData contact) {
    editContactById(contact.getId());
    fillContactForm(contact, false);
    submitContactModification();
    contactCache = null;
    returnToHomePage();
  }

  public void submitContactCreation() {
    click(By.name("submit"));
  }

  public void returnToHomePage() {
        click(By.linkText("home"));
  }

  public void selectContact(int index) {

    wd.findElements(By.name("selected[]")).get(index).click();
  }

  public void selectContactById(int id) {
    WebElement table = wd.findElement(By.xpath("//*[@id=\"maintable\"]"));

    WebElement row = table.findElement(By.xpath("//tr/td/*[@id='"+id+"']"));

    row.findElement(By.xpath("//td[8]/a/img")).click();
    //wd.findElement(By.cssSelector("input[value='" + id + "']")).click();
  }

  private void selectDetailsById(int id) {
    WebElement table = wd.findElement(By.xpath("//*[@id=\"maintable\"]"));
    WebElement row = table.findElement(By.xpath("//tr/td/*[@id='" + id + "']"));
    row.findElement(By.xpath("//td[7]/a/img")).click();
  }

  public void editContact(int index) {
    wd.findElements(By.xpath("//img[@alt='Edit']")).get(index).click();
  }

  public void editContactById(int id) {

    wd.findElement(By.cssSelector(String.format("a[href='edit.php?id=%s']", id))).click();
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
    if (isAlertPresent()){
    wd.switchTo().alert().accept();
  }
  }
  public boolean isThereAContact() {
    return isElementPresent(By.name("selected[]"));
  }

  public int count() {

    return wd.findElements(By.name("selected[]")).size();
  }

//  public List<ContactData> list() {
//    List<ContactData> contacts = new ArrayList<ContactData>();
//    List<WebElement> elements = wd.findElements(By.name("entry"));
//    for (WebElement element : elements) {
//      String name = element.findElement(By.cssSelector("td:nth-child(3)")).getText();
//      String lastName = element.findElement(By.cssSelector("td:nth-child(2)")).getText();
//      int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
//      ContactData contact = new ContactData().withId(id).withContactName(name).withLastName(lastName);
//      contacts.add(contact);
//    }
//    return contacts;
//  }

  private Contacts contactCache = null;

  public Contacts all() {
    if (contactCache != null) {
      return new Contacts(contactCache);
    }
    contactCache = new Contacts();
    List<WebElement> elements = wd.findElements(By.xpath("//tr[.//input[@name='selected[]']]"));
    for (WebElement element : elements) {
      int id = Integer.parseInt(element.findElement(By.xpath(".//input[@name='selected[]']")).getAttribute("id"));
      String name  = element.findElement(By.xpath(".//td[3]")).getText();
      String lastName = element.findElement(By.xpath(".//td[2]")).getText();
      String address = element.findElement(By.xpath(".//td[4]")).getText();
      String allPhones = element.findElement(By.xpath(".//td[6]")).getText();
      String allEmails = element.findElement(By.xpath(".//td[5]")).getText();
      ContactData contact = new ContactData().withId(id).withContactName(name).withLastName(lastName).
              withAllEmails(allEmails).withAllPhones(allPhones).withAllEmails(allEmails).withAddress(address);
      contactCache.add(contact);
    }
    return new Contacts(contactCache);
  }

  public ContactData infoFromDetailsPage(ContactData contact) {
    selectDetailsById(contact.getId());
    ContactData contactDetails = new ContactData();
    String[] allText = wd.findElement(By.xpath("//*[@id=\"content\"]")).getText().split("\n");
    for (int i = 0; i < allText.length; i++) {
    }
    String[] names = allText[0].split(" ");
    String address = allText[1];
    String homePhone = allText[3].replaceAll("[^0-9]", "");
    String mobilePhone = allText[4].replaceAll("[^0-9]", "");
    String workPhone = allText[5].replaceAll("[^0-9]", "");
    String email = allText[7];
    String email2 = allText[8];
    String email3 = allText[9];
    String[] groups = allText[12].split(" ");


    contactDetails.withContactName(names[0]).withLastName(names[1]).withAddress(address).withHomePhone(homePhone).withMobilePhone(mobilePhone)
            .withWorkPhone(workPhone).withEmail(email).withEmail2(email2).withEmail3(email3).withGroup(groups[2]);

    wd.navigate().back();
    return contactDetails;
  }

  public ContactData infoFromEditForm(ContactData contact) {
    selectContactById(contact.getId());
    String firstname = wd.findElement(By.name("firstname")).getAttribute("value");
    String lastname = wd.findElement(By.name("lastname")).getAttribute("value");
    String home = wd.findElement(By.name("home")).getAttribute("value");
    String mobile = wd.findElement(By.name("mobile")).getAttribute("value");
    String work = wd.findElement(By.name("work")).getAttribute("value");
    String address = wd.findElement(By.name("address")).getAttribute("value");
    String email = wd.findElement(By.name("email")).getAttribute("value");
    String email2 = wd.findElement(By.name("email2")).getAttribute("value");
    String email3 = wd.findElement(By.name("email3")).getAttribute("value");
    wd.navigate().back();
    return new ContactData().withId(contact.getId()).withContactName(firstname).withLastName(lastname)
            .withEmail(email).withEmail2(email2).withEmail3(email3)
            .withHomePhone(home).withMobilePhone(mobile).withWorkPhone(work).withAddress(address);
  }

  private void initContactModificationById(int id) {
    WebElement checkbox = wd.findElement(By.cssSelector(String.format("input[value='%s']", id)));
    WebElement row = checkbox.findElement(By.xpath("./../.."));
    List<WebElement> cells = row.findElements(By.tagName("td"));
    cells.get(7).findElement(By.tagName("a")).click();

//    wd.findElement(By.xpath(String.format("//input[@value='%s']/../../td[8]/a", id))).click();
//    wd.findElement(By.xpath(String.format("//tr[.//input[@value='%s']]/td[8]/a", id))).click();
//    wd.findElement(By.cssSelector(String.format("a[href='edit.php?id=%s']", id))).click();
  }
}