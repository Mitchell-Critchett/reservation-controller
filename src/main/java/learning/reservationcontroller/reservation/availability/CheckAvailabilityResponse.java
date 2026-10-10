package learning.reservationcontroller.reservation.availability;

public record CheckAvailabilityResponse(
        String message,
        AvailabilityStatus status
) {
}
