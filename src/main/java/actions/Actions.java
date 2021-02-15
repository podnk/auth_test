package actions;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import java.io.IOException;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

public class Actions
{
    public static WebDriver driver;

    public static void waitForPageLoad()
    {
        try
        {
            String pageLoadState = (String) ((JavascriptExecutor) driver).executeScript("return document.readyState");

            for (int i = 0; i < 60; i++)
            {
                if (pageLoadState.equals("complete") || pageLoadState.equals("loaded"))
                {
                    break;
                }
                else
                {
                    Thread.sleep(1000);
                    pageLoadState = (String) ((JavascriptExecutor) driver).executeScript("return document.readyState");
                }
            }
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }

    public static void waitForElementPresent(By by) throws InterruptedException
    {
        for (int i = 0; i < 30; i++)
        {
            try
            {
                driver.findElement(by);
                break;
            }
            catch (Exception ex)
            {
                Thread.sleep(1000);
                ex.printStackTrace();
            }
        }
    }

    public void setupDriver()
    {
        System.setProperty("webdriver.chrome.driver", "chromedriver\\chromedriver.exe");

        DesiredCapabilities chrome = DesiredCapabilities.chrome();

        try
        {
            chrome.setCapability("chrome.switches",
                    Arrays.asList("--disk-cache-size=1", "--media-cache-size=1", "--disable-extensions"));

            ChromeOptions options = new ChromeOptions();
            options.addArguments("--incognito");
            options.addArguments("--start-maximized");

            driver = new ChromeDriver(options);
            driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
    }

    public void closeDriver()
    {
        try
        {
            driver.close();
        }
        catch (Exception ignored){}

        try
        {
            if (System.getProperty("os.name").contains("Windows"))
            {
                Runtime.getRuntime().exec("taskkill /F /IM chromedriver.exe");
                System.out.println("Windows chromedriver process successfully killed");
            }
            else
            {
                Runtime.getRuntime().exec("pkill chromedriver");
                System.out.println("UNIX chromedriver process successfully killed");
            }
        }
        catch(IOException ioException)
        {
            ioException.printStackTrace();
        }

        try
        {
            if (System.getProperty("os.name").contains("Windows"))
            {
                Runtime.getRuntime().exec("taskkill /F /IM chrome32.exe");
                System.out.println("Windows Google Chrome process successfully killed");
            }
            else
            {
                Runtime.getRuntime().exec("pkill chrome");
                System.out.println("UNIX Google Chrome process successfully killed");
            }
        }
        catch(IOException ioException)
        {
            ioException.printStackTrace();
        }
    }
}
