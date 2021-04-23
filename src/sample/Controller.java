package sample;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import netscape.javascript.JSObject;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class Controller {

    @FXML
    private TextField city;

    @FXML
    private Button button;

    @FXML
    private Text temperature;

    @FXML
    private Text feels;

    @FXML
    private Text max;

    @FXML
    private Text pressure;

    @FXML
    private Text min;

    @FXML
    void initialize() {
        button.setOnAction(event -> {
            String getUserCity = city.getText().trim();
            if(!getUserCity.equals("")) {
                String output = getURLContent("http://api.openweathermap.org/data/2.5/weather?q=" + getUserCity + "&units=metric&appid={Perosnal key}");
                if (!output.isEmpty()) {
                    JSONObject obj = new JSONObject(output);
                    temperature.setText("Температура: " + obj.getJSONObject("main").getDouble("temp"));
                    feels.setText("Ощущается: " + obj.getJSONObject("main").getDouble("feels_like"));
                    max.setText("Максимум: " + obj.getJSONObject("main").getDouble("temp_max"));
                    min.setText("Минимум: " + obj.getJSONObject("main").getDouble("temp_min"));
                    pressure.setText("Давление:  " + obj.getJSONObject("main").getDouble("pressure"));
                }
            }

        });
    }

    private static String getURLContent(String urlAdress) {
        StringBuffer content = new StringBuffer();

        try {
            URL url = new URL(urlAdress);
            URLConnection urlConn = url.openConnection();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConn.getInputStream()));
            String line;

            while ((line = bufferedReader.readLine()) != null) {
                content.append(line + "\n");
            }
            bufferedReader.close();
        } catch (Exception e) {
            System.out.println("There is no such city");
            System.out.println(e);
        }
        return content.toString();
    }

}
