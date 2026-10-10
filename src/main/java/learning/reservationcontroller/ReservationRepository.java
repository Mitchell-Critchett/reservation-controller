package learning.reservationcontroller;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ReservationRepository extends JpaRepository<ReservationEntity, Long> {

//    List<ReservationEntity> findAllByStatusIs(ReservationStatus status);


    @Modifying
    @Query("""
        update ReservationEntity r
        set r.status = :status
        where r.id = :id
        """)
    void setStatus(
            @Param("id") Long id,
            @Param("status") ReservationStatus reservationStatus
    );



}
