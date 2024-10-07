import com.google.gson.Gson;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ConsultaPelicula {

    public Pelicula buscaPelicula(int numeroDePelicula){
        URI direccion = URI.create("https://swapi.py4e.com/api/films/" + numeroDePelicula + "/");

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(direccion)
                .build();

        try {
            HttpResponse<String> response = client
                    .send(request, HttpResponse.BodyHandlers.ofString());
            // Imprime el cuerpo de la respuesta para depurar
            System.out.println("Cuerpo de la respuesta: " + response.body());

            // Intenta deserializar la respuesta a un objeto Pelicula
            Pelicula pelicula = new Gson().fromJson(response.body(), Pelicula.class);
            if (pelicula == null) {
                System.out.println("La deserialización falló");
            }

            return pelicula;
        } catch (Exception e) {
            throw new RuntimeException("No encontre esa pelicula");
        }

    }
}
