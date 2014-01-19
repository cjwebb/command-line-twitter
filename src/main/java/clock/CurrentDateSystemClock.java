package clock;

import java.util.Date;

public class CurrentDateSystemClock implements SystemClock {
    @Override
    public Date getTime() {
        return new Date();
    }
}
