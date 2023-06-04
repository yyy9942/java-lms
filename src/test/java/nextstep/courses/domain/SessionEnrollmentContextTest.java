package nextstep.courses.domain;

import nextstep.users.domain.NsUserTest;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

class SessionEnrollmentContextTest {

    private Session JAVA_TDD_SESSION;

    private Session KOTLIN_TDD_SESSION;

    @BeforeEach
    void init() {
        JAVA_TDD_SESSION = new Session(
                LocalDate.of(2021, 1, 1),
                LocalDate.of(2021, 1, 2),
                "url",
                Session.BillType.FREE,
                new Price(0L),
                100L,
                0L
        );

        KOTLIN_TDD_SESSION = new Session(
                LocalDate.of(2021, 1, 1),
                LocalDate.of(2021, 1, 2),
                "url",
                Session.BillType.PAID,
                new Price(1000L),
                1L,
                0L
        );
    }

    @Test
    @DisplayName("강의 생성시 초기 상태는 NOT_STARTED 이다")
    void testCreateStatus() {
        // given
        SessionEnrollmentContext sessionEnrollmentContext = new SessionEnrollmentContext(
                100L
        );

        // when&then
        Assertions.assertThat(sessionEnrollmentContext.statusEquals(SessionEnrollmentContext.Status.NOT_STARTED)).isTrue();
    }

    @Test
    @DisplayName("강의 시작시 상태는 IN_PROGRESS 이다")
    void testStart() {
        // given
        SessionEnrollmentContext sessionEnrollmentContext = new SessionEnrollmentContext(
                100L
        );

        // when
        sessionEnrollmentContext.start();

        // then
        Assertions.assertThat(sessionEnrollmentContext.statusEquals(SessionEnrollmentContext.Status.IN_PROGRESS)).isTrue();
    }

    @Test
    @DisplayName("강의 종료시 상태는 FINISHED 이다")
    void testEnd() {
        // given
        SessionEnrollmentContext sessionEnrollmentContext = new SessionEnrollmentContext(
                100L
        );

        // when
        sessionEnrollmentContext.end();

        // then
        Assertions.assertThat(sessionEnrollmentContext.statusEquals(SessionEnrollmentContext.Status.FINISHED)).isTrue();
    }
}
