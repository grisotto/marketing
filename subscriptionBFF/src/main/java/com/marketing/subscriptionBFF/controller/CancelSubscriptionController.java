package com.marketing.subscriptionBFF.controller;

import com.marketing.subscriptionBFF.service.api.CancelSubscriptionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@Api(tags = "Subscription")
@RestController
@RequestMapping({"/api", "/api/v1"})
public class CancelSubscriptionController {

    private final CancelSubscriptionService cancelSubscriptionService;

    public CancelSubscriptionController(CancelSubscriptionService cancelSubscriptionService) {
        this.cancelSubscriptionService = cancelSubscriptionService;
    }

    @ApiOperation("Cancel an existing subscription to a newsletter")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Returns the subscription identification"),
            @ApiResponse(responseCode = "500", description = "Returns generic error message")
    })
    @DeleteMapping("/subscription/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public boolean cancel(@PathVariable("id") String id) {

        return cancelSubscriptionService.cancel(id);
    }
}
