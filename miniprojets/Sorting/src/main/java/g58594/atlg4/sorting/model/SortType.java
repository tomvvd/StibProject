package g58594.atlg4.sorting.model;

public enum SortType {
    BUBBLESORT("Tri à bulles"),
    MERGESORT("Tri fusion");

    private String name;
    private SortType(String name){
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
