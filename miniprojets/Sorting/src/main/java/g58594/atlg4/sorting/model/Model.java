package g58594.atlg4.sorting.model;

import g58594.atlg4.sorting.util.Observable;
import g58594.atlg4.sorting.util.Observer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Model implements Observable {
    private List<Observer> observers;
    private ExecutorService executor;

    public Model(){
        observers = new ArrayList<>();
        executor = Executors.newFixedThreadPool(2);
    }

    public void startSorting(SortType st, Difficulty diff, int nbThreads){
        executor = Executors.newFixedThreadPool(nbThreads);
        int nbMax = diff.getNbMax();
        for (int i = 0; i <= nbMax; i+=nbMax/10) {
            int[] arr = arrayMaker(i);
            executor.execute(()-> {
                switch (st) {
                    case BUBBLESORT -> notifyObserver(Sort.bubbleSort(arr));
                    case MERGESORT -> notifyObserver(Sort.mergeSort(arr));
                }
            });
        }
        shutdownExecutor();
    }

    public void shutdownExecutor(){
        executor.shutdown();
    }

    private int[] arrayMaker(int size){
        int[] res = new int[size];
        Random random = new Random();
        for (int i = 0; i< size; i++){
            res[i] = random.nextInt(Integer.MAX_VALUE);
        }
        return res;
    }

    @Override
    public void register(Observer observer) {
        if(!observers.contains(observer)){
            observers.add(observer);
        }
    }
    @Override
    public void unregister(Observer observer) {
        observers.remove(observer);
    }
    private synchronized void notifyObserver(SortRecord sortRecord){
        for (var obs : observers){
            obs.update(sortRecord);
        }
    }
}
