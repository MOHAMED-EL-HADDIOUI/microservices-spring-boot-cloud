package com.shefalibisht.store.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.shefalibisht.store.CustomerDateAndTimeDeserialize;
import lombok.Data;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="film")
@Data
@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "film_id")
    private Long id;

    @Column(name = "title")
    private String title;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY,optional = false)
    @JoinColumn(name = "category_id", nullable = false)
    private Movie_Category category;

    @Column(name = "description")
    private String description;

    @Column(name = "release_year")
    private Integer release_year;

    @Column(name = "last_update")
    @UpdateTimestamp
    @JsonDeserialize(using= CustomerDateAndTimeDeserialize.class)
    private Date last_update;
}
