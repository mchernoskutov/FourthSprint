package ordertest;

import org.junit.After;
import org.junit.Assert;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.junit.Test;
import org.openqa.selenium.chrome.ChromeDriver;
import ya.practikum.constants.Constants;
import ya.practikum.pages.MainPage;
import ya.practikum.pages.OrderPageFirst;
import ya.practikum.pages.OrderPageSecond;



@RunWith(Parameterized.class)
public class OrderScooter {


    private WebDriver driver;
        //список проверяемых данных
        //имя
        private final String name;
        //фамилия
        private final String surname;
        //адрес
        private final String address;
        //станция метро
        private final String metroStation;
        //номер телефона
        private final String phoneNumber;
        //дата
        private final String date;
        //срок аренды
        private final String rentPeriod;
        //цвет самоката
        private final String color;
        //комментарий для курьера
        private final String comment;
        //верхняя кнопка заказать
        private final boolean upButtonOrder;

    public OrderScooter (String name
                        ,String surname
                        ,String address
                        ,String metroStation
                        ,String phoneNumber
                        ,String date
                        ,String rentPeriod
                        ,String color
                        ,String comment
            ,boolean upButtonOrder
                ) {
            this.name = name;
            this.surname = surname;
            this.address = address;
            this.metroStation = metroStation;
            this.phoneNumber = phoneNumber;
            this.date = date;
            this.rentPeriod = rentPeriod;
            this.color = color;
            this.comment = comment;
            this.upButtonOrder = upButtonOrder;
    }


        @Parameterized.Parameters
        public static Object[] getCredential() {
            return new Object[][] {
                    {"Михаил", "Иванов", "г. Москва", "Котельники", "79223344555",
                    "15.06.2024", "сутки", Constants.BLACK_COLOR, "Комментарий для курьера", true },

                    {"Алекс", "Мёрфи", "ул. Ленина, 92", "Арбатская", "89015566777",
                    "19.06.2024", "трое суток", Constants.GRAY_COLOR, "Комментарий для курьера №2", false},

            };
        }


    @Test
    public void checkOrder() {

        driver = new ChromeDriver();

        //driver = new FirefoxDriver();

        //создаем главную страницу
        MainPage objMainPage = new MainPage(driver);
        //открываем страницу
        objMainPage.openPage();
        //создаем страницу заказа
        OrderPageFirst objOrderPageFirst = new OrderPageFirst(driver);
        //открываем страницу заказа
        if (upButtonOrder) { //через верхнюю кнопку заказать
            objMainPage.clickButtonOrderHeader();
        } else { //через нижнюю кнопку заказать
            objMainPage.clickButtonOrderDown();
        }

        //ждем открытия страницы заказа
        objOrderPageFirst.waitForPageLoad();

        //вводим имя, фамилию, адрес, станцию и номер
        objOrderPageFirst.setInputName(name);
        objOrderPageFirst.setInputSurname(surname);
        objOrderPageFirst.setInputAdress(address);
        objOrderPageFirst.setInputMetroStation(metroStation);
        objOrderPageFirst.setInputPhoneNumber(phoneNumber);

        //проверяем введенные данные
        Assert.assertEquals("Имя в поле не соответствует введеному", objOrderPageFirst.getTextInputName(), name);
        Assert.assertEquals("Фамилия в поле не соответствует введеному", objOrderPageFirst.getTextInputSurname(), surname);
        Assert.assertEquals("Адрес в поле не соответствует введеному", objOrderPageFirst.getTextInputAdress(), address);
        Assert.assertEquals("Станция в поле не соответствует введеному", objOrderPageFirst.getTextInputMetroStation(), metroStation);
        Assert.assertEquals("Номер в поле не соответствует введеному", objOrderPageFirst.getTextInputPhoneNumber(), phoneNumber);

        //проверяем отсутствие сообщений об ошибке в полях
        Assert.assertFalse("Отображается сообщение об ошибке в имени", objOrderPageFirst.isErrorNameVisible());
        Assert.assertFalse("Отображается сообщение об ошибке в фамилии", objOrderPageFirst.isErrorSurnameVisible());
        Assert.assertFalse("Отображается сообщение об ошибке в адресе", objOrderPageFirst.isErrorAdressVisible());
        Assert.assertFalse("Отображается сообщение об ошибке в номере телефона", objOrderPageFirst.isErrorPhoneNumberVisible());


        //нажимаем дальше
        objOrderPageFirst.clickButtonNext();

        // создаем вторую страницу заказа
        OrderPageSecond objOrderPageSecond = new OrderPageSecond(driver);

        //вводим дату
        objOrderPageSecond.setInputDate(date);
        //нажимаем enter
        objOrderPageSecond.sendEnterInputDate();

        //проверяем введеную дату
        Assert.assertEquals("Дата в поле ввода указана не верно ", objOrderPageSecond.getInputDate(), date);


        //кликаем на срок аренды
        objOrderPageSecond.clickInputRentPeriod();


        //проверяем, что открылось меню
        Assert.assertTrue("Меню срока аренды самоката не видно",objOrderPageSecond.isInputRentPeriodMenuVisible());

        //выбираем срок
        objOrderPageSecond.clickRentPeriodMenu(rentPeriod);
        //проверяем выбранный срок
        Assert.assertEquals("Срок аренды не был указан после выбора в меню", objOrderPageSecond.getInputRentPeriodText(), rentPeriod);

        //нажимаем на цвет

        if (color.equals(Constants.BLACK_COLOR)) {
            objOrderPageSecond.clickCheckBoxColorBlack();

        } else if (color.equals(Constants.GRAY_COLOR)) {
            objOrderPageSecond.clickCheckBoxColorGray();
        } else {
            teardown();
            Assert.fail("Указан некорректный цвет самоката");
        }
        //проверяем что цвет выбран

        Assert.assertTrue("Не выбран чек-бокс цвета самоката",objOrderPageSecond.isCheckBoxFill());

        //добавляем комментарий
        objOrderPageSecond.setInputCommentary(comment);

        //проверяем, что текст комментария добавлен

        Assert.assertEquals("Не указан текст комментария в поле ввода", objOrderPageSecond.getInputCommentary(), comment);

        //нажимаем заказать
        objOrderPageSecond.clickButtonOrder();

        //проверяем открытие модального окна
        Assert.assertTrue("Модальное окно подтверждения заказа не открылось",objOrderPageSecond.isModalWindowVisible());
        //нажимаем "Да" в модальном окне
        objOrderPageSecond.clickButtonYes();

        //проверяем открытие модального окна с номером заказа
        Assert.assertTrue("Модальное окно подтверждения заказа не открылось",objOrderPageSecond.isModalWindowOrderHeaderVisible());
        //проверяем, что есть текст с номером заказа
        Assert.assertTrue("Модальное окно подтверждения заказа не открылось",objOrderPageSecond.isModalOrderNumberVisible());


    }


    @After
    public void teardown() {
        // Закрыть браузер после теста
        driver.quit();
    }


}
