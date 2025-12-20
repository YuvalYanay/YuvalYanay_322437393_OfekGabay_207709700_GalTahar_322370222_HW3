package exe3KnockKnock;

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
private static final int UPDATE_PASSWORD = 10;
private static final int UPDATE_YEARS = 11;

private int state = WAITING;


public String inputProcessing(String theInput){

    String theOutput;

    switch (state){

        case WAITING:

            theOutput = "Do you want to change your username? (yes/no)";
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

        case ASK_USERNAME:

            //if (isUs)





    }


}

}
