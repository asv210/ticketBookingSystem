package com.darkSProject.ticketBooking.services.validation;

import com.darkSProject.ticketBooking.entities.User;
import com.darkSProject.ticketBooking.exception.BadRequestException;
import com.darkSProject.ticketBooking.exception.ErrorCode;
import com.darkSProject.ticketBooking.exception.UserNotFoundException;
import com.darkSProject.ticketBooking.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserValidationServiceImpl implements UserValidationService{

    private final UserRepository userRepository;

    @Override
    public User getCurrentUser() {

        Authentication authentication= SecurityContextHolder
                .getContext()
                .getAuthentication();

        if (authentication == null
                ||
                !authentication.isAuthenticated()
        ){
            throw new BadRequestException(
                    "User not authenticated", ErrorCode.USER_NOT_AUTHENTICATED
            );
        }

        String email=authentication.getName();

        return userRepository.findByEmail(email)
                .orElseThrow(()->
                        new UserNotFoundException(
                            "User not found",
                                ErrorCode.USER_NOT_FOUND
                        )
                );
    }
}
