package milan.somyk.AWS.test.repository;

import milan.somyk.AWS.test.dto.ReportSpecification;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface ReportSpecificationRepository extends MongoRepository<ReportSpecification, String> {
    ReportSpecification findReportSpecificationByDataStartTimeAndDataEndTime(String dataStartTime, String dataEndTime);

}
