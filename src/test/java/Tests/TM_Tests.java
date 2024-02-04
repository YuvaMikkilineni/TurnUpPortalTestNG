package Tests;

import Pages.*;
import Utilities.CommonDriver;
import net.bytebuddy.build.Plugin;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class TM_Tests extends CommonDriver {

    @BeforeTest
    public void SetUpTM()
    {
        //Open Chrome Browser
        driver = new ChromeDriver();

        LoginPage loginPage = new LoginPage();
        loginPage.LoginActions(driver);

        HomePage homePage = new HomePage();
        homePage.VerifyHomePage(driver);
        homePage.GoToTMPage(driver);
    }

    @Test(priority = 1)
    public void testCreateTimeRecord() throws InterruptedException
    {
        TMPage tMPage = new TMPage();
        tMPage.createTimeRecord(driver);
    }

    @Test(priority = 2)
    public void testEditTimeRecord()
    {
        TMPage tMPage = new TMPage();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        tMPage.editTimeRecord(driver);
    }

    @Test(priority = 3)
    public void TestDeleteTimeRecord() throws InterruptedException
    {
        TMPage tMPage = new TMPage();
        driver.manage().timeouts().implicitlyWait(5,TimeUnit.SECONDS);
        tMPage.deleteTimeRecord(driver);
    }

    @AfterTest
    public void CloseTestRun()
    {
        driver.quit();
    }

}
