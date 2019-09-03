package main;

import common.*;
import gui.GUI;
import gui.GUIKeyPad;
import gui.GUIMain;
import printer.Receipt;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Register {
    //create layout?
    //make the classes
    //constructor

    //debug lists
    private boolean isOpen = false; //if the register is already open
    public List<Topping> toppings = new LinkedList<>(); //fill this list with toppings
    public List<Item> foods = new LinkedList<>(); //fill this list with foods
    public List<Side> sides = new LinkedList<>();

    public List<Item> getFoods(){
        return foods;
    }

    public List<Transaction> list = new LinkedList<>(); //this is now a list of transactions (will be moved to logger at some point)
    private Transaction transaction = new Transaction(); //the current transaction

    private GUIKeyPad guiKeyPad;

    public void setGuiKeyPad(GUIKeyPad guiKeyPad) {
        this.guiKeyPad = guiKeyPad;
    }

    private List<Observer<Register>> observers = new LinkedList<>();

    public void addObserver(Observer<Register> observer){
        observers.add(observer);
    }

    public List<Item> getList() {
        //returns the current
        return transaction.list;
    }


    public Register(){

    }

    public void openRegister(){
        isOpen = true;
    }

    public void closeRegister(){
        isOpen = false;
    }

    public boolean isOpen() {
        return isOpen;
    }

    private boolean transDone = false;

    public void cashout(Transaction.PAYMENT_TYPE type){
        cashout((int)(guiKeyPad.getValue()*100),type);
    }

    public void cashout(int ent, Transaction.PAYMENT_TYPE type){
        transDone = transaction.cashOut(ent, type);
        Main.transactionDB.addTransToCurr(transaction);
        Receipt.print_Receipt(transaction);
        Receipt.print_Receipt(transaction);

        alertObservers();
    }

    public void addTopping(Item newItem){
        AdjustableFood temp = (AdjustableFood)getLast();
        temp.addTopping((Topping)newItem);
        alertObservers();
    }

    public boolean canAddTopping(){
        return (getLast() instanceof AdjustableFood);
    }

    public void addSide(Item newItem){
        //if no item is selected then the item is added right to the list
        transaction.list.add(newItem);
        alertObservers();
    }

    public void addFood(Item item){
        if(isTransDone()){
            transDone = false;
            list.add(transaction);
            transaction = new Transaction();
        }
        if(item instanceof AdjustableFood){
            //if its an adjustable food it must be a new instance becuase of the toppings
            transaction.list.add(new AdjustableFood((AdjustableFood) item));
        } else{
            transaction.list.add(item);
        }
        alertObservers();
        //food also has changable food
    }

    public boolean removeIndex(int index){
        if(list.size()==0){
            return false;
        }
        list.remove(index);
        return true;
    } //48sec

    public boolean removeLast() {
        if(transaction.removeLast()) {
            alertObservers();
            return true;
        }
        return false;

    }

    public Item getIndex(int index){
        return transaction.ItemGetIndex(index);
    }

    public Item getLast(){
        return transaction.getLast();
    }

    public void voidTransaction(){
        //this should void the transaction and make a new one
        transaction = new Transaction();
        alertObservers();
    }

    public int getTotal(){
       return transaction.getTotal();
    }

    public String getTotalString(){
        return transaction.getTotalString();
    }

    public int getEntered(){
        return transaction.entered;
    }

    public int getTax(){
        return transaction.tax;
    }

    public int getChange(){
        return transaction.change;
    }

    public boolean isTransDone() {
        return transDone;
    }

    public void alertObservers(){
        //update the GUIs related to the register
        //update total and translist

        for(Observer<Register> obs : this.observers){
            obs.update(this);
        }

    }

}
