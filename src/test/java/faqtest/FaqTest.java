package faqtest;

import org.junit.*;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import ya.practikum.pages.MainPage;

import static ya.practikum.constants.TestData.FAQ_ANSWER;



@RunWith(Parameterized.class)
public class FaqTestP {

     private WebDriver driver;
     private MainPage objMainPage;

     //для параметризации
     private final int faqNumber;

     public FaqTestP (int faqNumber) {
         this.faqNumber = faqNumber;

     }
     //открываем браузер и скроллим до вопросов перед тестами
     @Before
     public void open() {

         driver = new ChromeDriver();
         objMainPage = new MainPage(driver);

         //открываем страницу
         objMainPage.openPage();

         //скролл до FAQ
         objMainPage.scrollToFAQ();
     }

    //номер вопроса и ответа для теста
    @Parameterized.Parameters
    public static Object[] getNumber() {
        return new Object[][] {
                {0},
                {1},
                {2},
                {3},
                {4},
                {5},
                {6},
                {7},

        };
    }


    //каждый тест на одну пару вопроса и ответа
    @Test
    public void faqTest (){

        //проверяем, что виден вопрос и не виден ответ в начале теста
        Assert.assertTrue("Вопрос FAQ: "+faqNumber+ " не виден в начале теста", objMainPage.isTextAccordeonHeadVisible(faqNumber));
        Assert.assertFalse("Ответ FAQ: "+faqNumber+" не скрыт в начале теста",objMainPage.isTextAccordeonAnswerHidden(faqNumber));

        //кликаем на вопрос
        objMainPage.clickAccordeonHead(faqNumber);

        //проверяем видимость ответа
        Assert.assertTrue("Ответ FAQ: " + faqNumber + " не видно после клика",objMainPage.isTextAccordeonAnswerVisible(faqNumber));
        //проверяем текст ответа
        Assert.assertEquals("Текст ответа FAQ: " + faqNumber + " не соответствует ожидаемому", FAQ_ANSWER[faqNumber],objMainPage.getTextAccordeonAnswer(faqNumber));

    }

    @After
    public void teardown() {
        // Закрыть браузер после теста
        driver.quit();
    }


}
