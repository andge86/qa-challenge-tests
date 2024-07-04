package com.qa.service;

import com.qa.pojo.VersionRequest;
import com.qa.pojo.VersionResponse;
import io.qameta.allure.Step;
import io.restassured.response.Response;

import java.util.List;
import java.util.UUID;

public class VersionsService extends BaseService {

    public VersionsService(UUID modelUuid) {
        super("/models/" + modelUuid.toString() + "/versions");
    }

    @Step
    public Response postVersion(VersionRequest versionRequest) {
        return callEndpoint(createRequestWithBody(versionRequest), "POST");
    }

    public VersionResponse postVersionResponse(VersionRequest versionRequest) {
        return postVersion(versionRequest).getBody().as(VersionResponse.class);
    }

    @Step
    public Response getVersions() {
        return callEndpoint(createRequest(), "GET");
    }

    public List<VersionResponse> getVersionsResponse() {
        return List.of(getVersions().as(VersionResponse[].class));
    }

    @Step
    public Response deleteVersion(UUID versionUuid) {
        return callEndpoint(createRequestWithUUID(versionUuid), "DELETE");
    }

}
