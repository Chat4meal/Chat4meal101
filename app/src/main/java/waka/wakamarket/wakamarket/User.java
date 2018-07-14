package waka.wakamarket.wakamarket;

/**
 * Created by Muhammad on 4/28/2018.
 */
import com.google.firebase.database.IgnoreExtraProperties;



@IgnoreExtraProperties
public class User {

    public String name;
    public String email;

    // Default constructor required for calls to
    // DataSnapshot.getValue(User.class)
    public User() {
    }

    public User(String name, String email) {
        this.name = name;
        this.email = email;
    }
}