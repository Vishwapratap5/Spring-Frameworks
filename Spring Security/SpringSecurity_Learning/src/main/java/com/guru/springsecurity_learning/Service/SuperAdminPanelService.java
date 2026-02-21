package com.guru.springsecurity_learning.Service;

import com.guru.springsecurity_learning.Enums.Role;

public interface SuperAdminPanelService {
    void assignRole(Long customerId, Role role);

    void deleteRole(Long customerId, Role role);
}
