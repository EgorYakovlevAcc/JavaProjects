import java.util.Date;

public class Note {
    Date date;
    String content;

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Note(String s, Date date) {
        setDate(date);
        setContent(s);
    }
}
