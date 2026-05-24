package com.darkSProject.ticketBooking.controller;

import com.darkSProject.ticketBooking.dto.AddTrainRequestDTO;
import com.darkSProject.ticketBooking.dto.AddTrainResponseDTO;
import com.darkSProject.ticketBooking.dto.ApiResponse;
import com.darkSProject.ticketBooking.services.TrainService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/trains")
public class TrainController {
    private final TrainService trainService;

    @PostMapping("/add-train")
    public ResponseEntity<ApiResponse<AddTrainResponseDTO>> addTrain(
            @RequestBody AddTrainRequestDTO request
    ){
        ApiResponse<AddTrainResponseDTO> response=trainService.addTrain(request);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }

}
