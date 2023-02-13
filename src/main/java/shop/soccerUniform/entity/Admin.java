package shop.soccerUniform.entity;

import lombok.Getter;

import javax.persistence.*;

@Entity
@Table(name = "T_ADMIN")
@Getter
@DiscriminatorValue("ADMIN")
public class Admin extends User {

    @Column(name = "ADMIN_NUM")
    private String adminNum;

    @Column(name = "POSITION")
    private String position;
}
