package com.philsco.challenge4.module.schedules.dto;

import com.philsco.challenge4.module.films.model.FilmModel;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@AllArgsConstructor
public class AddScheduleDTO {
    private FilmModel filmCode;
    private Integer filmPrice;
    private LocalDate scheduleDate;
    private LocalTime timeStart;
    private LocalTime timeEnd;
}
