package g58594.atlg4.sorting.model;

public enum Difficulty {
    VERYEASY("Very Easy : 0 - 100",100),
    EASY("Easy : 0 - 1000",1000),
    MIDDLE("Middle : 0 - 10000",10000),
    HARD("Hard : 0 - 100000",100000),
    VERYHARD("Very Hard : 0 - 1000000",1000000);

    private String name;
    private int nbMax;

    private Difficulty(String name, int nbMax){
        this.name = name;
        this.nbMax = nbMax;
    }

    public int getNbMax() {
        return nbMax;
    }

    @Override
    public String toString() {
        return name;
    }
}
