package AtheleData;
/**
 * The the start point of the package. Here the user is asked to either create database or load new database.
 */

/**
 * @author Peter Varun Dsouza
 *
 */


import java.io.*;
public class Start {

    public static void main(String[] args)throws IOException
    {
        Start User =new Start();
        File UserFile=User.welcome();
        User.menu(UserFile);

    }

    private void menu(File userFile) {
        System.out.println("Enter your choice:");
        System.out.println("1-Calculate TRIMP for your workout(In development)");
        System.out.println("2-Update your zones (Under development)");
        System.out.println("3-Plot Fitness and fatigue curves (under development");
        System.out.println("4-Enter a future workout in to the planner");
        System.out.println("5-Find out your work out for today");

    }

    private File welcome() throws NumberFormatException, IOException {
        System.out.println("For new User, enter 1\nFor Returning user, enter 2");
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int choice1=Integer.parseInt(br.readLine());
        if(choice1==1)
            return newUser();
        else return returningUser();
    }

    private File returningUser() throws IOException {
        System.out.println("Enter Athlete Name.");
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String name=br.readLine();
        String name2=name+"\\"+name+".txt";
        System.out.println(name2);
        if(checkDatabase(name2)==false)
        {
            System.out.println("Database not found. Try again");
            returningUser();
        }	else{
            File fr = new File(name2);
            return fr;
        }
        return null;
    }

    private File newUser() throws IOException {
        System.out.println("Enter Athlete Name. \nA file with the same details will be created and your data will be saved there");
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String name=br.readLine();
        String name2=name+"\\"+name+"txt";
        if(checkDatabase(name2)==false)
            return createDatabase(name);
        else
        {
            System.out.println("Athlete "+name+" already exists. Please try again");
            welcome();
        }
        return null;
    }

    private boolean checkDatabase(String name) {
        try{
            File fr = new File(name);
            if(fr.exists())
                return true;
            else return false;
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return false;
    }

    private File createDatabase(String name) {
        File database=null;
        try{
            new File(name).mkdir();
            String FileName = name+"\\"+name+".txt";
            File DataBase= new File(FileName);
            database=enterDataToFile(DataBase);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return database;
    }

    private File enterDataToFile(File dataBase) throws NumberFormatException, IOException {
        double[] data = inputData();
        try{
            BufferedWriter writer = new BufferedWriter(new FileWriter(dataBase));
            for(int i=0;i<6;i++)
            {
                writer.write(Double.toString(data[i]));
                writer.newLine();
            }
            writer.close();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return dataBase;
    }

    private double[] inputData() throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        double data[]=new double[6];
        System.out.println("Enter age in years");
        data[0]=Double.parseDouble(br.readLine());
        System.out.println("Enter weight in kg");
        data[1]=Double.parseDouble(br.readLine());
        System.out.println("Enter Resting Heart Rate");
        data[2]=Double.parseDouble(br.readLine());
        System.out.println("Enter Maximum Heart Rate for Cycling");
        data[3]=Double.parseDouble(br.readLine());
        System.out.println("Enter Maximum Heart Rate for Running");
        data[4]=Double.parseDouble(br.readLine());
        System.out.println("Enter Swimming Threshold");
        data[5]=Double.parseDouble(br.readLine());
        return data;
    }

}
