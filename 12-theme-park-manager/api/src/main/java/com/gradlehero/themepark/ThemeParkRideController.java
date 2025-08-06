package com.gradlehero.themepark;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
public class ThemeParkRideController {

    @GetMapping(path = "/rides")
    public List<ThemeParkRide> getRides() {
        return Arrays.asList(
            new ThemeParkRide(
                "Rollercoaster",
                "Train ride that speeds you along.",
                RideStatusService.getRideStatus("rollercoaster")
            ),
            new ThemeParkRide(
                "Log flume",
                "Boat ride with plenty of splashes.",
                RideStatusService.getRideStatus("logflume")
            ),
            new ThemeParkRide(
                "Teacups",
                "Spinning ride in a giant tea-cup.",
                RideStatusService.getRideStatus("teacups")
            )
        );
    }
}