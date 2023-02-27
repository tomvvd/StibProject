package g58594.atlg4.sorting.model;

public enum NbElem {
    VERYEASY("Very Easy : 0 - 100 by 10",100, 10),
    EASY("Easy : 0 - 1000 by 100",1000,100),
    MIDDLE("Middle : 0 - 10000 by 1000",10000,1000);

    private String name;
    private int nbMax;
    private int tranche;

    private NbElem(String name,int nbMax,int tranche){
        this.name = name;
        this.nbMax = nbMax;
        this.tranche = tranche;
    }

    public int getNbMax() {
        return nbMax;
    }

    public int getTranche() {
        return tranche;
    }

    @Override
    public String toString() {
        return name;
    }
}
