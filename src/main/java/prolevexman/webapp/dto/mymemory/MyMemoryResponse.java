package prolevexman.webapp.dto.mymemory;

public class MyMemoryResponse {

    private ResponseData responseData;

    public MyMemoryResponse(ResponseData responseData) {
        this.responseData = responseData;
    }

    public ResponseData getResponseData() {
        return responseData;
    }

    public void setResponseData(ResponseData responseData) {
        this.responseData = responseData;
    }
}
