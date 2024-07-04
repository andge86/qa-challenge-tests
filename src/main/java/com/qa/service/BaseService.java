package com.qa.service;

import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import java.util.UUID;

public class BaseService {

    private final String BASE_URI = "http://localhost:8000";

  private RequestSpecification requestSpecification;
    private ResponseSpecification responseSpecification;

    public BaseService(String uri) {
        requestSpecification = new RequestSpecBuilder()
                .setBaseUri(BASE_URI + uri)
                .log(LogDetail.ALL)
                .build()
                .filter(new AllureRestAssured())
                .contentType(ContentType.JSON);
        responseSpecification = new ResponseSpecBuilder()
                .log(LogDetail.ALL)
                .build();
    }

    protected RequestSpecification createRequest() {
        return RestAssured.given(requestSpecification);
    }

    protected <T> RequestSpecification createRequestWithBody(T bodyObject) {
        return createRequest()
                .body(bodyObject);
    }

    protected RequestSpecification createRequestWithUUID(UUID uuid) {
        return createRequest()
                .basePath(uuid.toString());
    }

    protected Response callEndpoint(RequestSpecification requestSpecification, String method) {
            Response response = switch (method) {
                case "GET" -> requestSpecification.get();
                case "POST" -> requestSpecification.post();
                case "DELETE" -> requestSpecification.delete();
                default -> requestSpecification.head();
            };

        return response
                .then()
                .spec(responseSpecification)
                .extract()
                .response();
    }

}
