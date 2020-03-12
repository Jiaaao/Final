public class GenerateCleaner extends bpM{

  int generateCtr = 0;
  public Cleaner GenerateCleaner(int removectr, int newGen){

    String[] Task = { "Washing", "Furniture", "Pool", "Car" };

    generateCtr = generateCtr - removectr;
    newGen = newGen + generateCtr;

    while( generateCtr < newGen )
    {
      Cleaner cleanerArray = new Cleaner();

      int rand = (int) (Math.random()*4);
      int rand2 = (int) (Math.random()*4);
      cleanerArray.setCleanerTask1(Task[rand]);                    //Set Random Task between 1 - 4 from the Aray
      cleanerArray.setCleanerTask2(Task[rand2]);
      cleanerAl.add(cleanerArray);


      Cleaner cleanerGetArray = new Cleaner();
      cleanerGetArray = cleanerAl.get(generateCtr);

      //Display
      System.out.println("\nCleaner " + ( generateCtr + 1 ) + " Task 1: " + cleanerGetArray.getCleanerTask1() + " Task 2 : " + cleanerGetArray.getCleanerTask2() );


      generateCtr++;
    }
    return cleanerGetArray;
  }
}
