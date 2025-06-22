package tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import helpers.Attach;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.Map;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Selenide.$;
import static io.qameta.allure.Allure.step;

public class RegistrationRemoteTest {
  @BeforeAll
  static void beforeAll() {
    Configuration.pageLoadStrategy = "eager";
    Configuration.baseUrl = "https://demoqa.com";
    Configuration.browser = System.getProperty("browser", "chrome");
    Configuration.browserSize = System.getProperty("windowSize", "1920x1080");
    Configuration.browserVersion = System.getProperty("version", "101");
    Configuration.remote = System.getProperty("remoteBrowser", "https://user1:1234@selenoid.autotests.cloud/wd/hub");

    DesiredCapabilities capabilities = new DesiredCapabilities();
    capabilities.setCapability("selenoid:options", Map.<String, Object>of(
            "enableVNC", true,
            "enableVideo", true
    ));
    Configuration.browserCapabilities = capabilities;

    SelenideLogger.addListener("allure", new AllureSelenide());
  }

  @AfterEach
  void addAttachments(){
    Attach.screenshotAs("Last screenshot");
    Attach.pageSource();
    Attach.browserConsoleLogs();
    Attach.addVideo();
  }

  @Test
  @Tag("demoqa")
  void fillFormTest() {
    step("Open form", () -> {
      open("/automation-practice-form");
      executeJavaScript("$('#fixedban').remove()");
      executeJavaScript("$('footer').remove()");
      executeJavaScript("$('#Ad.Plus-970x250-2').remove()");
    });
    step("Fill form", () -> {
      $("#firstName").setValue("Victoria");
      $("#lastName").setValue("Test");
      $("#userEmail").setValue("victoria@mail.com");
      $("[for=gender-radio-2]").click();
      $("#userNumber").setValue("7458521458");
      $("#dateOfBirthInput").click();
      $(".react-datepicker__month-select").find("option[value='3']").click();
      $(".react-datepicker__year-select").find("option[value='2000']").click();
      $(".react-datepicker__day--014").click();
      $("#subjectsInput").setValue("Accounting").pressEnter();
      $("#hobbiesWrapper").$(byText("Sports")).click();
      $("#uploadPicture").uploadFromClasspath("test.png");
      $("#currentAddress").setValue("Some street 1");
      $("#state").click();
      $("#stateCity-wrapper").$(byText("NCR")).click();
      $("#city").click();
      $("#stateCity-wrapper").$(byText("Delhi")).click();
      $("#submit").click();
    });
    step("Verify result", () -> {
      $(".modal-header #example-modal-sizes-title-lg").shouldHave(text("Thanks for submitting the form"));
      $(".modal-body tr th:nth-child(1)").shouldHave(text("Label"));
      $(".modal-body tr th:nth-child(2)").shouldHave(text("Values"));
      $(".table-responsive").shouldHave(text("Victoria Test"));
      $(".table-responsive").shouldHave(text("victoria@mail.com"));
      $(".table-responsive").shouldHave(text("Female"));
      $(".table-responsive").shouldHave(text("7458521458"));
      $(".table-responsive").shouldHave(text("14 April,2000"));
      $(".table-responsive").shouldHave(text("Accounting"));
      $(".table-responsive").shouldHave(text("Sports"));
      $(".table-responsive").shouldHave(text("test.png"));
      $(".table-responsive").shouldHave(text("Some street 1"));
      $(".table-responsive").shouldHave(text("NCR Delhi"));
    });
  }
}
