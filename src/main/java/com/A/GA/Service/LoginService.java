package com.A.GA.Service;

import com.A.GA.Model.Login;
import com.A.GA.Repository.LoginRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginService {
    @Autowired
    LoginRepository loginRepository;
    public Login CheckLogin(String TKUser, String password, String role ) {
        if (!LoginRepository.tableLogin.isEmpty()){
            return  loginRepository.checkLogin(TKUser,password,role);
        }
        return null;
    }

    public String image() {
        return loginRepository.loadImage();
    }
}
