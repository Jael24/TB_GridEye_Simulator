package ch.grideye.log.generator.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service
public class CpuService {

    @Autowired
    private LoggerService logger;

    @Scheduled(cron = "0 0/11 * * * ?")
    public void computeCpuGreedyAlgorithm() {
        List<Thread> threads = new LinkedList<>();
        logger.debug(getClass(), "Start of background processing for a greedy CPU algorithm !");
        for (int i = 0; i < 5000; ++i) {
            Thread thread = new Thread(() -> {
                    for (int j = 0; j < 10000; ++j) {
                        try {
                            Thread.sleep(1);
                        } catch (InterruptedException e){
                            logger.error(getClass(), "While processing a greedy CPU algorithm !");
                        }
                    }
                });
            threads.add(thread);
            thread.start();
        }
        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e){
                logger.error(getClass(), "While processing a greedy CPU algorithm !");
            }
        }
        logger.debug(getClass(), "End of background processing for a greedy CPU algorithm !");
    }
}
