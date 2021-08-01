package pages;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class BasePage {

    // Функция открывает переданную в неё ссылку
    public void openPage(String url) {
        open(url);
    }

    // Функция нажимает на submit
    public void clickSubmit() {
        $("#submit").click();
    }
}
