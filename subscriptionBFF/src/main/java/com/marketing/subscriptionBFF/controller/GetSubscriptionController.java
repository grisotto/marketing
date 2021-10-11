package com.marketing.subscriptionBFF.controller;

import com.marketing.subscriptionBFF.model.SubscriptionDTO;
import com.marketing.subscriptionBFF.service.api.GetSubscriptionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@Api(tags = "Subscription")
@RestController
@RequestMapping({"/api", "/api/v1"})
public class GetSubscriptionController {

    private final GetSubscriptionService getSubscriptionService;

    public GetSubscriptionController(GetSubscriptionService getSubscriptionService) {
        this.getSubscriptionService = getSubscriptionService;
    }


    @ApiOperation("Get a subscription to a newsletter")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Returns the subscription details"),
            @ApiResponse(responseCode = "400", description = "Returns the subscription identification not found"),
            @ApiResponse(responseCode = "500", description = "Returns generic error message")
    })
    @GetMapping("/subscription/{id}")
    @ResponseStatus(HttpStatus.OK)
    public SubscriptionDTO get(@PathVariable("id") Long id) {

        return getSubscriptionService.get(id);
    }
}
