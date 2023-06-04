package g58594.atlg4.stibRide.model.dto;

public class FavoriDto extends Dto<String> {

    private String origin;
    private String destination;

    public FavoriDto(String key, String origin, String destination) {
        super(key);
        this.origin = origin;
        this.destination = destination;
    }

    public String getOrigin() {
        return origin;
    }

    public String getDestination() {
        return destination;
    }
}
