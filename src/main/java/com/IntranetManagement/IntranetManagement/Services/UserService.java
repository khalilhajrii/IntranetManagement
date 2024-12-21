package com.IntranetManagement.IntranetManagement.Services;
import com.IntranetManagement.IntranetManagement.Entities.Department;
import com.IntranetManagement.IntranetManagement.Entities.Document;
import com.IntranetManagement.IntranetManagement.Entities.User;
import com.IntranetManagement.IntranetManagement.dtos.RegisterUserDto;
import com.IntranetManagement.IntranetManagement.repositories.DepartmentRepository;
import com.IntranetManagement.IntranetManagement.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private  UserRepository userRepository;
    @Autowired
    private DepartmentRepository departmentRepository;
    @Autowired
    private  PasswordEncoder passwordEncoder;

    public User saveUser(RegisterUserDto input) {
        Department department = departmentRepository.findById(input.getDepartementId())
                .orElseThrow(() -> new RuntimeException("Department not found with ID: " + input.getDepartementId()));
        User user = new User()
                .setFullName(input.getFullName())
                .setEmail(input.getEmail())
                .setPassword(passwordEncoder.encode(input.getPassword()))
                .setIsAdmin(input.getIsAdmin())
                .setDepartment(department);
        return userRepository.save(user);
    }

    public List<User> GetAllUsers(){
        try {
            return userRepository.findAll();
        }
        catch (Exception ex){
            System.out.println(ex.getMessage());
        }
        return new ArrayList<>();
    }

    public void deleteUser(Long UserId){
        try {
             userRepository.deleteById(UserId);
        }catch (Exception ex){
            System.out.println(ex.getMessage());
        }
    }

    public User updateUser(Long userId, RegisterUserDto input) {
        User existingUser = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with ID: " + userId));

        Department department = departmentRepository.findById(input.getDepartementId())
                .orElseThrow(() -> new RuntimeException("Department not found with ID: " + input.getDepartementId()));

        existingUser.setFullName(input.getFullName())
                .setEmail(input.getEmail());

        if (input.getPassword() != null && !input.getPassword().isEmpty()) {
            existingUser.setPassword(passwordEncoder.encode(input.getPassword()));
        }

        existingUser.setIsAdmin(input.getIsAdmin())
                .setDepartment(department);

        return userRepository.save(existingUser);
    }


    public Optional<User> getuserById(Long userId) {
        try{
            return userRepository.findById(userId);
        }catch (Exception ex){
            System.out.println(ex.getMessage());
        }
        return Optional.of(new User());
    }
}
