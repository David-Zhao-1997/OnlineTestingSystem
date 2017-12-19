package beans;

import entity.StudentEntity;
import service.StudentService;
import util.FacesUtil;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;
import javax.inject.Named;
import javax.servlet.ServletRequest;
import java.io.IOException;
import java.io.Serializable;

import static java.lang.System.out;

@Named
@RequestScoped
public class Login_Signup {
    private String phoneno;
    private String password;
    private String email;
    private boolean remember;
    private String error1;
    private String error2;
    private String userinfo;
    private String login_null_error;
    private String signup_null_error;
    private String signup_success;

    public void login() {
        //若登陆时存在inputText为空
        if(phoneno.equals("") || password.equals("")){
            login_null_error = "Ensure every input not null, please enter it again";
        }else{
            StudentService stuService = new StudentService();
            boolean sign = stuService.login(phoneno, password);
            if (sign) {
                userinfo = phoneno;
                System.out.println(userinfo);
            } else {
                error1 = "Invalid PhoneNo or Password, check and enter it again";
                out.println("login failed");
            }
        }
    }

    public void signup() {
        //若注册时存在inputText为空
        if(phoneno.equals("") || email.equals("") || password.equals("")){
            signup_null_error = "Ensure every input not null, please enter it again";
        }else{
            StudentService stuService = new StudentService();
            StudentEntity student = new StudentEntity();
            student.setPhoneNum(phoneno);
            student.setEmail(email);
            student.setStuPwd(password);
            boolean sign = stuService.register(student);
            if (sign) {
                signup_success = "Sign up success";
                System.out.println(signup_success);
            } else {
                error2 = "Sign up failed #_# Check your format of phone number and email address, please enter it again";
                out.println("Signup failed");
            }
        }
    }

    public String getPhoneno() {
        return phoneno;
    }

    public void setPhoneno(String phoneno) {
        this.phoneno = phoneno;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isRemember() {
        return remember;
    }

    public void setRemember(boolean remember) {
        this.remember = remember;
    }

    public String getError1() {
        return error1;
    }

    public void setError1(String error1) {
        this.error1 = error1;
    }

    public String getError2() {
        return error2;
    }

    public void setError2(String error2) {
        this.error2 = error2;
    }

    public String getUserinfo() {
        return userinfo;
    }

    public void setUserinfo(String userinfo) {
        this.userinfo = userinfo;
    }

    public String getLogin_null_error() {
        return login_null_error;
    }

    public void setLogin_null_error(String login_null_error) {
        this.login_null_error = login_null_error;
    }

    public String getSignup_null_error() {
        return signup_null_error;
    }

    public void setSignup_null_error(String signup_null_error) {
        this.signup_null_error = signup_null_error;
    }

    public String getSignup_success() {
        return signup_success;
    }

    public void setSignup_success(String signup_success) {
        this.signup_success = signup_success;
    }
}