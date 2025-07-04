package Application;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Tasks {
    private int id;
    private String text;
    private boolean done;
    private String dataAlteracao;

    public Tasks(String text) {
        this.text = text;
        this.done = false;
        this.dataAlteracao = now();
    }

    public Tasks(int id, String text, boolean done, String dataAlteracao) {
        this.id = id;
        this.text = text;
        this.done = done;
        this.dataAlteracao = dataAlteracao;
    }

    private String now() {
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }


    public int getId() { return id; }
    public String getText() { return text; }
    public boolean isDone() { return done; }
    public String getDataAlteracao() { return dataAlteracao; }

    public void setId(int id) { this.id = id; }
    public void setText(String text) { this.text = text; }
    public void setDone(boolean done) { this.done = done; }
    public void setDataAlteracao(String dataAlteracao) { this.dataAlteracao = dataAlteracao; }

    @Override
    public String toString() {
        if (done) {
            return (" |X|  - " + this.text + " - " +  this.dataAlteracao);
        }
        else{
            return (" |  |  - " + this.text + " - " +  this.dataAlteracao);
        }
    }
}
