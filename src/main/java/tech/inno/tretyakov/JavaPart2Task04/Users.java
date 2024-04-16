package tech.inno.tretyakov.JavaPart2Task04;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.*;

@Entity
@Table(name = "users")
@AllArgsConstructor
@NoArgsConstructor
public class Users {
    public Users(String username, String fio) {
        this.username = username;
        this.fio = fio;
    }
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    @Getter
    @Setter
    @Column(name = "username")
    String username;

    @Getter
    @Setter
    @Column(name = "fio")
    String fio;

    @OneToMany(mappedBy = "id", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    @Getter
    @Setter
    List<Logins> logins = new ArrayList<>();

    @Override
    public String toString() {
        return "Users{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", fio='" + fio + '\'' +
                ", logins=" + logins +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Users users = (Users) o;
        return Objects.equals(username, users.username) && Objects.equals(fio, users.fio);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, fio);
    }
}
