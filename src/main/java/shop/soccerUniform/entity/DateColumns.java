package shop.soccerUniform.entity;

import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@MappedSuperclass
@Getter
public class DateColumns {

    @Column(name = "REG_DATE", updatable = false, nullable = false)
    private LocalDateTime regDate;

    @Column(name = "MOD_DATE", nullable = false)
    private LocalDateTime modDate;
}
