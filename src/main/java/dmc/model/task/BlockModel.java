package dmc.model.task;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.bson.types.ObjectId;
import javax.persistence.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collation = "blocks")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BlockModel {
    @Id
    private ObjectId id;
}
