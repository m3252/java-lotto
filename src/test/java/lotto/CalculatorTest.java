package lotto;

import lotto.domain.Calculator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;

public class CalculatorTest {

    private Calculator calculator;

    @BeforeEach
    public void setUp() {
        calculator = new Calculator();
    }

    @ParameterizedTest
    @DisplayName("구입금액, 로또 가격으로 수량을 계산한다")
    @CsvSource(value = {"14000:14", "14500:14", "900:0"}, delimiter = ':')
    public void calculate_lotto_ticket(int input, int result) {
        assertThat(calculator.calculateLottoTicket(input)).isEqualTo(result);
    }

    public void fail_to_buy() {

    }
}
