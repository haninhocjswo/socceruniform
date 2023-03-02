package shop.soccerUniform.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.util.StringUtils;
import shop.soccerUniform.entity.enumtype.CategoryState;

import javax.persistence.*;

import static lombok.AccessLevel.PROTECTED;

@Entity
@Table(name = "T_CATEGORY")
@Getter
@NoArgsConstructor(access = PROTECTED)
public class Category extends DateColumns {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CATEGORY_ID", nullable = false)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Integer depth;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PARENT_ID")
    @JsonIgnore
    private Category parent;

    @Column(nullable = false)
    private CategoryState state;

    public Category(String name, Integer depth, Category parent, CategoryState state) {
        this.name = name;
        this.depth = depth;
        if(depth > 1) {
            this.parent = parent;
        }
        this.state = state;
    }

    public void editCategory(String name, Category parent, CategoryState state) {
        if(this.name != name && StringUtils.hasText(name)) this.name = name;
        if(this.parent != parent) this.parent = parent;
        if(this.state != state && state != null) this.state = state;
    }

    public void delCategory() {
        state = CategoryState.DISABLE;
    }
}
