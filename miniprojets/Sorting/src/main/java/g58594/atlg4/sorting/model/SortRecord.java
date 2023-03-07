package g58594.atlg4.sorting.model;

public class SortRecord {
    private SortType sortType;
    private int size;
    private long nbOp;
    private long duration;

    public SortRecord(SortType sortType, int size, long nbOp, long duration) {
        this.sortType = sortType;
        this.size = size;
        this.nbOp = nbOp;
        this.duration = duration;
    }

    public SortType getSortType() {
        return sortType;
    }

    public int getSize() {
        return size;
    }

    public long getNbOp() {
        return nbOp;
    }

    public long getDuration() {
        return duration;
    }
}
