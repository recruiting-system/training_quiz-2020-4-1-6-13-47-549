import java.sql.SQLException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;
import static org.mockito.quality.Strictness.LENIENT;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = LENIENT)
class ParkingLotTest {

  @Mock
  ParkingRepository parkingRepository;

  @InjectMocks
  ParkingLot parkingLot;

  @BeforeEach
  void setUp() {
    parkingLot = new ParkingLot(1, 1, parkingRepository);
  }

  @Test
  void should_client_get_a_ticket_no_when_call_park_method_given_parking_lot_is_not_full_and_car() throws SQLException {
    when(parkingRepository.queryIsUsed(1, 1)).thenReturn(false);
    Car car = new Car(1);
    Ticket ticket = parkingLot.park(car);
    assertNotNull(ticket);
    assertEquals(1, ticket.getParkingLotId());
  }

  @Test
  void should_client_get_nothing_when_call_park_method_given_parking_lot_is_full_and_car() throws SQLException {
    when(parkingRepository.queryIsUsed(1, 1)).thenReturn(true);
    Car car = new Car(1);

    assertThrows(ParkingLotFullException.class, () -> parkingLot.park(car));
  }

  @Test
  void should_client_get_his_car_when_call_fetch_method_given_valid_ticket() {
    Car parkingCar =  new Car(1);
    Ticket ticket = parkingLot.park(parkingCar);

    Car fetchCar = parkingLot.fetchCar(ticket);

    assertEquals(parkingCar, fetchCar);
  }

  @Test
  void should_client_can_not_get_his_car_when_call_fetch_method_given_invalid_ticket() {
    Ticket invalidTicket = new Ticket(2, 1);

    assertThrows(InvalidTicketException.class, () -> parkingLot.fetchCar(invalidTicket));
  }

  @Test
  void should_client_can_not_get_his_car_when_call_fetch_method_given_valid_ticket_is_used() {
    Car parkingCar =  new Car(1);
    Ticket ticket = parkingLot.park(parkingCar);

    parkingLot.fetchCar(ticket);

    assertThrows(InvalidTicketException.class, () -> parkingLot.fetchCar(ticket));
  }

  @Test
  void should_client_get_ticket_when_park_given_that_full_parking_not_is_fetched_a_car() {
    Car existCar = new Car(111);
    Ticket existedTicket = parkingLot.park(existCar);
    parkingLot.fetchCar(existedTicket);
    Car car = new Car(1);

    Ticket ticket = parkingLot.park(car);

    assertNotNull(ticket);
  }


}
