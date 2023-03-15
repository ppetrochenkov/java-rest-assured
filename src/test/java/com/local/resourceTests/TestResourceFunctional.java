package com.local.resourceTests;

import com.local.client.resource.ResourceClient;
import com.local.model.ResourceDto;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.local.utils.TestSuiteTags.FUNCTIONAL;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;

public class TestResourceFunctional {

    private final ResourceClient resourceClient = new ResourceClient();

    @Test
    @Tag(FUNCTIONAL)
    @DisplayName("Verify all resources fetching")
    public void testListResourceFetch() {
        Response response = resourceClient.fetchAllResourcesResponse(20);

        int totalResourceCount = response.path("total");
        List<ResourceDto> resourceList = response.jsonPath().getList("data", ResourceDto.class);

        assertThat(totalResourceCount, equalTo(resourceList.size()));
    }

    @Test
    @Tag(FUNCTIONAL)
    @DisplayName("Verify existing single resource fetching")
    public void testSingleResourceFetch() {
        ResourceDto resourceDto = resourceClient.fetchSingleResourceResponse(2, true)
                .jsonPath()
                .getObject("data", ResourceDto.class);

        assertThat(resourceDto.getId(), equalTo(2));
    }

    @Test
    @Tag(FUNCTIONAL)
    @DisplayName("Verify not existing single resource fetching")
    public void testNotFoundResourceFetch() {
        Response response = resourceClient.fetchSingleResourceResponse(23, false);

        assertThat(response.getStatusCode(), equalTo(404));
        assertThat(response.getStatusLine(), containsString("Not Found"));
    }
}
