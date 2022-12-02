package com.philsco.challenge4.module.films.controller;

import com.philsco.challenge4.module.films.dto.AddFilmDTO;
import com.philsco.challenge4.module.films.dto.UpdateFilmDTO;
import com.philsco.challenge4.module.films.mapping.FilmMapping;
import com.philsco.challenge4.module.films.model.FilmModel;
import com.philsco.challenge4.module.films.service.FilmServiceImpl;
import com.philsco.challenge4.module.schedules.service.ScheduleServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/api/v1/cinema/films")
public class FilmController {
    private HashMap<String, Object> data = new HashMap<String, Object>();

    private HashMap<String, List<Object>> showSchedule = new HashMap<String, List<Object>>();

    @Autowired
    private FilmServiceImpl filmServiceImpl;

    @Autowired
    private FilmMapping filmMapping;

    @Autowired
    private ScheduleServiceImpl scheduleServiceImpl;

    private static final Logger LOG = LoggerFactory.getLogger(FilmController.class);

    @Operation(summary = "get all film with isPlaying=true")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Film shown!",
                    content = {@Content(schema = @Schema(example = "Film shown!"))})
    })
    @GetMapping("/public")
    public ResponseEntity<HashMap<String, Object>> getListFilms() {
        List<FilmModel> listFilms = filmServiceImpl.showAllFilms();
        data.put("data",listFilms);
        LOG.info(listFilms.toString());
        return ResponseEntity.ok(data);
    }

    @Operation(summary = "Add a film, add 'filmCode', 'filmName', and 'isPlaying' and program will assign the new filmCode")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Film Added!",
                    content = {@Content(schema = @Schema(example = "Film Added!"))})
    })
    @PostMapping("/admin/")
    public ResponseEntity<HashMap<String, Object>> addFilm(@RequestBody AddFilmDTO newFilm) {
        filmServiceImpl.addFilm(filmMapping.addFilm(newFilm));
        LOG.info("film added: {}", newFilm.getFilmName());
        data.put("data", newFilm);
        LOG.info(newFilm.toString());
        return ResponseEntity.ok(data);
    }

    @Operation(summary = "Change a film data, update 'filmName' related to their filmCode")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Film updated!",
                    content = {@Content(schema = @Schema(example = "Film updated!"))})
    })
    @PutMapping("/admin//{film_code}/update")
    public ResponseEntity<HashMap<String, Object>> updateNameFilm(@PathVariable("film_code") String filmCode, @RequestBody UpdateFilmDTO updateFilm){
        filmServiceImpl.updateNameFilm(filmCode, filmMapping.updateFilm(updateFilm));
        LOG.info("film updated");
        data.put("data",updateFilm);
        LOG.info(updateFilm.toString());
        return ResponseEntity.ok(data);
    }

    @Operation(summary = "Delete a film and their related schedule entity")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "202", description = "Film deleted!",
                    content = {@Content(schema = @Schema(example = "Film deleted!"))})
    })
    @DeleteMapping("/admin//{film_code}")
    public ResponseEntity<HttpStatus> deleteFilm (@PathVariable("film_code") String filmCode){
        scheduleServiceImpl.deleteSchedule(filmCode);
        filmServiceImpl.deleteFilm(filmCode);
        LOG.info("film deleted");
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @Operation(summary = "get a film schedule by filmCode, require known filmCode")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Schedule Film shown!",
                    content = {@Content(schema = @Schema(example = "Schedule Film shown!"))})
    })
    @GetMapping("/public/{film_code}/show-schedules")
    public ResponseEntity<HashMap<String, List<Object>>> showScheduleFilm(@PathVariable("film_code") String filmCode){
        List<Object> listSchd = filmServiceImpl.showScheduleFilm(filmCode);
        LOG.info("List Film with schedule");
        LOG.info(listSchd.toString());
        showSchedule.put("data",listSchd);
        return ResponseEntity.ok(showSchedule);
    }

}
