package g58594.atlg4.stibRide.util;

public interface Observable {
    /**
     * Adds an observer to the set of observers for this object,
     * provided that it is not the same as some observer already in the set.
     * @param observer Observer type
     */
    public void register(Observer observer);

    /**
     * Deletes an observer from the set of observers of this object.
     * @param observer Observer type
     */
    public void unregister(Observer observer);

    /**
     * Notify the observers.
     */
    public void notifyObserver(Object object);
}
