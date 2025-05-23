package com.shefalibisht.inventory.models;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.shefalibisht.inventory.CustomerDateAndTimeDeserialize;
import lombok.Data;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="inventory")
@Data
@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
public class Inventory {
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getLast_update() {
        return last_update;
    }

    public void setLast_update(Date last_update) {
        this.last_update = last_update;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "inventory_id")
    private Long id;

    @Column(name = "film_id")
    private Long movieId;
    @Column(name = "store_id")
    private Long storeId;

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Boolean getIs_active() {
        return is_active;
    }

    public void setIs_active(Boolean is_active) {
        this.is_active = is_active;
    }

    @Column(name = "quantity")
    private Integer quantity;

    @Column(name = "is_active")
    private Boolean is_active;


    public Long getMovieId() {
        return movieId;
    }

    public void setMovieId(Long movieId) {
        this.movieId = movieId;
    }

    public Long getStoreId() {
        return storeId;
    }

    public void setStoreId(Long storeId) {
        this.storeId = storeId;
    }


    @Column(name = "last_update")
    @UpdateTimestamp
    @JsonDeserialize(using= CustomerDateAndTimeDeserialize.class)
    private Date last_update;
}
