/*
* File: Main.java
* Author: John Kucera
* Date: January 29, 2020
* Purpose: This Java program is meant to be run alongside MyThread.java and is designed to
* create 3 threads who display their thread id in turn for 5 iterations. This Main class
* creates the threads and 3 semaphores which the threads will wait for and release
* to each other so the info printing can be synchronized.
*/

// import necessary java classes
import java.util.concurrent.Semaphore;

// Main class with main method
public class Main {
  public static void main(String[] args) {
    try {
      // Create semaphores, acquire their permits
      final Semaphore sem1 = new Semaphore(1);
      sem1.acquire();
      final Semaphore sem2 = new Semaphore(1);
      sem2.acquire();
      final Semaphore sem3 = new Semaphore(1);
      sem3.acquire();

      // Create threads, assign semaphores to their "before" and "after", start them
      final Thread thread1 = new Thread(new MyThread(sem3, sem1));
      thread1.start();
      final Thread thread2 = new Thread(new MyThread(sem1, sem2));
      thread2.start();
      final Thread thread3 = new Thread(new MyThread(sem2, sem3));
      thread3.start();

      // Release semaphore 3 so that thread 1 can begin iteration
      sem3.release();
    } // end of try
    catch (InterruptedException ex) {
      System.out.println("InterruptedException: main method");
    } // end of catch
  } // end of main method
} // end of class