package com.philsco.challenge4.module.seats.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@Getter
@Setter
public class SeatId implements Serializable {
    private String studioName;
    private Integer seatNumber;
}
