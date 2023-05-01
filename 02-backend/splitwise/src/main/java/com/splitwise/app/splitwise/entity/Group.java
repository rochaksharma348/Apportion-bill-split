package com.splitwise.app.splitwise.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "group_table")
public class Group {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "name")
    private String name;

    @Column(name = "type")
    private String type;

    @Column(name = "date_created")
    @CreationTimestamp
    private LocalDateTime dateCreated;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "group")
    private Set<Expense> expenses;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "group")
    private Set<Payment> payments;

    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.DETACH, CascadeType.MERGE,
            CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinTable(name = "user_group",
            joinColumns = @JoinColumn(name = "group_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id"))
    private Set<User> users;

    // constructor

    public Group(String name, String type) {
        this.name = name;
        this.type = type;
    }

    public Group(){

    }

    public void addUser(User user) {
        if (user != null) {
            if (users == null) {
                this.users = new HashSet<>();
            }
            user.addGroup(this);
        }
    }

    public void addExpense(Expense expense) {
        if (expense != null) {
            if (this.expenses == null) {
                this.expenses = new HashSet<>();
            }
            this.expenses.add(expense);
            expense.setGroup(this);
        }
    }

}












