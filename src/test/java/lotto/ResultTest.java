package lotto;

import lotto.domain.Lotto;
import lotto.domain.entity.LottoPrice;
import lotto.domain.Result;
import lotto.domain.entity.LottoList;
import lotto.domain.entity.Number;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

public class ResultTest {

    private LottoList lottoList;
    private Result result;
    private LottoPrice lottoPrice;
    private Lotto winningLotto, firstLotto, secondLotto, thirdLotto, fourthLotto, fifthLotto, missLotto;

    @BeforeEach
    public void setUp() {
        lottoPrice = new LottoPrice("1000");
        winningLotto = new Lotto(Arrays.asList(10, 12, 23, 26, 28, 44)); // 지난주 당첨 번호
        Number bonusNumber = Number.of(34);

        firstLotto = new Lotto(Arrays.asList(10, 12, 23, 26, 28, 44)); // 1등 - 1장
        secondLotto = new Lotto(Arrays.asList(10, 12, 23, 26, 28, 34)); // 2등 - 1장
        thirdLotto = new Lotto(Arrays.asList(10, 12, 23, 26, 28, 33)); // 3등 - 1장
        fourthLotto = new Lotto(Arrays.asList(10, 12, 23, 26, 33, 34)); // 4등 - 1장
        fifthLotto = new Lotto(Arrays.asList(10, 12, 23, 32, 33, 34)); // 5등 - 1장
        missLotto = new Lotto(Arrays.asList(1, 2, 3, 4, 5, 6)); // 꽝

        result = new Result(winningLotto, bonusNumber);
    }

    @Test
    @DisplayName("1,2,3,4등을 한번 씩 당첨된 당첨금을 확인한다.")
    public void 당첨금_확인() {
        BigDecimal totalWinnings = new BigDecimal("2031555000");
        lottoList = new LottoList(firstLotto, secondLotto, thirdLotto, fourthLotto, fifthLotto, missLotto);
        result.confirm(lottoList, lottoPrice);
        assertThat(result.winnings()).isEqualTo(totalWinnings);
    }

    @Test
    @DisplayName("로또 구매 리스트에 대한 총 수익률을 구한다. case1: 이천원 내고 오천원 당첨")
    public void 총_수익률_구하기_1() {
        lottoList = new LottoList(fifthLotto, missLotto);
        result.confirm(lottoList, lottoPrice);
        assertThat(result.profitRate()).isEqualTo("2.50");
    }

    @Test
    @DisplayName("로또 구매 리스트에 대한 총 수익률을 구한다. case1: 이천원 내고 2등, 3등 당첨")
    public void 총_수익률_구하기_2() {
        lottoList = new LottoList(secondLotto, thirdLotto);
        result.confirm(lottoList, lottoPrice);
        assertThat(result.profitRate()).isEqualTo("15750.00");
    }

    @Test
    @DisplayName("지난 주 당첨번호에 보너스 번호가 포함되면 = 오류")
    public void 보너스_번호_유효성_확인() {
        assertThatThrownBy(() -> new Result(winningLotto, Number.of(44)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("보너스 볼은 당첨 번호들과 같을 수 없습니다.");
    }
}
