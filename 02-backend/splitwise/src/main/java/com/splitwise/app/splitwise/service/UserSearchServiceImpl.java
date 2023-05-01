package com.splitwise.app.splitwise.service;

import com.splitwise.app.splitwise.dao.UserRepository;
import com.splitwise.app.splitwise.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;

@Service
public class UserSearchServiceImpl implements UserSearchService{
    @Autowired
    private UserRepository userRepository;

    @Override
    @Transactional
    public List<User> searchUser(String query) {

        List<List<User>> list = new ArrayList<>();

//        List<User> l1 = userRepository.findByUsernameContainsIgnoreCase(query);
//        List<User> l2 = userRepository.findByEmailContainingIgnoreCase(query);
        List<User> l3 = userRepository.findByPhoneNumberIgnoreCase(query);
//        List<User> l4 = userRepository.findByFullNameContainingIgnoreCase(query);

//        if (l1 != null && !l1.isEmpty()) list.add(l1);
//        if (l2 != null && !l2.isEmpty()) list.add(l2);
        if (l3 != null && !l3.isEmpty()) list.add(l3);
//        if (l4 != null && !l4.isEmpty()) list.add(l4);

        List<User> resList = new ArrayList<>();
        Set<User> set = new HashSet<>();

        for (List<User> li : list) {
            for (User user : li) {
                if (!set.contains(user)) {
                    resList.add(user);
                    set.add(user);
                }
            }
        }
        return resList;
    }
}
