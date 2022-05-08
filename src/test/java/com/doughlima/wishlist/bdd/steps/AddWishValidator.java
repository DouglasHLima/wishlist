package com.doughlima.wishlist.bdd.steps;

import com.doughlima.wishlist.bdd.steps.utils.Mapper;
import com.doughlima.wishlist.domains.ValidationError;
import com.fasterxml.jackson.core.JsonProcessingException;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import lombok.SneakyThrows;
import lombok.val;
import org.hamcrest.Matchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.UUID;
import java.util.stream.IntStream;

import static java.lang.String.format;
import static org.hamcrest.Matchers.equalTo;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class AddWishValidator {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private Mapper mapper;

    private ResultActions result;

    @SneakyThrows
    @Given("A user Post {int} wishes to endpoint {string}")
    public void add_products(int quantityOfWishes, String endpoint) {
        for (int i = 0; i < quantityOfWishes; i++) {
            val request = mapper.createWishRequestJson(UUID.randomUUID().toString());
            mockMvc.perform(post(endpoint)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(request));
        }
    }

    @When("perform a POST to endpoint {string} adding a new wish")
    public void performAPOSTToEndpointAddingANewWish(String endpoint) throws Exception {
        val request = mapper.createWishRequestJson(UUID.randomUUID().toString());
        result = mockMvc.perform(post(endpoint)
                .contentType(MediaType.APPLICATION_JSON)
                .content(request));
    }

    @Then("the system should return the status code {int} - Bad Request")
    public void theSystemShouldReturnTheStatusCodeBadRequest(int statusCode) throws Exception {
        result.andExpect(status().is(equalTo(statusCode)));
    }

    @And("user GET user wishlist on endpoint {string} must be {int} products on size.")
    public void userWishlistMustBeProductsOnSize(String endpoint, int listSize) throws Exception {
        mockMvc.perform(get(endpoint))
                .andExpect(jsonPath("$._embedded.wishes.size()", equalTo(listSize)));
    }
}
