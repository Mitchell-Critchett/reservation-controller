package learning.reservationcontroller.reservation.availability;

import org.springframework.boot.availability.AvailabilityState;

public record CheckAvailabilityResponse(
        String message,
        AvailabilityStatus status
) {
}
