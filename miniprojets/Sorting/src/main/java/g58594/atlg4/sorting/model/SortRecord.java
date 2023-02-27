package g58594.atlg4.sorting.model;

public class SortRecord {
    private int nbOp;
    private long duration;

    public SortRecord(int nbOp, long duration){
        this.nbOp = nbOp;
        this.duration = duration;
    }

    public int getNbOp() {
        return nbOp;
    }

    public long getDuration() {
        return duration;
    }
}
