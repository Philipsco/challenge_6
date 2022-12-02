package com.philsco.challenge4.module.schedules.controller;

import com.philsco.challenge4.module.films.controller.FilmController;
import com.philsco.challenge4.module.schedules.dto.AddScheduleDTO;
import com.philsco.challenge4.module.schedules.mapping.ScheduleMapping;
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

@RestController
@RequestMapping("/api/v1/cinema/schedules")
public class ScheduleController {
    private HashMap<String, Object> data = new HashMap<String, Object>();

    @Autowired
    private ScheduleServiceImpl scheduleServiceImpl;

    @Autowired
    private ScheduleMapping scheduleMapping;

    private static final Logger LOG = LoggerFactory.getLogger(FilmController.class);

    // add schedule film
    @Operation(summary = "Add a schedule film, add 'filmCode', 'scheduleDate', 'timeStart', 'timeEnd', and 'filmPrice' and program will assign the new scheduleId")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Schedule Film Added!",
                    content = {@Content(schema = @Schema(example = "Schedule Film Added!"))})
    })
    @PostMapping("/admin")
    public ResponseEntity<HashMap<String, Object>> addScheduleFilm(@RequestBody AddScheduleDTO addScheduleDTO){
        scheduleServiceImpl.addSchedule(scheduleMapping.addSchedule(addScheduleDTO));
        data.put("data",addScheduleDTO);
        LOG.info("schedule added:");
        return ResponseEntity.ok(data);
    }

    // delete schedule film
    @Operation(summary = "Delete a schedule film")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "202", description = "Film deleted!",
                    content = {@Content(schema = @Schema(example = "Film deleted!"))})
    })
    @DeleteMapping("/admin//{film_code}")
    public ResponseEntity<HttpStatus> deleteScheduleFilm(@PathVariable("film_code") String filmCode){
        scheduleServiceImpl.deleteSchedule(filmCode);
        LOG.info("Schedule deleted");
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }
}
