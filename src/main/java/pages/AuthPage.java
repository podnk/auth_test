package pages;

import actions.Actions;
import conf.Conf;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;

public class AuthPage extends Actions
{
    private static final Logger log = Logger.getLogger(AuthPage.class);

    public AuthPage()
    {
        startTest();
    }

    public static void startTest()
    {
        driver.get(Conf.getProperty("main_page"));
        waitForPageLoad();
        log.info("Page loaded: " + Conf.getProperty("main_page"));

        driver.findElement(By.xpath("//*[@id=\"udemy\"]/div[1]/div[1]/div[2]/div[6]/a")).click();

        driver.findElement(By.id("id_fullname")).sendKeys(Conf.getProperty("name"));
        log.info("Name has been typed: " + "\"" + Conf.getProperty("name") + "\"");

        driver.findElement(By.id("email--1")).sendKeys(MailPage.getMail());
        log.info("Email has been typed: " + "\"" + MailPage.getMail() + "\"");

        driver.findElement(By.id("password")).sendKeys(Conf.getProperty("pass"));
        log.info("Name has been typed: " + "\"" + Conf.getProperty("pass") + "\"");

        driver.findElement(By.id("submit-id-submit")).click();
        log.info("Registration completed");
    }
}
