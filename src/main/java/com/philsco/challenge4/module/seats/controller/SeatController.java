package com.philsco.challenge4.module.seats.controller;

import com.philsco.challenge4.module.films.dto.AddFilmDTO;
import com.philsco.challenge4.module.seats.service.SeatServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/cinema/seats")
public class SeatController {

    @Autowired
    private SeatServiceImpl seatServiceImpl;

    private static final Logger LOG = LoggerFactory.getLogger(SeatController.class);

    @GetMapping("/public")
    public ResponseEntity<HttpStatus> insertSeats() {
        seatServiceImpl.insertSeats();
        LOG.info("Seats has been Inserted");
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }
}
