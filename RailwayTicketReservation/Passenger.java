public class Passenger {
    int passengerId;
    static int id=1;
    String name,berthPreference,alloted;
    int age,number;
    Passenger(String name,int age,String berthPreference) {
        this.name=name;
        this.age=age;
        this.berthPreference=berthPreference;
        this.passengerId=id++;
        number=-1;
        alloted="";
    }
}