package self.sunng.miscellaneous.match;

import org.joda.time.DateTime;

import java.math.BigDecimal;

/**
 * Created by sunxiaodong on 2016/12/19.
 */
public class Match {

    private int lendID;

    private int loanID;

    private BigDecimal amount;

    private DateTime startTime;

    private int matchDays;

    public int getLendID() {
        return lendID;
    }

    public void setLendID(int lendID) {
        this.lendID = lendID;
    }

    public int getLoanID() {
        return loanID;
    }

    public void setLoanID(int loanID) {
        this.loanID = loanID;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public DateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(DateTime startTime) {
        this.startTime = startTime;
    }

    public int getMatchDays() {
        return matchDays;
    }

    public void setMatchDays(int matchDays) {
        this.matchDays = matchDays;
    }

    public Match() {

    }

    public Match(int lendID, int loanID, BigDecimal amount, DateTime startTime, int matchDays) {
        this.lendID = lendID;
        this.loanID = loanID;
        this.amount = amount;
        this.startTime = startTime;
        this.matchDays = matchDays;
    }

    @Override
    public String toString() {
        return String.format("loanid:\t%d;\tlendid:\t%d;\tamount:\t%s;\tmatchdays:\t%d", loanID, lendID, amount.toString(), matchDays);
    }
}
