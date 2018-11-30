package com.cs4125.bikerentalapp.model.entity;

/**
 *
 * @author jamie
 */
public class UserCredential {

    private String username;
    private String password;
    private String studentCardId;
    private String email;
    private UserType userType;

    private UserCredential(){

    }

    //BUILDER PATTERN
    public static class Builder{

        private String username;
        private String password;
        private String studentCardId;
        private String email;
        private UserType userType;

        public Builder setUsername(String username){
            this.username = username;

            return this;
        }

        public Builder setPassword(String password){
            this.password = password;

            return this;
        }

        public Builder setEmail(String email) {
            this.email = email;

            return this;
        }

        public Builder setStudentCardId(String studentCardId) {
            this.studentCardId = studentCardId;

            return this;
        }

        public Builder setUserType(UserType userType) {
            this.userType = userType;

            return this;
        }

        public UserCredential build(){
            UserCredential userCredential = new UserCredential();

            userCredential.username = this.username;
            userCredential.password = this.password;
            userCredential.email = this.email;
            userCredential.studentCardId = this.studentCardId;
            userCredential.userType = this.userType;

            return userCredential;
        }
    }

    public String getUsername(){
        return this.username;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public String getStudentCardId() {
        return studentCardId;
    }

    public int getUserType() {
        return userType.getValue();
    }

    @Override
    public String toString(){
        return "Username: " + username
                + "Password: " + password
                + "Email: " + email
                + "StudentCardId: " + studentCardId
                + "UserType: " + userType.getValue();
    }
}