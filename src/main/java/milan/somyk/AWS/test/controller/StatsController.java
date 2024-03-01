package milan.somyk.AWS.test.controller;

import lombok.RequiredArgsConstructor;
import milan.somyk.AWS.test.dto.response.ResponseContainer;
import milan.somyk.AWS.test.service.StatsService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class StatsController {
    private final StatsService statsService;
    @GetMapping(value = "/stats", params = "date")
    ResponseEntity<ResponseContainer> findByDate(@RequestParam String date) throws InterruptedException {
        ResponseContainer statsByDate = statsService.getStatsByDate(date);
        return ResponseEntity.status(statsByDate.getStatusCode()).body(statsByDate);
    }
    @GetMapping(value ="/stats", params = {"startDate","endDate"})
    ResponseEntity<ResponseContainer> findByDates(@RequestParam String startDate, @RequestParam String endDate) throws InterruptedException {
        ResponseContainer statsByDates = statsService.getStatsByDates(startDate, endDate);
        return ResponseEntity.status(statsByDates.getStatusCode()).body(statsByDates);
    }
    @GetMapping(value = "/stats", params = "asin")
    ResponseEntity<ResponseContainer> findByAsin(@RequestParam String asin) throws InterruptedException {
        ResponseContainer statsByAsin = statsService.getStatsByAsin(asin);
        return ResponseEntity.status(statsByAsin.getStatusCode()).body(statsByAsin);
    }
    @GetMapping(value = "/stats")
    ResponseEntity<ResponseContainer> findByAsinList(@RequestBody List<String> asinList) throws InterruptedException {
        ResponseContainer statsByAsinList = statsService.getStatsByAsinList(asinList);
        return ResponseEntity.status(statsByAsinList.getStatusCode()).body(statsByAsinList);
    }
    @GetMapping(value = "/stats/sum", params = "type")
    ResponseEntity<ResponseContainer> findSumByDate(@RequestParam String type) throws InterruptedException {
        ResponseContainer sumStatsByDate = statsService.getSumStats(type);
        return ResponseEntity.status(sumStatsByDate.getStatusCode()).body(sumStatsByDate);
    }
}
