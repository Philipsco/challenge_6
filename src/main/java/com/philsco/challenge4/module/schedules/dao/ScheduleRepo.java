package com.philsco.challenge4.module.schedules.dao;

import com.philsco.challenge4.module.films.model.FilmModel;
import com.philsco.challenge4.module.schedules.model.ScheduleModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Repository
@Transactional
public interface ScheduleRepo extends JpaRepository<ScheduleModel, Integer> {
    @Modifying
    @Query(nativeQuery = true, value = "insert into schedules(film_code, film_price, schedule_date, time_start, time_end) values(:film_code, :film_price, :schedule_date, :time_start, :time_end)")
    void addSchedule(
            @Param("film_code") FilmModel filmCode,
            @Param("film_price") Integer priceFilm,
            @Param("schedule_date") LocalDate scheduleDate,
            @Param("time_start") LocalTime timeStart,
            @Param("time_end") LocalTime timeEnd);

    @Modifying
    @Query(nativeQuery = true, value = "delete from schedules where film_code = :film_code")
    void deleteSchedule(@Param("film_code") String filmCode);

    @Modifying
    @Query(nativeQuery = true, value = "select * from schedules where film_code = :film_code")
    List<ScheduleModel> scheduleEntity(
            @Param("film_code") String filmCode
    );

}
