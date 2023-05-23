package sn.work.lostandfound.correspondence;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import sn.work.lostandfound.SemanticSimilarity.SemanticSimilarity;
import sn.work.lostandfound.SemanticSimilarity.SemanticSimilarityScore;
import sn.work.lostandfound.constant.Constant;
import sn.work.lostandfound.myException.NotFoundException;
import sn.work.lostandfound.myException.ObjectAlreadyExistsException;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CorrespondenceServiceImpl implements CorrespondenceService{

    private final CorrespondenceConverter correspondenceConverter;

    private final CorrespondenceRepository correspondenceRepository;

    public CorrespondenceServiceImpl(
            CorrespondenceConverter correspondenceConverter,
            CorrespondenceRepository correspondenceRepository
    ){
        this.correspondenceConverter = correspondenceConverter;
        this.correspondenceRepository = correspondenceRepository;
    }
    @Override
    public CorrespondenceDto addCorrespondence(CorrespondenceDto correspondenceDto) {
        Correspondence correspondence = correspondenceConverter.converterToEntity(correspondenceDto);
        try {
            Correspondence correspondenceSaved = correspondenceRepository.save(correspondence);
            return correspondenceConverter.converterToDto(correspondenceSaved);
        }catch (DataIntegrityViolationException e) {
            throw new ObjectAlreadyExistsException("Correspondence already exists.");
        }
    }

    @Override
    public CorrespondenceDto updateCorrespondence(Long id, CorrespondenceDto correspondenceDto) {

        Correspondence correspondenceToUpdate = correspondenceRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Correspondence not found with id " + id));

        correspondenceToUpdate.setCorrespondenceStatus(correspondenceDto.getCorrespondenceStatus());
        correspondenceToUpdate.setCorrespondenceScore(correspondenceDto.getCorrespondenceScore());
        correspondenceToUpdate.setObjetLostId(correspondenceDto.getObjetLostId());
        correspondenceToUpdate.setObjetFoundId(correspondenceDto.getObjetFoundId());
        correspondenceToUpdate.setUserIdFound(correspondenceDto.getUserIdFound());
        correspondenceToUpdate.setUserIdLost(correspondenceDto.getUserIdLost());

        Correspondence correspondenceSaved = correspondenceRepository.save(correspondenceToUpdate);
        return  correspondenceConverter.converterToDto(correspondenceSaved);
    }
    @Override
    public void deleteCorrespondence(Long correspondenceId){
        correspondenceRepository.deleteById(correspondenceId);
    }
    @Override
    public List<CorrespondenceDto> findAllCorrespondence(){
        List<Correspondence> correspondenceList = correspondenceRepository.findAll();
        return correspondenceList.stream().map(correspondenceConverter::converterToDto).collect(Collectors.toList());
    }
    @Override
    public SemanticSimilarityScore getScore(SemanticSimilarity semanticSimilarity){
        RestTemplate restTemplate = new RestTemplate();
        SemanticSimilarityScore similarity_score = restTemplate.postForObject(Constant.SCORE_URL,semanticSimilarity, SemanticSimilarityScore.class);
        return similarity_score;
    }
}
