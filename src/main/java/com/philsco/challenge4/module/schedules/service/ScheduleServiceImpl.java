package com.philsco.challenge4.module.schedules.service;

import com.philsco.challenge4.impl.schedules.ScheduleServiceInterface;
import com.philsco.challenge4.module.films.dao.FilmRepo;
import com.philsco.challenge4.module.films.model.FilmModel;
import com.philsco.challenge4.module.schedules.dao.ScheduleRepo;
import com.philsco.challenge4.module.schedules.model.ScheduleModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;

@Service
public class ScheduleServiceImpl implements ScheduleServiceInterface {
    @Autowired
    public ScheduleServiceImpl(ScheduleRepo scheduleRepo){
        this.scheduleRepo = scheduleRepo;
    }

    @Autowired
    private final ScheduleRepo scheduleRepo;


    @Override
    public void addSchedule(ScheduleModel scheduleModel) {
        FilmModel filmCode = scheduleModel.getFilmCode();
        Integer filmPrice = scheduleModel.getFilmPrice();
        LocalDate scheduleDate = scheduleModel.getScheduleDate();
        LocalTime timeStart = scheduleModel.getTimeStart();
        LocalTime timeEnd = scheduleModel.getTimeEnd();
        scheduleRepo.addSchedule(filmCode,filmPrice,scheduleDate,timeStart,timeEnd);
    }

    @Override
    public void deleteSchedule(String filmCode) {
        scheduleRepo.deleteSchedule(filmCode);
    }
}
