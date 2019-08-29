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
        loadCurr();
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
    }

    public void loadCurr(){
        //loads in the curr data
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

        ArrayList<Double> rt = new ArrayList<>();
        Double totSales = 0.0;
        Double valueSales = 0.0;
        Double noSales = 0.0;
        Double itemsEntMan = 0.0;
        Double startIdTemp = 0.0;
        Double endIdTemp = 0.0;
        Double bottleDepositTotal = 0.0;

        for(Transaction transaction : getCurrList(startId)){
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
                    if (((BasicFood) item).isDeposit()) {
                        bottleDepositTotal += Main.values.BOTTLE_DEPOSIT;
                    }
                } catch (Exception e){
                    e.printStackTrace();
                }
            }
        }
        startIdTemp = Double.parseDouble("" + startId);
        endIdTemp = Double.parseDouble("" + endIdCurr);

        rt.add(0, totSales);
        rt.add(1, valueSales);
        rt.add(2, noSales);
        rt.add(3, itemsEntMan);
        rt.add(4, startIdTemp);
        rt.add(5, endIdTemp);
        rt.add(6, bottleDepositTotal);
        return rt;
    }

    public int getStartIdCurr() {
        return startIdCurr;
    }
}
