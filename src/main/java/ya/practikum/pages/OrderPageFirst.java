package ya.practikum.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import ya.practikum.action.Action;

import java.time.Duration;

//страница заказа
public class OrderPageFirst {

    private long waitTime = 10;
    private WebDriver driver;


    //локаторы
    //имя
    private By inputName = By.xpath(".//input[contains(@placeholder,'Имя')]");
    //фамилия
    private By inputSurname = By.xpath(".//input[contains(@placeholder,'Фамилия')]");
    //адрес
    private By inputAdress = By.xpath(".//input[contains(@placeholder,'Адрес')]");
    //станция метро
    private By inputMetroStation = By.xpath(".//input[contains(@placeholder,'Станция метро')]");
    //составная часть для селектора станции метро
    private String inputMetroStationSelectorStart = ".//div[contains(@class,'select-search__select')]//*[text()='";
    private String inputMetroStationSelectorEnd = "']";
    //номер телефона
    private By inputPhoneNumber = By.xpath(".//input[contains(@placeholder,'Телефон')]");
    //сообщение об ошибке ввода имени
    private By errorName = By.xpath(".//div[text()='Введите корректное имя']");
    //сообщение об ошибке ввода фамилии
    private By errorSurname = By.xpath(".//div[text()='Введите корректную фамилию']");
    //сообщение об ошибке ввода адреса
    private By errorAdress = By.xpath(".//div[text()='Введите корректный адрес']");
    //сообщение об ошибке ввода станции метро
    private By errorMetroStation = By.xpath(".//div[text()='Выберите станцию']");
    //сообщение об ошибке ввода телефона
    private By errorPhoneNumber = By.xpath(".//div[text()='Введите корректный номер']");

    //кнопка дальше
    private By buttonNext = By.xpath(".//button[text()='Далее']");
    //заголовок в окне
    private By orderLabel = By.xpath(".//div[text()='Для кого самокат']");

    public OrderPageFirst(WebDriver driver) {
        this.driver = driver;
    }

    public void waitForPageLoad() {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(waitTime));
        wait.until(ExpectedConditions.visibilityOfElementLocated(orderLabel));
        wait.until(ExpectedConditions.elementToBeClickable(buttonNext));

    }

    public void setInputName(String text) {

        Action.setText(text, inputName,driver);
    }

    public void setInputSurname(String text) {

        Action.setText(text, inputSurname,driver);
    }

    public void setInputAdress(String text) {

        Action.setText(text, inputAdress,driver);
    }
    public void setInputMetroStation(String text) {

        Action.setText(text, inputMetroStation,driver);
        //собираем селектор станции
        By selector = By.xpath(inputMetroStationSelectorStart+text+inputMetroStationSelectorEnd);

        Action.clickElement(selector,driver);

    }
    public void setInputPhoneNumber(String text) {

        Action.setText(text, inputPhoneNumber,driver);
    }

    public String getTextInputName () {

        return Action.getElementValueText(inputName, driver);
    }

    public String getTextInputSurname () {
        return Action.getElementValueText(inputSurname, driver);

    }
    public String getTextInputAdress () {
        return Action.getElementValueText(inputAdress, driver);
    }
    public String getTextInputMetroStation () {
        return Action.getElementValueText(inputMetroStation, driver);
    }
    public String getTextInputPhoneNumber () {
        return Action.getElementValueText(inputPhoneNumber,driver);
    }

    public Boolean isErrorNameVisible(){
        return Action.isElementHidden(errorName, driver);
    }

    public Boolean isErrorSurnameVisible(){
        return Action.isElementHidden(errorSurname, driver);
    }

    public Boolean isErrorAdressVisible(){
        return Action.isElementHidden(errorAdress, driver);
    }

    public Boolean isErrorMetroStationVisible(){
        return Action.isElementHidden(errorMetroStation, driver);
    }

    public Boolean isErrorPhoneNumberVisible(){

        return Action.isElementHidden(errorPhoneNumber, driver);
    }

    public void clickButtonNext() {

        Action.clickElement(buttonNext, driver);
    }

}
