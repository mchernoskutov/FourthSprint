package faqtest;

import org.junit.*;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import ya.practikum.pages.MainPage;
import static ya.practikum.constants.Constants.CHROME;
import static ya.practikum.constants.Constants.FIREFOX;


@RunWith(Parameterized.class)
public class FaqTest {

     private WebDriver driver;
     private MainPage objMainPage;

     //для параметризации вопрос, ответ, номер пары
     private String faqHead;
     private String faqAnswers;
     private int faqNumber;
    private String webDriverType;


     public FaqTest(String webDriverType, String faqHead, String faqAnswers, int faqNumber) {
         this.webDriverType = webDriverType;
         this.faqHead = faqHead;
         this.faqAnswers = faqAnswers;
         this.faqNumber = faqNumber;

     }
     //открываем браузер и скроллим до вопросов перед тестом
     @Before
     public void open() {

         if (webDriverType.equals(CHROME)) {
             driver = new ChromeDriver();
         } else if (webDriverType.equals(FIREFOX)) {
             driver = new FirefoxDriver();
         }
         objMainPage = new MainPage(driver);

         //открываем страницу
         objMainPage.openPage();

         //скролл до FAQ
         objMainPage.scrollToFAQ();
     }

    @Parameterized.Parameters
    public static Object[] getAnswers() {
        return new Object[][] {
                //вопрос, ответ, номер пары
                {CHROME,"Сколько это стоит? И как оплатить?",
                    "Сутки — 400 рублей. Оплата курьеру — наличными или картой.", 0}
                ,{CHROME,"Хочу сразу несколько самокатов! Так можно?",
                    "Пока что у нас так: один заказ — один самокат. Если хотите покататься с друзьями, можете просто сделать несколько заказов — один за другим.", 1}
                ,{CHROME,"Как рассчитывается время аренды?",
                    "Допустим, вы оформляете заказ на 8 мая. Мы привозим самокат 8 мая в течение дня. Отсчёт времени аренды начинается с момента, когда вы оплатите заказ курьеру. Если мы привезли самокат 8 мая в 20:30, суточная аренда закончится 9 мая в 20:30.",2}
                ,{CHROME,"Можно ли заказать самокат прямо на сегодня?",
                    "Только начиная с завтрашнего дня. Но скоро станем расторопнее.",3}
                ,{CHROME,"Можно ли продлить заказ или вернуть самокат раньше?",
                    "Пока что нет! Но если что-то срочное — всегда можно позвонить в поддержку по красивому номеру 1010.",4}
                ,{CHROME,"Вы привозите зарядку вместе с самокатом?",
                    "Самокат приезжает к вам с полной зарядкой. Этого хватает на восемь суток — даже если будете кататься без передышек и во сне. Зарядка не понадобится.",5}
                ,{CHROME,"Можно ли отменить заказ?",
                    "Да, пока самокат не привезли. Штрафа не будет, объяснительной записки тоже не попросим. Все же свои.",6}
                ,{CHROME,"Я жизу за МКАДом, привезёте?",
                    "Да, обязательно. Всем самокатов! И Москве, и Московской области.",7}
        };
    }


    //каждый тест на одну пару вопроса и ответа
    @Test
    public void faqTest (){

        //проверяем, что виден вопрос и не виден ответ в начале теста
        Assert.assertTrue("Вопрос FAQ: "+faqNumber+ " не виден в начале теста", objMainPage.isTextAccordeonHeadVisible(faqNumber));
        Assert.assertFalse("Ответ FAQ: "+faqNumber+" не скрыт в начале теста",objMainPage.isTextAccordeonAnswerHidden(faqNumber));

        //проверяем текст вопроса
        Assert.assertEquals("Текст вопроса FAQ: " + faqNumber + " не соответствует ожидаемому", faqHead,objMainPage.getTextAccordeonHead(faqNumber));

        //кликаем на вопрос
        objMainPage.clickAccordeonHead(faqNumber);

        //проверяем видимость ответа
        Assert.assertTrue("Ответ FAQ: " + faqNumber + " не видно после клика",objMainPage.isTextAccordeonAnswerVisible(faqNumber));

        //проверяем текст ответа
        Assert.assertEquals("Текст ответа FAQ: " + faqNumber + " не соответствует ожидаемому", faqAnswers,objMainPage.getTextAccordeonAnswer(faqNumber));

    }

    @After
    public void teardown() {
        // Закрыть браузер после теста
        driver.quit();
    }


}
