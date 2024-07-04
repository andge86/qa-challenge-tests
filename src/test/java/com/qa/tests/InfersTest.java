package com.qa.tests;

import com.qa.pojo.*;
import com.qa.service.InfersService;
import com.qa.service.ModelsService;
import com.qa.service.VersionsService;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Random;

public class InfersTest {

    @Test
    public void sendPromptSuccessTest() {
        int random = 10000 + new Random().nextInt(90000);
        ModelRequest modelRequest = new ModelRequest("Model_A_" + random, "Andrii_" + random);
        VersionRequest versionRequest = new VersionRequest("Version 1 - Tiny Llama", "TinyLlama/TinyLlama-1.1B-Chat-v1.0");
        final String PROMPT = "Please write Java method to calculate the sum of 2 numbers";

        ModelsService modelsService = new ModelsService();
        ModelResponse modelResponse = modelsService.postModelResponse(modelRequest);

        VersionsService versionsService = new VersionsService(modelResponse.getUuid());
        VersionResponse versionResponse = versionsService.postVersionResponse(versionRequest);

        InfersService infersService = new InfersService(modelResponse.getUuid(), versionResponse.getUuid());
        InferResponse inferResponse = infersService.postPromptResponse(new InferRequest(PROMPT));
        Assert.assertTrue(inferResponse.getText().contains(PROMPT),
                PROMPT + " is not present in " + inferResponse.getText());
    }

}
