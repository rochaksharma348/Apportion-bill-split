package com.splitwise.app.splitwise.dao;

import com.splitwise.app.splitwise.entity.Expense;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel  = "expenses", path = "expenses")
public interface ExpenseRepository extends JpaRepository<Expense, Long> {

}
