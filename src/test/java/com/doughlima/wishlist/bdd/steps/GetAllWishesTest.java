package com.doughlima.wishlist.bdd.steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.assertj.core.api.Assertions;
import org.hamcrest.Matchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.io.UnsupportedEncodingException;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Slf4j
public class GetAllWishesTest {

    @Autowired
    private MockMvc mockMvc;
    private ResultActions result;

    @When("perform a GET to endpoint {string}")
    public void perform_GET_to_endpoint(String endpoint) throws Exception {
        result = mockMvc.perform(get(endpoint)).andDo(print());
    }

    @Then("the system should return the list")
    public void should_return_the_list_with_status_code_expected() throws Exception {
        result.andExpect(jsonPath("$._embedded.wishes", Matchers.instanceOf(List.class)));
    }

    @And("with status code {string}")
    public void withStatusCodeExpectedResult(String statusCode) throws Exception {
        result.andExpect(status().is(Integer.parseInt(statusCode)));
    }
}
