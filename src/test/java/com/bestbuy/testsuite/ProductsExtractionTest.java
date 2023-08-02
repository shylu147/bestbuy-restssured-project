package com.bestbuy.testsuite;

import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;

import static com.bestbuy.testsuite.ProductsAssertionTest.response;
import static io.restassured.RestAssured.given;

public class ProductsExtractionTest {
    static ValidatableResponse response;

    @BeforeClass
    public static void inIt() {
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = 3030;
        response = given()
                .when()
                .get("/products")
                .then().statusCode(200);


    }


    // 1) Extract the value of limit
    @Test
    public void test001() {
        int limit = response.extract().path("limit");


        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The value of limit is : " + limit);
        System.out.println("------------------End of Test---------------------------");

    }

    // Extract the total
    @Test
    public void test002() {
        int total = response.extract().path("total");


        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The value of limit is : " + total);
        System.out.println("------------------End of Test---------------------------");

    }


    //Extract the name of 5th product
    @Test
    public void test003() {
        String productName = response.extract().path("data[4].name");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The 5th product name is : " + productName);
        System.out.println("------------------End of Test---------------------------");
    }

    // Extract the names of all the products
    @Test
    public void test004() {
        List<String> listOfAllProductsName = response.extract().path("data.name");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("List of Ids are : " + listOfAllProductsName);
        System.out.println("------------------End of Test---------------------------");
    }

    // Extract the productId of all the products
    @Test
    public void test005() {
        List<Integer> listOfIds = response.extract().path("data.id");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("List of Ids are : " + listOfIds);
        System.out.println("------------------End of Test---------------------------");
    }
    //Print the size of the data list
    @Test
    public void test006() {
        List<Integer> listOfIds = response.extract().path("data.id");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("List of Ids are : " + listOfIds.size());
        System.out.println("------------------End of Test---------------------------");
    }

    //Get all the value of the product where product name = Energizer - MAX Batteries AA (4-Pack)
    @Test
    public void test007() {
        List<HashMap<String, ?>> valueListMap = response.extract().path("data.findAll{it.name == 'Energizer - MAX Batteries AA (4-Pack)'}");
        HashMap<String, ?> valueMap = valueListMap.get(0);
        int value = (Integer) valueMap.get("id");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The value of product whose name 'Energizer - N Cell E90 Batteries (2-Pack)' is : "+valueMap);
        System.out.println("------------------End of Test---------------------------");
    }

    //Get the model of the product where product name = Energizer - N Cell E90 Batteries (2-Pack)

    // Get all the categories of 8th products
    @Test
    // Get the model of the product where product name = Energizer - N Cell E90 Batteries (2-Pack)
    public void test008() {
        List<String> nCellEnergizer = response.extract().path("data.findAll{it.name == 'Energizer - N Cell E90 Batteries (2-Pack)'}.model");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("'Energizer - N Cell E90 Batteries (2-Pack) : " + nCellEnergizer);
        System.out.println("------------------End of Test---------------------------");
    }
    //Get all the categories of 8th product
    @Test
    public void test009() {
        List<Integer> listOfCategories = response.extract().path("data[7].categories");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("List of categories are : " + listOfCategories);
        System.out.println("------------------End of Test---------------------------");
    }
    //Get categories of the store where product id = 150115
    @Test
    public void test010() {
        List<HashMap<String, ?>> cat150115 = response.extract().path("data[3].categories");
        System.out.println("storeservices details of the service name = Magnolia Home Theater" + cat150115 );}


    //Get all the descriptions of all the products
    @Test
    public void test011() {
        List<?> listOfDescriptions = response.extract().path("data.description");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("List of categories are : " + listOfDescriptions);
        System.out.println("------------------End of Test---------------------------");
    }
    // Get id of all the all categories of all the products
    @Test
    public void test012() {
        List<Integer> listOfId = response.extract().path("data.categories.id");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("List of categories are : " + listOfId);
        System.out.println("------------------End of Test---------------------------");
    }

    //Find the product names Where type = HardGood
    @Test
    public void test013() {
         List<String> productName = response.extract().path("data.findAll{it.type == 'HardGood'}.name");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println(" type  : " + productName);
        System.out.println("------------------End of Test---------------------------");}

    //Find the Total number of categories for the product where product name = Duracell - AA 1.5V CopperTop Batteries (4-Pack)
        @Test

        public void test014() {
            List<String> categoriesTotal = response.extract().path("data.findAll{it.name == 'Duracell - AA 1.5V CopperTop Batteries (4-Pack)'}.categories");
            System.out.println("------------------StartingTest---------------------------");
            System.out.println("The total num of categories is : " + categoriesTotal.size());
            System.out.println("------------------End of Test---------------------------");
        }
    //Find the createdAt for all products whose price < 5.49
    @Test
    public void test015() {
            List<?> createdAtList = response.extract().path("data.findAll{it.categories.findAll{it.price<'5.49'}}.categories.createdAt");
            System.out.println("------------------StartingTest---------------------------");
            System.out.println("createdList the product where categories : " + createdAtList);
            System.out.println("------------------End of Test---------------------------");
        }
    //Find the name of all categories Where product name = “Energizer - MAX Batteries AA (4-Pack)”
    @Test
    public void test016() {
        List<String> totalCategories = response.extract().path("data.findAll{it.name=='Energizer - MAX Batteries AA (4-Pack)'}.categories");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println(" name of all categories :" + totalCategories);
        System.out.println("------------------End of Test---------------------------");
    }
    //Find the manufacturer of all the products
    @Test
    public void test017() {
        List<String> listManu = response.extract().path("data.manufacturer");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("List manu : " + listManu);
        System.out.println("------------------End of Test---------------------------");
    }
    @Test
    // Find the imge of products whose manufacturer is = Energizer
    public void test018() {
        List<String> imgEnergizer = response.extract().path("data.findAll{it.manufacturer=='Energizer'}.image");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("the imge of products whose manufacturer is = Energizer : " + imgEnergizer);
        System.out.println("------------------End of Test---------------------------");

    }

    //Find the createdAt for all categories products whose price > 5.99
    @Test
    // Find the createdAt for all categories products whose price > 5.99
    public void test039() {
        List<HashMap<String, ?>> price599 = response.extract().path("data.findAll{it.price>5.99}.createdAt");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("the createdAt for all categories products whose price > '5.99'" + price599);
        System.out.println("------------------End of Test---------------------------");
    }

    // Find the uri of all the product
    @Test
    public void test020(){
        List<String>allUrL=response.extract().path("data.url");
        System.out.println("All ; " +allUrL);
    }
}
