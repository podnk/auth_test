import actions.Actions;
import org.apache.log4j.Logger;
import pages.AuthPage;
import pages.MailPage;
import pages.PersonalInfoPage;

public class Main
{
    private static final Logger log = Logger.getLogger(Main.class);

    public static void main(String[] args) throws InterruptedException
    {
        Actions actions = new Actions();
        actions.setupDriver();
        log.info("Driver is set up");

        // Get generated email
        MailPage.startTest();

        // Register new account
        AuthPage.startTest();

        // Upload personal photo
        PersonalInfoPage.startTest();

        actions.closeDriver();
        log.info("Driver is closed");
    }
}
