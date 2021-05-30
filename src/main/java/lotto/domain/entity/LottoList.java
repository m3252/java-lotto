package lotto.domain.entity;

import lotto.domain.Lotto;
import lotto.domain.generator.AutomaticLottoNumbersGenerator;

import java.util.*;
import java.util.function.Consumer;

public final class LottoList {

    private final List<Lotto> lottoList = new ArrayList<>();

    public LottoList(int purchaseCount) {
        for (int i = 0; i < purchaseCount; i++) {
            lottoList.add(new Lotto(new AutomaticLottoNumbersGenerator()));
        }
    }

    public LottoList(Lotto... lottoArrays) {
        lottoList.addAll(Arrays.asList(lottoArrays));
    }

    public int size() {
        return lottoList.size();
    }

    public Lotto get(int index) {
        return lottoList.get(index);
    }

    public void forEach(Consumer<? super Lotto> action) {
        Objects.requireNonNull(action);
        for (Lotto lotto : lottoList) {
            action.accept(lotto);
        }
    }

    @Override
    public String toString() {
        return "LottoList = " + lottoList;
    }
}
