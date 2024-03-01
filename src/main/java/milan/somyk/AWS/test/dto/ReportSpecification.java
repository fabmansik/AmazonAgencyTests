package milan.somyk.AWS.test.dto;

import lombok.Data;

import java.util.List;
@Data
public class ReportSpecification {
    String reportType;
    ReportOptions reportOptions;
    String dataStartTime;
    String dataEndTime;
    List<String> marketplaceIds;
}
