package clock;

import java.util.Date;

/**
 * Not thread safe
 */
public class FakeSystemClock implements SystemClock {

    private Date date = new Date();

    public void increment(long n) {
        long newTime = date.getTime() + n;
        date = new Date(newTime);
    }

    public void setTime(long t) {
        date = new Date(t);
    }

    @Override
    public Date getTime() {
        return date;
    }
}
