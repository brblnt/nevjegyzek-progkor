package hu.nye.progkor.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.GenerationType;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.Data;

/**
 * Represents a user in the database. Contains the users' id, name.
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
@Entity
@Table(name = "user")
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "user_name", nullable = false)
    private String userName;

    @Column(name = "email", nullable = false, unique = false)
    private String emailAddress;

    @Column(name = "password", nullable = false)
    private String password;


}
