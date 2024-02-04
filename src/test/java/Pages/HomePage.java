package Pages;

import dev.failsafe.internal.util.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import static org.testng.Assert.fail;

public class HomePage {

    public void GoToTMPage(WebDriver driver)
    {
        try
        {
            //Navigate to Time and Material Module
            WebElement adminstrationDropDown = driver.findElement(By.xpath("/html/body/div[3]/div/div/ul/li[5]/a"));
            adminstrationDropDown.click();

            WebElement TMoption = driver.findElement(By.xpath("/html/body/div[3]/div/div/ul/li[5]/ul/li[3]/a"));
            TMoption.click();
        }
        catch (Exception ex)
        {
            Assert.state(1==0,"TM page not loaded sucessfully.. " + ex.getMessage());
        }
    }

    public void VerifyHomePage(WebDriver driver)
    {
        //Check if user has logged in successfully
        WebElement helloHari = driver.findElement(By.xpath("//*[@id=\"logoutForm\"]/ul/li/a"));

        Assert.state(helloHari.getText().equals("Hello hari!"), "User has logged in...");

        //if (helloHari.Text == "Hello hari!")
        //{
        //    Console.WriteLine("User has logged in successfully");
        //}
        //else
        //{
        //    Console.WriteLine("User hasn't ben logged in...");
        //}
    }
}
