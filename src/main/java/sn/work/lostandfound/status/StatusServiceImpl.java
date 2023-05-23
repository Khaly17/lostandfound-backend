package sn.work.lostandfound.status;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import sn.work.lostandfound.myException.NotFoundException;
import sn.work.lostandfound.myException.ObjectAlreadyExistsException;

import java.util.List;
import java.util.stream.Collectors;
@Service
public class StatusServiceImpl implements StatusService{
    private final StatusConverter statusConverter;
    private final StatusRepository statusRepository;

    public StatusServiceImpl(
            StatusConverter statusConverter,
            StatusRepository statusRepository
            ){
        this.statusConverter = statusConverter;
        this.statusRepository = statusRepository;
    }
    @Override
    public StatusDto addStatus(StatusDto statusDto) {
        Status status = statusConverter.convertToEntity(statusDto);
        try {
            Status statusSaved = statusRepository.save(status);
            return statusConverter.convertToDto(statusSaved);
        } catch (DataIntegrityViolationException e) {
            throw new ObjectAlreadyExistsException("Status already exists.");
        }
    }

    @Override
    public StatusDto updateStatus(Long id, StatusDto statusDto) {
        Status statusToUpdate = statusRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Status not found with id " + id));
        statusToUpdate.setCode(statusDto.getCode());
        statusToUpdate.setValue(statusDto.getValue());

        Status statusSaved = statusRepository.save(statusToUpdate);
        return statusConverter.convertToDto(statusSaved);
    }

    @Override
    public List<StatusDto> findAllStatus() {
        List<Status> statusList = statusRepository.findAll();
        return statusList.stream().map(statusConverter::convertToDto).collect(Collectors.toList());
    }

    @Override
    public void deleteStatus(Long id) {
        statusRepository.deleteById(id);
    }
}
