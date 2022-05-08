package com.doughlima.wishlist.bdd.steps;

import com.doughlima.wishlist.bdd.steps.utils.Mapper;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import lombok.val;
import org.hamcrest.Matchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static java.lang.Integer.parseInt;
import static org.hamcrest.Matchers.equalTo;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class DeleteWishTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private Mapper mapper;
    private ResultActions result;
    @Given("I am a logged-in user:{string} and added the item {string} to my wishlist")
    public void add_product_to_wishlist(String userId, String productId) throws Exception {
        String uri = "/api/v1/"+userId+"/wishlist";
        val request = mapper.createWishRequestJson(productId);
        mockMvc.perform(post(uri)
                        .content(request)
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print());
    }

    @When("perform a DELETE to endpoint {string}.")
    public void perform_delete_to_endpoint(String endpoint) throws Exception {
        result = mockMvc.perform(delete(endpoint));
    }

    @Then("the system should return the following status code {string}")
    public void should_return_expected_status_code(String statusCode) throws Exception {
        result.andExpect(status().is(equalTo(parseInt(statusCode))));
    }
}
