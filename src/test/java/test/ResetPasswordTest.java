package test;

import org.testng.Assert;
import org.testng.annotations.Test;
import page.*;

import static java.lang.Thread.sleep;

public class ResetPasswordTest extends BaseTest {

    /**
     * Preconditions:
     * - Open browser
     * - Navigate to linkedin.com
     *
     * Scenario:
     * - Click Forgot Password link
     * - Verify Request password reset page is loaded
     * - Enter "Email" into emailField
     * - Click findAccountButton
     * - Verify Request password reset submit page is loaded
     * - Check email and open the resetPasswordLink from email
     * - Verify Password reset page is loaded
     * - Click on newPassword field and enter new password
     * - Click on retypeNewPassword field and enter the same password
     * - Click submitButton
     * - Verify Request password reset submit page is loaded and click goToHomepage button
     * - Verify Home page is loaded
     *
     * Postcondition:
     * - Close browser
     */

    @Test
    public void basicResetPasswordTest() throws InterruptedException {
        RequestPasswordResetPage requestPasswordResetPage = loginPage.open();
        Assert.assertTrue(requestPasswordResetPage.isPageLoaded(), "Request Password Reset Page is not loaded");

        RequestPasswordResetSubmitPage requestPasswordResetSubmitPage = requestPasswordResetPage.requestResetPassword("mr3y6p+test@gmail.com");
        Assert.assertTrue(requestPasswordResetSubmitPage.isPageLoaded(), "Request Password Reset Submit Page is not loaded");

        /* This step should contain login to the email and check email. Now wait 2 minutes was added */
        try {
            sleep(40000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        PasswordResetPage passwordResetPage = new PasswordResetPage(webDriver);
        Assert.assertTrue(passwordResetPage.isPageLoaded(), "Password Reset Page is not loaded");

        PasswordResetSubmitPage passwordResetSubmitPage = passwordResetPage.resetPasswordSubmit("P@ssword1");
        Assert.assertTrue(passwordResetSubmitPage.isPageLoaded(), "Password Reset Submit Page is not loaded");

        HomePage homePage = passwordResetSubmitPage.open();
        Assert.assertTrue(homePage.isPageLoaded(), "Home Page is not loaded");
    }


}
