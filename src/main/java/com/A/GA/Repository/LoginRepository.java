package com.A.GA.Repository;

import com.A.GA.Model.Login;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class LoginRepository {
    public static final List<Login> tableLogin = new ArrayList<>();
    static {
        tableLogin.add(new Login("tuyen","123","1"));
        tableLogin.add(new Login("tuyen1","1234","2"));
        tableLogin.add(new Login("tuyen2","1236","1"));
    }
    public Login checkLogin(String TKUser, String password, String role) {
        for (Login login : tableLogin){
            if (login.getTKUser() != null && login.getPasswork() != null &&login.getRole() !=null){
                if ( login.getTKUser().equals(TKUser)  && login.getPasswork().equals(password) && login.getRole().equals(role)){
                    return login;
                }
            }

        }
        return null;
    }
}
