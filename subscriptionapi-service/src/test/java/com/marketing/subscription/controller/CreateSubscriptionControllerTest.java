package com.marketing.subscription.controller;

import com.marketing.subscription.model.Gender;
import com.marketing.subscription.model.SubscriptionDTO;
import com.marketing.subscription.service.api.CreateSubscriptionService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureJsonTesters
@WebMvcTest(CreateSubscriptionController.class)
public class CreateSubscriptionControllerTest {


    private static final String API_SUBSCRIPTIONS = "/api/subscriptions";

    @MockBean
    private CreateSubscriptionService createSubscriptionService;

    @Autowired
    private MockMvc mvc;

    @Autowired
    private JacksonTester<SubscriptionDTO> jsonSubscription;


    @Test
    public void giveSubscriptionDTO_whenPostCreateSubscription_thenSuccess() throws Exception {
        var subscriptionDTO = new SubscriptionDTO();
        subscriptionDTO.setConsent(true);
        subscriptionDTO.setEmail("email@email.com");
        subscriptionDTO.setDateOfBirth(LocalDate.now().minusDays(100));
        subscriptionDTO.setGender(Gender.MALE);
        subscriptionDTO.setFirstName("First name");
        subscriptionDTO.setNewsletterId(1);
        given(createSubscriptionService.create(subscriptionDTO)).willReturn(1L);

        MockHttpServletResponse response = mvc.perform(
                        post(API_SUBSCRIPTIONS)
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
        given(createSubscriptionService.create(subscriptionDTO)).willReturn(1L);

        mvc.perform(
                        post(API_SUBSCRIPTIONS)
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
        given(createSubscriptionService.create(subscriptionDTO)).willReturn(1L);

        mvc.perform(
                        post(API_SUBSCRIPTIONS)
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
        given(createSubscriptionService.create(subscriptionDTO)).willReturn(1L);

        mvc.perform(
                        post(API_SUBSCRIPTIONS)
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
        given(createSubscriptionService.create(subscriptionDTO)).willReturn(1L);

        mvc.perform(
                        post(API_SUBSCRIPTIONS)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(jsonSubscription.write(subscriptionDTO).getJson())
                                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andReturn();


    }
}
