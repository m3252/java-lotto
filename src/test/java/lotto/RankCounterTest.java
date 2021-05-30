package lotto;

import lotto.domain.Lotto;
import lotto.domain.entity.LottoPrice;
import lotto.domain.Result;
import lotto.domain.entity.LottoList;
import lotto.domain.entity.Number;
import lotto.domain.RankCounter;
import lotto.domain.entity.Rank;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class RankCounterTest {

    @Test
    @DisplayName("일치하는 숫자에 등수를 확인한다.")
    public void 등수_확인() {
        Lotto winningLotto = new Lotto(new Number(1), new Number(2), new Number(3), new Number(4), new Number(5), new Number(6));
        Number bonusNumber = new Number(10);
        Result result = new Result(winningLotto, bonusNumber);

        LottoList lottoList = new LottoList(
                new Lotto(new Number(1), new Number(2), new Number(3), new Number(4), new Number(5), new Number(6)) // 1등
                , new Lotto(new Number(1), new Number(2), new Number(3), new Number(4), new Number(5), new Number(10)) // 2등
                , new Lotto(new Number(1), new Number(2), new Number(3), new Number(4), new Number(5), new Number(11)) // 3등
                , new Lotto(new Number(1), new Number(2), new Number(3), new Number(4), new Number(8), new Number(10)) // 4등
        );

        result.confirm(lottoList, new LottoPrice("1000"));
        RankCounter rankCounter = result.rank();

        assertThat(rankCounter.countByRank(Rank.FIRST)).isEqualTo(1);
        assertThat(rankCounter.countByRank(Rank.SECOND)).isEqualTo(1);
        assertThat(rankCounter.countByRank(Rank.THIRD)).isEqualTo(1);
        assertThat(rankCounter.countByRank(Rank.FOURTH)).isEqualTo(1);
        assertThat(rankCounter.countByRank(Rank.FIFTH)).isEqualTo(0);
    }
}
