package com.shefalibisht.store.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.shefalibisht.store.CustomerDateAndTimeDeserialize;
import lombok.Data;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name="store")
@Data
@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
public class Store {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "store_id")
    private Long store_id;

    @Column(name = "store_city")
    private String city;

    @Column(name = "store_state")
    private String state;

    public Long getStore_id() {
        return store_id;
    }

    public void setStore_id(Long store_id) {
        this.store_id = store_id;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Date getLast_update() {
        return last_update;
    }

    public void setLast_update(Date last_update) {
        this.last_update = last_update;
    }

    @Column(name = "last_update")
    @UpdateTimestamp
    @JsonDeserialize(using= CustomerDateAndTimeDeserialize.class)
    private Date last_update;
}
