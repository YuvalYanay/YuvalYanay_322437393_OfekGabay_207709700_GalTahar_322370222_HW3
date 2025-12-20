package exe3KnockKnock;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class RuppinRegistrationProtocol {

private static final int WAITING = 0;
private static final int ASK_REGISTER = 1;
private static final int ASK_USERNAME = 2;
private static final int ASK_PASSWORD = 3;
private static final int ASK_STAT = 4;
private static final int ASK_YEARS = 5;
private static final int LOGIN_USERNAME = 6;
private static final int LOGIN_PASSWORD = 7;
private static final int ASK_UPDATE = 8;
private static final int UPDATE_USERNAME = 9;
private static final int UPDATE_USERNAME_INPUT = 10;
private static final int UPDATE_PASSWORD = 11;
private static final int UPDATE_PASSWORD_INPUT = 12;
private static final int UPDATE_YEARS = 13;
private static final int UPDATE_YEARS_INPUT = 14;


private int state = WAITING;

private Client temp;
private List<Client> clients;


public RuppinRegistrationProtocol(List<Client> clients){

    this.clients = clients;

}

public String inputProcessing(String theInput){

    String theOutput = "";

    switch (state){

        case WAITING:

            theOutput = "Do you want to register? (yes/no)";
            state = ASK_REGISTER;
            break;

        case ASK_REGISTER:

            if (theInput.equalsIgnoreCase("yes")){
                theOutput = "Enter a username:";
                state = ASK_USERNAME;
            }
            else {
                theOutput = "Username:";
                state = LOGIN_USERNAME;
            }

            break;


        case LOGIN_USERNAME:

            if (isExisted(theInput)){ //Checking if the user exits

                for (Client c : clients){

                    if (c.getUsername().equals(theInput)){

                        temp = c; //Getting the user's details
                        break;

                    }
                }

                theOutput = "Password:";
                state = LOGIN_PASSWORD;

            }
            else {
                theOutput = "User not found. Username:";
            }
            break;

        case LOGIN_PASSWORD:

            if (temp.getPassword().equals(theInput)){
                theOutput = "Welcome back " + temp.getUsername()
                        + ". Last time you defined yourself as "
                        + temp.getAcademicStat() + " for " + temp.getYears() + " years. "
                        + "Do you want to update your information? (yes/no)";

                state = ASK_UPDATE;
            }
            else {
                theOutput = "Wrong password. Try again:";
            }
            break;


        case ASK_UPDATE:

            if (theInput.equalsIgnoreCase("yes")){

                theOutput = "Do you want to change your username? (yes/no):";
                state = UPDATE_USERNAME;

            }
            else {
                theOutput = "Thanks. Your information remains the same.";;
                state = WAITING;
            }
            break;

        case UPDATE_USERNAME:


                if (theInput.equals("yes")){

                    theOutput = "Enter new username:";
                    state = UPDATE_USERNAME_INPUT;

                }
                else {
                    theOutput = "Do you want to change your password? (yes/no)";
                    state = UPDATE_PASSWORD;
                }
            break;



        case UPDATE_USERNAME_INPUT:

            try{
                if (isExisted(theInput)){
                    theOutput = "Username already taken. Try another:";
                } else {
                    temp.setUsername(theInput);
                    theOutput = "Username updated successfully. Do you want to change your password? (yes/no)";
                    state = UPDATE_PASSWORD;
                }
            } catch (Exception e) {
                theOutput = "Username cannot be empty. Try again:";
            }

            break;


        case UPDATE_PASSWORD:

                if (theInput.equalsIgnoreCase("yes")) {

                    theOutput = "Enter new password:";
                    state = UPDATE_PASSWORD_INPUT;
                }
                else {
                    theOutput = "Do you want to update your years of study? (yes/no)";
                    state = UPDATE_YEARS;
                }
            break;



        case UPDATE_PASSWORD_INPUT:

            try{
                if (strongPass(theInput)){

                    temp.setPassword(theInput);
                    theOutput = "Password updated successfully. Do you want to update your years of study? (yes/no)";
                    state = UPDATE_YEARS;

                } else {
                    theOutput = "Weak password! (9+ chars, Upper, Lower, Digit). Try again:";
                }


            } catch (Exception e) {
            }
            break;





        case UPDATE_YEARS:

                if (theInput.equalsIgnoreCase("yes")){
                    theOutput = "Enter number of years:";
                    state = UPDATE_YEARS_INPUT;
                }
                else {
                    theOutput = "Thanks. Your information has been updated.";
                    state = WAITING;
                }
            break;


        case UPDATE_YEARS_INPUT:

            try{

                int years = Integer.parseInt(theInput);
                temp.setYears(years);
                theOutput = "Years of study updated successfully. Thanks. Your information has been updated.";
                state = WAITING;

            } catch (Exception e) {
                theOutput = "Years cannot be negative. Try again:";
            }
        break;




        case ASK_USERNAME:

            try{

                temp = new Client(theInput, "0", "", 0);

                if (isExisted(theInput)){

                    theOutput="Username is already taken. Try another one:";

                }
                else {

                    temp = new Client(theInput,"0","",0);
                    theOutput = "Checking name... Ok. Enter a strong password:";
                    state = ASK_PASSWORD;
                }
            } catch (Exception e) {
                theOutput = "Username cannot be empty. Try again:";
            }


            break;

        case ASK_PASSWORD:

            try{
                if (strongPass(theInput)){

                    temp.setPassword(theInput);
                    theOutput = "Password accepted. What is your status? (student/teacher/other)";
                    state = ASK_STAT;

                }
                else {
                    theOutput = "Weak password! (9+ chars, Upper, Lower, Digit). Try again:";
                }
            } catch (Exception e) {
                theOutput = "Password cannor be empty. Try again:";
            }


            break;

        case ASK_STAT:

            temp.setAcademicStat(theInput);
            theOutput = "How many years have you been at Ruppin?";
            state = ASK_YEARS;
            break;

        case ASK_YEARS:

            try{
                int years = Integer.parseInt(theInput);
                temp.setYears(years);
                clients.add(temp);
                if (clients.size() % 3 == 0) {
                    saveToCSV();
                }
                theOutput = "Registration completed.";
                state = WAITING;
            } catch (Exception e) {
                theOutput = "Please enter a valid number of years:";
            }

            break;


        default:

            theOutput = "Error occured. Starting over...";
            state = WAITING;


    }

    return theOutput;


}


public boolean isExisted(String username){ //Checks if the user already exits

    Client search = new Client(username,"0","",0);

    return clients.contains(search); //Going to the equals method for checking identical usernames
}


public boolean strongPass(String password){

    return password.length() >= 9 && password.matches(".*[A-Z].*") && password.matches(".*[a-z].*") && password.matches(".*\\d.*");

}


    private void saveToCSV() { //Function for saving data to csv
        String fileName = "clients_" + System.currentTimeMillis() + ".csv";
        try (PrintWriter writer = new PrintWriter(new File(fileName))) {
            for (Client c : clients) {
                writer.println(c.getUsername() + "," + c.getAcademicStat() + "," + c.getYears());
            }
        } catch (IOException e) {
            System.err.println("Error saving CSV: " + e.getMessage());
        }
    }


}
