package sn.work.lostandfound.correspondence;


import sn.work.lostandfound.SemanticSimilarity.SemanticSimilarity;
import sn.work.lostandfound.SemanticSimilarity.SemanticSimilarityScore;

import java.util.List;

public interface CorrespondenceService {
    CorrespondenceDto addCorrespondence(CorrespondenceDto correspondenceDto);
    CorrespondenceDto updateCorrespondence(Long id, CorrespondenceDto correspondenceDto);
    List<CorrespondenceDto> findAllCorrespondence();
    void deleteCorrespondence(Long id);
    SemanticSimilarityScore getScore(SemanticSimilarity semanticSimilarity);
}
