package com.philsco.challenge4.module.films.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;

@Data
@Entity(name = "films")
public class FilmModel implements Serializable {

    @Id
    @Column(name = "film_code")
    private String filmCode;

    @Column(name = "film_name")
    private String filmName;

    @Column(name = "is_playing")
    private Boolean isPlaying;

    public FilmModel(){

    }

    public FilmModel(String filmCode){
        this.filmCode = filmCode;
    }

}
