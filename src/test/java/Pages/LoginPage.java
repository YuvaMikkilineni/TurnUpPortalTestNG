package Pages;

import dev.failsafe.internal.util.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.AssertJUnit.*;
import static org.testng.Assert.*;


public class LoginPage {

    //[FindsBy(How = How.Name , Using = "UserName")]
    //IWebElement usernameTextBox;

    //[FindsBy(How = How.Name , Using = "Password")]
    //[CacheLookup]
    //IWebElement passwordTextBox;

    public void LoginActions(WebDriver driver)
    {
        //Maximize the browser
        driver.manage().window().maximize();

        try
        {
            //Launch TurnUp Portal and navigate to login page (http://horse.industryconnect.io/Account/Login?ReturnUrl=%2f)
            driver.navigate().to("http://horse.industryconnect.io/Account/Login?ReturnUrl=%2f");

            //identify username textbox and enter valid username
            WebElement usernameTextBox = driver.findElement(By.id("UserName"));
            usernameTextBox.sendKeys("hari");


            //identify password textbox and enter valid username
            WebElement passwordTextBox = driver.findElement(By.id("Password"));
            passwordTextBox.sendKeys("123123");

            //identify the login button and click on the button
            WebElement loginButton = driver.findElement(By.xpath("//*[@id=\"loginForm\"]/form/div[3]/input[1]"));
            loginButton.click();
        }
        catch (Exception ex)
        {
            //Assert.assetEquals


            Assert.isTrue(1==0, "TurnUp Portal didn't launch" + ex.getMessage());
        }
    }
}
