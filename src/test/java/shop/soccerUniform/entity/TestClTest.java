package shop.soccerUniform.entity;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
class TestClTest {

    @Test
    void test()  {
        TestCl build = TestCl.builder().build();
        log.info("builder={}", build);

        TestCl build1 = TestCl.builder()
                .username("gksdlsgh")
                .password("1234")
                .build();

        log.info("build1={}", build1);
    }
}