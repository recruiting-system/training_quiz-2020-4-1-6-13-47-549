import sun.reflect.generics.reflectiveObjects.NotImplementedException;


public class ParkingLot {

  private int id;

  public ParkingLot() {
  }

  public ParkingLot(int id, int size, ParkingRepository parkingRepository) {
    throw new NotImplementedException();
  }

  public int getId() {
    return id;
  }

  public int getAvailableSize() {
    throw new NotImplementedException();
  }

  public Boolean isFull() {
    throw new NotImplementedException();
  }

  public Ticket park(Car car) {
    throw new NotImplementedException();
  }

  public Car fetchCar(Ticket ticket) {
    throw new NotImplementedException();
  }
}
