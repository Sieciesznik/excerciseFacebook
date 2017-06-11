package facebook;

import javafx.util.Pair;
import org.json.JSONObject;

public class FacebookCity {

    String countryName;
    String cityName;
    String stateName;
    Pair<Double, Double> coords;

    public FacebookCity(JSONObject city){
        this.countryName = city.getString("countryName");
        this.cityName = city.getString("cityName");
        this.stateName = city.getString("stateName");
        this.coords = new Pair<>(city.getJSONObject("coords").getDouble("lon"), city.getJSONObject("coords").getDouble("lat"));
    }

    public String toString(){
        return  "\n\tcountryName = " + this.countryName +
                "\n\tcityName = " + this.cityName +
                "\n\tstateName = " + this.stateName +
                "\n\tlon = " + this.coords.getKey().toString() +
                "\n\tlat = " + this.coords.getValue().toString();
    }
}
