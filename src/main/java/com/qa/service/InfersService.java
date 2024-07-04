package com.qa.service;

import com.qa.pojo.InferRequest;
import com.qa.pojo.InferResponse;
import com.qa.pojo.VersionRequest;
import com.qa.pojo.VersionResponse;
import io.restassured.response.Response;

import java.util.UUID;

public class InfersService extends BaseService {

    public InfersService(UUID modelUuid, UUID versionUuid) {
        super("/models/" + modelUuid.toString() + "/versions/" + versionUuid.toString() + "/infer");
    }


    public Response postPrompt(InferRequest inferRequest) {
        return callEndpoint(createRequestWithBody(inferRequest), "POST");
    }

    public InferResponse postPromptResponse(InferRequest inferRequest) {
        return postPrompt(inferRequest).getBody().as(InferResponse.class);
    }

}
