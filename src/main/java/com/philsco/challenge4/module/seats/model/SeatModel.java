package com.philsco.challenge4.module.seats.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

@Data
@Entity(name = "seats")
@Getter
@Setter
public class SeatModel {
    @EmbeddedId
    private SeatId seatId;
}
