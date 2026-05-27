package com.darkSProject.ticketBooking.train.controller;

import com.darkSProject.ticketBooking.common.dto.PaginationResponseDTO;
import com.darkSProject.ticketBooking.train.dto.AddTrainRequestDTO;
import com.darkSProject.ticketBooking.train.dto.AddTrainResponseDTO;
import com.darkSProject.ticketBooking.common.dto.ApiResponse;
import com.darkSProject.ticketBooking.train.dto.SearchTrainResponseDTO;
import com.darkSProject.ticketBooking.train.service.TrainService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

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
