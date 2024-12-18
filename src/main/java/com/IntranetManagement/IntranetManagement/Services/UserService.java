package com.IntranetManagement.IntranetManagement.Services;
import com.IntranetManagement.IntranetManagement.Entities.Department;
import com.IntranetManagement.IntranetManagement.Entities.User;
import com.IntranetManagement.IntranetManagement.dtos.RegisterUserDto;
import com.IntranetManagement.IntranetManagement.repositories.DepartmentRepository;
import com.IntranetManagement.IntranetManagement.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

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
}
