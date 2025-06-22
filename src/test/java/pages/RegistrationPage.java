package pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import pages.components.CalendarComponent;
import pages.components.ResultModelDialogComponent;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Selenide.executeJavaScript;

public class RegistrationPage {

  private final SelenideElement firstNameInput = $("#firstName"),
          lastNameInput = $("#lastName"),
          userEmailInput = $("#userEmail"),
          genterWrapper = $("#genterWrapper"),
          userNumberInput = $("#userNumber"),
          calendarInput = $("#dateOfBirthInput"),
          subjectsInput = $("#subjectsInput"),
          hobbiesWrapper = $("#hobbiesWrapper"),
          uploadPicture = $("#uploadPicture"),
          currentAddressInput = $("#currentAddress"),
          stateInput = $("#state"),
          stateCityWrapper = $("#stateCity-wrapper"),
          cityInput = $("#city"),
          submit = $("#submit"),
          modalDialog = $(".modal-dialog"),
          exampleModalSizesTitleLg = $("#example-modal-sizes-title-lg");

  CalendarComponent calendarComponent = new CalendarComponent();
  ResultModelDialogComponent resultModelDialogComponent = new ResultModelDialogComponent();

  @Step("Open Page")
  public RegistrationPage openPage(){
    open("/automation-practice-form");
    $(".practice-form-wrapper").shouldHave(text("Student Registration Form"));

    return this;
  }

  @Step("Remove Banners")
  public RegistrationPage removeBanners(){
    executeJavaScript("$('#fixedban').remove()");
    executeJavaScript("$('footer').remove()");
    executeJavaScript("$('#Ad.Plus-970x250-2').remove()");

    return this;
  }

  @Step("Fill in the field FirstName")
  public RegistrationPage setFirstName(String value){
    firstNameInput.setValue(value);

    return this;
  }

  @Step("Fill in the field Last Name")
  public RegistrationPage setLastName(String value){
    lastNameInput.setValue(value);

    return this;
  }

  @Step("Fill in the field Email")
  public RegistrationPage setEmail(String value){
    userEmailInput.setValue(value);

    return this;
  }

  @Step("Fill in the field Gender")
  public RegistrationPage setGender(String value){
    genterWrapper.$(byText(value)).click();

    return this;
  }

  @Step("Fill in the field Mobile Number")
  public RegistrationPage setUserNumber(String value){
    userNumberInput.setValue(value);

    return this;
  }

  @Step("Fill in the field Date of Birth")
  public RegistrationPage setDateOfBirth(String day, String month, String year){
    calendarInput.click();
    calendarComponent.setDate(day,month,year);

    return this;
  }

  @Step("Fill in the field Subjects")
  public RegistrationPage setSubjects(String value){
    subjectsInput.setValue(value).pressEnter();

    return this;
  }

  @Step("Fill in the field Hobbies")
  public RegistrationPage setHobbies(String value){
    hobbiesWrapper.$(byText(value)).click();

    return this;
  }

  @Step("Upload image")
  public RegistrationPage setPicture(String img){
    uploadPicture.uploadFromClasspath(img);

    return this;
  }

  @Step("Fill in the field Current Address")
  public RegistrationPage setAddress(String value){
    currentAddressInput.setValue(value);

    return this;
  }

  @Step("Fill in the field State")
  public RegistrationPage setState(String value){
    stateInput.click();
    stateCityWrapper.$(byText(value)).click();

    return this;
  }

  @Step("Fill in the field City")
  public RegistrationPage setCity(String value){
    cityInput.click();
    stateCityWrapper.$(byText(value)).click();

    return this;
  }

  @Step("Press the button Submit")
  public RegistrationPage submit(){
    submit.click();

    return this;
  }

  @Step("Check that the confirmation submitting the form has appeared")
  public RegistrationPage checkFormAppears(){
    modalDialog.should(appear);
    exampleModalSizesTitleLg.shouldHave(text("Thanks for submitting the form"));

    return this;
  }

  @Step("Check that the confirmation submitting the form has not appeared")
  public RegistrationPage checkFormNotAppears(){
    modalDialog.shouldNot(appear);

    return this;
  }

  @Step("Check saved value for fields {key}")
  public RegistrationPage checkResultValue(String key, String value){

    resultModelDialogComponent.checkResultValue(key, value);

    return this;
  }
}
