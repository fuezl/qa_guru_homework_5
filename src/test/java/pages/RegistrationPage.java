package pages;

import components.Calendar;

import java.io.File;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static java.lang.String.format;

public class RegistrationPage extends BasePage {

    private final static String FORM_TITLE = "Student Registration Form";
    private final static String RESULTS_TITLE = "Thanks for submitting the form";

    private Calendar calendar = new Calendar();

    public void inputFirstName(String firstName) {
        $("input#firstName").val(firstName);
    }

    public void inputLastName(String lastName) {
        $("input#lastName").val(lastName);
    }

    public void inputUserEmail(String email) {
        $("input#userEmail").val(email);
    }

    public void selectGender(String gender){
        $(format("[name=gender][value=%s]", gender)).parent().click();
    }

    public void inputPhone(String phone){
        $("input#userNumber").val(phone);
    }

    public void setDateOfBirth(String day, String month, String year){
        calendar.setDate(day,month,year);
    }

    public void typeSubjects(String subjects){
        $("#subjectsInput").setValue(subjects).pressEnter();
    }

    public void typeHobbies(String hobbies){
        $("#hobbiesWrapper").$(byText(hobbies)).click();
    }

    public void uploadFile(String picture){
        $("#uploadPicture").uploadFile(new File(format("src/test/resources/%s", picture)));
    }

    public void typeAddress(String address){
        $("#currentAddress").setValue(address);
    }

    public void selectState(String state){
        $("#react-select-3-input").setValue(state).pressEnter();
    }

    public void selectCity(String city){
        $("#react-select-4-input").setValue(city).pressEnter();
    }
}
