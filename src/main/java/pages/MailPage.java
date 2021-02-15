package pages;

import actions.Actions;
import conf.Conf;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;

public class MailPage extends Actions
{
    private static final Logger log = Logger.getLogger(MailPage.class);
    private static String tempEmail;

    public static void startTest()
    {
        driver.get(Conf.getProperty("email_page"));
        waitForPageLoad();
        log.info("Page loaded: " + Conf.getProperty("email_page"));

        tempEmail = driver.findElement(By.id("email_ch_text")).getText();
        log.info("Temp email has been copied and saved: " + "\""+tempEmail+"\"");
    }

    public static String getMail()
    {
        return tempEmail;
    }
}
