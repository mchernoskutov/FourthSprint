package faqtest;

import org.junit.After;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.junit.Test;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import ya.practikum.pages.MainPage;

import static ya.practikum.constants.TestData.FAQ_ANSWER;
import static ya.practikum.constants.TestData.FAQ_HEAD;

public class FaqTest {

    private WebDriver driver;




    //проверяем выпадающий список FAQ
    @Test
    public void checkFaq() {

        driver = new ChromeDriver();
        //driver = new FirefoxDriver();

        //создаем главную страницу
        MainPage objMainPage = new MainPage(driver);

        //открываем страницу
        objMainPage.openPage();

        //скролл до FAQ
        objMainPage.scrollToFAQ();

        //проверяем, что все ответы изначально скрыты, все вопросы видны
        for (int i = 0; i < FAQ_HEAD.length; i++) {
            Assert.assertTrue("Вопрос FAQ: "+i+ " не виден при открытии страницы", objMainPage.isTextAccordeonHeadVisible(i));
            Assert.assertFalse("Ответ FAQ: "+i+" не скрыт при открытии страницы",objMainPage.isTextAccordeonAnswerHidden(i));
        }

        //нажимаем последовательно на пункты, проверяем текст, проверяем, что остальные ответы закрываются
        for (int i = 0; i < FAQ_HEAD.length; i++) {

            //кликаем на вопрос
            objMainPage.clickAccordeonHead(i);

            //проверяем, что видно ответ, другие ответы скрыты
            for (int j = 0; j < FAQ_HEAD.length; j++) {
                if (i != j ) {     //при несовпадениее номера вопроса и ответа
                    //проверяем, что другие ответы скрыты
                    Assert.assertFalse("Ответ FAQ: " + j + " не скрыт при клике на вопрос: " + i , objMainPage.isTextAccordeonAnswerHidden(j));
                } else { //при совпадении номера вопроса и ответа
                    //проверяем, что ответ видно
                    Assert.assertTrue("Ответ FAQ: " + i + " не видно после клика",objMainPage.isTextAccordeonAnswerVisible(i));
                    //проверяем текст ответа
                    Assert.assertEquals("Текст ответа FAQ: " + i + " не соответствует ожидаемому", FAQ_ANSWER[i],objMainPage.getTextAccordeonAnswer(i));
                }
            }
        }
    }

    @After
    public void teardown() {
        // Закрыть браузер после теста
        driver.quit();
    }

}
