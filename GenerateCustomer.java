class GenerateCustomer  extends bpM{

  int generateCtr = 0;
  public Customer GenerateCustomer(int removectr, int newGen)
  {

    String[] Task = { "Washing", "Furniture", "Pool", "Car" };

    generateCtr = generateCtr - removectr;
    newGen = newGen + generateCtr;

    while( generateCtr < newGen )
    {
      Customer customerArray = new Customer();
      int rand = (int) (Math.random()*4);
      //int rand2 = (int) (Math.random()*4);
      customerArray.setCustomerTask1(Task[rand]);
      //customerArray.setCustomerTask2(Task[rand2]);
      customerAl.add(customerArray);


      Customer customerGetArray = new Customer();
      customerGetArray = customerAl.get(generateCtr);

      //Display
      System.out.println("\nCustomer " + ( generateCtr + 1 ) + " Task 1: " + customerGetArray.getCustomerTask1() /*+ " Task 2: " + customerGetArray.getCustomerTask2() */ );


      generateCtr++;
    }
    return customerGetArray;
  }
}
