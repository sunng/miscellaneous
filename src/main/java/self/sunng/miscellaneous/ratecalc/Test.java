package self.sunng.miscellaneous.ratecalc;

import org.joda.time.Days;
import org.joda.time.LocalDate;
import org.joda.time.Months;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by sunxiaodong on 2017/3/30.
 */
public class Test {

    // 0
    private static BigDecimal ZERO = new BigDecimal(0);
    // 1
    private static BigDecimal ONE = new BigDecimal(1);
    // 一年12个月
    private static BigDecimal MONTH_COUNT_OF_YEAR = new BigDecimal(12);
    // 一年的计息天数
    private static BigDecimal DAY_COUNT_OF_YEAR = new BigDecimal(360);
    // 利息小数点后保留位数
    private static int MONEY_SCALE = 2;
    // 利率小数点后保留位数
    private static int RATE_SCALE = 10;

    public static void main(String[] args) {

        List<RepayTask> plan1 = genRepayPlanByEqualPrincipal(new BigDecimal(10000), LocalDate.now(), LocalDate.now().plusDays(41), 3, new BigDecimal("0.1"));

        for (RepayTask repayTask : plan1) {
            System.out.println(" 还款期数 " + repayTask.getRepayTerm());
            System.out.println(" 还款日期 " + repayTask.getRepayDate());
            System.out.println(" 归还本金 " + repayTask.getPrincipal());
            System.out.println(" 归还利息 " + repayTask.getInterest());
            System.out.println(" 剩余本金 " + repayTask.getLeftPrincipal());
            System.out.println("--------------------------");
        }

        System.out.println("---------------------------------------------------------");

        List<RepayTask> plan2 = genRepayPlanByEqualPrincipalAddInterest(new BigDecimal(10000), LocalDate.now(), LocalDate.now().plusDays(41), 3, new BigDecimal("0.1"));

        for (RepayTask repayTask : plan2) {
            System.out.println(" 还款期数 " + repayTask.getRepayTerm());
            System.out.println(" 还款日期 " + repayTask.getRepayDate());
            System.out.println(" 归还本金 " + repayTask.getPrincipal());
            System.out.println(" 归还利息 " + repayTask.getInterest());
            System.out.println(" 剩余本金 " + repayTask.getLeftPrincipal());
            System.out.println("--------------------------");
        }

        System.out.println("---------------------------------------------------------");

        List<RepayTask> plan3 = genRepayPlanByOnlyInterest(new BigDecimal(10000), LocalDate.now(), LocalDate.now().plusDays(41), 3, new BigDecimal("0.1"));

        for (RepayTask repayTask : plan3) {
            System.out.println(" 还款期数 " + repayTask.getRepayTerm());
            System.out.println(" 还款日期 " + repayTask.getRepayDate());
            System.out.println(" 归还本金 " + repayTask.getPrincipal());
            System.out.println(" 归还利息 " + repayTask.getInterest());
            System.out.println(" 剩余本金 " + repayTask.getLeftPrincipal());
            System.out.println("--------------------------");
        }

    }

    /**
     * 等额本金生成还款计划
     * 等额本金计算公式：每月还款金额 = （贷款本金 ÷ 还款月数）+（本金 — 已归还本金累计额）×每月利率
     *
     * @param amount         贷款总额
     * @param startDate      计息日
     * @param firstRepayDate 首次还款日
     * @param terms          期数
     * @param yearRate       年利率 如:12% 为 0.12
     * @return 还款计划
     */
    private static List<RepayTask> genRepayPlanByEqualPrincipal(BigDecimal amount, LocalDate startDate, LocalDate firstRepayDate, int terms, BigDecimal yearRate) {
        //月利率
        BigDecimal monthRate = yearRate.divide(MONTH_COUNT_OF_YEAR, RATE_SCALE, RoundingMode.UP);

        List<RepayTask> plan = new ArrayList<>();
        BigDecimal bdTerms = new BigDecimal(terms);
        BigDecimal everyMonthPrincipal = amount.divide(bdTerms, MONEY_SCALE, BigDecimal.ROUND_DOWN);
        BigDecimal totalLeft = amount.subtract(everyMonthPrincipal.multiply(bdTerms));
        BigDecimal leftPrincipal = new BigDecimal(amount.doubleValue());
        for (int i = 0; i < terms; i++) {
            RepayTask repayTask = new RepayTask();
            repayTask.setRepayTerm(i + 1);
            repayTask.setRepayDate(firstRepayDate.plusMonths(i));
            repayTask.setPrincipal(i == terms - 1 ? everyMonthPrincipal.add(totalLeft) : everyMonthPrincipal);

            //首次还款与放款时间之间的间隔不是一个整月,需单独计算首月利息
            if (i == 0 && !startDate.isEqual(firstRepayDate.minusMonths(1))) {
                //日利率
                BigDecimal dayRate = yearRate.divide(DAY_COUNT_OF_YEAR, RATE_SCALE, RoundingMode.UP);
                int months = Months.monthsBetween(startDate, firstRepayDate).getMonths();
                //基于还款日不能是29、30、31这三天,所以计算日期时采用的是结束日期减去月数来计算多出来的天数,其他地方相同
                int days = Days.daysBetween(startDate, firstRepayDate.minusMonths(months)).getDays();
                repayTask.setInterest(amount.multiply(dayRate).multiply(new BigDecimal(days)).add(amount.multiply(monthRate).multiply(new BigDecimal(months))));
            } else {
                repayTask.setInterest(leftPrincipal.multiply(monthRate));
            }
            repayTask.setInterest(repayTask.getInterest().setScale(2, RoundingMode.UP));
            repayTask.setLeftPrincipal(leftPrincipal = leftPrincipal.subtract(repayTask.getPrincipal()));

            plan.add(repayTask);
        }

        return plan;
    }


    /**
     * 等额本息生成还款计划
     * 等额本息计算公式：
     * 月均还款: (贷款本金 × 月利率 × (1＋月利率)＾还款月数) ÷ ((1＋月利率)＾还款月数 － 1)
     * 每月归还利息: (贷款本金 x 月利率 - 月均还款) x (1 + 月利率)^(还款期数 - 1) + 月均还款
     *
     * @param amount         贷款总额
     * @param startDate      计息日
     * @param firstRepayDate 首次还款日
     * @param terms          期数
     * @param yearRate       年利率 如:12% 为 0.12
     * @return 还款计划
     */
    private static List<RepayTask> genRepayPlanByEqualPrincipalAddInterest(BigDecimal amount, LocalDate startDate, LocalDate firstRepayDate, int terms, BigDecimal yearRate) {

        if (startDate.isAfter(firstRepayDate.minusMonths(1))) {
            System.out.println("等额本金首次还款时间距离放款时间不能小于一个月");
            throw new RuntimeException("等额本金首次还款时间距离放款时间不能小于一个月");
        }
        //月利率
        BigDecimal monthRate = yearRate.divide(MONTH_COUNT_OF_YEAR, RATE_SCALE, RoundingMode.UP);
        //月还金额
        BigDecimal monthRepay = amount.multiply(monthRate).multiply(monthRate.add(ONE).pow(terms)).divide(monthRate.add(ONE).pow(terms).subtract(ONE), MONEY_SCALE, RoundingMode.DOWN);
        System.out.println("每月还款 " + monthRepay);
        BigDecimal totalInterest = amount.subtract(monthRepay.multiply(new BigDecimal(terms))).setScale(MONEY_SCALE);
        System.out.println("总利息 " + totalInterest);

        List<RepayTask> plan = new ArrayList<>();
        BigDecimal leftPrincipal = new BigDecimal(amount.doubleValue());
        for (int i = 0; i < terms; i++) {
            RepayTask repayTask = new RepayTask();
            repayTask.setRepayTerm(i + 1);
            repayTask.setRepayDate(firstRepayDate.plusMonths(i));
            repayTask.setInterest(amount.multiply(monthRate).subtract(monthRepay).multiply(monthRate.add(ONE).pow(i)).add(monthRepay).setScale(MONEY_SCALE, RoundingMode.UP));
            //如果是最后一期,最后一期本金等于剩余本金
            if (i == terms - 1) {
                repayTask.setPrincipal(leftPrincipal);
            } else {
                repayTask.setPrincipal(monthRepay.subtract(repayTask.getInterest()));
            }

            // 首次还款日距离放款日大于一个月,需加上多出来时间的利息
            BigDecimal moreInterest = new BigDecimal(0);
            if (i == 0 && startDate.isBefore(firstRepayDate.minusMonths(1))) {
                //日利率
                BigDecimal dayRate = yearRate.divide(DAY_COUNT_OF_YEAR, RATE_SCALE, RoundingMode.UP);
                int months = Months.monthsBetween(startDate, firstRepayDate.minusMonths(1)).getMonths();
                int days = Days.daysBetween(startDate, firstRepayDate.minusMonths(1 + months)).getDays();
                moreInterest = amount.multiply(dayRate).multiply(new BigDecimal(days)).add(amount.multiply(monthRate).multiply(new BigDecimal(months))).setScale(2, RoundingMode.UP);
                repayTask.setInterest(repayTask.getInterest().add(moreInterest));
            }

            repayTask.setLeftPrincipal(leftPrincipal = leftPrincipal.subtract(repayTask.getPrincipal()));

            plan.add(repayTask);
        }

        return plan;

    }

    //

    /**
     * 按月还息到期还本生成还款计划
     *
     * @param amount         贷款总额
     * @param startDate      计息日
     * @param firstRepayDate 首次还款日
     * @param terms          期数
     * @param yearRate       年利率 如:12% 为 0.12
     * @return 还款计划
     */
    private static List<RepayTask> genRepayPlanByOnlyInterest(BigDecimal amount, LocalDate startDate, LocalDate firstRepayDate, int terms, BigDecimal yearRate) {
        //月利率
        BigDecimal monthRate = yearRate.divide(MONTH_COUNT_OF_YEAR, RATE_SCALE, RoundingMode.UP);

        List<RepayTask> plan = new ArrayList<>();
        for (int i = 0; i < terms; i++) {
            RepayTask repayTask = new RepayTask();
            repayTask.setRepayTerm(i + 1);
            repayTask.setRepayDate(firstRepayDate.plusMonths(i));
            if (i == terms - 1) {
                repayTask.setPrincipal(amount);
            } else {
                repayTask.setPrincipal(ZERO);
            }

            //首次还款与放款时间之间的间隔不是一个整月,需单独计算首期利息
            if (i == 0 && !startDate.isEqual(firstRepayDate.minusMonths(1))) {
                //日利率
                BigDecimal dayRate = yearRate.divide(DAY_COUNT_OF_YEAR, RATE_SCALE, RoundingMode.UP);
                int months = Months.monthsBetween(startDate, firstRepayDate).getMonths();
                int days = Days.daysBetween(startDate, firstRepayDate.minusMonths(months)).getDays();
                repayTask.setInterest(amount.multiply(dayRate).multiply(new BigDecimal(days)).add(amount.multiply(monthRate).multiply(new BigDecimal(months))));
            } else {
                repayTask.setInterest(amount.multiply(monthRate));
            }
            repayTask.setInterest(repayTask.getInterest().setScale(2, RoundingMode.UP));
            repayTask.setLeftPrincipal(amount);

            plan.add(repayTask);
        }

        return plan;
    }
}
