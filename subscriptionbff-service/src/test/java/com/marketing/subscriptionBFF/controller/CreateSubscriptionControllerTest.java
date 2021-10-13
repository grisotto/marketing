package com.marketing.subscriptionBFF.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.marketing.subscriptionBFF.model.Gender;
import com.marketing.subscriptionBFF.model.SubscriptionDTO;
import com.marketing.subscriptionBFF.service.api.CreateSubscriptionService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(CreateSubscriptionController.class)
public class CreateSubscriptionControllerTest {

    @MockBean
    private CreateSubscriptionService createSubscriptionService;

    @Autowired
    private MockMvc mvc;

    private JacksonTester<SubscriptionDTO> jsonSubscription;


    @Before
    public void setup() {
        JacksonTester.initFields(this, new ObjectMapper());
    }

    @Test
    public void giveSubscriptionDTO_whenPostCreateSubscription_thenSuccess() throws Exception {
        var subscriptionDTO = new SubscriptionDTO();
        subscriptionDTO.setConsent(true);
        subscriptionDTO.setEmail("email@email.com");
        subscriptionDTO.setDateOfBirth(LocalDate.now().minusDays(100));
        subscriptionDTO.setGender(Gender.MALE);
        subscriptionDTO.setFirstName("First name");
        subscriptionDTO.setNewsletterId(1);
        given(createSubscriptionService.create(subscriptionDTO)).willReturn("1");

        MockHttpServletResponse response = mvc.perform(
                        post("/api/subscriptions")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(jsonSubscription.write(subscriptionDTO).getJson())
                                .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.CREATED.value());
        assertThat(response.getContentAsString()).isEqualTo("1");

    }

    @Test
    public void giveSubscriptionDTOWithEmailNull_whenPostCreateSubscription_thenStatus400() throws Exception {
        var subscriptionDTO = new SubscriptionDTO();
        subscriptionDTO.setConsent(true);
        subscriptionDTO.setDateOfBirth(LocalDate.now().minusDays(100));
        subscriptionDTO.setGender(Gender.MALE);
        subscriptionDTO.setFirstName("First name");
        subscriptionDTO.setNewsletterId(1);
        given(createSubscriptionService.create(subscriptionDTO)).willReturn("1");

        mvc.perform(
                        post("/api/subscription")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(jsonSubscription.write(subscriptionDTO).getJson())
                                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andReturn();


    }


    @Test
    public void giveSubscriptionDTOWithConsentFalse_whenPostCreateSubscription_thenStatus400() throws Exception {
        var subscriptionDTO = new SubscriptionDTO();
        subscriptionDTO.setConsent(false);
        subscriptionDTO.setEmail("email@email.com");
        subscriptionDTO.setDateOfBirth(LocalDate.now().minusDays(100));
        subscriptionDTO.setGender(Gender.MALE);
        subscriptionDTO.setFirstName("First name");
        subscriptionDTO.setNewsletterId(1);
        given(createSubscriptionService.create(subscriptionDTO)).willReturn("1");

        mvc.perform(
                        post("/api/subscription")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(jsonSubscription.write(subscriptionDTO).getJson())
                                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andReturn();


    }

    @Test
    public void giveSubscriptionDTOWithDateOfBirthNull_whenPostCreateSubscription_thenStatus400() throws Exception {
        var subscriptionDTO = new SubscriptionDTO();
        subscriptionDTO.setConsent(true);
        subscriptionDTO.setEmail("email@email.com");
        subscriptionDTO.setGender(Gender.MALE);
        subscriptionDTO.setFirstName("First name");
        subscriptionDTO.setNewsletterId(1);
        given(createSubscriptionService.create(subscriptionDTO)).willReturn("1");

        mvc.perform(
                        post("/api/subscription")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(jsonSubscription.write(subscriptionDTO).getJson())
                                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andReturn();


    }

    @Test
    public void giveSubscriptionDTOWithNewsletterIdNull_whenPostCreateSubscription_thenStatus400() throws Exception {
        var subscriptionDTO = new SubscriptionDTO();
        subscriptionDTO.setConsent(true);
        subscriptionDTO.setEmail("email@email.com");
        subscriptionDTO.setDateOfBirth(LocalDate.now().minusDays(100));
        subscriptionDTO.setGender(Gender.MALE);
        subscriptionDTO.setFirstName("First name");
        given(createSubscriptionService.create(subscriptionDTO)).willReturn("1");

        mvc.perform(
                        post("/api/subscription")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(jsonSubscription.write(subscriptionDTO).getJson())
                                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andReturn();


    }


}