import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.nio.charset.Charset;
import java.util.Map;
import java.util.Random;

import static org.junit.Assert.assertTrue;

/**
 * Created by Таня on 24.03.2019.
 */
public class SurveyTest {
    private WebDriver driver;
    private MainPage mainPage;

    @Before
    public void setUp() throws Exception {
        System.setProperty("webdriver.chrome.driver", "target/test-classes/chromedriver.exe");
        driver = new ChromeDriver();
//        System.setProperty("webdriver.gecko.driver", "C:\\Users\\Таня\\IdeaProjects\\tz\\src\\test\\resources\\geckodriver.exe");
//        FirefoxOptions opt = new FirefoxOptions();
//        //opt.addArguments("-profile", "C:\\Users\\Таня\\AppData\\Roaming\\Mozilla\\Firefox\\Profiles\\nahd6ha2.default");
//        WebDriver driver = new FirefoxDriver(opt);
        mainPage = new MainPage(driver);

    }

    @Test
    public void testSurvey() throws Exception {
        mainPage.clickFreeTrialButton();
        String email = RandomStringUtils.randomAlphanumeric(RandomUtils.nextInt(3, 11))+"wpt@wriketask.qaa";
        mainPage.setTrialEmail(email);
        mainPage.clickCreateMyWrikeAccount();
        Thread.sleep(5000);
        assertTrue(driver.getCurrentUrl().equals("https://www.wrike.com/resend/"));

        mainPage.clickAnswerOne(RandomUtils.nextInt(1, 3));
        mainPage.clickAnswerTwo(RandomUtils.nextInt(1, 6));
        int val = RandomUtils.nextInt(1, 3);
        if (val == 1) {
            mainPage.clickAnswerThree(RandomUtils.nextInt(1, 3));
        }
        else {
            String other = RandomStringUtils.randomAlphanumeric(RandomUtils.nextInt(10, 21));
            mainPage.clickAnswerThreeOther(other);
        }

        mainPage.clickSubmiteResults();
        Thread.sleep(5000);
        assertTrue(mainPage.isSuccessVisible());

        mainPage. clickResendEmail();
        Thread.sleep(5000);
        assertTrue(mainPage.isAgainVisible());

        assertTrue(mainPage.isFollowUsVisible());

    }

    @After
    public void tearDown() throws Exception {
        driver.close();
        driver.quit();
    }
}
