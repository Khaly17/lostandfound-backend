package sn.work.lostandfound.status;

import org.springframework.stereotype.Component;

@Component
public class StatusConverter {
    public StatusDto convertToDto(Status status){
        StatusDto statusDto = new StatusDto();
        statusDto.setId(status.getId());
        statusDto.setCode(status.getCode());
        statusDto.setValue(status.getValue());
        return statusDto;
    }

    public Status convertToEntity(StatusDto statusDto){
        Status status = new Status();
        status.setId(statusDto.getId());
        status.setCode(statusDto.getCode());
        status.setValue(statusDto.getValue());
        return status;
    }
}
