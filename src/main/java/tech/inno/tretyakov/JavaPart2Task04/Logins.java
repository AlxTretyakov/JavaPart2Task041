package tech.inno.tretyakov.JavaPart2Task04;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Cascade;

import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "logins")
@AllArgsConstructor
@NoArgsConstructor
public class Logins {
    @Getter
    @Setter
    @Transient
    String logFileName;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    @ManyToOne
    @Cascade(org.hibernate.annotations.CascadeType.SAVE_UPDATE)
    @JoinColumn(name = "userid")
    @Getter
    @Setter
    private Users user;

    @Getter
    @Setter
    @Column(name = "access_date")
    java.sql.Timestamp access_time;

    @Getter
    @Setter
    @Column(name = "application")
    String application;

    public Logins(Timestamp access_time, String application, Users user, String logFileName) {
        this.user = user;
        this.access_time = access_time;
        this.application = application;
        this.logFileName = logFileName;
    }

    @Override
    public String toString() {
        return "Logins{" +
                "logFileName='" + logFileName + '\'' +
                ", id=" + id +
                ", user=" + user.getFio() +
                ", access_time=" + access_time +
                ", application='" + application + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Logins logins = (Logins) o;
        return Objects.equals(logFileName, logins.logFileName) && Objects.equals(id, logins.id) && Objects.equals(user, logins.user) && Objects.equals(access_time, logins.access_time) && Objects.equals(application, logins.application);
    }

    @Override
    public int hashCode() {
        return Objects.hash(logFileName, id, user, access_time, application);
    }
}
