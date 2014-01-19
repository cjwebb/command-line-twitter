package formatter;

import clock.SystemClock;
import model.Output;
import model.Post;
import org.ocpsoft.prettytime.PrettyTime;
import org.ocpsoft.prettytime.units.JustNow;

public class PrettyPrintFormatter implements Formatter {

    private final SystemClock clock;

    public PrettyPrintFormatter(SystemClock clock) {
        this.clock = clock;
    }

    public String format(Output output) {
        StringBuilder builder = new StringBuilder();
        for (Post p: output.getPosts()) {
            builder.append(prettyPrintPost(p) + "\n");
        }
        return builder.toString();
    }

    private String prettyPrintPost(Post p) {
        PrettyTime pt = new PrettyTime(clock.getTime());
        pt.removeUnit(JustNow.class);

        String time = pt.format(p.getDate());

        return p.getUser().getName() + " - " + p.getText() + " (" + time + ")";
    }
}
