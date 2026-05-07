import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class CsvWriter {

    private BufferedWriter writer;

    public CsvWriter(String fileName) throws IOException {
        writer = new BufferedWriter(new FileWriter(fileName));
        writer.write("algoritmo,tipo,threads,tamanho,entrada,execucao,tempo_ms\n");
    }

    public void writeLine(String line) throws IOException {
        writer.write(line + "\n");
    }

    public void close() throws IOException {
        writer.close();
    }
}