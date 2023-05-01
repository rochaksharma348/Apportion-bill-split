package com.splitwise.app.splitwise.dao;

import com.splitwise.app.splitwise.entity.Group;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "groups", path = "groups")
public interface GroupRepository extends JpaRepository<Group,Long> {

}
