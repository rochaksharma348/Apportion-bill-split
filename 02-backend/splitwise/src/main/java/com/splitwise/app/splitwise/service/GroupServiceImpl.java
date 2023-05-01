package com.splitwise.app.splitwise.service;

import com.splitwise.app.splitwise.dao.GroupRepository;
import com.splitwise.app.splitwise.dao.UserRepository;
import com.splitwise.app.splitwise.entity.Group;
import com.splitwise.app.splitwise.entity.Notification;
import com.splitwise.app.splitwise.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class GroupServiceImpl implements GroupService {

    @Autowired
    private GroupRepository groupRepository;

    @Autowired
    private UserRepository userRepository;


    @Override
    @Transactional
    public void createGroup(List<Long> users, String name, String type) {
        Group group = new Group(name, type);

        for (long id : users) {
            User user = userRepository.getById(id);
            group.addUser(user);
            user.addNotification(new Notification("You are added into a new group: \"" + group.getName() + "\""));
        }

        groupRepository.save(group);
    }


}
