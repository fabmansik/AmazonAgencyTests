package milan.somyk.AWS.test.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import milan.somyk.AWS.test.dto.ReportFile;
import milan.somyk.AWS.test.dto.response.ResponseContainer;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.io.File;

@Service
@RequiredArgsConstructor
@Slf4j
public class JsonFileService {
    public ResponseContainer updateFile(){
        ObjectMapper mapper = new ObjectMapper();
        ResponseContainer responseContainer = new ResponseContainer();
        ReportFile reportFile;
        try {
            reportFile = mapper.readValue(new File("src/test_report.json"), ReportFile.class);
        } catch (Exception e){
            log.error(e.getMessage());
            return responseContainer.setErrorMessageAndStatusCode(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value());
        }
        log.info("file updated");
        return responseContainer.setSuccessResult(reportFile);
    }
}
