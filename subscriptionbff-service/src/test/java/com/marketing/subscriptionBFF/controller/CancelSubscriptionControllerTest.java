package com.marketing.subscriptionBFF.controller;

import com.marketing.subscriptionBFF.service.api.CancelSubscriptionService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(CancelSubscriptionController.class)
public class CancelSubscriptionControllerTest {


    @MockBean
    private CancelSubscriptionService cancelSubscriptionService;

    @Autowired
    private MockMvc mvc;


    @Test
    public void giveSubscriptionId_whenDeleteCancelSubscription_then204() throws Exception {

        mvc.perform(delete("/api/subscriptions/{id}", 0)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());

        ArgumentCaptor<Long> idCaptor = ArgumentCaptor.forClass(Long.class);
        verify(cancelSubscriptionService).cancel(idCaptor.capture());

        assertEquals(0, idCaptor.getValue());

    }
}