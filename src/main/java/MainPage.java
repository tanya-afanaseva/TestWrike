import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


/**
 * Created by Таня on 23.03.2019.
 */
public class MainPage {
    private WebDriver driver;

    public MainPage(WebDriver driver) {
        this.driver = driver;
    }

    public void clickFreeTrialButton() {
        driver.findElement(By.xpath("//*[contains(@class,'wg-header__free-trial-button wg-btn wg-btn--green')]")).click();
    }
    public void setTrialEmail(String email) {
        driver.findElement(By.xpath("//*[contains(@class,'wg-input modal-form-trial__input')]")).sendKeys(email);
    }

    public void clickCreateMyWrikeAccount() {
        driver.findElement(By.xpath("//*[contains(@class,'wg-btn wg-btn--blue modal-form-trial__submit')]")).click();
    }

    /*public void assertionResend() {
        assertTrue(driver.getCurrentUrl().equals("https://www.wrike.com/resend/"));
    }*/

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
        otherAnswer.sendKeys(str);
    }

    public void clickSubmiteResults() {
        driver.findElement(By.xpath("//*[contains(@class,'submit wg-btn wg-btn--navy js-survey-submit')]")).click();
    }

    public boolean isSuccessVisible() {
        WebElement success = driver.findElement(By.xpath("//*[contains(@class,'wg-input modal-form-trial__input')]"));
        return !"none".equals(success.getCssValue("display"));
    }

    public void clickResendEmail() {
        driver.findElement(By.xpath("//*[contains(@class,'wg-btn wg-btn--white wg-btn--hollow button js-button')]")).click();
    }

    public boolean isAgainVisible() {
        WebElement success = driver.findElement(By.xpath("//*[contains(@class,'again')]"));
        return !"none".equals(success.getCssValue("display"));
    }



}
