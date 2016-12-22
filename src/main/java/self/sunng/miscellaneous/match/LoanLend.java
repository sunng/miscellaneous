package self.sunng.miscellaneous.match;

/**
 * Created by sunxiaodong on 2016/12/19.
 */
public class LoanLend {

    private Lend lend;
    private Loan loan;

    public Lend getLend() {
        return lend;
    }

    public void setLend(Lend lend) {
        this.lend = lend;
    }

    public Loan getLoan() {
        return loan;
    }

    public void setLoan(Loan loan) {
        this.loan = loan;
    }

    public LoanLend(Loan loan, Lend lend) {
        this.lend = lend;
        this.loan = loan;
    }
}
