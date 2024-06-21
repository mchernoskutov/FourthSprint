package ya.practikum.pages;



import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import ya.practikum.action.Action;
import ya.practikum.constants.Constants;


public class OrderPageSecond {


    private WebDriver driver;

    //локаторы
    //когда привезти
    private By inputDate = By.xpath(".//input[contains(@placeholder,'Когда привезти самокат')]");
    //срок аренды
    private By inputRentPeriod = By.xpath(".//div[contains(@class,'Dropdown-placeholder')]");
    //меню срока
    private By inputRentPeriodMenu = By.className("Dropdown-menu");

    //составная часть селектора срока аренды
    private String inputRentMenuSelectorStart = ".//div[contains(@class,'Dropdown-option') and text()='";
    private String inputRentMenuSelectorEnd = "']";
    //черный цвет
    private By checkBoxColorBlack = By.id(Constants.BLACK_COLOR);
    //серый цвет
    private By checkBoxColorGray = By.id(Constants.GRAY_COLOR);

    //наличие чек-бокса
    private By checkBoxFill = By.xpath(".//div[contains (@class,'FilledContainer')]");

    //комментарий для курьера
    private By inputCommentary = By.xpath(".//input[contains(@placeholder,'Комментарий для курьера')]");
    //кнопка заказать
    private By buttonOrder = By.xpath(".//div[contains(@class,'Order_Content')]//button[text()='Заказать']");
    //кнопка назад
    private By buttonBack = By.xpath(".//button[text()='Назад']");

    //модальное окно
    private By modalWindowHeader = By.xpath(".//div[contains(@class,'Order_ModalHeader') and text()='Хотите оформить заказ?']");

    //модальное окно с номером заказа
    private By modalWindowOrderHeader = By.xpath(".//div[contains(@class,'Order_ModalHeader') and text()='Заказ оформлен']");

    //номер заказа
    private By modalOrderNumber = By.xpath(".//div[contains(@class,'Order_ModalHeader') and text()='Заказ оформлен']//div[contains(@class,'Order_Text') and contains(text(),'Номер заказа')]");


    //кнопка "Да" в модальном окне
    private By buttonYes = By.xpath(".//button[text()='Да']");

    //кнопка "Нет" в модальном окне
    private By buttonNo = By.xpath(".//button[text()='Нет']");


    public OrderPageSecond(WebDriver driver) {
        this.driver = driver;
    }


    public void setInputDate(String text) {
        Action.setText(text, inputDate, driver);
    }

    public void setInputCommentary(String text) {
        Action.setText(text, inputCommentary, driver);
    }

    public void clickInputRentPeriod () {
        Action.clickElement(inputRentPeriod, driver);
    }

    public boolean isInputRentPeriodMenuVisible () {
        return Action.isElementVisible(inputRentPeriodMenu, driver);
    }

    public void sendEnterInputDate() {
        Action.sendEnter(inputDate, driver);
    }


    public void clickRentPeriodMenu (String text) {
        By selector = By.xpath(inputRentMenuSelectorStart + text + inputRentMenuSelectorEnd);

        Action.clickElement(selector, driver);
    }

    public void clickButtonOrder(){
        Action.clickElement(buttonOrder, driver);
    }

    public void clickButtonYes (){
        Action.clickElement(buttonYes, driver);
    }

    public void clickButtonNo (){
        Action.clickElement(buttonNo, driver);
    }

    public String getInputDate (){
        return Action.getElementValueText(inputDate, driver);
    }

    public String getInputRentPeriodText (){
        return Action.getElementText(inputRentPeriod, driver);
    }

    public String getInputCommentary (){
        return Action.getElementValueText(inputCommentary, driver);
    }

    public void clickCheckBoxColorBlack () {
        Action.clickElement(checkBoxColorBlack, driver);
    }

    public void clickCheckBoxColorGray () {
        Action.clickElement(checkBoxColorGray, driver);
    }

    public boolean isCheckBoxFill () {
        return Action.isElementVisible(checkBoxFill, driver);
    }

    public boolean isModalWindowVisible () {
        return Action.isElementVisible(modalWindowHeader, driver);
    }

    public boolean isModalWindowOrderHeaderVisible () {
        return Action.isElementVisible(modalWindowOrderHeader, driver);
    }


    public boolean isModalOrderNumberVisible () {
        return Action.isElementVisible(modalOrderNumber, driver);
    }

}
