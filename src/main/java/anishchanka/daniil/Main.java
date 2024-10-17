package anishchanka.daniil;

import lombok.extern.log4j.Log4j2;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Log4j2
public class Main {

    private static final int AMOUNT_OF_CYCLES = 10;

    public static void main(String[] args) {
        causeInterruptedException();
    }

    private static void task_1_to_2() {
        log.info("Hello, World!");

        Runnable runnable = () -> {
            for (int i = 0; i <= 10; i++) {
                log.info("Thread name: {} i = {}", Thread.currentThread().getName(), i);
            }
        };

        Thread thread = new Thread(runnable);
        thread.start();
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        for (int i = 0; i <= 10; i++) {
            log.info("Thread name: {} i = {}", Thread.currentThread().getName(), i);
        }
    }

    private static void task_3(int amountOfThreads) {
        List<String[]> tasks = Arrays.asList(
                new String[]{"building the maritime industry", "building ships", "building ports", "creating seas"},
                new String[]{"developing the tech industry", "writing software", "building hardware", "creating data centers"},
                new String[]{"building the healthcare system", "constructing hospitals", "training doctors", "producing medicine"},
                new String[]{"developing the education system", "building schools", "training teachers", "creating curricula"},
                new String[]{"building the energy sector", "constructing power plants", "building grids", "creating renewable energy sources"},
                new String[]{"destroying everything", "sinking ships", "demolishing buildings", "burning data centers", "shutting down power grids"}
        );

        for (int i = 0; i <= amountOfThreads; i++) {
            String[] currentTask = tasks.get(i);
            new Thread(() -> serviceMethodTask3(currentTask)).start();
        }

    }

    private static void serviceMethodTask3(String[] text) {
        for (int i = 0; i < text.length; i++) {
            log.info("Thread number {} is executing step number {} and {}", Thread.currentThread().getName(), i, text[i]);

        }
        System.out.println();

    }


    private static void causeInterruptedException() {
        Thread thread = new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                log.info("Thread {} was interrupted", Thread.currentThread().getName());
            }
        });

        thread.start();

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            log.error(e);
        }
        thread.interrupt();
    }

}
