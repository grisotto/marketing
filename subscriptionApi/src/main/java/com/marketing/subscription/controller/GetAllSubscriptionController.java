package com.marketing.subscription.controller;

import com.marketing.subscription.model.SubscriptionDTO;
import com.marketing.subscription.service.api.GetAllSubscriptionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Api(tags = "Subscription")
@RestController
@RequestMapping({"/api", "/api/v1"})
public class GetAllSubscriptionController {

    private final GetAllSubscriptionService getAllSubscriptionService;

    public GetAllSubscriptionController(GetAllSubscriptionService getAllSubscriptionService) {
        this.getAllSubscriptionService = getAllSubscriptionService;
    }


    @ApiOperation("Get all subscriptions to a newsletter")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Returns the all subscriptions"),
            @ApiResponse(responseCode = "404", description = "Returns subscriptions not found"),
            @ApiResponse(responseCode = "500", description = "Returns generic error message")
    })
    @GetMapping("/subscriptions")
    @ResponseStatus(HttpStatus.OK)
    public List<SubscriptionDTO> getAll() {

        return getAllSubscriptionService.getAll();
//        return MessageProperties.findMessage("ERROR-01");
    }
}
