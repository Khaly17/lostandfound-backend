package sn.work.lostandfound.correspondence;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import sn.work.lostandfound.SemanticSimilarity.SemanticSimilarity;
import sn.work.lostandfound.SemanticSimilarity.SemanticSimilarityScore;
import sn.work.lostandfound.constant.Constant;

import java.util.List;

@RestController
@RequestMapping("/find")
public class CorrespondenceController {
    @Autowired
    private CorrespondenceServiceImpl correspondenceService;

    @PostMapping("/correspondence/create")
    public CorrespondenceDto addCorrespondence(
            @RequestBody CorrespondenceDto correspondenceDto
    ){
        return correspondenceService.addCorrespondence(correspondenceDto);
    }
    @PutMapping("/correspondence/update/{correspondenceId}")
    public CorrespondenceDto updateCorrespondence(
            @PathVariable Long correspondenceId,
            @RequestBody CorrespondenceDto correspondenceDto
    ){
        return correspondenceService.updateCorrespondence(correspondenceId,correspondenceDto);
    }
    @DeleteMapping("/correspondence/delete/{correspondenceId}")
    public void deleteCorrespondence(@PathVariable Long correspondenceId){
       try{
           correspondenceService.deleteCorrespondence(correspondenceId);
       } catch (Exception e){
           e.printStackTrace();
       }
    }

    @PostMapping("/similarity")
    public SemanticSimilarityScore getScore(@RequestBody SemanticSimilarity semanticSimilarity){
        RestTemplate restTemplate = new RestTemplate();
        SemanticSimilarityScore similarity_score = restTemplate.postForObject(Constant.SCORE_URL,semanticSimilarity, SemanticSimilarityScore.class);
        return similarity_score;
    }

    @GetMapping("/correspondence/all")
    public List<CorrespondenceDto> findAllCorrespondence(){
        List<CorrespondenceDto> correspondenceDtoList = null;
        try{
            correspondenceDtoList = correspondenceService.findAllCorrespondence();
        } catch (Exception e){
            e.printStackTrace();
        }
        return correspondenceDtoList;
    }
}
