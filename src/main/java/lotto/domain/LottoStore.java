package lotto.domain;

import lotto.domain.entity.LottoList;

import java.math.BigDecimal;

public final class LottoStore {

    private final BigDecimal lottoPrice = LottoPrice.PRICE;

    public BigDecimal price() {
        return lottoPrice;
    }

    public LottoList toSell(BigDecimal amountReceived) {
        BigDecimal purchaseCount = amountReceived.divide(price(), BigDecimal.ROUND_DOWN);
        return lottoGenerator(purchaseCount.intValue());
    }

    private LottoList lottoGenerator(int purchaseCount) {
        return new LottoList(purchaseCount);
    }

}
