package ch.grideye.log.generator.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service
public class MemoryLeakService {
    @Autowired
    private LoggerService logger;

    public void createMemoryLeak() {
        List<byte[]> ram = new LinkedList<>();
        logger.debug(getClass(), "Start of eating RAM!");

        for (int i = 0; i < 16000; ++i) { // 16 GB
            ram.add(new byte[1000*1000]); // 1 MB
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
                logger.error(getClass(),"Failed to sleep");
            }
        }

        logger.debug(getClass(), "Are you still alive?");
    }
}
