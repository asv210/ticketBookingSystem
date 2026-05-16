package com.darkSProject.ticketBooking.controller;

import com.darkSProject.ticketBooking.dto.*;
import com.darkSProject.ticketBooking.services.TrainService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/trains")
public class TrainController {
    private final TrainService trainService;

    @PostMapping("/add-train")
    public ResponseEntity<ApiResponse<AddTrainResponseDTO>> addTrain(
            @Valid @RequestBody AddTrainRequestDTO request
    ) {
        ApiResponse<AddTrainResponseDTO> response = trainService.addTrain(request);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }

    @GetMapping("/search")
    public ResponseEntity<ApiResponse<SearchTrainResponseDTO>>
    searchTrain(
            @RequestBody
            @Valid
            SearchTrainRequestDTO request,

            @RequestParam(defaultValue = "0")
            int page,

            @RequestParam(defaultValue = "10")
            int size
    ) {
        return ResponseEntity.ok(
                trainService.searchTrain(
                        request,
                        page,
                        size
                )
        );
    }
}
