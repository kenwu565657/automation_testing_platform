package com.platform.testing.service.testcase.persistence;

import com.platform.testing.service.testcase.domain.aggregateroot.TestCaseDataDomainModel;
import com.platform.testing.service.testcase.domain.aggregateroot.TestCaseDomainModel;
import com.platform.testing.service.testcase.domain.outputport.persistence.ITestCasePersistenceService;
import com.platform.testing.service.testcase.persistence.mapper.ITestCaseDataMapper;
import com.platform.testing.service.testcase.persistence.mapper.ITestCaseMapper;
import com.platform.testing.service.testcase.persistence.repository.ITestCaseDataRepository;
import com.platform.testing.service.testcase.persistence.repository.ITestCaseRepository;
import com.platform.testing.service.testcase.persistence.repository.ITestPlanTestCaseRelationshipRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TestCasePersistenceService implements ITestCasePersistenceService {
    private final ITestCaseDataMapper testCaseDataMapper;
    private final ITestCaseMapper testCaseMapper;
    private final ITestCaseDataRepository testCaseDataRepository;
    private final ITestCaseRepository testCaseRepository;
    private final ITestPlanTestCaseRelationshipRepository testPlanTestCaseRelationshipRepository;

    @Override
    public List<TestCaseDomainModel> getTestCaseListByTestPlanId(String testPlanId) {
        var testCaseIdList = testPlanTestCaseRelationshipRepository.findTestCaseIdsByTestPlanId(testPlanId);
        if (testCaseIdList == null || testCaseIdList.isEmpty()) {
            return List.of();
        }
        var testCaseEntityList = testCaseRepository.getTestCaseListByIdIn(testCaseIdList);
        return testCaseEntityList.stream().map(testCaseMapper::mapTestCaseEntityToTestCaseDomainModel).collect(Collectors.toList());
    }

    @Override
    public TestCaseDataDomainModel getTestCaseDataById(String testCaseDataId) {
        var testCaseDataEntity = testCaseDataRepository.getTestCaseDataEntityById(testCaseDataId);
        if (testCaseDataEntity != null) {
            return testCaseDataMapper.mapTestCaseDataEntityToTestCaseDataDomainModel(testCaseDataEntity);
        }
        return null;
    }

    @Override
    public boolean saveTestCaseDomainModel(TestCaseDomainModel testCaseDomainModel) {
        try {
            var testCaseEntity = testCaseMapper.mapTestCaseDomainModelToTestCaseEntity(testCaseDomainModel);
            var savedTestCaseEntity = testCaseRepository.save(testCaseEntity);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean saveTestCaseDataDomainModel(TestCaseDataDomainModel testCaseDataDomainModel) {
        try {
            var testCaseDataEntity = testCaseDataMapper.mapTestCaseDataDomainModelToTestCaseDataEntity(testCaseDataDomainModel);
            var savedTestCaseDataEntity = testCaseDataRepository.save(testCaseDataEntity);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}