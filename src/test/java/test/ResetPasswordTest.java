package test;

import org.testng.Assert;
import org.testng.annotations.Test;
import page.*;


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
    public void basicResetPasswordTest() {
        String userEmail = "mzub.test@gmail.com";

        RequestPasswordResetPage requestPasswordResetPage = loginPage.clickOnForgotPasswordLink();
        Assert.assertTrue(requestPasswordResetPage.isPageLoaded(), "Request Password Reset Page is not loaded");

        RequestPasswordResetSubmitPage requestPasswordResetSubmitPage = requestPasswordResetPage.findAccount(userEmail);
        Assert.assertTrue(requestPasswordResetSubmitPage.isPageLoaded(), "Request Password Reset Submit Page is not loaded");

        SetNewPasswordPage setNewPasswordPage = requestPasswordResetSubmitPage.navigateToLinkFromEmail();
        Assert.assertTrue(setNewPasswordPage.isPageLoaded(), "Password Reset Page is not loaded");

        PasswordResetSubmitPage passwordResetSubmitPage = setNewPasswordPage.resetPasswordSubmit("P@ssword1");
        Assert.assertTrue(passwordResetSubmitPage.isPageLoaded(), "Password Reset Submit Page is not loaded");

        HomePage homePage = passwordResetSubmitPage.open();
        Assert.assertTrue(homePage.isPageLoaded(), "Home Page is not loaded");
    }


}
