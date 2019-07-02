package database;

import common.Transaction;

import javax.xml.crypto.Data;
import java.io.File;
import java.util.*;

public class TransactionDB {

    private class transFileRange {
        public int startId;
        public int endId;
        public String fileName;
        public int day;
    }

    private int startIdCurr = 0;
    private int endIdCurr;
    private HashMap<Integer, Transaction> currList = new HashMap<>(); //hash of the current list of transactions
    private HashMap<Integer, Transaction> tempList = new HashMap<>();
    private HashMap<Integer,transFileRange> transFiles = new HashMap<>(); //hash of the locations of transactions

    public HashMap<Integer, Transaction> getCurrHash() {
        return currList;
    }

    public List<Transaction> getCurrList(){
        List<Transaction> temp = new LinkedList<>();
        for(Integer t : currList.keySet()){
            temp.add(currList.get(t));
        }
        return temp;
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
    }

    public ArrayList<Double> getSummary(int startId){
        //takes the starting id and will count all the information from that id till the end of the list
        //returns an arrayList of information relating to the summary
        //1 - total # sales
        //2 - total value of sales
        //3 - # of no sales
        //4 - items entered manually : i'll do this later

        ArrayList<Double> rt = new ArrayList<>();
        Double totSales = 0.0;
        Double valueSales = 0.0;
        Double noSales = 0.0;

        for(Transaction transaction : getCurrList(startId)){
            totSales += 1.0;
            valueSales += transaction.getTotal();
            if(transaction.getLast().getName().equals("NO SALE")){
                noSales+=1.0;
                totSales -= 1.0; //No sales are not counted towards the total # of sales
                valueSales -= transaction.getTotal(); //just in case the trans had a total it is removed from the count
            }
        }
        rt.add(totSales);
        rt.add(valueSales);
        rt.add(noSales);
        return rt;
    }

    public int getStartIdCurr() {
        return startIdCurr;
    }
}
