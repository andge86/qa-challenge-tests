package com.qa.service;

import com.qa.pojo.ModelRequest;
import com.qa.pojo.ModelResponse;
import io.restassured.response.Response;

import java.util.List;
import java.util.UUID;

public class ModelsService extends BaseService {

    public ModelsService() {
        super("/models");
    }


    public Response postModel(ModelRequest modelRequest) {
        return callEndpoint(createRequestWithBody(modelRequest), "POST");
    }

    public ModelResponse postModelResponse(ModelRequest modelRequest) {
        return postModel(modelRequest).getBody().as(ModelResponse.class);
    }

    public Response getModels() {
        return callEndpoint(createRequest(), "GET");
    }

    public List<ModelResponse> getModelsResponse() {
        return List.of(getModels().as(ModelResponse[].class));
    }

    public Response deleteModel(UUID uuid) {
        return callEndpoint(createRequestWithUUID(uuid), "DELETE");
    }

}
