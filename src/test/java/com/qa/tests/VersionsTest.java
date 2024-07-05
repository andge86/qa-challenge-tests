package com.qa.tests;

import com.qa.pojo.ModelRequest;
import com.qa.pojo.ModelResponse;
import com.qa.pojo.VersionRequest;
import com.qa.pojo.VersionResponse;
import com.qa.service.ModelsService;
import com.qa.service.VersionsService;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.List;
import java.util.Random;

public class VersionsTest {

    @Test
    public void createVersionSuccessTest() {
        int random = 10000 + new Random().nextInt(90000);
        ModelRequest modelRequest = new ModelRequest("Model_A_" + random, "Andrii_" + random);
        VersionRequest versionRequest = new VersionRequest("Version 1 - Tiny Llama", "TinyLlama/TinyLlama-1.1B-Chat-v1.0");

        ModelsService modelsService = new ModelsService();
        ModelResponse modelResponse = modelsService.postModelResponse(modelRequest);

        VersionsService versionsService = new VersionsService(modelResponse.getUuid());
        VersionResponse versionResponse = versionsService.postVersionResponse(versionRequest);
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(versionResponse.getName(), versionRequest.getName());
        softAssert.assertEquals(versionResponse.getHugging_face_model(), versionRequest.getHugging_face_model());
        softAssert.assertNotNull(versionResponse.getUuid());
        softAssert.assertNotNull(versionResponse.getParent_model_id(), modelResponse.getUuid().toString());
        softAssert.assertAll();

        List<VersionResponse> modelResponseList = versionsService.getVersionsResponse();
        Assert.assertTrue(modelResponseList.contains(versionResponse));
    }

    @Test
    public void deleteVersionSuccessTest() {
        int random = 10000 + new Random().nextInt(90000);
        ModelRequest modelRequest = new ModelRequest("Model_A_" + random, "Andrii_" + random);
        VersionRequest versionRequest = new VersionRequest("Version 1 - Tiny Llama", "TinyLlama/TinyLlama-1.1B-Chat-v1.0");

        ModelsService modelsService = new ModelsService();
        ModelResponse modelResponse = modelsService.postModelResponse(modelRequest);

        VersionsService versionsService = new VersionsService(modelResponse.getUuid());
        VersionResponse versionResponse = versionsService.postVersionResponse(versionRequest);

        Response response = versionsService.deleteVersion(versionResponse.getUuid());
        Assert.assertEquals(response.statusCode(), HttpStatus.SC_OK);

        List<VersionResponse> versionResponseList = versionsService.getVersionsResponse();
        Assert.assertFalse(versionResponseList.contains(versionResponse));
    }

}
