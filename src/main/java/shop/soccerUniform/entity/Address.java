package shop.soccerUniform.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import shop.soccerUniform.entity.enumtype.AddressState;

import javax.persistence.*;

import static lombok.AccessLevel.PROTECTED;

@Entity
@Table(name = "T_ADDRESS")
@Getter
@NoArgsConstructor(access = PROTECTED)
public class Address extends DateColumns {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ADDRESS_ID", nullable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MEMBER_ID")
    private Member member;

    @Column(nullable = false)
    private String memo;

    @Column(nullable = false)
    private Integer post;

    @Column(nullable = false)
    private String addr;

    @Column(nullable = false)
    private String detailAddr;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private AddressState state;

    @Column(nullable = false)
    private Boolean isBase;

    public Address(Member member, String memo, Integer post, String addr, String detailAddr, AddressState state, Boolean isBase) {
        this.member = member;
        this.memo = memo;
        this.post = post;
        this.addr = addr;
        this.detailAddr = detailAddr;
        this.state = state;
        this.isBase = isBase;
    }
}
