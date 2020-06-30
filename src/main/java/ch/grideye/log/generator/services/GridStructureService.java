package ch.grideye.log.generator.services;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class GridStructureService {

    private final LoggerService logger;
    private final Random random;

    public GridStructureService(final LoggerService logger) {
        this.logger = logger;
        this.random = new Random();
    }

    @Scheduled(cron = "0 0/28 * * * ?")
    public void createHvMvStationGroup() {
        switch (random.nextInt(3)) {
            case 0:
                action("Updating HvMvStationGroup.",
                        "Successfully updated HvMvStationGroup.",
                        "Failed to updated new HvMvStationGroup.",
                        "Delay during update failed !");
                break;
            case 1:
                action("Deleting HvMvStationGroup.",
                        "Successfully deleted HvMvStationGroup.",
                        "Failed to delete HvMvStationGroup.",
                        "Delay during deletion failed !");
                break;
            default:
                action("Creating new HvMvStationGroup.",
                        "Successfully created new HvMvStationGroup.",
                        "Failed to create new HvMvStationGroup.",
                        "Delay during creation failed !");
                break;
        }
    }

    @Scheduled(cron = "0 0/24 * * * ?")
    public void createMvFeederGroup() {
        switch (random.nextInt(3)) {
            case 0:
                action("Updating MvFeederGroup.",
                        "Successfully updated MvFeederGroup.",
                        "Failed to updated new MvFeederGroup.",
                        "Delay during update failed !");
                break;
            case 1:
                action("Deleting MvFeederGroup.",
                        "Successfully deleted MvFeederGroup.",
                        "Failed to delete MvFeederGroup.",
                        "Delay during deletion failed !");
                break;
            default:
                action("Creating new MvFeederGroup.",
                        "Successfully created new MvFeederGroup.",
                        "Failed to create new MvFeederGroup.",
                        "Delay during creation failed !");
                break;
        }
    }

    @Scheduled(cron = "0 0/20 * * * ?")
    public void createLvGroup() {
        switch (random.nextInt(3)) {
            case 0:
                action("Updating LvGroup.",
                        "Successfully updated LvGroup.",
                        "Failed to updated new LvGroup.",
                        "Delay during update failed !");
                break;
            case 1:
                action("Deleting LvGroup.",
                        "Successfully deleted LvGroup.",
                        "Failed to delete LvGroup.",
                        "Delay during deletion failed !");
                break;
            default:
                action("Creating new LvGroup.",
                        "Successfully created new LvGroup.",
                        "Failed to create new LvGroup.",
                        "Delay during creation failed !");
                break;
        }
    }

    @Scheduled(cron = "0 0/16 * * * ?")
    public void createNode() {
        switch (random.nextInt(3)) {
            case 0:
                action("Updating Node.",
                        "Successfully updated Node.",
                        "Failed to updated new Node.",
                        "Delay during update failed !");
                break;
            case 1:
                action("Deleting Node.",
                        "Successfully deleted Node.",
                        "Failed to delete Node.",
                        "Delay during deletion failed !");
                break;
            default:
                action("Creating new Node.",
                        "Successfully created new Node.",
                        "Failed to create new Node.",
                        "Delay during creation failed !");
                break;
        }
    }

    @Scheduled(cron = "0 0/12 * * * ?")
    public void createFeeder() {
        switch (random.nextInt(3)) {
            case 0:
                action("Updating Feeder.",
                        "Successfully updated Feeder.",
                        "Failed to updated new Feeder.",
                        "Delay during update failed !");
                break;
            case 1:
                action("Deleting Feeder.",
                        "Successfully deleted Feeder.",
                        "Failed to delete Feeder.",
                        "Delay during deletion failed !");
                break;
            default:
                action("Creating new Feeder.",
                        "Successfully created new Feeder.",
                        "Failed to create new Feeder.",
                        "Delay during creation failed !");
                break;
        }
    }

    private void action(final String debug, final String info, final String warn, final String error) {
        if (random.nextBoolean()) {
            logger.debug(getClass(), debug);
            try {
                Thread.sleep(50);
            } catch (InterruptedException e){
                logger.error(getClass(), error);
            }
            if (random.nextBoolean()) {
                logger.info(getClass(), info);
            } else {
                logger.warn(getClass(), warn);
            }
        }
    }
}
