package pages;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class BasePage {

    public void openPage(String url) {
        open(url);
    }

    public void clickSubmit() {
        $("#submit").click();
    }
}
