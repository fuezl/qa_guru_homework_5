import com.codeborne.selenide.Configuration;
import com.github.javafaker.Faker;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

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

    Faker faker = new Faker(new Locale("ru"));

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
        open("/automation-practice-form");

        //Act
        $("input#firstName").setValue("Ivan");
        $("input#lastName").setValue("Ivanov");
        $("input#userEmail").setValue("ivan.ivanov@gmail.com");
        $("label[for='gender-radio-1']").click();
        $("input#userNumber").setValue("9251116644");
        $("input#dateOfBirthInput").click();
        $(".react-datepicker__year-select").selectOptionContainingText("1990");
        $(".react-datepicker__month-select").selectOptionContainingText("May");
        $(".react-datepicker__day--019").click();
        $(".subjects-auto-complete__value-container").click();
        $("#subjectsInput").setValue("Economics").pressEnter();
        $("label[for='hobbies-checkbox-1']").click();
        $("#uploadPicture").uploadFile(new File("src/test/resources/Mr_beans.jpg"));
        $("#currentAddress").setValue("Russia");
        $("input#react-select-3-input").setValue("NCR").pressEnter();
        $("#react-select-4-input").setValue("Noida").pressEnter();
        $("#submit").scrollTo().click();

        //Assert
        $("tbody").$(byText("Student Name")).sibling(0).shouldHave(matchText("Ivan Ivanov"));
        $(".table-hover>tbody>tr:nth-child(2)>td:nth-child(2)").shouldHave(matchText("ivan.ivanov@gmail.com"));
        $(".table-hover>tbody>tr:nth-child(4)>td:nth-child(2)").shouldHave(matchText("9251116644"));
    }
}