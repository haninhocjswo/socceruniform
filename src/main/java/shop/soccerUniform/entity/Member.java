package shop.soccerUniform.entity;

import lombok.Getter;
import shop.soccerUniform.entity.enumtype.Gender;
import shop.soccerUniform.entity.enumtype.Grade;

import javax.persistence.*;

@Entity
@Table(name = "T_MEMBER")
@Getter
@DiscriminatorValue("MEMBER")
public class Member extends User {

    @Column(nullable = false)
    private Gender gender;

    @Column(nullable = false)
    private Grade grade;

    @Column(name = "MOBILE", nullable = false)
    private String mobile;

    @Column(name = "HOME_NUM", nullable = false)
    private String homeNum;
}
