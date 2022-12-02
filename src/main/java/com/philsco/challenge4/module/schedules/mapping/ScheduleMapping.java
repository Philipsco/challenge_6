package com.philsco.challenge4.module.schedules.mapping;


import com.philsco.challenge4.module.schedules.dto.AddScheduleDTO;
import com.philsco.challenge4.module.schedules.model.ScheduleModel;
import org.springframework.stereotype.Component;

@Component
public class ScheduleMapping {
    public ScheduleModel addSchedule(AddScheduleDTO req){
        ScheduleModel schedule = new ScheduleModel();
        schedule.setFilmCode(req.getFilmCode());
        schedule.setFilmPrice(req.getFilmPrice());
        schedule.setScheduleDate(req.getScheduleDate());
        schedule.setTimeStart(req.getTimeStart());
        schedule.setTimeEnd(req.getTimeEnd());
        return schedule;
    }
}
