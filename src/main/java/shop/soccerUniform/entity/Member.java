package shop.soccerUniform.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import shop.soccerUniform.entity.enumtype.Gender;
import shop.soccerUniform.entity.enumtype.Grade;

import javax.persistence.*;

import java.util.ArrayList;
import java.util.List;

import static lombok.AccessLevel.PROTECTED;

@Entity
@Table(name = "T_MEMBER")
@Getter
@DiscriminatorValue("MEMBER")
@NoArgsConstructor(access = PROTECTED)
public class Member extends User {

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Grade grade;

    @Column(name = "MOBILE", nullable = false)
    private String mobile;

    @Column(name = "HOME_NUM", nullable = false)
    private String homeNum;

    @OneToMany(mappedBy = "member")
    private List<Point> points = new ArrayList<>();

    public Member(Gender gender, Grade grade, String mobile, String homeNum) {
        this.gender = gender;
        this.grade = grade;
        this.mobile = mobile;
        this.homeNum = homeNum;
    }
}
