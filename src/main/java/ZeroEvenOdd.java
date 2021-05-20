import java.util.stream.IntStream;

public class ZeroEvenOdd extends Thread {
    int n;
    public static int i = 1;
    boolean block = true;
    private final Object evenOddMotion = new Object();


    public ZeroEvenOdd(int n) {
        this.n = n;

    }

    public void zero() throws InterruptedException {
        synchronized (evenOddMotion) {
            if (n == 1) {
                while (!block) {
                    evenOddMotion.wait();
                }
                System.out.println("0");
                block = false;
                evenOddMotion.notifyAll();
            } else {
                while (i < n) {
                    while (!block) {
                        evenOddMotion.wait();
                    }
                    System.out.println("0");
                    block = false;

                    evenOddMotion.notifyAll();
                }
            }
        }
    }

    public void odd() throws InterruptedException {
        synchronized (evenOddMotion) {
            if (n == 1) {

                System.out.println(i);
                block = true;
                evenOddMotion.notify();
            } else {
                while (i < n) {
                    while (block) {
                        evenOddMotion.wait();
                    }
                    while (i % 2 == 0) {
                        evenOddMotion.wait();
                    }
                    System.out.println(i);
                    block = true;
                    i++;
                    evenOddMotion.notify();
                }
            }

        }
    }


    public void even() throws InterruptedException {
        synchronized (evenOddMotion) {
            while (i < n) {
                while (block) {
                    evenOddMotion.wait();
                }
                while (i % 2 == 1) {
                    evenOddMotion.wait();
                }
                System.out.println(i);
                block = true;
                i++;
                evenOddMotion.notify();
            }

        }
    }
}