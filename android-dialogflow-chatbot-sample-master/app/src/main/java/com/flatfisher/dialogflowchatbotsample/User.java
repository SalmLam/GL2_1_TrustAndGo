package com.flatfisher.dialogflowchatbotsample;



import android.graphics.Bitmap;

import com.github.bassaer.chatmessageview.model.IChatUser;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class User implements IChatUser {
    private Integer id;
    private String name;
    private Bitmap icon;
    private String  email ,feilds,age , password, address;
    public User() {}

    public User(int id, String name, Bitmap icon) {
        this.id = id;
        this.name = name;
        this.icon = icon;
    }


    public User(int id, String name, Bitmap icon,String  email ,String feilds,String age,String password,String address) {
        this.id = id;
        this.name = name;
        this.icon = icon;
        this.email=email;
        this.feilds=feilds;
        this.age=age;
        this.password=password;
        this.address=address;
    }

    public String getFeilds() {
        return feilds;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setFeilds(String feilds) {
        this.feilds = feilds;
    }

    @NotNull
    @Override
    public String getId() {
        return this.id.toString();
    }

    @Nullable
    @Override
    public String getName() {
        return this.name;
    }

    @Nullable
    @Override
    public Bitmap getIcon() {
        return this.icon;
    }

    @Override
    public void setIcon(Bitmap icon) {
        this.icon = icon;
    }
}