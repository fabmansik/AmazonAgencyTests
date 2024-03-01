package milan.somyk.AWS.test.document;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

@Document(collection = "users")
@NoArgsConstructor
@Data
public class User {
    @MongoId
    private String id;
    private String username;
    private String password;
    private String role;

    public User(String userId, String username, String password, String role) {
        this.id = userId;
        this.username = username;
        this.password = password;
        this.role = role;
    }
    public User(String userId, String username){
        this.id = userId;
        this.username = username;
    }

}
