package project.altynbaev.dto;

public class ResponseDto {

    private String error;

    public ResponseDto(String error) {
        this.error = error;
    }

    public ResponseDto() {
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

}
