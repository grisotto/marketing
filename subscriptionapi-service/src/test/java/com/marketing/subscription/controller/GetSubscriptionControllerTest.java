package com.marketing.subscription.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.marketing.subscription.model.Gender;
import com.marketing.subscription.model.SubscriptionDTO;
import com.marketing.subscription.service.api.GetSubscriptionService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(GetSubscriptionController.class)
public class GetSubscriptionControllerTest {

    @MockBean
    private GetSubscriptionService getAllSubscriptionService;

    @Autowired
    private MockMvc mvc;

    private JacksonTester<SubscriptionDTO> jsonSubscriptionDTO;

    @Before
    public void setup() {
        JacksonTester.initFields(this, new ObjectMapper());
    }


    @Test
    public void giveSubscriptionId_whenGetSubscription_thenSuccess() throws Exception {
        var subscriptionDTO = new SubscriptionDTO();
        subscriptionDTO.setConsent(true);
        subscriptionDTO.setEmail("email@email.com");
        subscriptionDTO.setDateOfBirth(LocalDate.now().minusDays(100));
        subscriptionDTO.setGender(Gender.MALE);
        subscriptionDTO.setFirstName("First name");
        subscriptionDTO.setNewsletterId(1);

        when(getAllSubscriptionService.get(1L))
                .thenReturn(subscriptionDTO);


        MockHttpServletResponse response = mvc.perform(get("/api/subscriptions/{id}", 1L)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();


        assertThat(response.getContentAsString()).isEqualTo(
                jsonSubscriptionDTO.write(subscriptionDTO
                ).getJson());


    }
}
