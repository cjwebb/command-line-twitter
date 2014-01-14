import model.Output;
import model.Post;

/**
 *
 */
public class Formatter {
    public String format(Output output) {
        if (!output.getPosts().isEmpty()) {
            for (Post p: output.getPosts()){
                System.out.println(p.toString()); // todo: just construct a string, and pretty print
            }
        }
        return "";
    }
}
