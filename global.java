import java.util.*;

public class global{
  public static int V, E;


  ArrayList<ArrayList<Integer>> matchList = new ArrayList<ArrayList<Integer>>();  //Store the matched vertices in match[] array of arraylist into another arraylist //ArrayList of ArrayList
  public static String[] Task = { "Laundry", "Living Room", "Pool", "Car" };

  public static  int L = 5;             //Initial Customer
  public static  int R = 5;             //Initial Cleaner
  public static  int control = 5;       //A Number equal to or less that generates Customers and Cleaners
  public static  int batches = 5;       //A number of batch per 'Continue'

  public static ArrayList<Customer> customerAl = new ArrayList<Customer>();       //Arraylist for Customer and Cleaner Tasks
  public static ArrayList<Cleaner> cleanerAl = new ArrayList<>();


  public static ArrayList<Customer> removeCustomer = new ArrayList<>();           //Arraylist for customer and cleaners to be removed
  public static ArrayList<Cleaner> removeCleaner = new ArrayList<>();

}
