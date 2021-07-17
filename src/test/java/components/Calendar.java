package components;

import static com.codeborne.selenide.Selenide.$;
import static java.lang.String.format;

public class Calendar {
    public void setDate(String day, String month, String year) {
        $("#dateOfBirthInput").click();
        $(".react-datepicker__month-select").selectOption(month);
        $(".react-datepicker__year-select").selectOption(year);
        String dayLocator = "";
        if (Integer.parseInt(day) > 9)  {
            dayLocator = format(".react-datepicker__day--0%s" +
                    ":not(.react-datepicker__day--outside-month)", day);
        } else {
            dayLocator = format(".react-datepicker__day--00%s" +
                    ":not(.react-datepicker__day--outside-month)", day);
        }
        $(dayLocator).click();

    }
}