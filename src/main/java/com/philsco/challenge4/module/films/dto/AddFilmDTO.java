package com.philsco.challenge4.module.films.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class AddFilmDTO {
    private String filmCode;
    private String filmName;
    private Boolean isPlaying;
}
