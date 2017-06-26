package self.sunng.miscellaneous.ratecalc;

import lombok.Data;
import org.joda.time.LocalDate;

import java.math.BigDecimal;

/**
 * Created by sunxiaodong on 2017/3/30.
 */
@Data
public class RepayTask {

    private int repayTerm;
    private LocalDate repayDate;
    private BigDecimal principal;
    private BigDecimal interest;
    private BigDecimal leftPrincipal;
}
