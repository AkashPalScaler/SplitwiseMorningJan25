package scaler.com.splitwise.models;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity(name = "my_user") // not user
public class User extends BaseModel{
    private String name;
    private String phone;
    private String password;
    @ManyToMany
    private List<Group> groups;
}
