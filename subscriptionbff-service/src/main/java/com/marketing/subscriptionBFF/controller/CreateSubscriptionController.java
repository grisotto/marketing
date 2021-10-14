package com.marketing.subscriptionBFF.controller;

import com.marketing.subscriptionBFF.model.SubscriptionDTO;
import com.marketing.subscriptionBFF.service.api.CreateSubscriptionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Api(tags = "Subscription")
@RestController
@Log4j2
@RequestMapping({"/api", "/api/v1"})
public class CreateSubscriptionController {

    private final CreateSubscriptionService createSubscriptionService;

    public CreateSubscriptionController(CreateSubscriptionService createSubscriptionService) {
        this.createSubscriptionService = createSubscriptionService;
    }


    @ApiOperation("Create a subscription to a newsletter")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Returns the subscription identification"),
            @ApiResponse(responseCode = "400", description = "Returns the name of the missing required parameter"),
            @ApiResponse(responseCode = "500", description = "Returns generic error message")
    })
    @PostMapping("/subscriptions")
    @ResponseStatus(HttpStatus.CREATED)
    public Long create(@RequestBody @Valid SubscriptionDTO subscriptionDTO) {
        log.info("Creating subscription");
        return createSubscriptionService.create(subscriptionDTO);
    }
}
