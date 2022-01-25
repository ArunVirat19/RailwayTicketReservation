import java.util.*;
public class TicketBooker {
    static int availableLowerBerth=1;
    static int availableMiddleBerth=1;
    static int availableUpperBerth=1;
    static int availableRACTickets=1;
    static int availableWaitingList=1;

    static Queue<Integer> waitingList=new LinkedList<>();
    static Queue<Integer> racList=new LinkedList<>();
    static List<Integer> bookedTicketsList=new ArrayList<>();

    static List<Integer> lowerBerthPositions=new ArrayList<>(Arrays.asList(1));
    static List<Integer> middleBerthPositions=new ArrayList<>(Arrays.asList(1));
    static List<Integer> upperBerthPositions=new ArrayList<>(Arrays.asList(1));
    static List<Integer> racPositions=new ArrayList<>(Arrays.asList(1));
    static List<Integer> waitingListPositions=new ArrayList<>(Arrays.asList(1));

    static Map<Integer,Passenger> passengers=new HashMap<>();

    public void bookTicket(Passenger p,int berthInfo,String allotedSeat) {
        p.number=berthInfo;
        p.alloted=allotedSeat;
        passengers.put(p.passengerId,p);
        bookedTicketsList.add(p.passengerId);
        System.out.println("Your id is "+p.passengerId);
        System.out.println("Ticket Booked Successfully");
    }
    public void addToRac(Passenger p,int berthInfo,String allotedSeat) {
        p.number=berthInfo;
        p.alloted=allotedSeat;
        passengers.put(p.passengerId,p);
        racList.add(p.passengerId);
        availableRACTickets--;
        racPositions.remove(0);
        System.out.println("Your id is "+p.passengerId);
        System.out.println("Added to RAC Successfully"); 
    }
    public void addToWaitingList(Passenger p,int berthInfo,String allotedSeat) {
        p.number=berthInfo;
        p.alloted=allotedSeat;
        passengers.put(p.passengerId,p);
        waitingList.add(p.passengerId);
        availableWaitingList--;
        waitingListPositions.remove(0);
        System.out.println("Your id is "+p.passengerId);
        System.out.println("Added to Waiting List Successfully"); 
    }
    public void cancelTickets(int passengerId) {
        Passenger p=passengers.get(passengerId);
        passengers.remove(p.passengerId);
        bookedTicketsList.remove((Integer)passengerId);
        int positionBooked=p.number;
        System.out.println("Ticket Cancelled Successfully");
        if(p.alloted.equals("L")) {
            availableLowerBerth++;
            lowerBerthPositions.add(positionBooked);
        }
        else if(p.alloted.equals("M")) {
            availableMiddleBerth++;
            middleBerthPositions.add(positionBooked);
        }
        else if(p.alloted.equals("U")) {
            availableUpperBerth++;
            upperBerthPositions.add(positionBooked);
        }
        if(racList.size()>0) {
            Passenger passengerFromRAC=passengers.get(racList.poll());
            int positionRAC=passengerFromRAC.number;
            availableRACTickets++;
            racPositions.add(positionRAC);
            if(waitingList.size()>0) {
                Passenger passengerFromWaitingList=passengers.get(waitingList.poll());
                int positionWaitingList=passengerFromWaitingList.number;
                availableWaitingList++;
                waitingListPositions.add(positionWaitingList);
                waitingList.remove(passengerFromWaitingList.passengerId);
                passengerFromWaitingList.number=racPositions.get(0);
                passengerFromWaitingList.alloted="RAC";
                availableRACTickets--;
                racList.add(passengerFromWaitingList.passengerId);
                racPositions.remove(0);
            }
            Main.bookTicket(passengerFromRAC);
        } 
    }
    public void printAvailableTickets() {
        System.out.println("Available Lower Berths "+availableLowerBerth);
        System.out.println("Available Middle Berths "+availableMiddleBerth);
        System.out.println("Available Upper Berths "+availableUpperBerth);
        System.out.println("Available RAC tickets "+availableRACTickets);
        System.out.println("Available Waiting List tickets "+availableWaitingList);
        System.out.println("------------------------------------");
    }
    public void printBookedTickets() {
        if(passengers.size()==0) {
            System.out.println("No passenger details available");
            return;
        }
        for(Passenger p:passengers.values()) {
            System.out.println("Passenger ID "+p.passengerId);
            System.out.println("Passenger name "+p.name);
            System.out.println("Passenger age "+p.age);
            System.out.println("Status "+p.number+p.alloted);
            System.out.println("------------------------------------");
        }
    }
}