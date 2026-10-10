package learning.reservationcontroller.reservation;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface ReservationRepository extends JpaRepository<ReservationEntity, Long> {

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

    @Query("""
        SELECT r.id from ReservationEntity r
                WHERE r.roomId = :roomId
                and :startDate < r.endDate
                and r.startDate < :endDate
                and r.status = :status
        """)
    List<Long> findConflictReservationIds(
            @Param("roomId") Long roomId,
            @Param("startDate") LocalDate startDate,
            @Param("endDate") LocalDate endDate,
            @Param("status") ReservationStatus status
    );

    @Query("""
        SELECT r.id from ReservationEntity r
                WHERE (:roomId is NULL or r.roomId = :roomId)
                and (:userId is NULL or r.userId = :userId)
        """)
    List<ReservationEntity> searchAllByFilter(
            @Param("roomId") Long roomId,
            @Param("userId") Long userId,
            Pageable pageable
    );
}
