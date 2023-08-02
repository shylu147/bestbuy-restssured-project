package com.bestbuy.testsuite;

import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;

import static io.restassured.RestAssured.given;

public class StoresExtractionTest {

    static ValidatableResponse response;

    @BeforeClass
    public static void inIt() {
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = 3030;
        response = given()
                .when()
                .get("/stores")
                .then().statusCode(200);
    }

    // Extract the limit
    @Test

    public void test001() {
        int limit = response.extract().path("limit");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The value of limit is : " + limit);
        System.out.println("------------------End of Test---------------------------");
    }

    //Extract the total
    @Test
    public void test002() {
        int total = response.extract().path("total");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The value of limit is : " + total);
        System.out.println("------------------End of Test---------------------------");
    }

    //Extract the name of 5th store
    @Test
    public void test003() {
        String name = response.extract().path("data[4].name");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The value of name is : " + name);
        System.out.println("------------------End of Test---------------------------");
    }

    //Extract the names of all the store
    @Test
    public void test004() {
        List<String> allStoreNames = response.extract().path("data.name");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The value of name is : " + allStoreNames);
        System.out.println("------------------End of Test---------------------------");
    }

    //Extract the storeId of all the store
    @Test
    public void test005() {
        List<Integer> allStoreIds = response.extract().path("data.services.storeservices.storeId");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The value of name is : " + allStoreIds);
        System.out.println("------------------End of Test---------------------------");
    }

    //Print the size of the data list
    @Test
    public void test006() {
        int size = response.extract().path("data.size");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The value of name is : " + size);
        System.out.println("------------------End of Test---------------------------");
    }

    //Get all the value of the store where store name = St Cloud
    @Test
    public void test007() {

        List<HashMap<String, ?>> stCloud = response.extract().path("data.findAll{it.name == 'St Cloud'}");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The value are: " + stCloud);
        System.out.println("------------------End of Test---------------------------");
    }

    // Get the address of the store where store name = Rochester
    @Test
    public void test008() {

        List<String> address = response.extract().path("data.findAll{it.name == 'Rochester'}.address");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The value are: " + address);
        System.out.println("------------------End of Test---------------------------");
    }

    //Get all the services of 8th store
    @Test
    public void test009() {
        List<String> store8th = response.extract().path("data[7].services");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The services of store8th : " + store8th);
        System.out.println("------------------End of Test---------------------------");
    }

    //Get store services of the store where service name = Windows Store
    @Test
    public void test010() {
        List<HashMap<String, ?>> windowsStore = response.extract().path("data.findAll{it.services.findAll{it.name=='Windows Store'}}.services.storeservices");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The Services the store where service name = Windows Store : " + windowsStore);
        System.out.println("------------------End of Test---------------------------");
    }

    // Get all the storeId of all the store
    @Test
    //Get all the storeId of all the store
    public void test011() {
        List<Integer> allStoreID = response.extract().path("data.services.storeservices.storeId");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The value of StorId is : " + allStoreID);
        System.out.println("------------------End of Test---------------------------");
    }


    @Test
    // Get id of all the store
    public void test012() {
        List<Integer> allStoreID = response.extract().path("data.id");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The value of StorId is : " + allStoreID);
        System.out.println("------------------End of Test---------------------------");
    }

    //Find the store names Where state = ND
    @Test

    public void test013() {
        List<String> NDStore = response.extract().path("data.findAll{it.state == 'ND'}.name");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The names of NDStore is : " + NDStore);
        System.out.println("------------------End of Test---------------------------");
    }

    // Find the Total number of services for the store where store name = Rochester
    @Test

    public void test014() {
        List<Integer> servicesTotal = response.extract().path("data.findAll{it.name == 'Rochester'}.services");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The names of NDStore is : " + servicesTotal.size());
        System.out.println("------------------End of Test---------------------------");
    }

    //Find the createdAt for all services whose name = “Windows Store”
    @Test
    public void test015() {
        List<?> createdAtList = response.extract().path("data.findAll{it.services.findAll{it.name=='Windows Store'}}.services.storeservices.createdAt");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("createdList the store where service name = Windows Store : " + createdAtList);
        System.out.println("------------------End of Test---------------------------");
    }

    // Find the name of all services Where store name = “Fargo”
    @Test
    public void test016() {
        List<String> totalFargo = response.extract().path("data.findAll{it.name=='Fargo'}.services.name");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The Services the store where service name = Fargo : " + totalFargo);
        System.out.println("------------------End of Test---------------------------");
    }

    //Find the zip of all the store
    @Test
    public void test017() {
        List<String> listZip = response.extract().path("data.zip");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The value of name is : " + listZip);
        System.out.println("------------------End of Test---------------------------");
    }


    @Test
    // Find the zip of store name = Roseville
    public void test018() {
        List<String> zipRoseville = response.extract().path("data.findAll{it.name=='Roseville'}.zip");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The zip of all the store : " + zipRoseville);
        System.out.println("------------------End of Test---------------------------");
    }


    //Find the store services details of the service name = Magnolia Home Theater
    @Test
    public void test019() {
        List<HashMap<String, ?>> name = response.extract().path("data.findAll { it.services.find { it.name == 'Magnolia Home Theater'} != null }.services.storeservices");
        System.out.println("storeservices details of the service name = Magnolia Home Theater" + name);}


    // Find the lat of all the stores
    @Test
    public void test20() {
        List<String> listLat = response.extract().path("data.lat");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The value of name is : " + listLat);
        System.out.println("------------------End of Test---------------------------");
    }

}