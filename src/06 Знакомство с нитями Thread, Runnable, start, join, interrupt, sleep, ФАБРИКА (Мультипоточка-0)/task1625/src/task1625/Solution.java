package task1625;

/* 
Взаимная блокировка
1. Разберись, как работает программа.
2. Не меняя классы T1 и T2 сделай так, чтобы их нити завершились, не обязательно успешно.
3. Метод sleep не использовать.


Requirements:
1. Метод main должен запускать нить t1.
2. Метод main должен запускать нить t2.
3. Класс T1 не изменять.
4. Класс T2 не изменять.
5. Метод sleep не использовать.
6. Вывод программы должен состоять из 2х строк,
 информирующих о завершении нитей. Например: "T1 was interrupted" и "T2 finished".
7. Нити t1 и t2 должны завершаться (не обязательно успешно).*/

public class Solution {
    static Thread t1 = new T1();
    static Thread t2 = new T2();

    public static void main(String[] args) throws InterruptedException {
        t1.start();
        t2.start();

        t1.interrupt();
        t2.interrupt();

        t1.join();
        t2.join();

    }

    public static class T1 extends Thread {
        @Override
        public void run() {
            try {
                t2.join();
               System.out.println("T1 finished");
            } catch (InterruptedException e) {
                System.out.println("T1 was interrupted");
            }
        }
    }

    public static class T2 extends Thread {
        @Override
        public void run() {
            try {
                t1.join();
                System.out.println("T2 finished");
            } catch (InterruptedException e) {

            }
            System.out.println("T2 was interrupted");

        }

    }}

