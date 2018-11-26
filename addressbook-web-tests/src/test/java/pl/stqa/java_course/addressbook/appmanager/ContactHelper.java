package pl.stqa.java_course.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import pl.stqa.java_course.addressbook.model.GroupDataContact;

public class ContactHelper extends HelperBase {

  public ContactHelper(FirefoxDriver wd) {
    super(wd);
  }

  public void gotoNewContactPage() {
    wd.get("http://localhost/addressbook/edit.php");
  }

  public void fillContactForm(GroupDataContact groupDataContact) {
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
  }

  public void submitContactCreation() {
    click(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Notes:'])[1]/following::input[1]"));
  }
}
