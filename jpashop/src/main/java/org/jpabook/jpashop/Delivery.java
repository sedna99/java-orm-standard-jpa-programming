package org.jpabook.jpashop;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class Delivery extends BaseEntity{
    @Id @GeneratedValue
    private Long id;

    private String string;
    private String street;
    private String zipcode;
    private DeliveryStatus status;
}
