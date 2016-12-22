package self.sunng.miscellaneous.match;

import org.joda.time.DateTime;

import java.math.BigDecimal;

/**
 * Created by sunxiaodong on 2016/12/19.
 * 出借信息
 */
public class Lend {

    private int id;

    private DateTime startTime;

    private BigDecimal amount;

    private int remainDays;

    private BigDecimal matched;

    private BigDecimal unMatched;

    public int getRemainDays() {
        return remainDays;
    }

    public void setRemainDays(int remainDays) {
        this.remainDays = remainDays;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public DateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(DateTime startTime) {
        this.startTime = startTime;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public BigDecimal getMatched() {
        return matched;
    }

    public void setMatched(BigDecimal matched) {
        this.matched = matched;
    }

    public BigDecimal getUnMatched() {
        return unMatched;
    }

    public void setUnMatched(BigDecimal unMatched) {
        this.unMatched = unMatched;
    }

    public Lend(int id, DateTime startTime, BigDecimal amount, int remainDays) {
        this.id = id;
        this.startTime = startTime;
        this.amount = amount;
        this.remainDays = remainDays;
        this.matched = BigDecimal.ZERO;
        this.unMatched = BigDecimal.valueOf(amount.doubleValue());
    }
}
