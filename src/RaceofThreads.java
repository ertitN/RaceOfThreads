
import java.util.ArrayList;
public class RaceofThreads {

            private ArrayList<Integer> allNumbersList = new ArrayList<>();

             private  ArrayList<Integer> firstlist = new ArrayList<>();
             private  ArrayList<Integer> secondlist = new ArrayList<>();
             private  ArrayList<Integer> thirdlist = new ArrayList<>();
             private  ArrayList<Integer> fourthlist = new ArrayList<>();

            private  static  ArrayList<Integer> evenNumbers = new ArrayList<>();
            private static ArrayList<Integer> oddNumbers = new ArrayList<>();


    public RaceofThreads() {

        for (int i=0;i<10000;i++){
            allNumbersList.add(i);
        }

        assignNumbersToSubLists(firstlist);
        assignNumbersToSubLists(secondlist);
        assignNumbersToSubLists(thirdlist);
        assignNumbersToSubLists(fourthlist);



    }

    public ArrayList<Integer> getAllNumbersList() {
        return allNumbersList;
    }



    public ArrayList<Integer> getFirstlist() {
        return firstlist;
    }



    public ArrayList<Integer> getSecondlist() {
        return secondlist;
    }



    public ArrayList<Integer> getThirdlist() {
        return thirdlist;
    }



    public ArrayList<Integer> getFourthlist() {
        return fourthlist;
    }

    public static ArrayList<Integer> getEvenNumbers() {
        return evenNumbers;
    }

    public static ArrayList<Integer> getOddNumbers() {
        return oddNumbers;
    }

    public  void assignNumbersToSubLists(ArrayList<Integer> arrayList){


        for (int i=0; i<2500;i++){
            arrayList.add(this.allNumbersList.get(0));
            this.allNumbersList.remove(0);
        }
    }

    public void printList(ArrayList<Integer> arrayList){

        for (Integer num: arrayList){
            System.out.println(num);
        }

    }

    public static synchronized void addEvenNumbersToList(int num){
        RaceofThreads.getEvenNumbers().add(num);

    }

    public static synchronized void addOddNumbersToList(int num){
        RaceofThreads.getOddNumbers().add(num);

    }
    public void addAllNumberstoList(ArrayList<Integer> arrayList){

        for (int i=0;i<arrayList.size();i++){
            if(arrayList.get(i)%2==0){
                RaceofThreads.addEvenNumbersToList(arrayList.get(i));
            }
            else {
                RaceofThreads.addOddNumbersToList(arrayList.get(i));
            }

        }

    }

    public  void assignOddAndEvenNumbers(){

        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                addAllNumberstoList(firstlist);

            }


        });
        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                addAllNumberstoList(secondlist);


            }
        });
        Thread t3 = new Thread(new Runnable() {
            @Override
            public void run() {
                addAllNumberstoList(thirdlist);
            }
        });
        Thread t4 = new Thread(new Runnable() {
            @Override
            public void run() {
                addAllNumberstoList(fourthlist);
            }
        });


        t1.start();
        t2.start();
        t3.start();
        t4.start();

        try {
            t1.join();
            t2.join();
            t3.join();
            t4.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }


        System.out.println(evenNumbers.size()+oddNumbers.size());





    }


}
