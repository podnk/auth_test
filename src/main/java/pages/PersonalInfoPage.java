package pages;

import actions.Actions;
import conf.Conf;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.LocalFileDetector;
import org.openqa.selenium.remote.RemoteWebElement;
import java.io.File;

public class PersonalInfoPage extends Actions
{
    private static final Logger log = Logger.getLogger(PersonalInfoPage.class);

    public PersonalInfoPage() throws InterruptedException
    {
        startTest();
    }

    public static void startTest() throws InterruptedException
    {
        driver.get(Conf.getProperty("personal_page"));

        waitForPageLoad();
        log.info("Page loaded: " + Conf.getProperty("personal_page"));

        driver.findElement(By.xpath("//*[@id=\"main-section\"]/div[1]/ul/li[3]/a")).click();

        LocalFileDetector detector = new LocalFileDetector();
        WebElement fileInput = driver.findElement(By.xpath("//*[@id=\"FileUploaderS3-0--1\"]"));
        File file = detector.getLocalFile("test.png");
        ((RemoteWebElement)fileInput).setFileDetector(detector);
        fileInput.sendKeys(file.getAbsolutePath());
        waitForElementPresent(By.xpath("//*[@id=\"tooltip-reference-image_file\"]/div/div/div/div[2]/button"));
        log.info("Image has been uploaded");

        driver.findElement(By.id("submit-id-submit")).click();
        log.info("Image has been saved");
    }
}
