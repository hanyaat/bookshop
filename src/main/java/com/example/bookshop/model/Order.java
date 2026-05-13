package com.example.bookshop.model;

import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="orders")
public class Order {

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id;

@ManyToOne
@JoinColumn(name = "user_id")
private User user;

private String orderNumber;

private LocalDateTime orderedAt;

private int totalPrice;

@JsonManagedReference
@OneToMany(mappedBy="order",cascade=CascadeType.ALL)
private List<OrderItem> orderItems;

// ---getter/setter ---
public Long getId(){return id;}
public void setId(Long id){this.id=id;}

public User getUser(){return user;}
public void setUser(User user){this.user=user;}

public String getOrderNumber(){return orderNumber;}
public void setOrderNumber(String ordernumber){this.orderNumber=ordernumber;}

public LocalDateTime getOrderedAt(){return orderedAt;}
public void setOrderedAt(LocalDateTime orderedAt){this.orderedAt=orderedAt;}

public int getTotalPrice(){return totalPrice;}
public void setTotalPrice(int totalPrice){this.totalPrice=totalPrice;}

public List <OrderItem> getOrderItems(){return orderItems;}
public void setOrderItems(List<OrderItem> orderItems){this.orderItems=orderItems;}
}
