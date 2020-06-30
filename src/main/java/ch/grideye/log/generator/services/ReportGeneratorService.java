package ch.grideye.log.generator.services;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Random;

@Service
public class ReportGeneratorService {

    private final LoggerService logger;
    private final Random random;

    public ReportGeneratorService(final LoggerService logger) {
        this.logger = logger;
        this.random = new Random();
    }

    @Scheduled(cron = "* 12 */3 * * ?")
    public void onPqReportGeneration() {
        logger.debug(getClass(), "Start PQ report generation !");
        int numberOfDevices = random.nextInt(6000);
        int numberOfEvents = random.nextInt(12000);
        logger.info(getClass(), String.format(Locale.ENGLISH, "PQ report with %d devices and %d events", numberOfDevices, numberOfEvents));
        cpuAndRamWorker(numberOfDevices, numberOfEvents);
        logger.info(getClass(), "End of background processing for a greedy RAM algorithm !");
    }

    private void cpuAndRamWorker(final int base, final int worker) {
        final List<byte[]> ram = new LinkedList<>();
        final int[] counter = {0};
        List<Thread> threads = new LinkedList<>();
        int temporaryBase = Math.min(base, 5000);
        int temporaryWorker = Math.min(worker, 10000);
        for (int i = 0; i < temporaryBase; ++i) {
            Thread thread = new Thread(() -> {
                for (int j = 0; j < temporaryWorker; ++j) {
                    if (counter[0] < 2000) {
                        ram.add(new byte[1000*1000]); // 1 MB
                        ++counter[0];
                    }
                    try {
                        Thread.sleep(1);
                    } catch (InterruptedException e) {
                        logger.error(getClass(), "Worker delay failed !");
                    }
                }
            });
            threads.add(thread);
            thread.start();
            if (i == 4500 && base > 5000 && worker > 10000) {
                logger.error(getClass(), "Something went wrong, application crash !");
                System.exit(1);
            }
        }
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            logger.error(getClass(), "Worker delay failed");
        }
        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                logger.error(getClass(), "Worker delay failed");
            }
        }
        ram.clear();
        System.gc();
    }
}
