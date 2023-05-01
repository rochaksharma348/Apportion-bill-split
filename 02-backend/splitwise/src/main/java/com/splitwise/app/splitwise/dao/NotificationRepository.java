package com.splitwise.app.splitwise.dao;

import com.splitwise.app.splitwise.entity.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "notifications", path = "notifications")
public interface NotificationRepository extends JpaRepository<Notification, Long> {
}
