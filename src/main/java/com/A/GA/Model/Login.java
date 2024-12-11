package com.A.GA.Model;

public class Login {
    private int idUser;
    private String tKUser;
    private String Passwork;
    private String role ;

    public Login(int idUser, String tKUser, String passwork, String role) {
        this.idUser = idUser;
        this.tKUser = tKUser;
        Passwork = passwork;
        this.role = role;
    }

    public Login(String tKUser, String passwork, String role) {
        this.tKUser = tKUser;
        Passwork = passwork;
        this.role = role;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public String getTKUser() {
        return tKUser;
    }

    public void setTKUser(String TKUser) {
        this.tKUser = TKUser;
    }

    public String getPasswork() {
        return Passwork;
    }

    public void setPasswork(String passwork) {
        Passwork = passwork;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
