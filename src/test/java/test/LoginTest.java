package test;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import page.HomePage;
import page.LoginSubmitPage;

public class LoginTest extends BaseTest{

    @DataProvider
    public Object[][] emptyFieldDataProvider() {
        return new Object[][]{
                { "mr3y6p+test@gmail.com", "" },
                { "", "q0w9e8r7" },
                { "", "" }
        };
    }

    @DataProvider
    public Object[][] validDataProvider() {
        return new Object[][]{
                { "mr3y6p+test@gmail.com", "q0w9e8r7" },
                { "mr3y6p+test@Gmail.COM", "q0w9e8r7" },
                { " mr3y6p+test@gmail.com ", "q0w9e8r7" }
        };
    }

    @DataProvider
    public Object[][] incorrectFieldDataProvider() {
        return new Object[][]{
                { "mr3y6p+test@gmail.comq", "1111", "We don't recognize that email.\n" + "Did you mean: @gmail.com?", ""},
                { "mr3y6p+test@gmail.com", "testtest", "", "Hmm, that's not the right password. Please try again or request a new one."},
                { "mr3y6p+test@amazos.com", "1111", "We don't recognize that email.\n" + "Did you mean: @amazon.com?", ""},
                { "mr3y6p+test@linkedinn.com", "1111", "We don't recognize that email.\n" + "Did you mean: @linkedin.com?", ""},
                { "1243", "1111", "Be sure to include \"+\" and your country code.", ""},
                { "+1234", "1111", "Hmm, we don’t recognize that phone number. Please try again", ""},
                { "mr3y6p+test1@gmail.com", "q0w9e8r7", "Hmm, we don't recognize that email. Please try again.", ""},
                { "test", "1111", "Please enter a valid email address.", ""},
                { "mr3y6p+test1@gmail.com", "q0w9e8r7", "Hmm, we don't recognize that email. Please try again.", ""},
                { "mr3y6p+test@iicloud.com", "1111", "We don't recognize that email.\n" + "Did you mean: @icloud.com?", ""}
        };
    }

    @Test(dataProvider = "emptyFieldDataProvider")
    public void emptyPasswordTest(String userEmail, String userPass) {
        loginPage.login(userEmail, userPass);

        //Verify that Login page is still loaded
        Assert.assertTrue(loginPage.isPageLoaded(), "LogIn page is not loaded");
    }


    @Test(dataProvider = "validDataProvider")
    public void successfulLoginTest(String userEmail, String userPass) {
        HomePage homePage = loginPage.login(userEmail, userPass);

        //Verify that Home page is loaded
        Assert.assertTrue(homePage.isPageLoaded(), "Home page is not loaded");
    }


    @Test(dataProvider = "incorrectFieldDataProvider")
    public void negativeLeadsToLoginSubmitPage(String userEmail, String userPass, String emailError, String passError) {

        LoginSubmitPage loginSubmitPage = loginPage.login(
                userEmail, userPass);

        Assert.assertTrue(loginSubmitPage.isPageLoaded(),
                "Login Submit page is not loaded.");

        Assert.assertEquals(loginSubmitPage.getUserEmailError(),
                emailError,
                "userEmail Validation message is wrong.");

        Assert.assertEquals(loginSubmitPage.getUserPassError(),
                passError,
                "userPass Validation message is wrong.");
    }

}
