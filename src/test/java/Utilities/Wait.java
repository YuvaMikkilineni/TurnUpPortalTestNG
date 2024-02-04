package Utilities;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;

import java.time.Duration;


public class Wait {

    public static void WaitToBeClickable(WebDriver driver, String locatorType, String locatorValue, int seconds)
    {
        var wait = new WebDriverWait(driver, Duration.ofSeconds(seconds));

        if (locatorType.equals("XPath"))
        {
            wait.until(ExpectedConditions.elementToBeClickable(By.xpath(locatorValue)));
        }
        if (locatorType.equals("Id"))
        {
            wait.until(ExpectedConditions.elementToBeClickable(By.id(locatorValue)));
        }
        if (locatorType.equals("CssSelector"))
        {
            wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(locatorValue)));
        }
        if (locatorType.equals("Name"))
        {
            wait.until(ExpectedConditions.elementToBeClickable(By.name(locatorValue)));
        }

    }

    public static void WaitToBeVisible(WebDriver driver, String locatorType, String locatorValue, int seconds)
    {
        var wait = new WebDriverWait(driver, Duration.ofSeconds(seconds));

        if (locatorType.equals("XPath"))
        {
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locatorValue)));
        }
        if (locatorType.equals("Id"))
        {
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(locatorValue)));
        }
        if (locatorType.equals("CssSelector"))
        {
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(locatorValue)));
        }
        if (locatorType.equals("Name"))
        {
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.name(locatorValue)));
        }
    }

}
