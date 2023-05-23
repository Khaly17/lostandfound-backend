package sn.work.lostandfound.SemanticSimilarity;

import lombok.*;
import sn.work.lostandfound.objet.Objet;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class SemanticSimilarity {
    private Objet obj_lost;
    private Objet obj_found;
}
