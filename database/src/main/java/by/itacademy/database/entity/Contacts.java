package by.itacademy.database.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Data
@NoArgsConstructor
@AllArgsConstructor(staticName = "of")
@Embeddable
public class Contacts {

    @Column(name = "mail", nullable = false)
    private String mail;

    @Column(name = "tel", nullable = false, length = 17)
    private String tel;
}
