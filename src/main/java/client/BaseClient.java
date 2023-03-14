package client;

import config.ConfigReader;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.ResponseSpecification;
import org.hamcrest.Matcher;

import static org.hamcrest.Matchers.oneOf;

public class BaseClient {

    protected static final Matcher<Integer> STATUS_OK = oneOf(200, 201, 202, 204);

    static {
        RestAssured.baseURI = ConfigReader.baseApiConfig.baseUrl();
        RestAssured.basePath = ConfigReader.baseApiConfig.basePath();

        RestAssured.requestSpecification = new RequestSpecBuilder()
                .setAccept(ContentType.JSON)
                .setContentType(ContentType.JSON)
                .log(LogDetail.URI)
                .log(LogDetail.METHOD)
                .log(LogDetail.BODY)
                .build();
    }

    protected ResponseSpecification responseSpec(int statusCode) {
        return new ResponseSpecBuilder()
                .expectStatusCode(statusCode)
                .expectContentType(ContentType.JSON)
                .log(LogDetail.BODY)
                .build();
    }

    protected ResponseSpecification responseSpec(Matcher<Integer> statusCodes) {
        return new ResponseSpecBuilder()
                .expectStatusCode(statusCodes)
                .expectContentType(ContentType.JSON)
                .log(LogDetail.BODY)
                .build();
    }
}
