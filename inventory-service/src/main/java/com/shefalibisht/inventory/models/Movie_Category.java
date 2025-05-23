package com.shefalibisht.inventory.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.shefalibisht.inventory.CustomerDateAndTimeDeserialize;
import lombok.Data;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

//
//@Entity
//@Table(name="movie_category")
@Data
@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
public class Movie_Category {

    public Long getCategory_id() {
        return category_id;
    }

    public void setCategory_id(Long category_id) {
        this.category_id = category_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getLast_update() {
        return last_update;
    }

    public void setLast_update(Date last_update) {
        this.last_update = last_update;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long category_id;

    private String name;

   @JsonDeserialize(using= CustomerDateAndTimeDeserialize.class)
    private Date last_update;

}
