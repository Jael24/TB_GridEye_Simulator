package ch.grideye.log.generator.controllers;

import ch.grideye.log.generator.controllers.models.StatusResponse;
import ch.grideye.log.generator.services.LoggerService;
import ch.grideye.log.generator.services.MemoryLeakService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/memoryleak", produces = MediaType.APPLICATION_JSON_VALUE)
public class MemoryLeakController {
    @Autowired
    private LoggerService logger;

    @Autowired
    private MemoryLeakService memoryLeakService;

    @ApiOperation(
            value = "Use this endpoint to start a memory leak on simulator"
    )
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Cell successfully created.", response = StatusResponse.class)
    })
    @RequestMapping(method = RequestMethod.GET, path = "/start")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<StatusResponse> healthCheck() {
        logger.warn(getClass(), "Memory leak started !");
        memoryLeakService.createMemoryLeak();
        return ResponseEntity.status(HttpStatus.OK).body(new StatusResponse("OK", "Memory leak just started !"));
    }
}
