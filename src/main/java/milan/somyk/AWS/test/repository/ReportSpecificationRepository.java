package milan.somyk.AWS.test.repository;

import milan.somyk.AWS.test.dto.ReportSpecification;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ReportSpecificationRepository extends MongoRepository<ReportSpecification, String> {
    ReportSpecification findReportSpecificationByDataStartTimeAndDataEndTime(String dataStartTime, String dataEndTime);

}
