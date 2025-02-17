package task1612;

/* 
Stopwatch (Секундомер)
1. Разберись, что делает программа.
2. Реализуй логику метода doStep так, чтобы учитывалась скорость бегуна.
2.1. Метод getSpeed() в классе Runner показывает, сколько шагов в секунду делает бегун.
Нужно, чтобы бегун действительно делал заданное количество шагов в секунду.
Если Иванов делает 4 шага в секунду, то за 2 секунды он сделает 8 шагов.
Если Петров делает 2 шага в секунду, то за 2 секунды он сделает 4 шага.
2.2. Метод sleep в классе Thread принимает параметр типа long.

ВАЖНО! Используй метод Thread.sleep(), а не Stopwatch.sleep().


Requirements:
1. Метод getSpeed должен возвращать int.
2. Поле speed класса Runner должно иметь тип int.
3. Конструктор класса Runner должен принимать String и int.
4. Метод doStep должен учитывать скорость бегуна. Если скорость бегуна
2 шага в секунду, метод должен работать пол секунды; если скорость бегуна 4 шага в секунду,
метод должен работать четверть секунды.
5. Вывод программы должен отображать сколько шагов сделали Иванов и Петров за 2 секунды.*/
public class Solution {
    public static volatile boolean isStopped = false;

    public static void main(String[] args) throws InterruptedException {
        Runner ivanov = new Runner("Ivanov", 4);
        Runner petrov = new Runner("Petrov", 2);
        ivanov.start();
        petrov.start();
        Thread.sleep(2000);
        isStopped = true;


        System.out.println(ivanov.getName() + " сделал " + ivanov.getStepCount() + " шагов за 2 секунды.");
        System.out.println(petrov.getName() + " сделал " + petrov.getStepCount() + " шагов за 2 секунды.");
    }

    public static class Stopwatch extends Thread {
        private Runner owner;

        public Stopwatch(Runner runner) {
            this.owner = runner;
        }

        public void run() {
            try {
                while (!isStopped) {
                    owner.doStep();
                }
            } catch (InterruptedException e) {
            }
        }
    }

    public static class Runner {
        Stopwatch stopwatch;
        private String name;
        private int speed;
        private int stepCount = 0; // Счетчик шагов

        public Runner(String name, int speed) {
            this.name = name;
            this.speed = speed;
            this.stopwatch = new Stopwatch(this);
        }

        public String getName() {
            return name;
        }

        public int getSpeed() {
            return speed;
        }

        public int getStepCount() {
            return stepCount;
        }

        public void doStep() throws InterruptedException {
            stepCount++;
            System.out.println(name + " делает шаг №" + stepCount + "!");
            int sleepTime = 1000 / speed;
            Thread.sleep(sleepTime);
        }

        public void start() {
            stopwatch.start();
        }
    }
}
