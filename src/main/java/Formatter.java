import model.Output;
import model.Post;
import org.ocpsoft.prettytime.Duration;
import org.ocpsoft.prettytime.PrettyTime;
import org.ocpsoft.prettytime.units.JustNow;

import java.util.Date;
import java.util.Locale;

/**
 *
 */
public class Formatter {
    public String format(Output output) {
        StringBuilder builder = new StringBuilder();
        for (Post p: output.getPosts()) {
            builder.append(prettyPrintPost(p) + "\n");
        }
        return builder.toString();
    }

    private String prettyPrintPost(Post p) {
        PrettyTime pt = new PrettyTime();
        pt.removeUnit(JustNow.class);

        String time = pt.format(p.getDate());

        return p.getUser().getName() + " - " + p.getText() + " (" + time + ")";
    }
}
