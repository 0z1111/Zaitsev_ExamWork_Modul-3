package org.accountCreation;

import org.baseTests.BaseTest;
import org.junit.After;
import org.junit.Test;

import java.time.LocalDate;

import static org.data.TestData.*;

public class AccountCreationTest extends BaseTest {

    @Test
    public void AccountCreation() {
        pageProvider.getHomePage()
                .openHomePage()
                .getHeaderElement()
                .clickOnMyProfile();
        pageProvider.getHomePage()
                .acceptCookiesIfPresent();
        pageProvider.getHomePage()
                .checkIsSignInPopupDisplayed();
        pageProvider.getHomePage()
                .submitEmailInSignInPopup(VALID_EMAIL);
        pageProvider.getHomePage()
                .checkIsCreateAccountPopupDisplayed();
        pageProvider.getHomePage()
                .submitRegistrationForm(FIRST_NAME, LAST_NAME, LocalDate.of(1993, 8
                        , 26), MAIN_PASSWORD, MAIN_PASSWORD);
        pageProvider.getHomePage()
                .checkIsRegistrationSuccessfulAndClickDone();
        pageProvider.getMyProfilePage()
                .checkIsRedirectedOnMyProfilePage();
    }

    @After
    public void logOut() {
        pageProvider.getMyProfilePage()
                .logOut();
    }
}
