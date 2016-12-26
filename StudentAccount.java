
package swproject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.HashMap;
import java.util.Scanner;
//import static swproject.Game.GameName;

public class StudentAccount extends Account {
   
    String EducationLevel ;
    String Email ;
    int Score ;
    Formatter out ; 
    
    public StudentAccount() throws IOException
    {
     this.Score = 0 ;
     ReconstructUsers();      
    };
       
    void ReconstructUsers () throws FileNotFoundException, IOException
    {  
        FileReader fr = new FileReader("StudentsUsers.txt");
        BufferedReader buff = new BufferedReader(fr);
        String Line="";
        while((Line =buff.readLine()) != null) 
        {  
           if (Line != "")
           {
           String [] elements= Line.split(" ");
           Users_Pass.put(elements[0],elements[1]);  
           }
          
        }
        
        //System.out.println(Users_Pass);
    }
    

    public StudentAccount(String EducationLevel, String Email , int Score) {
        this.EducationLevel = EducationLevel;
        this.Email = Email;
        this.Score = Score ;
    }

    public int getScore() {
        return Score;
    }

    public void setScore(int Score) {
        this.Score = Score;
    }
   
    public String getEducationLevel() {
        return EducationLevel;
    }

    public void setEducationLevel(String EducationLevel) {
        this.EducationLevel = EducationLevel;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }
    
    public void OpenFile (String path)
    {
        try {
            FileWriter f = new FileWriter (path , true);
            out = new Formatter(f);
        } catch (Exception e) {
            System.out.println("Can not find File ");
        }

    }
    
    public void CloseFile() {
        out.close();
    }
    
    void write(String Data) {

        out.format("%s", Data);
        out.format("%n");
        out.flush(); 

    }
    
    public void CreatAccount ()
    {  
        // write kol el info
        OpenFile("StudentsData.txt");
        String Data = this.FName + " " + this.LName + " " + this.EducationLevel + " " + this.gender 
                      + " " + this.Age + " " + this.address + " "  + this.Email + " " + this.Score + " " + this.UserName + " "+ this.PassWord ;
        
        write(Data);
        CloseFile();
        
        // write username and passwords only 
        OpenFile("StudentsUsers.txt");
        write(this.UserName + " " + this.PassWord);
        CloseFile();
        
    }
    
    boolean IsUnique()
    {
       boolean unique = true ;
       for (int i=0 ; i<Users_Pass.size() ; i++)
       {
           if (this.getUserName().equals(Users_Pass.keySet().toArray()[i]))
           {
               unique = false ;
               break;
           }
       }
       
       return unique ;
    } 
    
     boolean Verify()
    {
        return this.Email.contains("@");
    }

    boolean CheckIdentity ()
    {
        
        boolean found = false ;
        for (int i=0 ; i<Users_Pass.size() ; i++)
        {
           if (this.getUserName().equals(Users_Pass.keySet().toArray()[i]) && this.getPassWord().equals(Users_Pass.values().toArray()[i]))
           {
               found = true ;
               break;
               
           }
        }
        
        return found ;
    }
   
}
