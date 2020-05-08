package ch.grideye.log.generator.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service
public class RamService {

    @Autowired
    private LoggerService logger;

    @Scheduled(cron = "0 0/16 * * * ?")
    public void computeRamGreedyAlgorithm() {
        List<byte[]> ram = new LinkedList<>();
        logger.debug(getClass(), "Start of background processing for a greedy RAM algorithm !");
        for (int i = 0; i < 2000; ++i) { // 2 GB
            ram.add(new byte[1000*1000]); // 1 MB
        }
        try {
            Thread.sleep(30000);
        } catch (InterruptedException e){
            logger.error(getClass(), "While processing a greedy RAM algorithm !");
        }
        ram.clear();
        System.gc();
        logger.debug(getClass(), "End of background processing for a greedy RAM algorithm !");
    }
}
