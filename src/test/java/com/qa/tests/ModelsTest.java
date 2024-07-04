package com.qa.tests;

import com.qa.pojo.ErrorResponse;
import com.qa.pojo.ModelRequest;
import com.qa.pojo.ModelResponse;
import com.qa.service.ModelsService;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.List;
import java.util.Random;

public class ModelsTest {
    
    @Test
    public void createModelSuccessTest() {
        int random = 10000 + new Random().nextInt(90000);
        ModelRequest modelRequest = new ModelRequest("Model_A_" + random, "Andrii_" + random);
        ModelsService modelsService = new ModelsService();

        ModelResponse modelResponse = modelsService.postModelResponse(modelRequest);
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(modelResponse.getName(), modelRequest.getName());
        softAssert.assertEquals(modelResponse.getOwner(), modelRequest.getOwner());
        softAssert.assertNotNull(modelResponse.getUuid());
        softAssert.assertAll();

        List<ModelResponse> modelResponseList = modelsService.getModelsResponse();
        Assert.assertTrue(modelResponseList.contains(modelResponse));
    }

    @Test
    public void deleteModelSuccessTest() {
        int random = 10000 + new Random().nextInt(90000);
        ModelRequest modelRequest = new ModelRequest("Model_A_" + random, "Andrii_" + random);
        ModelsService modelsService = new ModelsService();

        ModelResponse modelResponse = modelsService.postModelResponse(modelRequest);

       Response response = modelsService.deleteModel(modelResponse.getUuid());
       Assert.assertEquals(response.statusCode(), HttpStatus.SC_OK);

       List<ModelResponse> modelResponseList = modelsService.getModelsResponse();
       Assert.assertFalse(modelResponseList.contains(modelResponse));
    }

    @Test
    public void createModelValidationTest() {
        int random = 10000 + new Random().nextInt(90000);
        ModelRequest modelRequest = new ModelRequest("Model_A_" + random, "");
        ModelsService modelsService = new ModelsService();

        Response response = modelsService.postModel(modelRequest);
        Assert.assertEquals(response.statusCode(), HttpStatus.SC_BAD_REQUEST);
        Assert.assertEquals(response.body().as(ErrorResponse.class).getDetail(), "Empty name is not allowed");
    }
    
}
