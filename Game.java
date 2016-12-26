package swproject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Game {
	int GameD;
	/*static*/ String GameName;
	int NumOfLevel;
	float Rate;
	String Category;
	String Owner;
	int NumberOfQuestion;
	Map<String, String> QuestionAndAnswer = new HashMap<String, String>();
        //Questions q = new Questions();

    public String getGameName() {
        return GameName;
    }

    public void setGameName(String GameName) {
        this.GameName = GameName;
    }
          
       
        
	public Map<String, String> Load(String fileName) throws IOException {
            System.out.println("swproject.Game.Load()"+ fileName);
		FileReader f = new FileReader(fileName);
		BufferedReader buf = new BufferedReader(f);
		String Question = buf.readLine();
		String Answer = buf.readLine();
		QuestionAndAnswer.put(Question, Answer);
		while (Answer != null) {
			Question = buf.readLine();
			if (Question == null) {
				break;
			}
			// System.out.println(Question);
			Answer = buf.readLine();
			QuestionAndAnswer.put(Question, Answer);
		}

		// System.out.println("MAp = " +
		// QuestionAndAnswer.keySet().toArray()[0]);

		return QuestionAndAnswer;

	}

	public int PlayGame(Map<String, String> Data) {
		int Score = 0;
		System.out.println(" <<       Game Started      >>   ");
		
		System.out.println(" To Exit Press X  ");
		int count =1;
		
		for (int i = 0; i <Data.size() ; i++) {
			String Question = (String) Data.keySet().toArray()[i];
			System.out.println("Q" +(i+1)+" :"+Question);
			System.out.println("Answer:");
			Scanner input = new Scanner(System.in);
			String Answer = input.nextLine();
			if (Data.get(Question).equals(Answer)) {
				count++;
				System.out.println(" <<  Correct Answer  >> ");
				System.out.println("----------------------");
				Score++;

			} else {
				if (Answer.equals("x" ) || Answer.equals("X" )) {
					System.out.println("<<     Game Ended     >> ");
					break;
				}

				System.out.println("-- Wrong Answer --");

				System.out.println("Correct Answer is  :" + Data.values().toArray()[i]);

			}
			if(count==5)
			{
				
				System.out.println("===== Next Level =====");
				System.out.println();
				System.out.println("To continue press 1"+"       "+"To Exit press 2");
				int choice =input.nextInt();
				if(choice==1)
				{
					i=count-1;
					
				}
				else{
					break;
				}
			}
			if (i==Data.size())
			{
				i=0;
			}

		}

		return Score;

	}

//	public void Update_Score(int count)// count get from function play game
//	{
//		int FinalScore = 0; // get/read score from file; //
//		FinalScore += count;
//		// overwrite the score with FinalScore
//	}

	public  void writeinfile(String z) throws IOException {

		FileWriter s = new FileWriter(this.getGameName()+ ".txt", true);
		BufferedWriter out = new BufferedWriter(s);
		out.write(z.toString());
		out.newLine();

		out.close();
	}

    public void Add_Game() throws IOException 
    {
        System.out.println("Enter Game Name");
        Scanner input = new Scanner(System.in);
        GameName = input.next();
        System.out.println("Enter Number of Levels every level contain 5 Question ");
        int num = input.nextInt();

        NumberOfQuestion = num * 5;
        String Question, Answer = null;
        input.nextLine();
        int count = 0;
        for (int i = 1; i <= NumberOfQuestion; i++) {

            System.out.println("Enter Question :");
            Question = input.nextLine();
            writeinfile(Question);

            System.out.println("Enter Answer:");
            Answer = input.nextLine();
            writeinfile(Answer);
            count++;

            if (count == 5) {

                System.out.println(" Next Level ");

            }

        } 
        
        System.out.println(GameName + " is Created ");
        GamesDB();

    }
    
    
     public void GamesDB() throws IOException
     {
		FileWriter s = new FileWriter("GamesDB.txt",true);
		BufferedWriter out = new BufferedWriter(s);
		out.write(GameName);
		out.newLine();
		out.close();
     }

}
