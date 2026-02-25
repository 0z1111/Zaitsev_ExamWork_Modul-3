package org.accountDeletion;

import org.baseTests.BaseTest;
import org.data.TestData;
import org.junit.Before;
import org.junit.Test;

public class accountDeletion extends BaseTest {

    @Before
    public void LogIn() {
        pageProvider.getHomePage()
                .login(TestData.VALID_EMAIL, TestData.MAIN_PASSWORD);
    }

    @Test
    public void accountDeletion() {
        pageProvider.getHeaderElement()
                .clickOnMyProfile();
        pageProvider.getMyProfilePage()
                .clickOnAccountInformationMenuItem()
                .clickOnDeleteAccountButton()
                .checkIsSignInPopupDisplayed();
    }
}