package com.philsco.challenge4.module.seats.service;

import com.philsco.challenge4.impl.seats.SeatServiceInterface;
import com.philsco.challenge4.module.seats.dao.SeatRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SeatServiceImpl implements SeatServiceInterface {
    @Autowired
    public SeatServiceImpl(SeatRepo seatRepo){
        this.seatRepo = seatRepo;
    }

    @Autowired
    private SeatRepo seatRepo;

    @Override
    public void insertSeats() {
        seatRepo.insertSeats();
    }
}
