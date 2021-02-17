package org.sean.review.jacobincal.junit5;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.junit.platform.commons.util.StringUtils;

import java.time.Duration;
import java.util.Arrays;
import java.util.Iterator;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.DynamicTest.dynamicTest;

public class JUnit5Test {

    @DisplayName("测试异常")
    @Test
    void exceptionTest() {
        assertThrows(RuntimeException.class, () -> {
            throw new RuntimeException("测试异常!");
        });
    }

    @DisplayName("超时异常")
    @Test
    void timeoutTest() {
        assertTimeout(Duration.ofMillis(1000), () -> {
            Thread.sleep(500);
        });
    }

    @DisplayName("默认参数话设置")
    @ValueSource(strings = {"one", "two", "three"})
    @ParameterizedTest
    void parameterizedTest(String parameter) {
        assertTrue(StringUtils.isNotBlank(parameter), () -> {
            return "不为空测试";
        });
    }

    @DisplayName("从cvs文件参数化测试")
    @CsvFileSource(resources = "/junit5/parameterizedWithCsv.csv")
    @ParameterizedTest
    void parameterizedTestWithCsv(String name, int age) {
        assertNotNull(name);
        assertNotNull(age);
    }

    @DisplayName("从方法参数化测试")
    @MethodSource("parameterizedWithMethod")
    @ParameterizedTest
    void parameterizedTestWithMethod(String parameter) {
        assertNotNull(parameter);
    }

    static Stream<String> parameterizedWithMethod() {
        return Stream.of("one", "two", "three");
    }

    @Nested
    class NestedInJUnit {

        @DisplayName("嵌套测试与重复测试")
        @RepeatedTest(3)
        void nestedTest() {
            assertTrue(true);
        }

    }

    @DisplayName("动态单元测试")
    @TestFactory
    Iterator<DynamicTest> dynamicTests() {
        return Arrays.asList(
                dynamicTest("第一个动态测试", () -> assertTrue(true)),
                dynamicTest("第二个动态测试", () -> assertTrue(true))
        ).iterator();
    }

}
