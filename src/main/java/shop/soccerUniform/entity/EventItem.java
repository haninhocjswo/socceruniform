package shop.soccerUniform.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import static lombok.AccessLevel.PROTECTED;

@Entity
@Table(name = "T_EVENT_ITEM")
@Getter
@NoArgsConstructor(access = PROTECTED)
public class EventItem extends DateColumns {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "EVENT_ITEM_ID")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "EVENT_ID", nullable = false)
    private Event event;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ITEM_ID", nullable = false)
    private Item item;

    public EventItem(Event event, Item item) {
        this.event = event;
        this.item = item;
    }
}
