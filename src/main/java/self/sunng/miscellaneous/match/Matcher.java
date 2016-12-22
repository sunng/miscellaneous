package self.sunng.miscellaneous.match;

import org.joda.time.DateTime;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by sunxiaodong on 2016/12/19.
 */
public class Matcher {

    private static List<Lend> lendList = new ArrayList<>();
    private static List<Loan> loanList = new ArrayList<>();

    private static List<LoanLend> loanLendList = new LinkedList<>();

    private static List<Match> matchList = new LinkedList<>();

    public static void main(String[] args) {
        init();
        match();
        showResult();
    }

    private static void init() {
        initLendList();
        initLoadList();
        initLendLoanList();
    }

    private static void initLendList() {

        for (int i = 1; i < 5; i++) {
            lendList.add(new Lend(i, DateTime.now(), BigDecimal.valueOf(i * 100), 30 * ((i / 5) + 1)));
        }

    }

    private static void initLoadList() {
        for (int i = 1; i < 5; i++) {
            loanList.add(new Loan(i, DateTime.now(), BigDecimal.valueOf(i * 100), 30 * ((i / 5) + 1)));
        }

    }

    private static void initLendLoanList() {
        for (Loan loan : loanList) {
            for (Lend lend : lendList) {
                loanLendList.add(new LoanLend(loan, lend));
            }
        }

    }

    private static void rebuildLendLoanList() {
        loanLendList.clear();
        for (Loan loan : loanList) {
            if (loan.getUnMatched().compareTo(BigDecimal.ZERO) == 0) {
                continue;
            }
            for (Lend lend : lendList) {
                if (lend.getUnMatched().compareTo(BigDecimal.ZERO) == 0) {
                    continue;
                }
                loanLendList.add(new LoanLend(loan, lend));
            }
        }

    }

    private static void match() {
        if (loanLendList == null) {
            return;
        }
        while (true) {
            if (loanLendList.size() == 0) {
                return;
            }
            boolean rebuildFlag = false;
            for (LoanLend loanLend : loanLendList) {
                if (loanLend.getLoan().getRemainDays() == loanLend.getLend().getRemainDays()) {
                    rebuildFlag = true;
                    matchList.add(doMatch(loanLend));
                    break;
                }
            }
            if (rebuildFlag) {
                rebuildLendLoanList();
            } else {
                break;
            }
        }

        while (true) {
            if (loanLendList.size() == 0) {
                return;
            }
            LoanLend toMatchLoanLend = null;
            int maxLoanLendRemainDaysDiff = Integer.MIN_VALUE;
            for (LoanLend loanLend : loanLendList) {
                if (loanLend.getLoan().getRemainDays() < loanLend.getLend().getRemainDays()) {
                    int remainDaysDiff = loanLend.getLoan().getRemainDays() - loanLend.getLend().getRemainDays();
                    if (remainDaysDiff > maxLoanLendRemainDaysDiff) {
                        maxLoanLendRemainDaysDiff = remainDaysDiff;
                        toMatchLoanLend = loanLend;
                    }
                }
            }
            if (toMatchLoanLend != null) {
                matchList.add(doMatch(toMatchLoanLend));
                rebuildLendLoanList();
            } else {
                break;
            }
        }

        while (true) {
            if (loanLendList.size() == 0) {
                return;
            }
            LoanLend toMatchLoanLend = null;
            int minLoanLendRemainDaysDiff = Integer.MAX_VALUE;
            for (LoanLend loanLend : loanLendList) {
                if (loanLend.getLoan().getRemainDays() > loanLend.getLend().getRemainDays()) {
                    int remainDaysDiff = loanLend.getLoan().getRemainDays() - loanLend.getLend().getRemainDays();
                    if (remainDaysDiff < minLoanLendRemainDaysDiff) {
                        minLoanLendRemainDaysDiff = remainDaysDiff;
                        toMatchLoanLend = loanLend;
                    }
                }
            }
            if (toMatchLoanLend != null) {
                matchList.add(doMatch(toMatchLoanLend));
                rebuildLendLoanList();
            } else {
                break;
            }
        }
    }

    private static Match doMatch(LoanLend loanLend) {

        if (loanLend == null || loanLend.getLend() == null || loanLend.getLoan() == null) {
            return null;
        }

        Match match = new Match();
        match.setLoanID(loanLend.getLoan().getId());
        match.setLendID(loanLend.getLend().getId());
        match.setStartTime(DateTime.now());
        match.setMatchDays(loanLend.getLoan().getRemainDays());
        if (loanLend.getLoan().getUnMatched().compareTo(loanLend.getLend().getUnMatched()) <= 0) {
            match.setAmount(BigDecimal.valueOf(loanLend.getLoan().getUnMatched().doubleValue()));
        } else if (loanLend.getLoan().getAmount().compareTo(loanLend.getLend().getAmount()) > 0) {
            match.setAmount(BigDecimal.valueOf(loanLend.getLend().getUnMatched().doubleValue()));
        }

        loanLend.getLoan().setMatched(loanLend.getLoan().getMatched().add(match.getAmount()));
        loanLend.getLoan().setUnMatched(loanLend.getLoan().getUnMatched().subtract(match.getAmount()));

        loanLend.getLend().setMatched(loanLend.getLend().getMatched().add(match.getAmount()));
        loanLend.getLend().setUnMatched(loanLend.getLend().getUnMatched().subtract(match.getAmount()));

        return match;
    }

    private static void showResult() {

        for (Match match : matchList) {
            System.out.println(match.toString());
        }

    }

}
