package database;

import common.Transaction;

import java.util.HashMap;

public class TransactionManager {
    //support class to the TransactionDB
    //handles the sorting organizing the files

    String currTrnasLocation = "C:/Users/Public/POS/currTrans";

    public TransactionManager(){
        System.err.println("Transaction Manager HAS AWOKEN! Be careful lazy employees");
    }

    public void save(){

    }

    public HashMap<Integer, Transaction> loadCurr(){
        HashMap<Integer, Transaction> temp = new HashMap<>();



        return temp;
    }

}
