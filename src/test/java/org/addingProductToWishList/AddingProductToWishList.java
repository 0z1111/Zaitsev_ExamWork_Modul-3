package org.addingProductToWishList;

import org.baseTests.BaseTest;
import org.data.TestData;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class AddingProductToWishList extends BaseTest {

    @Before
    public void LogIn() {
        pageProvider.getHomePage()
                .login(TestData.VALID_EMAIL, TestData.MAIN_PASSWORD)
                .checkIsRedirectedOnMyProfilePage();
    }

    @Test
    public void addingProductToWishList() {
        pageProvider.getHomePage()
                .openHomePage()
                .getHeaderProduct()
                .openSubMenu("Freediving", "Freediving Fins");
        pageProvider.getFreedivingFinsPage()
                .checkIsRedirectToFreedivingFinsPage()
                .openProductByName("Razor Polygon");
        pageProvider.getRazorPolygonPage()
                .addToWishlist();
        pageProvider.getHeaderElement()
                .clickOnMyWishlist()
                .checkIsRedirectToMyWishList()
                .checkIsProductAddedToWishList("Razor Polygon");
    }

    @After
    public void deleteProductFromWishListAndLogOut() {
        pageProvider.getHeaderElement()
                .clickOnMyWishlist()
                .deleteAllProductsFromWishList();
        pageProvider.getMyProfilePage()
                .logOut();
    }
}
