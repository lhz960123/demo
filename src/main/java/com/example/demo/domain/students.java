package com.example.demo.domain;




public class students {
    private Integer Id;
    private String Name;
    private String Password;
    private Integer rights;

    public Integer getId() {
        return Id;
    }

    public void setId(Integer id) {
        Id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public Integer getRight() {
        return rights;
    }

    public void setRight(Integer right) {
        this.rights = right;
    }
}
