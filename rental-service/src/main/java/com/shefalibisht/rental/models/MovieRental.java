package com.shefalibisht.rental.models;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.shefalibisht.rental.CustomerDateAndTimeDeserialize;
import lombok.Data;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="rental")
@Data
@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
public class MovieRental {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "rental_id")
    private Long id;

    @Column(name = "rental_date")
    @JsonDeserialize(using= CustomerDateAndTimeDeserialize.class)
    private Date rental_date;

    @Column(name = "customer_id", nullable = false)
    private Long customerId;

    @Column(name = "inventory_id", nullable = false)
    private Long inventoryId;

    @Column(name = "return_date")
    @JsonDeserialize(using= CustomerDateAndTimeDeserialize.class)
    private Date return_date;

    @Column(name = "last_update")
    @JsonDeserialize(using= CustomerDateAndTimeDeserialize.class)
    @UpdateTimestamp
    private Date last_update;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getRental_date() {
        return rental_date;
    }

    public void setRental_date(Date rental_date) {
        this.rental_date = rental_date;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public Long getInventoryId() {
        return inventoryId;
    }

    public void setInventoryId(Long inventoryId) {
        this.inventoryId = inventoryId;
    }

    public Date getReturn_date() {
        return return_date;
    }

    public void setReturn_date(Date return_date) {
        this.return_date = return_date;
    }

    public Date getLast_update() {
        return last_update;
    }

    public void setLast_update(Date last_update) {
        this.last_update = last_update;
    }
}
