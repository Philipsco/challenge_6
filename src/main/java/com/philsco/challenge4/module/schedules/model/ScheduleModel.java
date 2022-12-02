package com.philsco.challenge4.module.schedules.model;

import com.philsco.challenge4.module.films.model.FilmModel;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;

@Data
@Entity(name = "schedules")
public class ScheduleModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "schedule_id")
    private Integer scheduleId;

    @ManyToOne(targetEntity = FilmModel.class)
    @JoinColumn(name = "film_code", referencedColumnName = "film_code")
    private FilmModel filmCode;

    @Column(name = "film_price")
    private Integer filmPrice;

    @Column(name = "schedule_date")
    private LocalDate scheduleDate;

    @Column(name = "time_start")
    private LocalTime timeStart;

    @Column(name = "time_end")
    private LocalTime timeEnd;

    public ScheduleModel(){

    }

    public ScheduleModel(Integer scheduleId){
        this.scheduleId = scheduleId;
    }
}
