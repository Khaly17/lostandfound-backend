package sn.work.lostandfound.status;

import java.util.List;

/**
 * @author DIENG Khaly (MS2E)
 */
public interface StatusService {
    /**
     *
     * @param statusDto
     * @return
     */
    StatusDto addStatus(StatusDto statusDto);

    /**
     *
     * @param id
     * @param statusDto
     * @return
     */
    StatusDto updateStatus(Long id, StatusDto statusDto);

    /**
     *
     * @return
     */
    List<StatusDto> findAllStatus();

    /**
     *
     * @param id
     */
    void deleteStatus(Long id);
}
