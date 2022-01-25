import java.util.*;
public class Main {
    public static void bookTicket(Passenger p) {
        TicketBooker booker=new TicketBooker();
        if(TicketBooker.availableWaitingList==0) {
            System.out.println("Sorry no tickets available");
            return;
        }
        if((p.berthPreference.equals("L") && TicketBooker.availableLowerBerth>0)||
            (p.berthPreference.equals("M") && TicketBooker.availableMiddleBerth>0)||
            (p.berthPreference.equals("U") && TicketBooker.availableUpperBerth>0)) {
                System.out.println("Preferred berth available");
                if(p.berthPreference.equals("L")) {
                    System.out.println("Lower berth given");
                    booker.bookTicket(p,TicketBooker.lowerBerthPositions.get(0),"L");
                    TicketBooker.lowerBerthPositions.remove(0);
                    TicketBooker.availableLowerBerth--;
                }
                else if(p.berthPreference.equals("M")) {
                    System.out.println("Middle berth given");
                    booker.bookTicket(p,TicketBooker.middleBerthPositions.get(0),"M");
                    TicketBooker.middleBerthPositions.remove(0);
                    TicketBooker.availableMiddleBerth--;
                }
                else {
                    System.out.println("Upper berth given");
                    booker.bookTicket(p,TicketBooker.upperBerthPositions.get(0),"U");
                    TicketBooker.upperBerthPositions.remove(0);
                    TicketBooker.availableUpperBerth--;
                }
        }
        else if(TicketBooker.availableLowerBerth>0) {
            System.out.println("Lower berth given");
            booker.bookTicket(p,TicketBooker.lowerBerthPositions.get(0),"L");
            TicketBooker.lowerBerthPositions.remove(0);
            TicketBooker.availableLowerBerth--;
        }
        else if(TicketBooker.availableMiddleBerth>0) {
            System.out.println("Middle berth given");
            booker.bookTicket(p,TicketBooker.middleBerthPositions.get(0),"M");
            TicketBooker.middleBerthPositions.remove(0);
            TicketBooker.availableMiddleBerth--;
        }
        else if(TicketBooker.availableUpperBerth>0) {
            System.out.println("Upper berth given");
            booker.bookTicket(p,TicketBooker.upperBerthPositions.get(0),"U");
            TicketBooker.upperBerthPositions.remove(0);
            TicketBooker.availableUpperBerth--;
        }
        else if(TicketBooker.availableRACTickets>0) {
            System.out.println("RAC available");
            booker.addToRac(p,TicketBooker.racPositions.get(0),"RAC");
        }
        else if(TicketBooker.availableWaitingList>0) {
            System.out.println("Waiting List available");
            booker.addToWaitingList(p,TicketBooker.waitingListPositions.get(0),"WL");
        }
    }
    public static void cancelTicket(int id) {
        TicketBooker booker=new TicketBooker();
        if(!booker.passengers.containsKey(id)) {
            System.out.println("Passenger Details Unknown");
        }
        else {
            booker.cancelTickets(id);
        }
    }
    public static void main(String[] args) {
        Scanner s=new Scanner(System.in);
        boolean loop=true;
        while(loop) {
            System.out.println("Railway Reservation Application");
            System.out.println("1.Book Ticket");
            System.out.println("2.Cancel Ticket");
            System.out.println("3.Available Tickets");
            System.out.println("4.Booked Tickets");
            System.out.println("5.Exit");
            int choice=s.nextInt();
            switch(choice) {
                case 1: {
                    System.out.println("Enter passenger name");
                    String name=s.next();
                    System.out.println("Enter passenger age");
                    int age=s.nextInt();
                    System.out.println("Enter passenger's berth preference");
                    String berthPreference=s.next();
                    Passenger p=new Passenger(name,age,berthPreference);
                    bookTicket(p);
                }
                break;
                case 2: {
                    System.out.println("Enter id to cancel ticket");
                    int id=s.nextInt();
                    cancelTicket(id);
                }
                break;
                case 3: {
                    TicketBooker booker=new TicketBooker();
                    booker.printAvailableTickets();
                }
                break;
                case 4: {
                    TicketBooker booker=new TicketBooker();
                    booker.printBookedTickets();
                }
                break;
                case 5: {
                    System.out.println("Thank you for using our Application");
                    loop=false;
                }
            }
        }
        s.close();
    }
}