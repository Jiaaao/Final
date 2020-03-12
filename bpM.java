import java.util.*;




public class bpM extends global{



  /* ------------------ MAIN CLASS ---------------- */
  public static void main(String[] args){


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


    int batchCounter = 0;


    /*------ ONLINE ------*/
    do
    {
        long startTime = System.currentTimeMillis();
        int V = L + R;
        hck.GraphV(V);
        int E = 0;

        //While there is L/Customer
        for( int n = 0; n < L; n++ )
        {
              //While there is a R/Cleaner
              for(int m = 0; m < R; m++)
              {
                //Finding a match for Customer with a cleaner
                if ( customerAl.get(n).getCustomerTask1() == cleanerAl.get(m).getCleanerTask1() || customerAl.get(n).getCustomerTask1() == cleanerAl.get(m).getCleanerTask2()  )
                {
                  hck.addEdge(n, m);
                  E = E + 1;
                }
              }
        }
        batchCounter++;
        System.out.println("\n\n------------ Batch " + batchCounter + " -----------------------------");
        hck.GraphE(E);
        hck.display();  //DISPLAY Adjacency List

        /* ------------------- Hopcroft Karp Algorithm ------------------- */
        ArrayList<ArrayList<Integer>> arr = hck.HopcroftKarp(L, R);
        System.out.println("Matched Customers and Cleaners: " + arr);    //prints matched vertices



        /* ------ Finding the matched vertices in the vertices of Customers and Cleaners ------ */
        int removeCustomerctr = 0;
        int removeCleanerctr = 0;

        if ( arr.size() > 0 )
          for (int u = 0; u < arr.size() ;u++ )
          {
            for(int l = 0; l < L; l++)
              if ( customerAl.get(arr.get(u).get(0) - 1).equals(customerAl.get(l)) )
                {
                  removeCustomer.add(customerAl.get(l));
                  removeCustomerctr++;
                }
            for(int k = 0; k < R; k++)
              if ( cleanerAl.get(arr.get(u).get(1) - 1).equals(cleanerAl.get(k)) )
                {
                  removeCleaner.add(cleanerAl.get(k));
                  removeCleanerctr++;
                }
          }
          /*------------------------------------------------------------------------------------*/




        /* ------ Remove Matched Customers and Cleaners ------ */
        customerAl.removeAll((removeCustomer));
        cleanerAl.removeAll((removeCleaner));
        L = L - removeCustomerctr;
        R = R - removeCleanerctr;


        /* ------ There is a matching which matches all vertices of the graph ------ */
        if(L == 0 && R == 0)
          System.out.println("Perfect Match: Yes");
        else
          System.out.println("Perfect Match: No");

        /* ------ Displays the number of removed Customers and Cleaners if matched ------ */
        System.out.println("\n\nNo. of Removed Customers: " +removeCleanerctr);
        System.out.println("No. of Removed Cleaners: " +removeCleanerctr);


        /* ------ Generate random number of customers and cleaners for new batch ------ */
        int genRandCust = (int) (Math.random()*control + 1);
        int genRandCle = (int) (Math.random()*control + 1);
        L = L + genRandCust;
        R = R + genRandCle;
        System.out.println("\n\nRandom Generated Customers: " + genRandCust);
        System.out.println("Random Generated Cleaners: " + genRandCle);



        /* ------ Customers ------ */
        System.out.println("\n\n------ CUSTOMERS ------");
        GenerateCustomer.GenerateCustomer(removeCustomerctr, genRandCust);
        System.out.println("------------------------");



        /* ------ Cleaners ------ */
        System.out.println("\n\n------ CLEANERS ------");
        GenerateCleaner.GenerateCleaner(removeCleanerctr, genRandCle);
        System.out.println("------------------------");



        /* ------ Displays the new current number of customers and cleaners ------ */
        System.out.println("\n\nNew Number of Customers: " + L);
        System.out.println("New Number of Cleaners: " + R);


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
            if ( (choice == 'y') || (choice == 'Y') )
              continue;
            else if ( (choice == 'n') || (choice == 'N') )
              break;
            else
              {
                System.out.println("\nWrong input.\nEND");
                break;
              }
          }
    }while( 1 > 0 ); //END OF WHILE LOOP

  }
}
