package com.platform.testing.domain.testcase;

import com.platform.testing.domain.common.ValueObject;

import java.util.List;
import java.util.Map;
import java.util.Objects;

public record TestParameter(String parameterName, List<Map<String, String>> dataRowList) implements ValueObject {
    public TestParameter {
        Objects.requireNonNull(parameterName);
        if (dataRowList == null) {
            dataRowList = List.of();
        }
    }
}
