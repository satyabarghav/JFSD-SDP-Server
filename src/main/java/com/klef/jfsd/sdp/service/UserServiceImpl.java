package com.klef.jfsd.sdp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.klef.jfsd.sdp.DTO.UserDetailsResponse;
import com.klef.jfsd.sdp.models.Admin;
import com.klef.jfsd.sdp.models.Role;
import com.klef.jfsd.sdp.models.Student;
import com.klef.jfsd.sdp.models.SuperAdmin; // Import your SuperAdmin model
import com.klef.jfsd.sdp.models.Teacher;
import com.klef.jfsd.sdp.models.User;
import com.klef.jfsd.sdp.models.UserPrincipal;
import com.klef.jfsd.sdp.repository.AdminRepository;
import com.klef.jfsd.sdp.repository.StudentRepository;
import com.klef.jfsd.sdp.repository.SuperAdminRepository; // Import your SuperAdminRepository
import com.klef.jfsd.sdp.repository.TeacherRepository;
import com.klef.jfsd.sdp.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private AdminRepository adminRepository;

	@Autowired
	private StudentRepository studentRepository;

	@Autowired
	private TeacherRepository teacherRepository;

	@Autowired
	private SuperAdminRepository superAdminRepository; // Inject the SuperAdmin repository

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	@Autowired
	private JWTService jwtService;

	@Override
	public User authenticateUser(String usernameOrEmail, String password) {
		User user = userRepository.findByUsernameOrEmail(usernameOrEmail, usernameOrEmail);

		// If user is found, check if the password matches
		if (user != null && passwordEncoder.matches(password, user.getPassword())) {
			return user; // Authentication successful
		}
		return null;
	}

	@Override
	public UserDetailsResponse getUserDetails(User user) {

		if (user == null) {
			return null; // or throw an exception
		}

		UserDetailsResponse userDetailsResponse = null;

		// Dynamically retrieve user details based on the role
		switch (user.getRole()) {
		case ADMIN:
			Admin admin = adminRepository.findByUsername(user.getUsername());
			userDetailsResponse = new UserDetailsResponse(admin.getFname(), admin.getLname(), admin.getUsername(),
					admin.getEmail(), "ADMIN",admin.getId());
			break;

		case STUDENT:
			Student student = studentRepository.findByUsername(user.getUsername());
			if (student != null) {
				userDetailsResponse = new UserDetailsResponse(student.getFname(), student.getLname(),
						student.getUsername(),student.getEmail(),  "STUDENT",student.isFirstLogin(),student.getId(),student.getRegNum());
			}
			break;

		case TEACHER:
			Teacher teacher = teacherRepository.findByUsername(user.getUsername());
			if (teacher != null) {
				userDetailsResponse = new UserDetailsResponse(teacher.getFname(), teacher.getLname(),
						 teacher.getUsername(), teacher.getEmail(),"TEACHER",teacher.getId());
			}
			break;

		case SUPERADMIN: // Add case for SUPERADMIN
			SuperAdmin superAdmin = superAdminRepository.findByUsername(user.getUsername());
			if (superAdmin != null) {
				userDetailsResponse = new UserDetailsResponse(superAdmin.getFname(), superAdmin.getLname(),
						superAdmin.getUsername(), superAdmin.getEmail(), "SUPERADMIN",superAdmin.getId());
			}
			break;

		default:
			return null; // or throw an exception for unknown role
		}

		// Generate a JWT token and set it in the response
		if (userDetailsResponse != null) {
			String token = jwtService.generateToken(userDetailsResponse);
			userDetailsResponse.setToken(token);
		}

		return userDetailsResponse;
	}

	@Override
	public UserDetails getByUsername(String username) throws Exception {
		User user = userRepository.findByUsername(username);

		if (user != null) {
			return new UserPrincipal(user);
		} else {
			throw new Exception("user not found");
		}
	}
}
