import model.Output;
import model.Post;

/**
 *
 */
public class Formatter {
    public String format(Output output) {
        for (Post p: output.getPosts()){
            System.out.println(p.toString());
        }
        return "";
    }
}
