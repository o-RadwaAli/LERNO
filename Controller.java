
package swproject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.HashMap;
import java.util.Scanner;

public class Controller 
{
    
    Scanner sc = new Scanner (System.in);
    ArrayList<StudentAccount> StudentsData = new ArrayList<>();
    ArrayList<String> GameNames = new ArrayList<>();
    
   
    void ReconstructData () throws FileNotFoundException, IOException // done GUI
    {
        FileReader fr = new FileReader("StudentsData.txt");
        BufferedReader buff = new BufferedReader(fr);
        String Line="";
       
        while((Line =buff.readLine()) != null) 
        {   
           StudentAccount cur = new StudentAccount();
           String [] attributes = Line.split(" ");
        
           
           cur.setFName(attributes[0]);
           cur.setLName(attributes[1]);
           cur.setEducationLevel(attributes[2]);
           cur.setGender(attributes[3]);
           cur.setAge(Integer.parseInt(attributes[4]));
           cur.setAddress(attributes[5]);
           cur.setEmail(attributes[6]);
           cur.setScore(Integer.parseInt(attributes[7]));
           cur.setUserName(attributes[8]);
           cur.setPassWord(attributes[9]);
          
           StudentsData.add(cur);
           
        }
        
        
    }
    
    void Login() throws IOException // done GUI
    {  
        
         System.out.println("1- Student");
         System.out.println("2- Teacher");
         
         int choice = sc.nextInt();
         
        if (choice == 1) // student
        {
            while (true)
            {
            StudentAccount user = new StudentAccount();
            System.out.println("Enter Username : ");
            user.setUserName( sc.next() ); // klma mn 8er space laz2a f b3dha
            System.out.println("Enter Password : ");
            user.setPassWord(sc.next());
            
            if (user.CheckIdentity())
            {   
                System.out.println("WelCome To OuR SysTem ^_^ !! ");
                ShowStudentOptions(user); // play game
                break;
            }
            
            else 
            {
                System.out.println("Username OR Password is not valid , Please Try again .. ");
            }
            
          }
            
        }
        
        
        else if (choice == 2) // student
        {
            while (true)
            {
            TeacherAccount user = new TeacherAccount();
            System.out.println("Enter Username : ");
            user.setUserName( sc.next() ); // klma mn 8er space laz2a f b3dha
            System.out.println("Enter Password : ");
            user.setPassWord(sc.next());
            
            if (user.CheckIdentity())
            {   
                System.out.println("WelCome To OuR SysTem ^_^ !! ");
                ShowTeacherOptions(user); // Add game OR test Game
                break;
            }
            
            else 
            {
                System.out.println("Username OR Password is not valid , Please Try again .. ");
            }
            
          }
            
        }
        
    }
   
    void SignUp() throws IOException // done GUI
    {
         System.out.println("1- As Student");
         System.out.println("2- As Teacher");
         
         int choice = sc.nextInt();
         
         if (choice == 1) // student
         {
             StudentAccount user = new StudentAccount();
             System.out.println("First Name: ");
             user.setFName(sc.next());
             System.out.println("Last Name: ");
             user.setLName(sc.next());
             System.out.println("Education Level: ");
             user.setEducationLevel(sc.next());
             System.out.println("Gender: ");
             user.setGender(sc.next());
             System.out.println("Age: ");
             user.setAge(sc.nextInt());
             System.out.println("Address: ");
             user.setAddress(sc.next());
             
             while (true){
                 
             System.out.println("Email: ");
             user.setEmail(sc.next());
             
             if (user.Verify())
             {
                 break;
             }
             
             else 
             {
                 System.out.println("Email is Not Valid");
             }
                 
             }
             
             while (true){
             System.out.println("Username:");
             user.setUserName(sc.next());
             if (user.IsUnique())
             {
                 break;
             }
             
             else 
             {
                 System.out.println("This Username is Taken , Please Try again");
             }
                 
             }
             
             System.out.println("Password: ");
             user.setPassWord(sc.next());
             
             user.CreatAccount();
             System.out.println("Your Account is Created ^_^ ");
             System.out.println("You are Logged in  ");
             ShowStudentOptions(user);
             
         }
         
         else if (choice == 2) // teacher
         {
             TeacherAccount user = new TeacherAccount();
             System.out.println("First Name: ");
             user.setFName(sc.next());
             System.out.println("Last Name: ");
             user.setLName(sc.next());
             System.out.println("Specialization: ");
             user.setSpecialization(sc.next());
             System.out.println("Gender: ");
             user.setGender(sc.next());
             System.out.println("Age: ");
             user.setAge(sc.nextInt());
             System.out.println("Address: ");
             user.setAddress(sc.next());
             
             while (true){
             System.out.println("Educational Email: ");
             user.setEducationEmail(sc.next());
             if (user.Verify())
             {
                 break;
             }
             
             else 
             {
                 System.out.println("Educational Email is Required ");
             }
                 
             }
             
             while (true){
             System.out.println("Username:");
             user.setUserName(sc.next());
             if (user.IsUnique())
             {
                 break;
             }
             
             else 
             {
                 System.out.println("This Username is Taken , Please Try again");
             }
                 
             }
             
             System.out.println("Password: ");
             user.setPassWord(sc.next());
             
             user.CreatAccount();
             System.out.println(" Your Account is Created ^_^ ");
             System.out.println("You are Logged in  ");
             ShowTeacherOptions(user);
             
             
         }
         
        
    }
    
    void Registeration () throws IOException
    {   
        int option ;
        System.out.println("1- Login");
        System.out.println("2- Sign Up");
        option = sc.nextInt();
        
        if (option ==1)
        {
          Login() ; 
        }
        else if (option ==2)
        {
          SignUp();  
        }
        
        
    }
    
    void ShowStudentOptions(StudentAccount user) throws IOException // done
    {
            Game game = new Game(); // play
            ReconstructData();
            int indx = ReconstructGamesNames();
            int score = game.PlayGame(game.Load(GameNames.get(indx-1)+".txt")); // true and false 
            System.out.println("Your Score : " + score);
            UpdateScore(score , user);
    
    }
    
    int ReconstructGamesNames() throws FileNotFoundException, IOException
    {
        FileReader fr = new FileReader("GamesDB.txt");
        BufferedReader buff = new BufferedReader(fr);
        String Line="";
        while((Line =buff.readLine()) != null) 
        {  
           GameNames.add(Line);
        }
        
        System.out.println("Choose Game To Play : ");
        for (int i=0 ; i<GameNames.size() ; i++)
        {
            System.out.println((i+1) + " - " + GameNames.get(i));
        }
        
        int choice = sc.nextInt();
        return choice ;
    }
    
    void ShowTeacherOptions(TeacherAccount user) throws IOException
    {
        System.out.println("Follow next instructions to create game ");
        Game game = new Game();
        game.Add_Game();
    }
    
    int UpdateScore (int bonus , StudentAccount user) throws IOException // done
    {
        int NewScore = 0 ;
        
        for (int i=0 ; i<StudentsData.size() ; i++)
        {
            if (user.getUserName().equals(StudentsData.get(i).getUserName()))
            {
                int PrevScore = StudentsData.get(i).getScore();
                NewScore = PrevScore + bonus ;
                StudentsData.get(i).setScore(NewScore);
                break;
                
            }
        }
        
        UpdateData();
        
        return NewScore ;
        
    }
    
    void UpdateData() throws IOException // done
    {
		
                FileWriter s = new FileWriter("StudentsData.txt");
		BufferedWriter out = new BufferedWriter(s);
		
                for (int i=0 ; i<StudentsData.size() ; i++)
                {
                    String data = StudentsData.get(i).getFName() + " " + StudentsData.get(i).getLName()+ 
                            " " + StudentsData.get(i).getEducationLevel() + " " + StudentsData.get(i).getGender()+ " "
                                + StudentsData.get(i).getAge()+ " " + StudentsData.get(i).getAddress()+ " " + StudentsData.get(i).getEmail()+ " "+
                                + StudentsData.get(i).getScore()+ " " + StudentsData.get(i).getUserName()+ " " + StudentsData.get(i).getPassWord() ;
                
                    out.write(data);
                    out.newLine();
                }
                
		out.close();
	
    }
    
    
    
    
}
