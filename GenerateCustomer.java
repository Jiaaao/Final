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
      customerArray.setCustomerTask1(Task[rand]);
      customerAl.add(customerArray);


      Customer customerGetArray = new Customer();
      customerGetArray = customerAl.get(generateCtr);

      //Display
      System.out.println("\nCustomer " + ( generateCtr + 1 ) + " Task : " + customerGetArray.getCustomerTask1()  );


      generateCtr++;
    }
    return customerGetArray;
  }
}
