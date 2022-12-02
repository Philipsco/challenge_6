package com.philsco.challenge4.module.seats.dao;

import com.philsco.challenge4.module.seats.model.SeatModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface SeatRepo extends JpaRepository<SeatModel, Void> {

   @Modifying
   @Query(nativeQuery = true, value = "call public.setupinsertseats()")
   void insertSeats();

}
