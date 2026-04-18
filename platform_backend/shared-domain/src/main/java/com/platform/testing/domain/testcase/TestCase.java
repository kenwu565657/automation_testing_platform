package com.platform.testing.domain.testcase;

import com.platform.testing.domain.common.AggregateRoot;
import com.platform.testing.domain.constant.Priority;
import com.platform.testing.domain.constant.TestType;
import com.platform.testing.domain.testsuite.TestSuiteId;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

public class TestCase implements AggregateRoot {

    private final TestCaseId id;
    private String name;
    private String description;
    private String featureTitle;
    private String scenarioTitle;
    private TestType testType;
    private Priority priority;
    private TestSuiteId testSuiteId;
    private final List<GherkinStep> steps;
    private final List<TestParameter> parameters;
    private final Set<String> tags;
    private boolean active;
    private int timeoutSeconds;
    private int retryCount;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private String createdBy;

    // ── Factory: create new ──
    public static TestCase create(String name, String featureTitle, String scenarioTitle,
                                  TestType testType, Priority priority, String createdBy) {
        return new TestCase(TestCaseId.generate(), name, featureTitle, scenarioTitle,
                testType, priority, createdBy);
    }

    // ── Factory: reconstitute from DB ──
    public static TestCase reconstitute(
            TestCaseId id, String name, String description,
            String featureTitle, String scenarioTitle,
            TestType testType, Priority priority, TestSuiteId testSuiteId,
            List<GherkinStep> steps, List<TestParameter> parameters, Set<String> tags,
            boolean active, int timeoutSeconds, int retryCount,
            LocalDateTime createdAt, LocalDateTime updatedAt, String createdBy) {
        TestCase tc = new TestCase(id, name, featureTitle, scenarioTitle, testType, priority, createdBy);
        tc.description = description;
        tc.testSuiteId = testSuiteId;
        tc.steps.addAll(steps);
        tc.parameters.addAll(parameters);
        tc.tags.addAll(tags);
        tc.active = active;
        tc.timeoutSeconds = timeoutSeconds;
        tc.retryCount = retryCount;
        tc.createdAt = createdAt;
        tc.updatedAt = updatedAt;
        return tc;
    }

    private TestCase(TestCaseId id, String name, String featureTitle,
                     String scenarioTitle, TestType testType,
                     Priority priority, String createdBy) {
        this.id = Objects.requireNonNull(id);
        this.name = Objects.requireNonNull(name);
        this.featureTitle = Objects.requireNonNull(featureTitle);
        this.scenarioTitle = Objects.requireNonNull(scenarioTitle);
        this.testType = Objects.requireNonNull(testType);
        this.priority = priority != null ? priority : Priority.MEDIUM;
        this.createdBy = createdBy;
        this.steps = new ArrayList<>();
        this.parameters = new ArrayList<>();
        this.tags = new LinkedHashSet<>();
        this.active = true;
        this.timeoutSeconds = 60;
        this.retryCount = 0;
        this.createdAt = LocalDateTime.now();
        this.updatedAt = this.createdAt;
    }

    // ── Domain behavior ──

    public void addStep(GherkinStep step) {
        Objects.requireNonNull(step);
        boolean duplicate = steps.stream().anyMatch(s -> s.orderIndex() == step.orderIndex());
        if (duplicate) {
            throw new IllegalArgumentException("Duplicate step orderIndex: " + step.orderIndex());
        }
        this.steps.add(step);
        this.updatedAt = LocalDateTime.now();
    }

    public void replaceSteps(List<GherkinStep> newSteps) {
        this.steps.clear();
        this.steps.addAll(newSteps);
        this.updatedAt = LocalDateTime.now();
    }

    public void removeStep(int orderIndex) {
        this.steps.removeIf(s -> s.orderIndex() == orderIndex);
        this.updatedAt = LocalDateTime.now();
    }

    public void addParameter(TestParameter parameter) {
        this.parameters.add(parameter);
        this.updatedAt = LocalDateTime.now();
    }

    public void addTag(String tag) {
        this.tags.add(tag.startsWith("@") ? tag : "@" + tag);
        this.updatedAt = LocalDateTime.now();
    }

    public void removeTag(String tag) {
        this.tags.remove(tag.startsWith("@") ? tag : "@" + tag);
        this.updatedAt = LocalDateTime.now();
    }

    public void assignToSuite(TestSuiteId suiteId) {
        this.testSuiteId = suiteId;
        this.updatedAt = LocalDateTime.now();
    }

    public void updateDescription(String description) {
        this.description = description;
        this.updatedAt = LocalDateTime.now();
    }

    public void deactivate() {
        this.active = false;
        this.updatedAt = LocalDateTime.now();
    }

    public void activate() {
        this.active = true;
        this.updatedAt = LocalDateTime.now();
    }

    // ── Query methods ──

    public List<GherkinStep> backgroundSteps() {
        return steps.stream().filter(GherkinStep::background).collect(Collectors.toList());
    }

    public List<GherkinStep> scenarioSteps() {
        return steps.stream().filter(s -> !s.background()).collect(Collectors.toList());
    }

    public int stepCount() { return steps.size(); }

    // ── Getters ──
    public TestCaseId getId() { return id; }
    public String getName() { return name; }
    public String getDescription() { return description; }
    public String getFeatureTitle() { return featureTitle; }
    public String getScenarioTitle() { return scenarioTitle; }
    public TestType getTestType() { return testType; }
    public Priority getPriority() { return priority; }
    public TestSuiteId getTestSuiteId() { return testSuiteId; }
    public List<GherkinStep> getSteps() { return Collections.unmodifiableList(steps); }
    public List<TestParameter> getParameters() { return Collections.unmodifiableList(parameters); }
    public Set<String> getTags() { return Collections.unmodifiableSet(tags); }
    public boolean isActive() { return active; }
    public int getTimeoutSeconds() { return timeoutSeconds; }
    public int getRetryCount() { return retryCount; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public LocalDateTime getUpdatedAt() { return updatedAt; }
    public String getCreatedBy() { return createdBy; }
}
