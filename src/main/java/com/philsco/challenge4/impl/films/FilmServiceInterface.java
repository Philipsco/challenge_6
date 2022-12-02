package com.philsco.challenge4.impl.films;

import com.philsco.challenge4.module.films.model.FilmModel;
import com.philsco.challenge4.module.schedules.model.ScheduleModel;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface FilmServiceInterface {

    void addFilm(FilmModel film);

    void updateNameFilm(String filmCode, FilmModel film);

    void deleteFilm(String filmCode);

    List<FilmModel> showAllFilms();

    List<Object> showScheduleFilm(String filmCode);

    List<ScheduleModel> scheduleFilm(String filmCode);
}
