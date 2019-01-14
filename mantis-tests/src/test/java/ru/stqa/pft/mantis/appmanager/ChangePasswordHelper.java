package ru.stqa.pft.mantis.appmanager;

  import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
//import org.openqa.selenium.WebElement;
import java.util.List;

  /**
   * Created by yana on 4/11/2016.
   */
  public class ChangePasswordHelper extends HelperBase {

    public ChangePasswordHelper(ApplicationManager app){
      super(app);
    }

//    public void selectUser(int id){
//    wd.findElement(By.cssSelector(String.format("//a[contains(@href, 'manage_user_edit_page.php?user_id=%s')]", id);
//    }

    public void start() throws InterruptedException {
      wd.get(app.getProperty("web.baseUrl")+"/login_page.php");
      type(By.name("username"),app.getProperty("web.adminLogin"));
      type(By.name("password"),app.getProperty("web.adminPassword"));
      click(By.cssSelector("input[value='Login']"));
    }

    public String modifyPassword() throws InterruptedException {
      click(By.xpath("//a[@href='/mantisbt-1.2.20/manage_user_page.php']"));
      String s = wd.findElement(By.xpath("/html/body/table[3]/tbody/tr[4]/td[1]/a")).getText();
      click(By.xpath("/html/body/table[3]/tbody/tr[4]/td[1]/a"));
      click(By.cssSelector("input[value='Reset Password']"));
      return s;
    }

    public void finish(String confirmationLink, String password){
      wd.get(confirmationLink);
      type(By.name("password"),password);
      type(By.name("password_confirm"),password);
      click(By.cssSelector("input[value='Update User']"));
    }




  }
