/*
* File: MyThread.java
* Author: John Kucera
* Date: January 29, 2020
* Purpose: This java program is meant to be run alongside Main.java. MyThread.java creates the "before"
* and "after" semaphores so the threads release and acquire them at appropriate times for the
* previous or next thread to continue. This class also contains the run() method for the threads
* which holds the print function and information to be printed.
*/

// import necessary java classes
import java.util.concurrent.Semaphore;

// MyThread class
public class MyThread implements Runnable {
  // Variable Declaration
  private final Semaphore beforeSemaphore;
  private final Semaphore afterSemaphore;

  // MyThread Constructor
  public MyThread (Semaphore beforeSemaphore, Semaphore afterSemaphore) {
    this.beforeSemaphore = beforeSemaphore;
    this.afterSemaphore = afterSemaphore;
  } // end of constructor

  // run() method: prints thread ID and iteration number for each MyThread object
  @Override
  public void run() {
    for (int i = 1; i <= 5; i++) {
      // Acquire "before" semaphore
      try {
        beforeSemaphore.acquire();
      } // end of try
      catch (InterruptedException ex) {
        System.out.println("InterruptedException: acquire() in MyThread");
      } // end of catch

      // Print thread ID and iteration number
      System.out.println("Thread " + Thread.currentThread().getId() + " - iteration no. " + i);

      // Release "after" semaphore
      afterSemaphore.release();
    } // end of for
  } // end of method
} // end of class