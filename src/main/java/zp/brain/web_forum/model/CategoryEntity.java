package zp.brain.web_forum.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.sql.Timestamp;

@Entity
@Table(name = "CATEGORY")
@Data
public class CategoryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "category_id")
    private Long categoryId;

    @Size(min = 3, max = 100, message = "Title of category must contain from 3 to 100 characters. This is required for create new Category...")
    @Column(name = "title_category")
    private String titleCategory;
}
