package milan.somyk.AWS.test.dto;

import lombok.Data;
import milan.somyk.AWS.test.document.StatsByAsin;
import milan.somyk.AWS.test.document.StatsByDate;

import java.util.List;
@Data
public class ReportFile {

    ReportSpecification reportSpecification;
    List<StatsByDate> salesAndTrafficByDate;
    List<StatsByAsin> salesAndTrafficByAsin;
}
