import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Queues buyingAmountOfStock = new Queues(); //The queue where the amount of stock is stored
        Queues buyingPriceOfStock = new Queues();  //the price of each share will be stored here

        int quantity; //the no.of shares bought
        int price;    //price at which he bought
        int gain;     //the total profit
        int loopIterator;        //the variable which is used to iterate through the loop when buying
        int shareRemover;        //the variable which is used to iterate through the loop when selling to remove shares
        int moneyCounter=0;        // to add all the prices of the shares we remove
        int sellingAmount;         // the amount of stock sold
        int sellingPrice;           //price at which the shares are sold
        int noOfSharesRemaining;
        int priceAtWhichBought;
        String option="";
        Scanner inputTaker = new Scanner(System.in);
// FIRST BUY=============================================================================================================================================
        System.out.println(" Welcome!, Start with entering your first BUY.. ");  // the user has to start with a buy always
        //----------------------Quantity----------------------------------
        try{
            System.out.print(" How many Shares did you buy: ");
            quantity = inputTaker.nextInt();
        }
        catch(InputMismatchException e){
            System.out.println(" Invalid input, pls enter only Integers for the amount!");
            System.out.print(" Re-enter the no.of Shares you bought:");
            inputTaker.next();      // to clear the input thingy
            quantity = inputTaker.nextInt();
        }
        //-------------------------prize-------------------------------------
        try {
            System.out.print("\n" + " Price per share: ");
            price = inputTaker.nextInt();
        }
        catch (InputMismatchException e){
            System.out.println(" Invalid input, pls enter only Integers for the prize!");
            System.out.print(" Re-enter the price per Share:");
            inputTaker.next();
            price = inputTaker.nextInt();
        }
        //--------------------ADDING TO THE QUEUES-----------------------
        for(loopIterator=1;loopIterator<=quantity;loopIterator++)
        {
            buyingAmountOfStock.enqueue(loopIterator);
            buyingPriceOfStock.enqueue(price);
        }

        noOfSharesRemaining=buyingAmountOfStock.gettingSize();
        System.out.println(" No. of Shares available= "+noOfSharesRemaining); //showing the user how many shares he has
//======================================================================================================================================================


        while (true) {                   //this loop runs until user enters esc and a break statement is present
            System.out.print("\n"+" Enter the operation you wish to do(Buy/Sell/Esc): ");
            option = inputTaker.next();
            //========================================================================
            if (option.equalsIgnoreCase("buy")){
                //----------------------Quantity----------------------------------
                try{
                    System.out.print(" How many Shares did you buy: ");
                    quantity = inputTaker.nextInt();
                }
                catch(InputMismatchException e){
                    System.out.println(" Invalid input, pls enter only Integers for the amount!");
                    System.out.print(" Re-enter the no.of Shares you bought:");
                    inputTaker.next();
                    quantity = inputTaker.nextInt();
                }
                //-------------------------prize---------------------------------------
                try {
                    System.out.print("\n" + " Price per share: ");
                    price = inputTaker.nextInt();
                }
                catch (InputMismatchException e){
                    System.out.println(" Invalid input, pls enter only Integers for the prize!");
                    System.out.print(" Re-enter the price per Share:");
                    inputTaker.next();
                    price = inputTaker.nextInt();
                }
                //--------------------ADDING TO THE QUEUES-----------------------
                for(loopIterator=1;loopIterator<=quantity;loopIterator++)
                {
                    buyingAmountOfStock.enqueue(loopIterator);
                    buyingPriceOfStock.enqueue(price);
                }

                noOfSharesRemaining=buyingAmountOfStock.gettingSize();
                System.out.println(" No. of Shares available= "+noOfSharesRemaining);

            }//=======================================================================
            else if (option.equalsIgnoreCase("sell")){
                //---------------------quantity-------------------------
                try {
                    System.out.print(" How many Shares are you selling: ");
                    sellingAmount = inputTaker.nextInt();
                }
                catch (InputMismatchException e){
                    System.out.println(" Invalid input, pls enter only Integers for the amount!");
                    System.out.print(" Re-enter the no.of Shares you are selling:");
                    inputTaker.next();
                    sellingAmount = inputTaker.nextInt();
                }
                //-----------------------PRICE------------------------------
                try {
                    System.out.print("\n" + " Price per Share: ");
                    sellingPrice = inputTaker.nextInt();
                }
                catch (InputMismatchException e){
                    System.out.println(" Invalid input, pls enter only Integers for the Price!");
                    System.out.print(" Re-enter the price per share:");
                    inputTaker.next();
                    sellingPrice = inputTaker.nextInt();
                }

                //----------------REMOVING THE SELLING SHARES------------------------
                noOfSharesRemaining=buyingAmountOfStock.gettingSize();
                if(noOfSharesRemaining<sellingAmount){          //checking whether user has that many shares to sell
                    System.out.println("\n"+" Insufficient Shares, Try Again!"+"\n");
                } else {
                    shareRemover = 0;

                    while(shareRemover!=sellingAmount){
                        priceAtWhichBought = buyingPriceOfStock.dequeue();
                        moneyCounter += priceAtWhichBought;
                        buyingAmountOfStock.dequeue();
                        shareRemover++;
                    }
                    gain = (sellingPrice*sellingAmount)-moneyCounter;

                    System.out.println(" **This is your capital gain:"+gain+"\n");
                    noOfSharesRemaining=buyingAmountOfStock.gettingSize();
                    System.out.println(" No. of Shares available= "+noOfSharesRemaining);
                    moneyCounter=0;
                }
            }//===================================================================
            else if (option.equalsIgnoreCase("esc")){
                break;
            }//===================================================================
            else{
                System.out.println(" Invalid keyword entered, pls try again");
            }
        }
        System.out.println(" Program Ended");
    }
}