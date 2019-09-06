package database;

import common.BasicFood;
import common.Item;
import common.Observer;
import common.Transaction;
import main.Main;

import javax.xml.crypto.Data;
import java.io.File;
import java.util.*;

public class TransactionDB {

    public TransactionDB(){
        //loadCurr();
    }

    private Observer<TransactionDB> observer;

    public void setObserver(Observer<TransactionDB> observer) {
        this.observer = observer;
    }

    public void alertObserver(){
        observer.update(this);
    }

    private int startIdCurr = 0;
    private int endIdCurr;
    private HashMap<Integer, Transaction> currList = new HashMap<>(); //hash of the current list of transactions


    public List<Transaction> getCurrList(){
        List<Transaction> temp = new LinkedList<>();
        for(Integer t : currList.keySet()){
            temp.add(currList.get(t));
        }
        return temp;
    }

    TransactionManager transactionManager = new TransactionManager();

    public void save(){
        //calles the transManager telling it to save and handle the currTrans Data
        transactionManager.save();
    }

    public void loadCurr(){
        //loads in the curr data
        transactionManager.loadCurr();
    }

    public void open(){
    //opens the register and gets the transactionManager Ready
        Main.register.openRegister();
        loadCurr();
        save();
        //must load the currTotals before opening the register, otherwise the file will be overwritten
        System.err.println("Opened");


    }

    public void close(){
        //closes the register and gets the transactionManager ready to close
        Main.register.closeRegister();
        save();
        System.err.println("Closed");
        Main.closeFeedBack("Register has closed, you can still print a summary for today. \n" +
                "if you did not mean to close the register, re-open the register without restarting the program");
    }

    public Transaction getTransaction(int id){
        if(currList.containsKey(id)){
            return currList.get(id);
        }else{
            System.err.println("Not In current list");
        }

        return null;
    }

    public List<Transaction> getCurrList(int start){
        List<Transaction> temp = new LinkedList<>();
        for(Integer t : currList.keySet()){
            if(currList.get(t).id >= start)
                temp.add(currList.get(t));
        }
        return temp;
    }

    public void addTransToCurr(Transaction transaction){
        currList.put(transaction.id, transaction);
        endIdCurr = transaction.id;
        alertObserver();
        transactionManager.save(); //tells the transactionManager to save the transactions
    }

    public ArrayList<Double> getSummary(int startId){
        //takes the starting id and will count all the information from that id till the end of the list
        //returns an arrayList of information relating to the summary
        //0 - total # sales
        //1 - total value of sales
        //2 - # of no sales
        //3 - items entered manually : i'll do this later
        //4 - start id
        //5 - end id
        //6 bottle deposit
        //7 avg sale
        //8 num of voids
        //9 value void
        //10 starting cash
        //11 num payments received
        //12 num cash payments
        //13 num credit payments
        //14 value total payments
        //15 value cash payments
        //16 value credit payments
        //17 tax
        //18 num of discount
        //19 value of discounts
        //20 taxable Sales
        //21 nontaxable sales

        ArrayList<Double> rt = new ArrayList<>();
        Double totSales = 0.0;
        Double valueSales = 0.0;
        Double noSales = 0.0;
        Double itemsEntMan = 0.0;
        Double startIdTemp = 0.0;
        Double endIdTemp = 0.0;
        Double bottleDepositTotal = 0.0;
        Double numVoids = 0.0;
        Double valueVoid = 0.0;
        Double numCashPayments = 0.0;
        Double numCreditPayments = 0.0;
        Double totalPayments = 0.0;
        Double valueCashPayments = 0.0;
        Double valueCreditPayments = 0.0;
        Double valueTotalPayments = 0.0;
        Double tax = 0.0;
        Double numDiscount = 0.0;
        Double valueDiscount = 0.0;
        Double taxableSales = 0.0;
        Double nonTaxableSales = 0.0;

        for(Transaction transaction : getCurrList(startId)){
            //checking is trans if void
            if(transaction.isVoid()) {
                numVoids += 1.0;
                valueVoid += transaction.getTotal();
                continue;
            }

            tax += transaction.getTax();
            totSales += 1.0;
            valueSales += transaction.getTotal();
            if(transaction.getLast().getName().equals("NO SALE")){
                noSales+=1.0;
                totSales -= 1.0; //No sales are not counted towards the total # of sales
                valueSales -= transaction.getTotal(); //just in case the trans had a total it is removed from the count
            }
            for(Item item : transaction.list){
                //goes through every item in the list
                try {
                    if (item.getName().equals("Man Add"))
                        itemsEntMan += 1.0;
                    if(item.getName().equals("DISCOUNT")){
                        numDiscount += 1.0;
                        valueDiscount += item.getPrice();
                    }
                    if(item.isTaxable()){
                        taxableSales += item.getPrice();
                    }else{
                        nonTaxableSales += item.getPrice();
                    }
                    if(item instanceof BasicFood) {
                        if (((BasicFood) item).isDeposit()) {
                            bottleDepositTotal += (Main.values.BOTTLE_DEPOSIT * 100.00);
                        }
                    }
                } catch (Exception e){
                    e.printStackTrace();
                }
            }

            for(Transaction.PAYMENT payment : transaction.getPayments()){
                totalPayments += 1.0;
                valueTotalPayments += payment.getValue();
                if(payment.getType().equals(Transaction.PAYMENT_TYPE.CASH)){
                    numCashPayments += 1.0;
                    valueCashPayments += payment.getValue();
                }else{
                    numCreditPayments += 1.0;
                    valueCreditPayments += payment.getValue();
                }
            }
        }
        startIdTemp = Double.parseDouble("" + startId);
        endIdTemp = Double.parseDouble("" + endIdCurr);

        //+ is for adding the totals if the machine had already been opened but closed
        rt.add(0, totSales + transactionManager.currTotals.get(0));
        rt.add(1, valueSales + transactionManager.currTotals.get(1));
        rt.add(2, noSales + transactionManager.currTotals.get(2));
        rt.add(3, itemsEntMan + transactionManager.currTotals.get(3));
        rt.add(4, startIdTemp + transactionManager.currTotals.get(4));
        rt.add(5, endIdTemp + transactionManager.currTotals.get(5));
        rt.add(6, bottleDepositTotal + transactionManager.currTotals.get(6));
        rt.add(7, rt.get(1)/rt.get(0));
        rt.add(8, numVoids + transactionManager.currTotals.get(8)); //skip 7
        rt.add(9, valueVoid + transactionManager.currTotals.get(9));
        rt.add(10, Main.values.STARTING_CASH);
        rt.add(11, totalPayments + transactionManager.currTotals.get(11)); //skip 10
        rt.add(12, numCashPayments + transactionManager.currTotals.get(12));
        rt.add(13, numCreditPayments + transactionManager.currTotals.get(13));
        rt.add(14, valueTotalPayments + transactionManager.currTotals.get(14));
        rt.add(15, valueCashPayments + transactionManager.currTotals.get(15));
        rt.add(16, valueCreditPayments + transactionManager.currTotals.get(16));
        rt.add(17, tax + transactionManager.currTotals.get(17));
        rt.add(18, numDiscount + transactionManager.currTotals.get(18));
        rt.add(19, valueDiscount + transactionManager.currTotals.get(19));
        rt.add(20, taxableSales + transactionManager.currTotals.get(20));
        rt.add(21, nonTaxableSales + transactionManager.currTotals.get(21));

        return rt;
    }

    public int getStartIdCurr() {
        return startIdCurr;
    }
}
