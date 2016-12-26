
package swproject;

import java.util.HashMap;

class Account 
{ 
   int  AccountID ;
   int  Age ;
   String UserName ;
   String PassWord ;
   String FName ;
   String LName ;
   String gender ;
   String address ;
   HashMap <String , String> Users_Pass = new HashMap(); // reconsruct mn file 

    public Account(int AccountID, int Age, String UserName, String PassWord, String FName, String LName, String gender, String address) {
        this.AccountID = AccountID;
        this.Age = Age;
        this.UserName = UserName;
        this.PassWord = PassWord;
        this.FName = FName;
        this.LName = LName;
        this.gender = gender;
        this.address = address;
    }

    Account() {}

    public int getAccountID() {
        return AccountID;
    }

    public void setAccountID(int AccountID) {
        this.AccountID = AccountID;
    }

    public int getAge() {
        return Age;
    }

    public void setAge(int Age) {
        this.Age = Age;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String UserName) {
        this.UserName = UserName;
    }

    public String getPassWord() {
        return PassWord;
    }

    public void setPassWord(String PassWord) {
        this.PassWord = PassWord;
    }

    public String getFName() {
        return FName;
    }

    public void setFName(String FName) {
        this.FName = FName;
    }

    public String getLName() {
        return LName;
    }

    public void setLName(String LName) {
        this.LName = LName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
  
    
}
