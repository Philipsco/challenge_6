package com.philsco.challenge4.module.films.service;

import com.philsco.challenge4.impl.films.FilmServiceInterface;
import com.philsco.challenge4.module.films.dao.FilmRepo;
import com.philsco.challenge4.module.films.model.FilmModel;
import com.philsco.challenge4.module.schedules.dao.ScheduleRepo;
import com.philsco.challenge4.module.schedules.model.ScheduleModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FilmServiceImpl implements FilmServiceInterface {
    @Autowired
    public FilmServiceImpl(FilmRepo filmRepo){
       this.filmRepo = filmRepo;
    }

    @Autowired
    private final FilmRepo filmRepo;

    @Autowired
    private ScheduleRepo scheduleRepo;

    @Override
    public void addFilm(FilmModel film) {
        String filmCode = film.getFilmCode();
        String filmName = film.getFilmName();
        Boolean isPlaying = film.getIsPlaying();
        filmRepo.addFilm(filmCode,filmName,isPlaying);
    }

    @Override
    public void updateNameFilm(String filmCode, FilmModel film) {
        String filmName = film.getFilmName();
        filmRepo.updateNameFilm(filmCode,filmName);
    }

    @Override
    public void deleteFilm(String filmCode) {
        filmRepo.deleteFilm(filmCode);
    }

    @Override
    public List<FilmModel> showAllFilms() {
        return filmRepo.getListShownFilms();
    }

    @Override
    public List<Object> showScheduleFilm(String filmCode) {
        return filmRepo.showFilmSchedule(filmCode);
    }

    @Override
    public List<ScheduleModel> scheduleFilm(String filmCode) {
        return scheduleRepo.scheduleEntity(filmCode);
    }
}
