package com.philsco.challenge4;

import com.philsco.challenge4.module.films.controller.FilmController;
import com.philsco.challenge4.module.films.dto.AddFilmDTO;
import com.philsco.challenge4.module.films.dto.UpdateFilmDTO;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class FilmControllerTest {
    @Autowired
    private FilmController filmController;

    // Tambah film
    @Test
    @DisplayName("1. Menambah Film Baru")
    void addFilm() {
        AddFilmDTO newFilm = new AddFilmDTO("filmKodeBaruNich1", "filmBaruNich1", true);
        filmController.addFilm(newFilm);
    }

    // Update info film
    @Test
    @DisplayName("2. Update Data Film")
    void updateFilm() {
        UpdateFilmDTO updatedFilm = new UpdateFilmDTO("filmKodeBaruNich1", "asdasd");
        filmController.updateNameFilm(updatedFilm.getFilmCode(), updatedFilm);
    }

    // Menampilkan show yang sedang tayang
    @Test
    @DisplayName("3. Menampilkan Film-film yang sedang tayang")
    void showSedangTayang() {
        filmController.getListFilms();
    }

    // Menampilkan schedule suatu film
    @Test
    @DisplayName("4. Menampilkan Jadwal suatu film (by filmcode)")
    void showFilmSchedule() {
        filmController.showScheduleFilm("filmKodeBaruNich1");
    }

    // Hapus film (plus schedule)
    @Test
    @DisplayName("5. Menghapus film +schedulefilm)")
    void deleteFilm() {
        filmController.deleteFilm("filmKodeBaruNich1");
    }

}
