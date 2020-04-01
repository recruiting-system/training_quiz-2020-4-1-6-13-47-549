import java.sql.SQLException;
import java.util.Objects;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public class ParkingSpace {

  private int number;
  private int parkingLotId;
  private Car car;
  private Boolean isUsed;
  private ParkingRepository parkingRepository;
  public ParkingSpace(int parkingLotId, int number, ParkingRepository parkingRepository) {
    throw new NotImplementedException();
  }


  public void setUsed(Boolean used) {
    isUsed = used;
  }

  public int getNumber() {
    return number;
  }

  public void put(Car car) {
    throw new NotImplementedException();
  }

  public Car takeCar() {
    throw new NotImplementedException();
  }

  public Car getCar() {
    return car;
  }

}
