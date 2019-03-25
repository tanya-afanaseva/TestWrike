import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.SourceType;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Optional;


/**
 * Created by Таня on 23.03.2019.
 */
public class MainPage {
    private static final String FREE_TRIAL_BUTTON_PATH = "//html/body/div[1]/header/div[3]/div[2]/div/div/div[2]/div/form/button";
    private static final String TRIAL_EMAIL_PATH = "//*[contains(@class,'wg-input modal-form-trial__input')]";
    private static final String CREATE_MY_WRIKE_ACCOUNT_PATH = "//*[contains(@class,'wg-btn wg-btn--blue modal-form-trial__submit')]";
    private static final String ANSWER_ONE_PATH = "//html/body/div[1]/main/div/div/div[2]/div/div[2]/div/form/div[1]";
    private static final String ANSWER_TWO_PATH = "//html/body/div[1]/main/div/div/div[2]/div/div[2]/div/form/div[2]";
    private static final String ANSWER_THREE_PATH = "//html/body/div[1]/main/div/div/div[2]/div/div[2]/div/form/div[3]";
    private static final String ANSWER_THREE_OTHER_BUTTON_PATH = "//html/body/div[1]/main/div/div/div[2]/div/div[2]/div/form/div[3]/label[3]/button";
    private static final String ANSWER_THREE_INPUT_PATH = ANSWER_THREE_OTHER_BUTTON_PATH + "/span/input";
    private static final String SUBMIT_RESULT_PATH = "//*[contains(@class,'submit wg-btn wg-btn--navy js-survey-submit')]";
    private static final String SUCCESS_PATH = "//html/body/div[1]/main/div/div/div[2]/div/div[2]/div/div";
    private static final String RESEND_EMAIL_PATH = "//*[contains(@class,'wg-btn wg-btn--white wg-btn--hollow button js-button')]";
    private static final String AGAIN_PATH = "//html/body/div[1]/main/div/div/div[2]/div/div[1]/p[1]/span";
    private static final String TWITTER_PATH = "//html/body/div[1]/div/div[3]/div/div[1]/div/ul/li[*]/a";
    private final String TWITTER_ICON_LINK = "/content/themes/wrike/dist/img/sprite/vector/footer-icons.symbol.svg?v2#twitter";
    private WebDriver driver;

    public MainPage(WebDriver driver) {
        this.driver = driver;
        driver.get("https://www.wrike.com");
    }

    public void clickFreeTrialButton() {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        WebElement freeTrialButton = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath(FREE_TRIAL_BUTTON_PATH))

        );
        freeTrialButton.click();
    }
    public void setTrialEmail(String email) {
        driver.findElement(By.xpath(TRIAL_EMAIL_PATH)).sendKeys(email);
    }

    public void clickCreateMyWrikeAccount() {
        driver.findElement(By.xpath(CREATE_MY_WRIKE_ACCOUNT_PATH)).click();
    }

    public void clickAnswerOne(int val) { // val = 1 2
        driver.findElement(By.xpath(ANSWER_ONE_PATH + "/label[" + val + "]/button")).click();
    }

    public void clickAnswerTwo(int val) { // val 1-5
        driver.findElement(By.xpath(ANSWER_TWO_PATH +"/label[" + val + "]/button")).click();
    }

    public void clickAnswerThree(int val) {
        driver.findElement(By.xpath(ANSWER_THREE_PATH +"/label[" + val + "]/button")).click();
    }

    public void clickAnswerThreeOther(String str) {
        WebElement otherAnswer = driver.findElement(By.xpath(ANSWER_THREE_OTHER_BUTTON_PATH));
        otherAnswer.click();
        WebElement otherInput = driver.findElement(By.xpath(ANSWER_THREE_INPUT_PATH));
        otherInput.sendKeys(str);
    }

    public void clickSubmitResults() {
        driver.findElement(By.xpath(SUBMIT_RESULT_PATH)).click();
    }

    public boolean isSuccessVisible() {
        WebElement success = driver.findElement(By.xpath(SUCCESS_PATH));
        return !"none".equals(success.getCssValue("display"));
    }

    public void clickResendEmail() {
        driver.findElement(By.xpath(RESEND_EMAIL_PATH)).click();
    }

    public boolean isAgainVisible() {
        WebElement success = driver.findElement(By.xpath(AGAIN_PATH));

        return !"none".equals(success.getCssValue("display"));
    }

    public boolean isTwitter() {
         return getTwitterLink()
                .map(twitterLink -> "https://twitter.com/wrike".equals(twitterLink.getAttribute("href")))
                .orElse(false);
    }

    private Optional<WebElement> getTwitterLink() {
        return driver.findElements(By.xpath(TWITTER_PATH))
               .parallelStream()
               .filter(o -> o.getAttribute("href").contains("twitter"))
               .findAny();
    }

    public boolean isTwitterIconRight() {
        return getTwitterLink()
                .map(link -> link.findElement(By.tagName("svg")))
                .map(svg -> svg.findElement(By.tagName("use")))
                .map(use -> use.getAttribute("xlink:href"))
                .map(svgLink -> TWITTER_ICON_LINK.equals(svgLink))
                .orElse(false);
    }
}
