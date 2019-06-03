package database;

import common.Transaction;

import javax.xml.crypto.Data;
import java.io.File;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

public class TransactionDB {

    private class transFileRange {
        public int startId;
        public int endId;
        public String fileName;
        public Data date;
    }

    private int startIdCurr;
    private int endIdCurr;
    private HashMap<Integer, Transaction> currList = new HashMap<>(); //hash of the current list of transactions
    private HashMap<Data,transFileRange> transFiles = new HashMap<>(); //hash of the locations of transactions

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

    public void addTransToCurr(Transaction transaction){
        currList.put(transaction.id, transaction);
    }
}
