import java.util.*;




public class bpM extends global{



  /* ------------------ MAIN CLASS ---------------- */
  public static void main(String[] args){

    //long startTime = System.currentTimeMillis();



    GenerateCleaner GenerateCleaner = new GenerateCleaner();
    GenerateCustomer GenerateCustomer = new GenerateCustomer();
    Hopcroft hck = new Hopcroft();

    Scanner input = new Scanner(System.in);
    char choice;


    /* ------ Initial Generation ------ */
    System.out.println("\n\n------ CUSTOMERS ------");
    GenerateCustomer.GenerateCustomer(0, L);

    System.out.println("\n\n------ CLEANERS ------");
    GenerateCleaner.GenerateCleaner(0, R);


    int size = L;
    int batchCounter = 0;
    int loop = 0;


    /*------ ONLINE ------*/
    do
    {
        long startTime = System.currentTimeMillis();
        int V = L + R;
        hck.GraphV(V);
        int E = 0;

        //WHILE THERE IS L\Customer LEFT
        for( int n = 0; n < customerAl.size(); n++ )
        {
              //Finding match to a cleaner
              for(int m = 0; m < cleanerAl.size(); m++)
              {

                if ( customerAl.get(n).getCustomerTask1() == cleanerAl.get(m).getCleanerTask() || customerAl.get(n).getCustomerTask2() == cleanerAl.get(m).getCleanerTask()  )
                {
                  hck.addEdge(n, m);
                  E = E + 1;
                }
              }
        }
        batchCounter++;
        System.out.println("\n\n------------ Batch " + batchCounter + " -----------------------------");
        hck.GraphE(E);
        //hck.display();  //DISPLAY Adjacency List

        /* ------ Hopcroft Karp Algorithm ------ */
        ArrayList<ArrayList<Integer>> arr = hck.HopcroftKarp(L, R);
        System.out.println("No. of Matches " + arr.size());
        //System.out.println("Match List " + arr);    //prints matched vertices



        /* ------ Finding the matched vertices in the vertices of Customers and Cleaners ------ */
        ArrayList<Customer> removeCustomer = new ArrayList<>();
        ArrayList<Cleaner> removeCleaner = new ArrayList<>();
        int removeCustomerctr = 0;
        int removeCleanerctr = 0;

        if ( arr.size() > 0 )
          for (int u = 0; u < arr.size() ;u++ )
          {
            for(int l = 0; l < customerAl.size(); l++)
              if ( customerAl.get(arr.get(u).get(0) - 1).equals(customerAl.get(l)) )
                {
                  removeCustomer.add(customerAl.get(l));
                  removeCustomerctr++;
                }
            for(int k = 0; k < cleanerAl.size(); k++)
              if ( cleanerAl.get(arr.get(u).get(1) - 1).equals(cleanerAl.get(k)) )
                {
                  removeCleaner.add(cleanerAl.get(k));
                  removeCleanerctr++;
                }
          }




        /* ------ Remove Matched Customers and Cleaners ------ */
        customerAl.removeAll((removeCustomer));
        cleanerAl.removeAll((removeCleaner));


        /* ------ There is a matching which matches all vertices of the graph ------ */
        if(customerAl.size() == 0 && cleanerAl.size() == 0)
          System.out.println("PERFECT MATCH");


        /* ------ Displays the number of removed Customers and Cleaners if matched ------ */
        System.out.println("\n\nNumber of Removed Customers: " +removeCleanerctr);
        System.out.println("Number of Removed Cleaners: " +removeCleanerctr);


        /* ------ Generate random number of customers and cleaners for new batch ------ */

        /* ------ Customers ------ */
        int genRandCust = (int) (Math.random()*control + 1);
        L = L - removeCustomerctr + genRandCust;
        System.out.println("\n\nRandom Generated Customers: " + genRandCust);
        System.out.println("\n\n------ CUSTOMERS ------");
        GenerateCustomer.GenerateCustomer(removeCustomerctr, genRandCust);
        System.out.println("------------------------");



        /* ------ Cleaners ------ */
        int genRandCle = (int) (Math.random()*control + 1);
        R = R - removeCleanerctr + genRandCle;
        System.out.println("\n\nRandom Generated Cleaners: " + genRandCle);
        System.out.println("\n\n------ CLEANERS ------");
        GenerateCleaner.GenerateCleaner(removeCleanerctr, genRandCle);
        System.out.println("------------------------");



        /* ------ Displays the new current number of customers and cleaners ------ */
        System.out.println("\n\nNew Number of Customers: " + customerAl.size());
        System.out.println("New Number of Cleaners: " + cleanerAl.size());


        /* ------ Execution Time per Batch ------ */
        long endTime = System.currentTimeMillis();
        long timeElapsed = endTime - startTime;
        System.out.println("\nExecution Time in milliseconds: " + timeElapsed);
        System.out.println("--------------------------------------------------");



        arr.clear();
        /* ------ 10 Batches per 'Continue' ------ */
        if( batchCounter % batches == 0)
          {
            System.out.println("\n\nContinue? Y or N");
            choice = input.next().charAt(0);
            if ( (choice == 'n') || (choice == 'N') )
              break;
          }
    }while( loop == 0 ); //END OF WHILE LOOP


    /* ------ Time Elapse ------*/
    /*long endTime = System.currentTimeMillis();
    long timeElapsed = endTime - startTime;
    System.out.println("\nExecution Time in milliseconds: " + timeElapsed);*/
  }
}
