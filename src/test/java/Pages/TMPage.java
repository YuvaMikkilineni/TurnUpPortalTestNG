package Pages;

import Utilities.Wait;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.util.List;
import java.util.concurrent.TimeUnit;

import static java.lang.Thread.sleep;
import static org.testng.Assert.*;
//import static sun.jvm.hotspot.utilities.Assert.*;

public class TMPage {

    private WebElement typeDropDown;
    private WebElement timeOption;
    private WebElement codeTextBox;
    private WebElement descriptionTextBox;
    private WebElement priceTextBox;
    private WebElement saveButton;
    //[FindsBy(How.XPath, "//*[@id=\"tmsGrid\"]/div[4]/a[4]/span")]
    private WebElement goToLastPage;
    String actualCode;

    public void createTimeRecord(WebDriver driver) throws InterruptedException {
        //Click on Create new button
        WebElement createNewButton = driver.findElement(By.xpath("//*[@id=\"container\"]/p/a"));
        createNewButton.click();

        //Select Time from TypeCode
        typeDropDown = driver.findElement(By.xpath("//*[@id=\"TimeMaterialEditForm\"]/div/div[1]/div/span[1]/span/span[2]/span"));
        typeDropDown.click();
        sleep(1000);
        timeOption = driver.findElement(By.xpath("//*[@id=\"TypeCode_listbox\"]/li[2]"));
        timeOption.click();

        //Input Code
        codeTextBox = driver.findElement(By.id("Code"));
        codeTextBox.sendKeys("January2024-Y");

        //Input Description
        descriptionTextBox = driver.findElement(By.id("Description"));
        descriptionTextBox.sendKeys("Automation Description Y");

        //Identify the Price TextBox and enter
        priceTextBox = driver.findElement(By.xpath("//*[@id=\"TimeMaterialEditForm\"]/div/div[4]/div/span[1]/span/input[1]"));
        priceTextBox.sendKeys("100");

        //Click on Save Button
        saveButton = driver.findElement(By.id("SaveButton"));
        saveButton.click();
        Wait.WaitToBeClickable(driver, "XPath", "//*[@id=\"tmsGrid\"]/div[4]/a[4]/span", 5);


        //Click on Last Page button
        goToLastPage = driver.findElement(By.xpath("//*[@id=\"tmsGrid\"]/div[4]/a[4]/span"));
        goToLastPage.click();

        Wait.WaitToBeClickable(driver, "XPath", "//*[@id=\"tmsGrid\"]/div[3]/table/tbody/tr[last()]/td[1]", 5);

        //Check if record is present
        actualCode = driver.findElement(By.xpath("//*[@id=\"tmsGrid\"]/div[3]/table/tbody/tr[last()]/td[1]")).getText();

        assertEquals(actualCode, "January2024-Y", "The Record Created Successfully");
    }

    public void editTimeRecord(WebDriver driver) {

        try {
            WebElement goToTheLastPage = driver.findElement(By.xpath("//span[contains(text(),'Go to the last page')]"));
            goToTheLastPage.click();
            int tmRecordPages = Integer.parseInt(driver.findElement(By.xpath("//span[@class='k-state-selected']")).getText());
            System.out.println(tmRecordPages);
            WebElement goToTheFirstPage = driver.findElement(By.xpath("//span[contains(text(),'Go to the first page')]"));
            goToTheFirstPage.click();
            outerloop:
            for (int i = 1 ; i <= tmRecordPages ; i++) {
                List<WebElement> tmPageRows = driver.findElements(By.xpath("//tr[@role='row']"));
                System.out.println(tmPageRows.size());
                for (int j = 1 ; j <= tmPageRows.size() ; j++) {

                    String firstNames = driver.findElement(By.xpath("//table/tbody/tr[" + j + "]/td[1]")).getText();
                    //Console.WriteLine(firstNames);
                    // Now click the checkBox where first Name would be Will
                    if (firstNames.equals("January2024-Y")) {
                        System.out.println(firstNames);
                        //Wait.WaitToBeClickable(driver,"XPath","//*[@id=\"tmsGrid\"]/div[3]/table/tbody/tr[" + i + "]/td[5]/a[1]",5);
                        WebElement editButton = driver.findElement(By.xpath("//*[@id=\"tmsGrid\"]/div[3]/table/tbody/tr[" + j + "]/td[5]/a[1]"));
                        editButton.click();

                        typeDropDown = driver.findElement(By.xpath("//*[@id=\"TimeMaterialEditForm\"]/div/div[1]/div/span[1]/span/span[2]/span"));
                        typeDropDown.click();
                        Wait.WaitToBeVisible(driver, "XPath", "//*[@id=\"TypeCode_listbox\"]/li[1]", 5);
                        timeOption = driver.findElement(By.xpath("//*[@id=\"TypeCode_listbox\"]/li[1]"));
                        timeOption.click();

                        codeTextBox = driver.findElement(By.id("Code"));
                        codeTextBox.clear();
                        codeTextBox.sendKeys("January2024-Yuva");

                        descriptionTextBox = driver.findElement(By.id("Description"));
                        descriptionTextBox.clear();
                        descriptionTextBox.sendKeys("Automation Testing- Yuva");

                        WebElement priceTag = driver.findElement(By.xpath("//*[@id=\"TimeMaterialEditForm\"]/div/div[4]/div/span[1]/span/input[1]"));
                        priceTag.click();

                        priceTextBox = driver.findElement(By.id("Price"));
                        priceTextBox.clear();

                        priceTag.click();

                        priceTextBox.sendKeys("50");

                        saveButton = driver.findElement(By.id("SaveButton"));
                        saveButton.click();

                        break outerloop;
                    }
                }
                driver.findElement(By.xpath("//span[contains(text(),'Go to the next page')]")).click();
            }
                //while (!driver.findElement(By.xpath("//*[@id=\"tmsGrid\"]/div[4]/a[3]")).getAttribute("class").endsWith("disabled"));


            sleep(2000);

            goToLastPage = driver.findElement(By.xpath("//*[@id=\"tmsGrid\"]/div[4]/a[4]/span"));
            goToLastPage.click();
            sleep(2000);

            WebElement editCode = driver.findElement(By.xpath("//*[@id=\"tmsGrid\"]/div[3]/table/tbody/tr[last()]/td[1]"));

            assertNotEquals(actualCode, editCode.getText(), "The Record Updated Successfully");
        }
        catch(Exception ex)
            {
                fail("Error Message in Catch ..." + ex.getMessage());
                //assertFalse(1==0,"Error Message in Catch ..." + ex.getMessage());
            }

            //if (editCode.Text != testActual)
            //{
            //    Console.WriteLine("Updated successfull");
            //}
            //else
            //{
            //    Console.WriteLine("Record Not Updated");
            //}
        }


        public void deleteTimeRecord (WebDriver driver) throws InterruptedException {

            goToLastPage = driver.findElement(By.xpath("//*[@id=\"tmsGrid\"]/div[4]/a[4]/span"));
            goToLastPage.click();
            sleep(2000);

            WebElement deleteCode = driver.findElement(By.xpath("//*[@id=\"tmsGrid\"]/div[3]/table/tbody/tr[last()]/td[1]"));

            if (deleteCode.getText().equals("January2024-Yuva")) {

                //Delete the Record January2024-Yuva
                WebElement deleteButton = driver.findElement(By.xpath("//*[@id=\"tmsGrid\"]/div[3]/table/tbody/tr[last()]/td[5]/a[2]"));
                driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
                deleteButton.click();

                Alert deleteAlert = driver.switchTo().alert();
                deleteAlert.accept();

                actualCode = driver.findElement(By.xpath("//*[@id=\"tmsGrid\"]/div[3]/table/tbody/tr[last()-1]/td[1]")).getText();

                assertFalse(actualCode.equals("January2024-Y"), "Record Deleted successfully");

                //if (actualCode.Text != "January2024-Y")
                //{
                //    Console.WriteLine("Deleted successfull");
                //}
                //else
                //{
                //    Console.WriteLine("Record Not Deleted");
                //}
            }
        }
    }

