package task1709;

/* 
Предложения
Не используя synchronized сделай так, чтобы количество сделанных и принятых предложений было одинаковым.


Requirements:
1. Класс Solution должен содержать нить MakeProposal.
2. Класс Solution должен содержать нить AcceptProposal.
3. Класс Solution должен содержать публичную статическую переменную int proposal.
4. Программа не должна содержать synchronized методов или synchronized блоков.
5. Переменная int proposal не должна находится в локальном кэше.
*/

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

public class Solution {
    public static AtomicInteger proposal = new AtomicInteger(0);

    public static void main(String[] args) {
        new AcceptProposal().start();
        new MakeProposal().start();

    }
    public static class MakeProposal extends Thread {
        @Override
        public void run() {
         while ( proposal.get() < 10){
                int currentProposal = proposal.incrementAndGet();
                System.out.println("Сделано предложение №" + currentProposal);
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static class AcceptProposal extends Thread {
        @Override
        public void run() {
            while (proposal.get() < 10){

                int currentProposal = proposal.get();
                if (currentProposal >0) {
                    System.out.println("Принято предложение №" + currentProposal);
                    proposal.decrementAndGet();
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}

