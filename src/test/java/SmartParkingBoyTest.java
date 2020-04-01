import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;
import static org.mockito.quality.Strictness.LENIENT;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = LENIENT)
class SmartParkingBoyTest {

  @Mock
  private ParkingLot parkingLot1;

  @Mock
  private ParkingLot parkingLot2;

  @InjectMocks
  SmartParkingBoy smartParkingBoy;

  @BeforeEach
  void setUp() {
    when(parkingLot1.isFull()).thenReturn(false);
    when(parkingLot2.isFull()).thenReturn(false);
    when(parkingLot1.getId()).thenReturn(1);
    when(parkingLot2.getId()).thenReturn(2);
    when(parkingLot1.getAvailableSize()).thenReturn(8);
    when(parkingLot2.getAvailableSize()).thenReturn(10);

    smartParkingBoy = new SmartParkingBoy(new ArrayList<>(Arrays.asList(parkingLot1, parkingLot2)));
  }

  @Test
  void should_return_ticket_and_verify_parking_car_to_parking_lot_has_more_space_when_park_to_parking_boy_given_a_car_and_parking_lots_has_different_space() throws SQLException {
    smartParkingBoy = new SmartParkingBoy(new ArrayList<>(Arrays.asList(parkingLot1, parkingLot2)));
    Car car = new Car(1);
    when(parkingLot2.park(car)).thenReturn(new Ticket(2, 1));
    Ticket ticket = smartParkingBoy.park(car);

    assertEquals(2, ticket.getParkingLotId());

  }

  @Test
  void should_return_ticket_and_verify_car_park_into_first_parking_lot_when_park_to_parking_boy_given_a_car_and_same_parking_space() throws SQLException {
    when(parkingLot2.getAvailableSize()).thenReturn(10);
    Car car = new Car(1);

    when(parkingLot1.park(car)).thenReturn(new Ticket(1, 1));

    Ticket ticket = smartParkingBoy.park(car);

    assertEquals(ticket.getParkingLotId(), 1);
  }

  @Test
  void should_client_get_his_car_when_call_fetch_method_given_valid_ticket() {
    Car parkingCar =  new Car(1);
    Ticket ticket = new Ticket(1, 1);
    when(parkingLot1.fetchCar(ticket)).thenReturn(parkingCar);

    Car fetchCar = smartParkingBoy.fetch(ticket);

    assertEquals(parkingCar, fetchCar);
  }

  @Test
  void should_client_can_not_get_his_car_when_call_fetch_method_given_invalid_ticket() {

    Ticket invalidTicket = new Ticket(3, 1);

    assertThrows(InvalidTicketException.class, () -> smartParkingBoy.fetch(invalidTicket));

  }
}