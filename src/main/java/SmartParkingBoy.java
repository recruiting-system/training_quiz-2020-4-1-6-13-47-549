import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public class SmartParkingBoy {

  List<ParkingLot> parkingLots;

  public SmartParkingBoy(List<ParkingLot> parkingLots) {
    this.parkingLots = parkingLots;
  }

  public Car fetch(Ticket ticket) {
   throw new NotImplementedException();
  }

  public Ticket park(Car car) {
    throw new NotImplementedException();
  }

}
