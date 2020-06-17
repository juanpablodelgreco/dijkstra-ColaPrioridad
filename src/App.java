import java.io.IOException;

public class App {

	public static void main(String[] args) throws IOException {
		Dijkstra algoritmo = new Dijkstra("input.in");
		algoritmo.ejecutar();
	}

}
