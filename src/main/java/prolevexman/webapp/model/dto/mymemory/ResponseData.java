package prolevexman.webapp.model.dto.mymemory;

public class ResponseData {

    private String translatedText;

    public ResponseData(String translatedText) {
        this.translatedText = translatedText;
    }

    public String getTranslatedText() {
        return translatedText;
    }

    public void setTranslatedText(String translatedText) {
        this.translatedText = translatedText;
    }
}
