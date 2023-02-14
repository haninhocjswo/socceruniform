package shop.soccerUniform.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import shop.soccerUniform.entity.enumtype.PointState;

import javax.persistence.*;

import static lombok.AccessLevel.PROTECTED;

@Entity
@Table(name = "T_POINT")
@Getter
@NoArgsConstructor(access = PROTECTED)
public class Point extends DateColumns {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "POINT_ID", nullable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MEMBER_ID")
    private Member member;

    @Column(nullable = false)
    private Integer point;

    @Column(nullable = false)
    private Integer updatePoint;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private PointState state;

    @Column(nullable = false)
    private Integer sort;

}
