package pl.stqa.java_course.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import pl.stqa.java_course.addressbook.model.ContactData;
import pl.stqa.java_course.addressbook.model.Contacts;

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
    //attach(By.name("photo"), ContactData.getPhoto());
    type(By.name("address"), ContactData.getAddress());
    type(By.name("home"), ContactData.getHomePhone());
    type(By.name("mobile"), ContactData.getMobilePhone());
    type(By.name("work"), ContactData.getWorkPhone());
    type(By.name("email"), ContactData.getEmail());
    type(By.name("email2"), ContactData.getEmail2());
    type(By.name("email3"), ContactData.getEmail3());


    if (creation) {
      if (ContactData.getGroups().size() > 0) {
        Assert.assertTrue(ContactData.getGroups().size() == 1);
        new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(ContactData.getGroups().iterator().next().getName());
      } else {
        Assert.assertFalse(isElementPresent(By.name("new_group")));
      }
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
    selectContactById(contact.getId());
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

//  public void selectContactById(int id) {
//    WebElement table = wd.findElement(By.xpath("//*[@id=\"maintable\"]"));
//    WebElement row = table.findElement(By.xpath("//tr/td/*[@id='"+id+"']"));
//    row.findElement(By.xpath("//td[8]/a/img")).click();
//  //  wd.findElement(By.cssSelector("input[value='" + id + "']")).click();
//  }

  private void selectDetailsById(int id) {
    wd.findElement(By.cssSelector(String.format("a[href='view.php?id=%s']", id))).click();
//    WebElement table = wd.findElement(By.xpath("//*[@id=\"maintable\"]"));
//    WebElement row = table.findElement(By.xpath("//tr/td/*[@id='" + id + "']"));
  //  row.findElement(By.xpath("//td[7]/a/img")).click();
  }

  public void editContact(int index) {
    wd.findElements(By.xpath("//img[@alt='Edit']")).get(index).click();
  }

  public void selectContactById(int id) {

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

  private Contacts contactCache = null;

  public Contacts all() {
    if (contactCache != null) {
      return new Contacts(contactCache);
    }
    contactCache = new Contacts();
    List<WebElement> elements = wd.findElements(By.xpath("//tr[.//input[@name='selected[]']]"));
    for (WebElement element : elements) {
      int id = Integer.parseInt(element.findElement(By.xpath(".//input[@name='selected[]']")).getAttribute("id"));

      String lastName = element.findElement(By.xpath(".//td[2]")).getText();
      String name  = element.findElement(By.xpath(".//td[3]")).getText();
      String address = element.findElement(By.xpath(".//td[4]")).getText();
      String allPhones = element.findElement(By.xpath(".//td[6]")).getText();
      String allEmails = element.findElement(By.xpath(".//td[5]")).getText();
      ContactData contact = new ContactData().withId(id).withContactName(name).withLastName(lastName).
              withAllEmails(allEmails).withAllPhones(allPhones).withAllEmails(allEmails).withAddress(address);
      contactCache.add(contact);
    }
    return new Contacts(contactCache);
  }

 public ContactData infoFromDetailsPage(ContactData contact){
   selectDetailsById(contact.getId());
   String details = wd.findElement(By.xpath("//div[@id='content']")).getText();
   wd.navigate().back();
  return new ContactData().withId(contact.getId()).withAllDetails(details);
 }

  public ContactData infoFromEditForm(ContactData contact) {
    selectContactById(contact.getId());
    String name = wd.findElement(By.name("firstname")).getAttribute("value");
    String lastName = wd.findElement(By.name("lastname")).getAttribute("value");
    String home = wd.findElement(By.name("home")).getAttribute("value");
    String mobile = wd.findElement(By.name("mobile")).getAttribute("value");
    String work = wd.findElement(By.name("work")).getAttribute("value");
    String address = wd.findElement(By.name("address")).getAttribute("value");
    String email = wd.findElement(By.name("email")).getAttribute("value");
    String email2 = wd.findElement(By.name("email2")).getAttribute("value");
    String email3 = wd.findElement(By.name("email3")).getAttribute("value");
    wd.navigate().back();
    return new ContactData().withId(contact.getId()).withContactName(name).withLastName(lastName)
            .withHomePhone(home).withMobilePhone(mobile).withWorkPhone(work).withAddress(address)
            .withEmail(email).withEmail2(email2).withEmail3(email3)
           ;
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