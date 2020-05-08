package ch.grideye.log.generator.controllers.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;

@Getter
@ApiModel(description = "The default response model with an information message.")
public class StatusResponse {

    @JsonProperty("status")
    @ApiModelProperty(notes = "The information status.")
    private String status;

    @JsonProperty("message")
    @ApiModelProperty(notes = "The information message.")
    private String message;

    public StatusResponse(final String status, final String message) {
        this.status = status;
        this.message = message;
    }
}