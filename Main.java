import java.util.*;
import java.util.concurrent.SynchronousQueue;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {

    /**
     * main создаёт объект класса Elevator - лифты
     * Создаёт поток rt, генерирующий вызовы лифта
     * Создаёт поток elt, управляющий работой лифтов
     */
    public static void main(String[] args) {
        Elevator els = new Elevator();  //Создание объекта лифтов

        Thread rt = new Thread(){  //Создание потока для генерации вызовов

            /*
             * Поток работает, пока пользователь не завершит работу программы
             * startFloor - этаж, с которого вызывают лифт
             * endFloor - этаж, на который нужно ехать
             */
            public void run(){
                Random rnd = new Random();
                while(true){
                    int startFloor = rnd.nextInt(15) + 1;
                    int endFloor = rnd.nextInt(15) + 1;
                    while (endFloor == startFloor) {
                        endFloor = rnd.nextInt(15) + 1;
                    }
                    Request request = new Request(startFloor, endFloor);
                    System.out.println("REQUEST: from floor " + startFloor + " to floor " + endFloor);

                    els.addRequest(request);

                    try {
                        Thread.sleep(4000); // Интервал между запросами, равен 4 шагам лифта
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };

        /*
         * Создание потока для управления лифтами
         */
        rt.start();
        Thread elt = new Thread(){
            public void run() {
                while (true) {
                    els.move();
                }
            }
        };
        elt.start();
    }
}
