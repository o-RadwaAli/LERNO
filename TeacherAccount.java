
package swproject;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Formatter;

public class TeacherAccount extends Account{
   
    String Specialization ;
    String EducationEmail ;
    Formatter out ; // to write on file 
    
    public TeacherAccount () throws IOException{
    
        ReconstructUsers();
    
    };
    
    public TeacherAccount(String Specialization, String EducationEmail) {
        this.Specialization = Specialization;
        this.EducationEmail = EducationEmail;
    }

    public String getSpecialization() {
        return Specialization;
    }

    public void setSpecialization(String Specialization) {
        this.Specialization = Specialization;
    }

    public String getEducationEmail() {
        return EducationEmail;
    }

    public void setEducationEmail(String EducationEmail) {
        this.EducationEmail = EducationEmail;
    }
    
     
    void ReconstructUsers () throws FileNotFoundException, IOException
    {  
        FileReader fr = new FileReader("TeachersUsers.txt");
        BufferedReader buff = new BufferedReader(fr);
        String Line="";
        while((Line =buff.readLine()) != null) 
        {
           String [] elements= Line.split(" ");
           Users_Pass.put(elements[0],elements[1]);
        }
        
      //  System.out.println(Users_Pass);
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
        out.flush(); // 34an yktb 3al file

    }
    
    public void CreatAccount ()
    { 
        OpenFile("TeachersData.txt");
        String Data = this.FName + " " + this.LName + " " + this.Specialization + " " + this.gender 
                      + " " + this.Age + " " + this.address + " " + this.EducationEmail + " "  + this.UserName + " "+  this.PassWord ;
        
        write(Data);
        CloseFile();
        
        // write username and passwords only 
        OpenFile("TeachersUsers.txt");
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
        return this.EducationEmail.contains(".edu.eg") && this.EducationEmail.contains("@");
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
