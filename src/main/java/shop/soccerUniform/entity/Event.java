package shop.soccerUniform.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import shop.soccerUniform.entity.enumtype.EventState;
import shop.soccerUniform.entity.enumtype.SaleType;

import javax.persistence.*;
import java.time.LocalDateTime;

import static lombok.AccessLevel.PROTECTED;

@Entity
@Table(name = "T_EVENT")
@Getter
@NoArgsConstructor(access = PROTECTED)
public class Event extends DateColumns {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "EVENT_ID", nullable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ADMIN_ID", nullable = false)
    private Admin admin;

    @Column(nullable = false)
    private String title;

    @Lob
    @Column(nullable = false)
    private String content;

    @Column(name = "SALE_TYPE", nullable = false)
    @Enumerated(EnumType.STRING)
    private SaleType saleType;

    @Column(name = "SALE_VALUE", nullable = false)
    private Double saleValue;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private EventState state;

    @Column(name = "START_DATE", nullable = false)
    private LocalDateTime startDate;

    @Column(name = "END_DATE", nullable = false)
    private LocalDateTime endDate;

    public Event(Admin admin, String title, String content, SaleType saleType, Double saleValue, EventState state, LocalDateTime startDate, LocalDateTime endDate) {
        this.admin = admin;
        this.title = title;
        this.content = content;
        this.saleType = saleType;
        this.saleValue = saleValue;
        this.state = state;
        this.startDate = startDate;
        this.endDate = endDate;
    }
}
