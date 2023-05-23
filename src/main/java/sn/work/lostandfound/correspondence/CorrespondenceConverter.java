package sn.work.lostandfound.correspondence;

import org.springframework.stereotype.Component;

@Component
public class CorrespondenceConverter {
    public CorrespondenceDto converterToDto(Correspondence correspondence){
        CorrespondenceDto correspondenceDto = new CorrespondenceDto();
        correspondenceDto.setId(correspondence.getId());
        correspondenceDto.setCorrespondenceScore(correspondence.getCorrespondenceScore());
        correspondenceDto.setCorrespondenceDate(correspondence.getCorrespondenceDate());
        correspondenceDto.setCorrespondenceStatus(correspondence.getCorrespondenceStatus());
        correspondenceDto.setObjetLostId(correspondence.getObjetLostId());
        correspondenceDto.setObjetFoundId(correspondence.getObjetFoundId());
        correspondenceDto.setUserIdFound(correspondence.getUserIdFound());
        correspondenceDto.setUserIdLost(correspondenceDto.getUserIdLost());
        return correspondenceDto;
    }
    public Correspondence converterToEntity(CorrespondenceDto correspondenceDto){
        Correspondence correspondence = new Correspondence();
        correspondence.setId(correspondenceDto.getId());
        correspondence.setCorrespondenceScore(correspondenceDto.getCorrespondenceScore());
        correspondence.setCorrespondenceDate(correspondenceDto.getCorrespondenceDate());
        correspondence.setCorrespondenceStatus(correspondenceDto.getCorrespondenceStatus());
        correspondence.setObjetLostId(correspondenceDto.getObjetLostId());
        correspondence.setObjetFoundId(correspondenceDto.getObjetFoundId());
        correspondence.setUserIdFound(correspondenceDto.getUserIdFound());
        correspondence.setUserIdLost(correspondenceDto.getUserIdLost());
        return correspondence;
    }
}