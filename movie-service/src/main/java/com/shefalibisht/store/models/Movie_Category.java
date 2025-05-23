package com.shefalibisht.store.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.shefalibisht.store.CustomerDateAndTimeDeserialize;
import lombok.Data;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="movie_category")
@Data
@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
public class Movie_Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id")
    private Long category_id;

    @Column(name = "category_name")
    private String name;
//
//    @OneToMany(cascade = CascadeType.ALL, mappedBy = "category")
//    private Set<Movie> products;

    @Column(name = "last_update")
    @JsonDeserialize(using= CustomerDateAndTimeDeserialize.class)
    private Date last_update;

}
