package com.splitwise.app.splitwise.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "expense")
public class Expense {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "name")
    private String name;

    @Column(name = "price")
    private BigDecimal price;

    @Column(name = "date_created")
    @CreationTimestamp
    private LocalDateTime dateCreated;

    @ManyToOne
    @JoinColumn(name = "paid_by", nullable = false)
    private User paidBy;

    @ManyToOne
    @JoinColumn(name = "added_by", nullable = false)
    private User addedBy;

    @ManyToOne
    @JoinColumn(name = "group_id", nullable = false)
    private Group group;

//    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.DETACH, CascadeType.MERGE,
//            CascadeType.PERSIST, CascadeType.REFRESH})
//    @JoinTable(name = "user_expense",
//            joinColumns = @JoinColumn(name = "expense_id"),
//            inverseJoinColumns = @JoinColumn(name = "user_id"))
//    private Set<User> users;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "expense")
    private Set<UserExpense> userExpenses;

    // constructor

    public Expense(String name, BigDecimal price) {
        this.name = name;
        this.price = price;
    }

    public Expense(){

    }

    public void addUser(User user, BigDecimal amount) {
        if (user != null) {
            if (userExpenses == null) {
                this.userExpenses = new HashSet<>();
            }
            UserExpense newUE = new UserExpense(user, this, amount);
            this.userExpenses.add(newUE);
            user.addUserExpense(newUE);
        }
    }

}




















