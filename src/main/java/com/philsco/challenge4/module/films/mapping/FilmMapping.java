package com.philsco.challenge4.module.films.mapping;

import com.philsco.challenge4.module.films.dto.AddFilmDTO;
import com.philsco.challenge4.module.films.dto.UpdateFilmDTO;
import com.philsco.challenge4.module.films.model.FilmModel;
import org.springframework.stereotype.Component;

@Component
public class FilmMapping {
    public FilmModel addFilm(AddFilmDTO req){
        FilmModel film = new FilmModel();
        film.setFilmCode(req.getFilmCode());
        film.setFilmName(req.getFilmName());
        film.setIsPlaying(req.getIsPlaying());
        return film;
    }

    public FilmModel updateFilm(UpdateFilmDTO req){
        FilmModel film = new FilmModel();
        film.setFilmName(req.getFilmName());
        return film;
    }
}
