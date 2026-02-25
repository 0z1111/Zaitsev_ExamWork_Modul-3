package org.searchProduct;

import org.baseTests.BaseTest;
import org.data.TestData;
import org.junit.Before;
import org.junit.Test;

public class searchProduct extends BaseTest {
    private final String productName = "Volo race";

    @Before
    public void LogIn() {
        pageProvider.getHomePage()
                .login(TestData.VALID_EMAIL, TestData.MAIN_PASSWORD);
    }
    @Test
    public void searchProduct() {
        pageProvider.getHomePage()
                .openHomePage()
                .searchProduct(productName)
                .verifySearchResult(productName);
    }
}
