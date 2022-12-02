package com.philsco.challenge4.impl.schedules;

import com.philsco.challenge4.module.schedules.model.ScheduleModel;
import org.springframework.stereotype.Service;

@Service
public interface ScheduleServiceInterface {
    void addSchedule(ScheduleModel scheduleModel);

    void deleteSchedule(String filmCode);
}
