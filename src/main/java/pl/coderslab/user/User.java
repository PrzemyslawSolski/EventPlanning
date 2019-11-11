package pl.coderslab.user;

import pl.coderslab.event.Event;
import org.mindrot.jbcrypt.BCrypt;
import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name="users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @NotEmpty(groups={RegistrationValidationGroup.class})
    private String name;
    @NotEmpty(groups={RegistrationValidationGroup.class})
    private String surname;
    @NotEmpty(groups={RegistrationValidationGroup.class, LoginValidationGroup.class})
    @Email(groups={RegistrationValidationGroup.class, LoginValidationGroup.class})
    private String email;
    @NotEmpty(groups={RegistrationValidationGroup.class, LoginValidationGroup.class})
    private String password;
    private boolean admin;
    @ManyToMany(mappedBy = "users")
    private List<Event> events;

    public void setPasswordHash(String password) {
        this.password = BCrypt.hashpw(password, BCrypt.gensalt());
    }

    @Override
    public boolean equals(Object o){
        if(this==o){
            return true;
        }
        if(!(o instanceof User)){
            return false;
        }

        User user = (User) o;
        return this.getId()==user.getId()
                && Objects.equals(this.getName(), user.getName())
                && Objects.equals(this.getSurname(), user.getSurname())
                && Objects.equals(this.getEmail(), user.getEmail())
                && Objects.equals(this.getPassword(), user.getPassword())
                && Objects.equals(this.isAdmin(), user.isAdmin());

    }

    @Override
    public int hashCode() {
        return Objects.hash(name, surname, email, password, admin);
    }



    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

    public List<Event> getEvents() {
        return events;
    }

    public void setEvents(List<Event> events) {
        this.events = events;
    }


}
