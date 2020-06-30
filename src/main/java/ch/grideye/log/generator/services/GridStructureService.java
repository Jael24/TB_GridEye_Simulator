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

    @Scheduled(cron = "0 0/28 */2 * * ?")
    public void onHvMvStationGroupAction() {
        switch (random.nextInt(3)) {
            case 0:
                action("Updating HvMvStationGroup.",
                        "HvMvStationGroup successfully updated.",
                        "Failed to updated HvMvStationGroup.",
                        "Delay during update failed !");
                break;
            case 1:
                action("Deleting HvMvStationGroup.",
                        "HvMvStationGroup successfully deleted.",
                        "Failed to delete HvMvStationGroup.",
                        "Delay during deletion failed !");
                break;
            default:
                action("Creating new HvMvStationGroup.",
                        "HvMvStationGroup successfully created.",
                        "Failed to create HvMvStationGroup.",
                        "Delay during creation failed !");
                break;
        }
    }

    @Scheduled(cron = "0 0/24 */2 * * ?")
    public void onMvFeederGroupAction() {
        switch (random.nextInt(3)) {
            case 0:
                action("Updating MvFeederGroup.",
                        "MvFeederGroup successfully updated.",
                        "Failed to updated MvFeederGroup.",
                        "Delay during update failed !");
                break;
            case 1:
                action("Deleting MvFeederGroup.",
                        "MvFeederGroup successfully deleted.",
                        "Failed to delete MvFeederGroup.",
                        "Delay during deletion failed !");
                break;
            default:
                action("Creating new MvFeederGroup.",
                        "MvFeederGroup successfully created.",
                        "Failed to create MvFeederGroup.",
                        "Delay during creation failed !");
                break;
        }
    }

    @Scheduled(cron = "0 0/20 */2 * * ?")
    public void onLvGroupAction() {
        switch (random.nextInt(3)) {
            case 0:
                action("Updating LvGroup.",
                        "LvGroup successfully updated.",
                        "Failed to updated LvGroup.",
                        "Delay during update failed !");
                break;
            case 1:
                action("Deleting LvGroup.",
                        "LvGroup successfully deleted.",
                        "Failed to delete LvGroup.",
                        "Delay during deletion failed !");
                break;
            default:
                action("Creating new LvGroup.",
                        "LvGroup successfully created.",
                        "Failed to create LvGroup.",
                        "Delay during creation failed !");
                break;
        }
    }

    @Scheduled(cron = "0 0/16 */2 * * ?")
    public void onNodeAction() {
        switch (random.nextInt(3)) {
            case 0:
                action("Updating Node.",
                        "Node successfully updated.",
                        "Failed to updated Node.",
                        "Delay during update failed !");
                break;
            case 1:
                action("Deleting Node.",
                        "Node successfully deleted.",
                        "Failed to delete Node.",
                        "Delay during deletion failed !");
                break;
            default:
                action("Creating new Node.",
                        "Node successfully created.",
                        "Failed to create Node.",
                        "Delay during creation failed !");
                break;
        }
    }

    @Scheduled(cron = "0 0/12 */2 * * ?")
    public void onFeederAction() {
        switch (random.nextInt(3)) {
            case 0:
                action("Updating Feeder.",
                        "Feeder successfully updated.",
                        "Failed to updated Feeder.",
                        "Delay during update failed !");
                break;
            case 1:
                action("Deleting Feeder.",
                        "Feeder successfully deleted.",
                        "Failed to delete Feeder.",
                        "Delay during deletion failed !");
                break;
            default:
                action("Creating new Feeder.",
                        "Feeder successfully created.",
                        "Failed to create Feeder.",
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
