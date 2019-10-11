package com.sonartype.numbertowords.controllers;

import com.sonartype.numbertowords.handlers.ApplicationResponseExceptionsHandler;
import com.sonartype.numbertowords.services.ConverterService;
import com.sonartype.numbertowords.services.impl.ConverterServiceImpl;
import io.restassured.module.mockmvc.RestAssuredMockMvc;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import static io.restassured.module.mockmvc.RestAssuredMockMvc.given;
import static org.hamcrest.core.IsEqual.equalTo;

@RunWith(MockitoJUnitRunner.class)
public class NumbersConverterControllerTest {
    @InjectMocks
    private ConverterService converterService = new ConverterServiceImpl();

    @InjectMocks
    private NumbersConverterController numbersConverterController = new NumbersConverterController(converterService);

    @InjectMocks
    private ApplicationResponseExceptionsHandler applicationResponseExceptionsHandler;

    @Before
    public void initialiseRestAssuredMockMvcStandalone() {
        RestAssuredMockMvc.standaloneSetup(numbersConverterController, applicationResponseExceptionsHandler);
    }

    @Test
    public void whenGetNumbersConverterReturnSuccessForNegative10Value(){
        final String result =
                "Ten";

        given().when()
                .get("/numbers-converter/10")
                .then()
                .assertThat()
                .statusCode(200)
                .body("words", equalTo(result));
    }

    @Test
    public void whenGetNumbersConverterReturnSuccessFor10Value(){
        final String result =
                "Ten";

        given().when()
                .get("/numbers-converter/10")
                .then()
                .assertThat()
                .statusCode(200)
                .body("words", equalTo(result));
    }

    @Test
    public void whenGetNumbersConverterReturnSuccessFor0Value(){
        final String result =
                "Zero";

        given().when()
                .get("/numbers-converter/0")
                .then()
                .assertThat()
                .statusCode(200)
                .body("words", equalTo(result));
    }

    @Test
    public void whenGetNumbersConverterReturnSuccessForNegative1000Value(){
        final String result =
                "Negative one thousand";

        given().when()
                .get("/numbers-converter/-1000")
                .then()
                .assertThat()
                .statusCode(200)
                .body("words", equalTo(result));
    }

    @Test
    public void whenGetNumbersConverterReturnSuccessFor999999999Value(){
        final String result =
                "Nine hundred ninety nine million nine hundred ninety nine thousand nine hundred and ninety nine";

        given().when()
                .get("/numbers-converter/999999999")
                .then()
                .assertThat()
                .statusCode(200)
                .body("words", equalTo(result));
    }

    @Test
    public void whenGetNumbersConverterReturnFailedForLettersValue(){
        given().when()
                .get("/numbers-converter/qewqewqe")
                .then()
                .assertThat()
                .statusCode(400);
    }

}