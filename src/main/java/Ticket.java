import java.util.Objects;

public class Ticket {
  private int parkingLotId;
  private int paringSpaceId;

  public Ticket(int parkingLotId, int parkingSpaceId) {
    this.parkingLotId = parkingLotId;
    this.paringSpaceId = parkingSpaceId;
  }

  public int getParkingLotId() {
    return this.parkingLotId;
  }

  public int getParingSpaceId() {
    return paringSpaceId;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Ticket ticket = (Ticket) o;
    return parkingLotId == ticket.parkingLotId;
  }

  @Override
  public int hashCode() {
    return Objects.hash(parkingLotId);
  }
}
