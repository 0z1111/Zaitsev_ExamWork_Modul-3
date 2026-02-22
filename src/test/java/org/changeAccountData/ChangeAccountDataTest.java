package org.changeAccountData;

import org.baseTests.BaseTest;
import org.data.TestData;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ChangeAccountDataTest extends BaseTest {

    @Before
    public void LogIn() {
        pageProvider.getHomePage()
                .login(TestData.VALID_EMAIL, TestData.MAIN_PASSWORD);
    }

    @Test
    public void changePassword() {
        pageProvider.getMyProfilePage()
                .clickOnAccountInformationMenuItem()
                .clickOnChangePasswordButton()
                .enterCurrentPassword(TestData.MAIN_PASSWORD)
                .enterNewPassword(TestData.NEW_PASSWORD)
                .enterConfirmNewPassword(TestData.NEW_PASSWORD)
                .clickSaveNewPasswordButton();
        pageProvider.getMyProfilePage()
                .logOut();
        pageProvider.getHomePage()
                .login(TestData.VALID_EMAIL, TestData.NEW_PASSWORD);
        pageProvider.getMyProfilePage()
                .clickOnAccountInformationMenuItem()
                .clickOnChangePasswordButton()
                .enterCurrentPassword(TestData.NEW_PASSWORD)
                .enterNewPassword(TestData.MAIN_PASSWORD)
                .enterConfirmNewPassword(TestData.MAIN_PASSWORD)
                .clickSaveNewPasswordButton();
    }

    @After
    public void logOut() {
        pageProvider.getMyProfilePage()
                .logOut();
    }
}
