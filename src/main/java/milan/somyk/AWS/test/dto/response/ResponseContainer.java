package milan.somyk.AWS.test.dto.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Data
@NoArgsConstructor
public class ResponseContainer {
    private Object result;
    private String errorMessage;
    private boolean isError;
    @JsonIgnore
    private int statusCode;

    public ResponseContainer setErrorMessageAndStatusCode(String errorMessage, int statusCode) {
        this.isError = true;
        this.errorMessage = errorMessage;
        this.statusCode = statusCode;
        return this;
    }
    public ResponseContainer setResultAndStatusCode(Object result, int statusCode){
        this.result = result;
        this.statusCode = statusCode;
        return this;
    }

    public void setErrorMessage(String errorMessage) {
        this.isError = true;
        this.errorMessage = errorMessage;
    }
    public ResponseContainer setSuccessResult(Object result){
        this.result = result;
        this.statusCode = HttpStatus.OK.value();
        return this;
    }
    public void setCreatedResult(Object result){
        this.result = result;
        this.statusCode = HttpStatus.CREATED.value();
    }

    public ResponseContainer(Object result, int statusCode){
        this.statusCode = statusCode;
        this.result = result;
    }
}



