
/**
 * Класс Request реализует запрос
 * startFloor - этаж, с которого приходит запрос
 * endFloor - этаж, на который едет пассажир
 * direction - направление: 1 - лифт едет вверх, 0 - стоп, -1 - едет вниз
 */
public class Request {
    private int startFloor;
    private int endFloor;
    private int direction;

    public Request(int startFloor, int endFloor) {
        this.startFloor = startFloor;
        this.endFloor = endFloor;
        this.direction = startFloor < endFloor ? 1 : -1;
    }

    public int getStartFloor() {
        return startFloor;
    }

    public int getEndFloor() {
        return endFloor;
    }

    public int getDirection() {
        return direction;
    }
}
