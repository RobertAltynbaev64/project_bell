package project.altynbaev.dictionary.doc.view;

import io.swagger.annotations.ApiModelProperty;

public class DocView {
    @ApiModelProperty(value = "Название документа", example = "Паспорт гражданина Российской Федерации")
    public String name;

    @ApiModelProperty(value = "Код документа", example = "21")
    public Integer code;

    public DocView(String name, Integer code) {
        this.name = name;
        this.code = code;
    }
}
