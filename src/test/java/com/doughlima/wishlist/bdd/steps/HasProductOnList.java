package com.doughlima.wishlist.bdd.steps;

import com.doughlima.wishlist.bdd.steps.utils.Mapper;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.hamcrest.Matchers.equalTo;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class HasProductOnList {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private Mapper mapper;

    private ResultActions result;

    @Given("I am a logged-in user:{string} and added the item {string} to my list")
    public void i_added_this_product_to_my_list(String userId, String productId) throws Exception {
        String uri = "/api/v1/"+userId+"/wishlist";
        val request = mapper.createWishRequestJson(productId);
        mockMvc.perform(post(uri)
                .content(request)
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print());
    }

    @When("perform a GET to endpoint {string}.")
    public void perform_GET_to_endpoint(String endpoint) throws Exception {
        result = mockMvc.perform(get(endpoint)).andDo(print());
    }

    @Then("the system should return {string}")
    public void the_system_should_return_the_expected_result(String expectedResult) throws Exception {
        result.andExpect(jsonPath("$.is-present", equalTo(Boolean.valueOf(expectedResult))));
    }

    @And("with following status code {string}")
    public void with_following_status_code(String statusCode) throws Exception {
        result.andExpect(status().is(equalTo(Integer.parseInt(statusCode))));
    }
}
