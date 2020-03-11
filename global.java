import java.util.*;

public class global{
  public static int V, E;


  ArrayList<ArrayList<Integer>> matchList = new ArrayList<ArrayList<Integer>>();  //Arraylist of an ArrayList for matched/traversal in DFS/BFS


  public static  int L = 5;             //Initial Customer
  public static  int R = 5;             //Initial Cleaner
  public static  int control = 5;       //A Number equal to or less that generates Customers and Cleaners
  public static  int batches = 5;       //A number of batch per 'Continue'

  public static ArrayList<Customer> customerAl = new ArrayList<Customer>();
  public static ArrayList<Cleaner> cleanerAl = new ArrayList();
  public static Cleaner cleanerGetArray = new Cleaner();
  public static Customer customerGetArray = new Customer();

}
