package com.guru.springsecurity_learning.DTO;

import com.guru.springsecurity_learning.Enums.Role;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CustomerToAdminRequestDTO {

        @NotNull
        private Long customerId;

        @NotNull
        private Role role;

}
