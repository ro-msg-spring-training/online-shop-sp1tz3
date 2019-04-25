package ro.msg.learning.shop.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import ro.msg.learning.shop.entity.Customer;
import ro.msg.learning.shop.entity.Location;
import ro.msg.learning.shop.entity.Orders;
import ro.msg.learning.shop.service.CustomerManagementService;
import ro.msg.learning.shop.service.LocationManagementService;
import ro.msg.learning.shop.service.OrderManagementService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Scanner;

@Component
@RequiredArgsConstructor
public class ConsoleController implements CommandLineRunner {
    private final Scanner scanner = new Scanner(System.in);
    private final CustomerManagementService customerManagementService;
    private final OrderManagementService orderManagementService;
    private final LocationManagementService locationManagementService;

    private boolean logged = false;
    private Customer loggedCustomer;

    @Override
    public void run(String... args) {
        System.out.println("Howdy!");
        System.out.println("Commands are: addcust, login, addorder, listcust, listorders, addloc, listloc and exit");
        boolean done = false;
        while (!done) {
            System.out.println("Enter command: ");
            String command = scanner.next().trim();
            try {
                done = handleCommand(command);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private boolean handleCommand(String command){
        switch(command){
            case "login":
                handleLogin();
                return false;
            case "logoff":
                handleLogoff();
                return false;
            case "listcust":
                handleListCustomers();
                return false;
            case "addcust":
                handleAddCustomer();
                return false;
            case "addorder":
                handleAddOrder();
                return false;
            case "listorders":
                handleListOrders();
                return false;
            case "addloc":
                handleAddLocation();
                return false;
            case "listloc":
                handleListLocations();
                return false;
            case "exit":
                return true;
            default:
                System.out.println("Try again!");
                return false;
        }
    }

    private void handleListCustomers(){
        customerManagementService.listCustomers().forEach(c->{
            System.out.println(c.toString());
        });
    }

    private void handleAddCustomer(){
        System.out.println("first name: ");
        scanner.nextLine();
        String firstName = scanner.nextLine();
        System.out.println("last name: ");
        String lastName = scanner.nextLine();
        System.out.println("username: ");
        String username = scanner.nextLine();
        System.out.println("password: ");
        String password = scanner.nextLine();
        System.out.println("email: ");
        String email = scanner.nextLine();
        Customer customer = customerManagementService.addCustomer(firstName, lastName, username, password, email);
        System.out.println("Added customer " + customer.toString());
    }

    private void handleLogin(){
        System.out.println("username: ");
        String username = scanner.next().trim();
        System.out.println("password: ");
        String password = scanner.next().trim();
        List<Customer> customers = customerManagementService.listCustomers();
        for(Customer c: customers){
            if(c.getUsername().equals(username) && c.getPassword().equals(password)){
                    logged = true;
                    loggedCustomer = c;
                    break;
            }
        }
        if(logged == true){
            System.out.println("Logged in as: " + loggedCustomer.getUsername());
        } else {
            System.out.println("Wrong credentials!");
        }
    }

    private void handleLogoff(){
        logged = false;
        loggedCustomer = null;
        System.out.println("logged off");
    }

    private void handleAddOrder(){
        if(logged == true && loggedCustomer != null) {
            System.out.println("location: ");
            Integer locationId = scanner.nextInt();
            List<Location> locations = locationManagementService.listLocations();
            Location orderLocation = null;
            for(Location l: locations){
                if(l.getLocationId().equals(locationId)){
                    orderLocation = l;
                    break;
                }
            }
            if(orderLocation == null){
                System.out.println("location doesn't exist");
                return;
            }

            System.out.println("country: ");
            String country = scanner.next();
            System.out.println("city: ");
            String city = scanner.next();
            System.out.println("county: ");
            String county = scanner.next();
            System.out.println("street: ");
            String street = scanner.next();
            LocalDateTime time = LocalDateTime.now();
            Orders order = orderManagementService.addOrder(
                    orderLocation, loggedCustomer, time, country, city, county, street
                    );
            System.out.println("New order added: " + order.toString());
        }

    }

    private void handleListOrders(){
        if(logged == true && loggedCustomer != null){
            orderManagementService.listOrders().forEach(o->{
                if(o.getCustomer().equals(loggedCustomer))
                    System.out.println(o.toString());
            }
            );
        }
    }

    private void handleAddLocation(){
        System.out.println("name: ");
        String name = scanner.next().trim();
        System.out.println("country");
        String country = scanner.next().trim();
        System.out.println("city");
        String city = scanner.next().trim();
        System.out.println("county");
        String county = scanner.next().trim();
        System.out.println("street: ");
        String street = scanner.next().trim();
        Location location = locationManagementService.addLocation(name, country, city, county, street);
        System.out.println("new location added: " + location.toString());
    }

    private void handleListLocations(){
        locationManagementService.listLocations().forEach(l->{
            System.out.println(l.toString());
        });
    }
}
