import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.SourceType;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


/**
 * Created by Таня on 23.03.2019.
 */
public class MainPage {
    private WebDriver driver;

    public MainPage(WebDriver driver) {
        this.driver = driver;
        driver.get("https://www.wrike.com");
    }

    public void clickFreeTrialButton() {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        WebElement freeTrialButton = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//html/body/div[1]/header/div[3]/div[2]/div/div/div[2]/div/form/button"))

        );
        freeTrialButton.click();
    }
    public void setTrialEmail(String email) {
        driver.findElement(By.xpath("//*[contains(@class,'wg-input modal-form-trial__input')]")).sendKeys(email);
    }

    public void clickCreateMyWrikeAccount() {
        driver.findElement(By.xpath("//*[contains(@class,'wg-btn wg-btn--blue modal-form-trial__submit')]")).click();
    }

    public void clickAnswerOne(int val) { // val = 1 2
        driver.findElement(By.xpath("//html/body/div[1]/main/div/div/div[2]/div/div[2]/div/form/div[1]/label[" + val + "]/button")).click();
    }

    public void clickAnswerTwo(int val) { // val 1-5
        driver.findElement(By.xpath("//html/body/div[1]/main/div/div/div[2]/div/div[2]/div/form/div[2]/label[" + val + "]/button")).click();
    }

    public void clickAnswerThree(int val) {
        driver.findElement(By.xpath("//html/body/div[1]/main/div/div/div[2]/div/div[2]/div/form/div[3]/label[" + val + "]/button")).click();
    }

    public void clickAnswerThreeOther(String str) {
        WebElement otherAnswer = driver.findElement(By.xpath("//html/body/div[1]/main/div/div/div[2]/div/div[2]/div/form/div[3]/label[3]/button"));
        otherAnswer.click();
        WebElement otherInput = driver.findElement(By.xpath("//html/body/div[1]/main/div/div/div[2]/div/div[2]/div/form/div[3]/label[3]/button/span/input"));
        otherInput.sendKeys(str);
    }

    public void clickSubmiteResults() {
        driver.findElement(By.xpath("//*[contains(@class,'submit wg-btn wg-btn--navy js-survey-submit')]")).click();
    }

    public boolean isSuccessVisible() {
        WebElement success = driver.findElement(By.xpath("//html/body/div[1]/main/div/div/div[2]/div/div[2]/div/div"));
        return !"none".equals(success.getCssValue("display"));
    }

    public void clickResendEmail() {
        driver.findElement(By.xpath("//*[contains(@class,'wg-btn wg-btn--white wg-btn--hollow button js-button')]")).click();
    }

    public boolean isAgainVisible() {
        WebElement success = driver.findElement(By.xpath("//html/body/div[1]/main/div/div/div[2]/div/div[1]/p[1]/span"));

        return !"none".equals(success.getCssValue("display"));
    }

    public boolean isFollowUsVisible() {
        try{
            WebElement followUs = driver.findElement(By.xpath("//html/body/div[1]/div/div[3]/div/div[1]/div"));
            followUs.findElements(By.xpath("//div/ul/li*"));
        } catch (NoSuchElementException e){
            return false;
        }
        return true;
    }



}
