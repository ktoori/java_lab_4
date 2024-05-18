import java.util.*;

/**
 * Класс Elevator представляет собой класс для инициализации лифтов
 * и реализации методов по работе с ними
 * addRequest - метод для выбора лифта из двух для выполнения запроса
 * move - метод для реализации движения двух лифтов
 */
public class Elevator {
    int cur_floor_1;  //Текущее положение первого лифта
    int direction_1; //Направление движения первого лифта: -1 - вниз, 0 - стоп, 1 - вверх

    /*
     * floors_1 - Список запросов для первого лифта
     * Если endFloor равен 0, значит startFloor - конечная точка запроса
     * Иначе endFloor является конечной точкой первоначального запроса
     */
    ArrayList<Request> floors_1 = new ArrayList<>();
    int cur_floor_2;  //Текущее положение второго лифта
    int direction_2; //Направление движения второго лифта
    ArrayList<Request> floors_2 = new ArrayList<>(); //Список запросов для второго лифта


    Elevator() {
        cur_floor_1 = 1;
        direction_1 = 0;
        cur_floor_2 = 1;
        direction_2 = 0;
    }


    /**
     * Метод addRequest выбирает лифт, который примет запрос
     * Выбирает ближайший лифт с попутным направлением
     * Или ближайший лифт без направления (пустой)
     * @param req - входящий запрос
     */
    public synchronized void addRequest(Request req) {
        if ((direction_1 == 0) && (direction_2 == 0)) {
            if (Math.abs(req.getStartFloor() - cur_floor_1) <= Math.abs(req.getStartFloor() - cur_floor_2)) {
                floors_1.add(req);
                floors_1.sort(Comparator.comparingInt(Request::getStartFloor));
            } else {
                floors_2.add(req);
                floors_2.sort(Comparator.comparingInt(Request::getStartFloor));
            }
        } else if ((req.getDirection() == direction_1) && (req.getDirection() == direction_2)) {
            if (req.getDirection() == -1) {
                if ((req.getStartFloor() < cur_floor_1) && (req.getStartFloor() < cur_floor_2)) {
                    if (Math.abs(req.getStartFloor() - cur_floor_1) <= Math.abs(req.getStartFloor() - cur_floor_2)) {
                        floors_1.add(req);
                        floors_1.sort(Comparator.comparingInt(Request::getStartFloor));
                    } else {
                        floors_2.add(req);
                        floors_2.sort(Comparator.comparingInt(Request::getStartFloor));
                    }
                } else if (req.getStartFloor() <= cur_floor_1) {
                    floors_1.add(req);
                    floors_1.sort(Comparator.comparingInt(Request::getStartFloor));
                } else if (req.getStartFloor() <= cur_floor_2) {
                    floors_2.add(req);
                    floors_2.sort(Comparator.comparingInt(Request::getStartFloor));
                } else {
                    if (Math.abs(req.getStartFloor() - cur_floor_1) <= Math.abs(req.getStartFloor() - cur_floor_2)) {
                        floors_1.add(req);
                        floors_1.sort(Comparator.comparingInt(Request::getStartFloor));
                    } else {
                        floors_2.add(req);
                        floors_2.sort(Comparator.comparingInt(Request::getStartFloor));
                    }
                }
            } else if (req.getDirection() == 1) {
                if ((req.getStartFloor() >= cur_floor_1) && (req.getStartFloor() >= cur_floor_2)) {
                    if (Math.abs(req.getStartFloor() - cur_floor_1) < Math.abs(req.getStartFloor() - cur_floor_2)) {
                        floors_1.add(req);
                        floors_1.sort(Comparator.comparingInt(Request::getStartFloor));
                    } else {
                        floors_2.add(req);
                        floors_2.sort(Comparator.comparingInt(Request::getStartFloor));
                    }
                } else if (req.getStartFloor() >= cur_floor_1) {
                    floors_1.add(req);
                    floors_1.sort(Comparator.comparingInt(Request::getStartFloor));
                } else if (req.getStartFloor() >= cur_floor_2){
                    floors_2.add(req);
                    floors_2.sort(Comparator.comparingInt(Request::getStartFloor));
                } else {
                    if (Math.abs(req.getStartFloor() - cur_floor_1) <= Math.abs(req.getStartFloor() - cur_floor_2)) {
                        floors_1.add(req);
                        floors_1.sort(Comparator.comparingInt(Request::getStartFloor));
                    } else {
                        floors_2.add(req);
                        floors_2.sort(Comparator.comparingInt(Request::getStartFloor));
                    }
                }
            }
        } else if (req.getDirection() == direction_1) {
            if (direction_1 == -1){
                if (req.getStartFloor() <= cur_floor_1){
                    floors_1.add(req);
                    floors_1.sort(Comparator.comparingInt(Request::getStartFloor));
                } else {
                    floors_2.add(req);
                    floors_2.sort(Comparator.comparingInt(Request::getStartFloor));
                }
            } else if (direction_1 == 1){
                if (req.getStartFloor() >= cur_floor_1){
                    floors_1.add(req);
                    floors_1.sort(Comparator.comparingInt(Request::getStartFloor));
                } else {
                    floors_2.add(req);
                    floors_2.sort(Comparator.comparingInt(Request::getStartFloor));
                }
            }

        } else if (req.getDirection() == direction_2) {
            if (direction_2 == -1){
                if (req.getStartFloor() <= cur_floor_2){
                    floors_2.add(req);
                    floors_2.sort(Comparator.comparingInt(Request::getStartFloor));
                } else {
                    floors_1.add(req);
                    floors_1.sort(Comparator.comparingInt(Request::getStartFloor));
                }
            } else if (direction_2 == 1){
                if (req.getStartFloor() >= cur_floor_2){
                    floors_2.add(req);
                    floors_2.sort(Comparator.comparingInt(Request::getStartFloor));
                } else {
                    floors_1.add(req);
                    floors_1.sort(Comparator.comparingInt(Request::getStartFloor));
                }
            }
        } else if ((direction_1 == 0)&&(direction_2 != 0)) {
            floors_1.add(req);
            floors_1.sort(Comparator.comparingInt(Request::getStartFloor));
        } else if ((direction_2 == 0)&&(direction_1 != 0)) {
            floors_2.add(req);
            floors_2.sort(Comparator.comparingInt(Request::getStartFloor));
        } else {
            if (Math.abs(req.getStartFloor() - cur_floor_1) <= Math.abs(req.getStartFloor() - cur_floor_2)) {
                floors_1.add(req);
                floors_1.sort(Comparator.comparingInt(Request::getStartFloor));
            } else {
                floors_2.add(req);
                floors_2.sort(Comparator.comparingInt(Request::getStartFloor));
            }
        }
    }


    /**
     * move - метод, реализующий движение лифтов
     * req_1 - запрос, который на данный момент обрабатывает лифт 1
     * лифт может поменять req_1, чтобы более эффективно забрать и выгрузить пассажиров
     * floor_1 - этаж, на который едет лифт 1
     * аналогично req_2 и floor_2 для лифта 2
     */
    public void move() {
        if (!floors_1.isEmpty()) {
            Request req_1 = floors_1.getFirst();

            /*
             * Поиск ближайшего этажа из очереди
             */
            if (Math.abs(floors_1.getFirst().getStartFloor() - cur_floor_1) <= Math.abs(floors_1.getLast().getStartFloor() - cur_floor_1)){
                req_1 = floors_1.getFirst();
            } else req_1 = floors_1.getLast();

            int floor1 = req_1.getStartFloor();

            /*
             * Далее определяется направление движения лифта в зависимости от ближайшего пункта назначения
             * 1 - движение вверх
             * -1 - движение вниз
             * MOVEMENT: Лифт 1 находится на _ этаже, движется вверх/вниз на этаж _
             * ARRIVAL: Лифт 1 забрал/высадил пассажиров на этаже _
             */
            if (cur_floor_1 < floor1){
                direction_1 = 1;
                System.out.println("MOVEMENT: Elevator 1 on " + cur_floor_1 + " floor MOVES UP to floor " + floor1);
                cur_floor_1++;
            } else if (cur_floor_1 > floor1){
                direction_1 = -1;
                System.out.println("MOVEMENT: Elevator 1 on " + cur_floor_1 + " floor MOVES DOWN to floor " + floor1);
                cur_floor_1--;
            } else {
                floors_1.remove(req_1);

                if (req_1.getEndFloor() != 0) {
                    floors_1.add(new Request(req_1.getEndFloor(), 0));
                    floors_1.sort(Comparator.comparingInt(Request::getStartFloor));
                    System.out.println("ARRIVAL: Elevator 1 PICKED UP passengers at floor " + floor1);
                } else System.out.println("ARRIVAL: Elevator 1 has DELIVERED passengers at floor " + floor1);

                direction_1 = 0;
            }
        }

        if (!floors_2.isEmpty()) {
            Request req_2 = floors_2.getFirst();

            /*
             * Поиск ближайшего этажа из очереди
             */
            if (Math.abs(floors_2.getFirst().getStartFloor() - cur_floor_2) <= Math.abs(floors_2.getLast().getStartFloor() - cur_floor_2)){
                req_2 = floors_2.getFirst();
            } else req_2 = floors_2.getLast();

            int floor2 = req_2.getStartFloor();

            /*
             * Далее определяется направление движения лифта в зависимости от ближайшего пункта назначения
             * 1 - движение вверх
             * -1 - движение вниз
             * MOVEMENT: Лифт 1 находится на _ этаже, движется вверх/вниз на этаж _
             * ARRIVAL: Лифт 1 забрал/высадил пассажиров на этаже _
             */
            if (cur_floor_2 < floor2){
                direction_2 = 1;
                System.out.println("MOVEMENT: Elevator 2 on " + cur_floor_2 + " MOVES UP to floor " + floor2);
                cur_floor_2++;
            } else if (cur_floor_2 > floor2){
                direction_2 = -1;
                System.out.println("MOVEMENT: Elevator 2 on " + cur_floor_2 + " MOVES DOWN to floor " + floor2);
                cur_floor_2--;
            } else {
                floors_2.remove(req_2);

                if (req_2.getEndFloor() != 0)
                {
                    floors_2.add(new Request(req_2.getEndFloor(), 0));
                    floors_2.sort(Comparator.comparingInt(Request::getStartFloor));
                    System.out.println("ARRIVAL: Elevator 2 has PICKED UP passengers at floor " + floor2);
                } else System.out.println("ARRIVAL: Elevator 2 has DELIVERED passengers at floor " + floor2);

                direction_2 = 0;
            }
        }

        try {
            Thread.sleep(1000);  //Время, за которое лифт проезжает один этаж (1 шаг)
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

}
