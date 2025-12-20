package exe3KnockKnock;

public class Client {

private String username;
private String password;
private String academicStat;
private int years;

//Constructor
public Client(String username, String password, String academicStat, int years){

    this.username = username;
    this.password = password;
    setAcademicStat(academicStat);
    setYears(years);

}


//Setters
public void setUsername(String username){ this.username = username; }
public void setPassword(String password){this.password = password; }
public void setAcademicStat(String academicStat){

    if (academicStat.equalsIgnoreCase("student")){
        this.academicStat = "student";
    } else if (academicStat.equalsIgnoreCase("teacher")) {
        this.academicStat = "teacher";
    } else {
        this.academicStat = "other";
    }

}
public void setYears(int years) throws IllegalArgumentException{

    if(years < 1){
        throw new IllegalArgumentException("Cannot have negative year.");
    }

    this.years = years;
}



//Getters
    public String getUsername(){return username;}
    public String getPassword(){return password;}
    public String getAcademicStat(){return academicStat;}
    public int getYears(){return years;}



    @Override
    public boolean equals(Object o){

    if (this == o) return  true;
    if (o == null || getClass() != o.getClass()) return false;

    Client c = (Client) o;

    return username.equalsIgnoreCase(c.username);

    }

    @Override
    public int hashCode(){return username.toLowerCase().hashCode();}

}
