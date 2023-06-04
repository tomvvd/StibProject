package g58594.atlg4.stibRide.model.dto;


public class StopDto extends Dto<Pair<Integer,Integer>>{
    private Integer id_order;

    private String nameStation;

    public StopDto(Integer id_line, Integer id_station, Integer id_order, String nameStation) {
        super(new Pair<>(id_line, id_station));
        this.id_order = id_order;
        this.nameStation = nameStation;
    }

    public Integer getId_order() {
        return id_order;
    }

    public String getNameStation() {
        return nameStation;
    }

    @Override
    public String toString() {
        return key.toString()+", id_order=" + id_order +", nameStation= " + nameStation;
    }
}