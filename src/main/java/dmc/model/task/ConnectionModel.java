package dmc.model.task;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.bson.types.ObjectId;
import javax.persistence.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collation = "connections")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ConnectionModel {
    @Id
    private ObjectId id;
}
