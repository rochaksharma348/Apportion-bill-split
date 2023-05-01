package com.splitwise.app.splitwise.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Formula;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Formula( "CONCAT_WS( ' ', first_name, last_name ) " )
    private String fullName;

    @Column(name = "email")
    private String email;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    @JsonIgnore
    private String password;

    @Column(name = "phone_no")
    private String phoneNumber;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "addedBy")
    @JsonIgnore
    private Set<Expense> addedExpenses;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "paidBy")
    @JsonIgnore
    private Set<Expense> paidExpenses;

    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.DETACH, CascadeType.MERGE,
            CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinTable(name = "user_group",
                joinColumns = @JoinColumn(name = "user_id"),
                inverseJoinColumns = @JoinColumn(name = "group_id"))
    @JsonIgnore
    private Set<Group> groups;

//    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.DETACH, CascadeType.MERGE,
//            CascadeType.PERSIST, CascadeType.REFRESH})
//    @JoinTable(name = "user_expense",
//            joinColumns = @JoinColumn(name = "user_id"),
//            inverseJoinColumns = @JoinColumn(name = "expense_id"))
//    @JsonIgnore
//    private Set<Expense> expenses;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    @JsonIgnore
    private Set<UserExpense> userExpenses;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "payer")
    @JsonIgnore
    private Set<Payment> paidPayments;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "payee")
    @JsonIgnore
    private Set<Payment> receivedPayments;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    @JsonIgnore
    private Set<Notification> notifications;


    // constructor


    public void addPaidExpenses(Expense expense) {
        if (expense != null) {
            if (this.paidExpenses == null) {
                this.paidExpenses = new HashSet<>();
            }
            this.paidExpenses.add(expense);
            expense.setPaidBy(this);
        }
    }

    public void addAddedExpenses(Expense expense) {
        if (expense != null) {
            if (this.addedExpenses == null) {
                this.addedExpenses = new HashSet<>();
            }
            this.addedExpenses.add(expense);
            expense.setAddedBy(this);
        }
    }

    public void addGroup(Group group) {
        if (group != null) {
            if (this.groups == null) {
                this.groups = new HashSet<>();
            }
            this.groups.add(group);
        }
    }

    public void addUserExpense(UserExpense userExpense) {
        if (userExpense != null) {
            if (this.userExpenses == null) {
                this.userExpenses = new HashSet<>();
            }
            this.userExpenses.add(userExpense);
        }
    }

    public void addNotification(Notification notification) {
        if (notification != null) {
            if (this.notifications == null) {
                this.notifications = new HashSet<>();
            }
            this.notifications.add(notification);
            notification.setUser(this);
        }
    }
}















