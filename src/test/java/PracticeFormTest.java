import com.codeborne.selenide.Configuration;
import com.github.javafaker.Faker;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import pages.RegistrationPage;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

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


    Map<String, String> person = new HashMap<String, String>() {{
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
        for (Map.Entry<String, String> entry : person.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            registrationPage.textCheck(key, value);
        }
    }
}