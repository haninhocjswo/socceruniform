package shop.soccerUniform.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import static lombok.AccessLevel.PROTECTED;

@Entity
@Table(name = "T_ADMIN")
@Getter
@DiscriminatorValue("ADMIN")
@NoArgsConstructor(access = PROTECTED)
public class Admin extends User {

    @Column(name = "ADMIN_NUM")
    private String adminNum;

    @Column(name = "POSITION")
    private String position;

    public Admin(String adminNum, String position) {
        this.adminNum = adminNum;
        this.position = position;
    }
}
