package ch.grideye.log.generator.controllers;

import ch.grideye.log.generator.controllers.models.StatusResponse;
import ch.grideye.log.generator.services.LoggerService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(path = "/lg", produces = MediaType.APPLICATION_JSON_VALUE)
public class HealthController {

    @Autowired
    private LoggerService logger;

    @ApiOperation(
            value = "Health Manager call to verify if services are running."
    )
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Cell successfully created.", response = StatusResponse.class)
    })
    @RequestMapping(method = RequestMethod.GET, path = "/health")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<StatusResponse> healthCheck() {
        logger.debug(getClass(), "Health Check required !");
        return ResponseEntity.status(HttpStatus.OK).body(new StatusResponse("OK", "Services seems to be running !"));
    }
}
