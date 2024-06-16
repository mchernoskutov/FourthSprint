package ya.practikum.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import ya.practikum.action.Action;
import ya.practikum.constants.Constants;

import java.time.Duration;


public class MainPage {


    private WebDriver driver;



    //список локаторов

    //кнопка cookie
    private By buttonCookie = By.xpath(".//button[contains(@class,'CookieButton')]");

    //кнопки
    //Кнопка "Заказать" в заголовке
    private By buttonOrderHeader = By.xpath(".//div[contains(@class, 'Header')]/button[text()='Заказать']");
    //Кнопка "Заказать" внизу
    private By buttonOrderDown = By.xpath(".//div[contains(@class, 'Finish')]/button[text()='Заказать']");
    //Кнопка "Статус заказа"
    private By buttonStatus = By.xpath(".//div[contains(@class, 'Header')]/button[text()='Статус заказа']");

    //заголовки FAQ
    private By[] accordeonHead = {
            By.xpath(".//div[@id='accordion__heading-0']")
            ,By.xpath(".//div[@id='accordion__heading-1']")
            ,By.xpath(".//div[@id='accordion__heading-2']")
            ,By.xpath(".//div[@id='accordion__heading-3']")
            ,By.xpath(".//div[@id='accordion__heading-4']")
            ,By.xpath(".//div[@id='accordion__heading-5']")
            ,By.xpath(".//div[@id='accordion__heading-6']")
            ,By.xpath(".//div[@id='accordion__heading-7']")
        };
    //ответы FAQ
    private By[] accordeonAnswer = {
            By.xpath(".//div[@class='accordion__panel' and @id='accordion__panel-0']")
            ,By.xpath(".//div[@class='accordion__panel' and @id='accordion__panel-1']")
            ,By.xpath(".//div[@class='accordion__panel' and @id='accordion__panel-2']")
            ,By.xpath(".//div[@class='accordion__panel' and @id='accordion__panel-3']")
            ,By.xpath(".//div[@class='accordion__panel' and @id='accordion__panel-4']")
            ,By.xpath(".//div[@class='accordion__panel' and @id='accordion__panel-5']")
            ,By.xpath(".//div[@class='accordion__panel' and @id='accordion__panel-6']")
            ,By.xpath(".//div[@class='accordion__panel' and @id='accordion__panel-7']")
    };

    //лого скутера в заголовке страницы для проверки открытия страницы
    private By logoScooterHeader = By.xpath(".//div[contains(@class,'Header_Logo')]//*[contains(@class,'LogoScooter')]");


    public MainPage (WebDriver driver) {
        this.driver = driver;
    }


    public void waitForPageLoad() {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(Constants.STANDART_WAIT_TIME));
        wait.until(ExpectedConditions.visibilityOfElementLocated(logoScooterHeader));
        wait.until(ExpectedConditions.elementToBeClickable(buttonStatus));

    }

    public void clickAccordeonHead(int headNumber) {
        Action.clickElement(accordeonHead[headNumber], driver);

    }

    public void clickButtonOrderHeader() {
        Action.clickElement(buttonOrderHeader, driver);

    }

    public void clickButtonOrderDown() {

        Action.scrollToElement(buttonOrderDown, driver);
        Action.clickElement(buttonOrderDown, driver);

    }


    public String getTextAccordeonHead(int headNumber) {

        return Action.getElementText(accordeonHead[headNumber],driver);
    }

    public String getTextAccordeonAnswer(int answerNumber) {

        return Action.getElementText(accordeonAnswer[answerNumber],driver);

    }

    public boolean isTextAccordeonHeadVisible(int headNumber) {

        return Action.isElementVisible(accordeonHead[headNumber], driver);
    }

    public boolean isTextAccordeonAnswerVisible(int answerNumber) {

        return Action.isElementVisible(accordeonAnswer[answerNumber], driver);
    }

    public boolean isTextAccordeonAnswerHidden(int answerNumber) {

        return Action.isElementHidden(accordeonAnswer[answerNumber], driver);

    }

    public void scrollToFAQ() {
        Action.scrollToElement(accordeonHead[7], driver);

    }



    public void openURL() {
        driver.get(Constants.URL);
    }

    public void clickCookie() {
        Action.clickElement(buttonCookie, driver);

    }

    public void openPage() {
        //открываем страницу
        openURL();

        //дожидаемся открытия страницы
        waitForPageLoad();

        //кликаем на куки
        clickCookie();

    }


}
