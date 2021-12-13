package com.group19.reportingservice.domain.model;

import com.group19.reportingservice.enums.Side;
import com.group19.reportingservice.enums.Status;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;


import javax.persistence.*;

import java.util.Date;
@Getter
@Setter
@Data
@Entity
@Table(name = "orders")
@NoArgsConstructor
public class Order {
    @Id
    @Column(name = "id", nullable = false)
    private String id;

    @CreationTimestamp
    @Column(name = "createAt", updatable = false)
    private Date createdAt;
    private String product;
    private int quantity;
    private double price;
    private Side side;
    private Status Status;

    @ManyToOne(targetEntity = User.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, name = "userId")
    private User user;

    @ManyToOne(targetEntity = Portfolio.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "portfolioId", nullable = false)
    private Portfolio portfolio;


}

