package com.philsco.challenge4.module.films.dao;

import com.philsco.challenge4.module.films.model.FilmModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Repository
@Transactional
public interface FilmRepo extends JpaRepository<FilmModel, String> {

    // Add Film
    @Modifying
    @Query(nativeQuery = true, value = "insert into films(film_code, film_name, is_playing) values(:film_code, :film_name, :isPlaying)")
    void addFilm(
            @Param("film_code") String filmCode,
            @Param("film_name") String filmName,
            @Param("isPlaying") Boolean isPlaying
    );

    @Query(nativeQuery = true, value = "SELECT * FROM films WHERE is_playing=true")
    List<FilmModel> getListShownFilms();

    // update nama film
    @Modifying
    @Query(nativeQuery = true, value = "update films set film_name= :film_name where film_code= :film_code")
    void updateNameFilm(@Param("film_code") String filmCode, @Param("film_name") String filmName);

    // delete film
    @Modifying
    @Query(nativeQuery = true, value = "delete from films where film_code= :film_code")
    void deleteFilm(@Param("film_code") String filmCode);

    // cek film schedule
    @Modifying
    @Query(nativeQuery = true, value = "select f.film_code as filmCode, f.film_name as filmName, f.is_playing as sedangTayang, s.schedule_date as tanggalTayang, s.time_start as jamMulai, s.time_end as jamSelesai, s.film_price as hargaTiket " +
            "from films f join schedules s " +
            "on f.film_code = s.film_code " +
            "where f.film_code = :film_code")
    List<Object> showFilmSchedule(@Param("film_code") String filmCode);
}
