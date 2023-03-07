package g58594.atlg4.sorting.util;

public interface Observable {

    /**
     * Adds an observer to the set of observers for this object,
     * provided that it is not the same as some observer already in the set.
     * @param observer Observer type
     */
    void register(Observer observer);

    /**
     * Deletes an observer from the set of observers of this object.
     * @param observer Observer type
     */
    void unregister(Observer observer);
}
