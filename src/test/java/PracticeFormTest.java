import com.codeborne.selenide.Configuration;
import com.github.javafaker.Faker;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import pages.RegistrationPage;

import java.io.File;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import static com.codeborne.selenide.Condition.matchText;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static java.lang.String.format;

public class PracticeFormTest {

    Faker faker = new Faker(new Locale("en"));
    RegistrationPage registrationPage = new RegistrationPage();

    String firstName = faker.name().firstName();
    String lastName = faker.name().lastName();
    String email = faker.internet().emailAddress();
    String gender = "Male";
    String phone = faker.numerify("##########");
    String day = "19";
    String month = "May";
    String year = "1990";
    String subjects = "Economics";
    String hobbies = "Sports";
    String picture = "Mr_beans.jpg";
    String address = faker.address().fullAddress();
    String state = "NCR";
    String city = "Noida";


    Map<String, String> person = new HashMap<String, String>()
    {{
      put("Student Name", format("%s %s", firstName, lastName));
      put("Student Email", email);
      put("Gender", gender);
      put("Mobile", phone);
      put("Date of Birth", format("%s %s,%s", day, month, year));
      put("Subjects", subjects);
      put("Hobbies", hobbies);
      put("Picture", picture);
      put("Address", address);
      put("State and City", format("%s %s", state, city));
    }};


    @BeforeAll
    static void setup() {
        Configuration.baseUrl = "https://demoqa.com";
    }

    @Test
    void positiveFillTest() {
        //Arrange
        registrationPage.openPage("/automation-practice-form");

        //Act
        registrationPage.inputFirstName(firstName);
        registrationPage.inputLastName(lastName);
        registrationPage.inputUserEmail(email);
        registrationPage.selectGender(gender);
        registrationPage.inputPhone(phone);
        registrationPage.setDateOfBirth(day, month, year);
        registrationPage.typeSubjects(subjects);
        registrationPage.typeHobbies(hobbies);
        registrationPage.uploadFile(picture);
        registrationPage.typeAddress(address);
        registrationPage.selectState(state);
        registrationPage.selectCity(city);
        registrationPage.clickSubmit();

        //Assert
        $("tbody").$(byText("Student Name")).sibling(0).shouldHave(matchText(format("%s %s", firstName, lastName)));
        $(".table-hover>tbody>tr:nth-child(2)>td:nth-child(2)").shouldHave(matchText(email));
        $(".table-hover>tbody>tr:nth-child(4)>td:nth-child(2)").shouldHave(matchText(phone));
    }
}