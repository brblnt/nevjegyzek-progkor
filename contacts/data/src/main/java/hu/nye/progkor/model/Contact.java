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
 * Representation of the data table.
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
@Entity
@Table(name = "contacts")
@Data
public class Contact {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(name = "birthday")
    private String birthday;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "email_address")
    private String emailAddress;

    @Column(name = "address")
    private String address;

    @Column(name = "other")
    private String other;
}
