package com.marketing.subscription.controller;

import com.marketing.subscription.model.SubscriptionDTO;
import com.marketing.subscription.service.api.GetSubscriptionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@Api(tags = "Subscription")
@Log4j2
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
    @GetMapping("/subscriptions/{id}")
    @ResponseStatus(HttpStatus.OK)
    public SubscriptionDTO get(@PathVariable("id") Long id) {
        log.info("Getting subscription id {}", id);

        return getSubscriptionService.get(id);
    }
}
