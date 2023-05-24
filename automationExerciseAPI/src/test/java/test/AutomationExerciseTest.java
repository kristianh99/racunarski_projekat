package test;

import com.github.javafaker.Faker;
import org.junit.Test;
import java.util.HashMap;
import static io.restassured.RestAssured.*;

public class AutomationExerciseTest {
    final static String BASE_URL = "https://automationexercise.com";

    @Test
    public void getProductList() {
        given()
                .baseUri(BASE_URL)
        .when()
                .get("/api/productsList")
        .then()
                .statusCode(200)
                .extract().response().prettyPrint();
    }

    @Test
    public void searchProduct() {
        given()
                .baseUri(BASE_URL)
                .formParams("search_product", "tshirt")
        .when()
                .post("/api/searchProduct")
        .then()
                .statusCode(200)
                .extract().response().prettyPrint();
    }

    @Test
    public void createAccount() {
        HashMap<String, String> user = createUser();

        given()
                .baseUri(BASE_URL)
                .formParams(user)
        .when()
                .post("/api/createAccount")
        .then()
                .statusCode(200)
                .extract().response().prettyPrint();
    }

    @Test
    public void deleteAccount() {
        final String EMAIL = "";
        final String PASSWORD = "fishtest123";

        given()
                .baseUri(BASE_URL)
                .formParams("email", EMAIL, "password", PASSWORD)
        .when()
                .delete("/api/deleteAccount")
        .then()
                .statusCode(200)
                .extract().response().prettyPrint();
    }

    @Test
    public void verifyLogin() {
        final String EMAIL = "dean.parisian140913@test.com";
        final String PASSWORD = "fishtest1234";

        given()
                .baseUri(BASE_URL)
                .formParams("email", EMAIL, "password", PASSWORD)
        .when()
                .post("/api/verifyLogin")
        .then()
                .statusCode(200)
                .extract().response().prettyPrint();
    }

    public HashMap<String, String> createUser(){
        Faker faker = new Faker();
        HashMap<String, String> user = new HashMap<>();
        String username = faker.name().username();

        user.put("name", username);
        user.put("email", username + faker.number().numberBetween(10000, 999999) + "@test.com");
        user.put("password", "fishtest123");
        user.put("title", "Mr");
        user.put("birth_date", faker.number().numberBetween(1, 28) + "");
        user.put("birth_month", faker.number().numberBetween(1, 12) + "");
        user.put("birth_year", faker.number().numberBetween(1970, 2007) + "");
        user.put("firstname", faker.name().firstName());
        user.put("lastname", faker.name().lastName());
        user.put("company", faker.company().name());
        user.put("address1", faker.address().streetAddress());
        user.put("address2", "");
        user.put("country", faker.address().country());
        user.put("zipcode", faker.address().zipCode());
        user.put("state", faker.address().state());
        user.put("city", faker.address().city());
        user.put("mobile_number", faker.phoneNumber() + "");

        System.out.println("username: " + username);
        System.out.println("email: " + user.get("email"));

        return user;
    }
}
