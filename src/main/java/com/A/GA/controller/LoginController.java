package com.A.GA.controller;


import com.A.GA.Model.Login;
import com.A.GA.Repository.LoginRepository;
import com.A.GA.Service.LoginService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class LoginController {
    @Autowired
    private LoginService loginService;

//    public LoginController(LoginService loginService) {
//        this.loginService = loginService;
//    }

    //    đây là trang login home
    @GetMapping("/login/home")
    public String homeLogin(Model model) {
        model.addAttribute("Image", loginService.image());
        return "Login";
    }

    // điều hướng của trang Login
    // Model
    @PostMapping("/login/home")
    @ResponseBody
        public ResponseEntity<String> login(HttpSession session,
                                            @RequestParam("TKUser") String TKUser,
                                            @RequestParam("PassWork") String passWork,
                                            @RequestParam("role") String role) {
        Login checkLogin = loginService.CheckLogin(TKUser, passWork, role);

        if (checkLogin != null) {
            if (role.equals("1") && checkLogin.getRole().equals("1")) {
                session.setAttribute("idUser", checkLogin.getIdUser());
                return ResponseEntity.ok("user");  // Trả về "user" nếu vai trò là "user"
            }
            if (role.equals("2") && checkLogin.getRole().equals("2")) {
                session.setAttribute("idUser", checkLogin.getIdUser());
//                session.setAttribute("role", checkLogin.getRole());
                return ResponseEntity.ok("admin");  // Trả về "admin" nếu vai trò là "admin"
            }
        }

        // Nếu thông tin đăng nhập sai
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Sai tên đăng nhập hoặc mật khẩu!");
    }
    @GetMapping("/register")
    public String register(Model model) {
        model.addAttribute("Image", loginService.image());
        return "Register";
    }

    @PostMapping("/register")
    @ResponseBody
    public ResponseEntity<String> register(@RequestParam("username") String username,
                                           @RequestParam("password") String password,
                                           @RequestParam("role") String role) {
        // Kiểm tra trùng username
            for (Login user : LoginRepository.tableLogin) {
            if (user.getTKUser().equals(username)) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Username already exists!");
            }
        }
        // Thêm người dùng mới vào danh sách
        LoginRepository.tableLogin.add(new Login(username, password, role));
        return ResponseEntity.ok("Register successful!");
    }

}
