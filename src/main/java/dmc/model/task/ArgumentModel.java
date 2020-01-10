package dmc.model.task;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.bson.types.ObjectId;
import javax.persistence.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collation = "arguments")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ArgumentModel {
    @Id
    private ObjectId id;
}
