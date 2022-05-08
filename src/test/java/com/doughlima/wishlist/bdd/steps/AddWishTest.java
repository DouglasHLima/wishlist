package com.doughlima.wishlist.bdd.steps;

import com.doughlima.wishlist.bdd.steps.utils.Mapper;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;


import static org.hamcrest.Matchers.equalTo;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@Slf4j
public class AddWishTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    Mapper mapper;

    private String request;
    private ResultActions result;

    @Given("I am a user logged in, i want to add the product: {string} to my wish list")
    public void add_the_product_to_my_wishList(String productId) throws JsonProcessingException {
        request = mapper.createWishRequestJson(productId);
    }

    @When("perform a POST to endpoint {string}")
    public void perform_POST_to_endpoint(String endpoint) throws Exception {
        val request = post(endpoint)
                .content(this.request)
                .contentType(MediaType.APPLICATION_JSON);
        result = mockMvc.perform(request).andDo(print());
    }

    @Then("the system should return the status code {string}")
    public void should_return_the_expected_result(String status) throws Exception {
        result.andExpect(status().is(Integer.parseInt(status)));
    }

    @And("must display {string} errors")
    public void must_display_this_quantity_of_errors(String errors) throws Exception {
        int errorQuantity = Integer.parseInt(errors);
        if (errorQuantity>0) {
            result.andExpect(jsonPath("$.errors.size()", equalTo(errorQuantity)));
        }
    }
}
