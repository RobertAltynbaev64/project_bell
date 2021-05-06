package project.altynbaev.dictionary.country.view;

import io.swagger.annotations.ApiModelProperty;

public class CountryView {
    @ApiModelProperty(value = "Название страны", example = "Россия")
    public String name;

    @ApiModelProperty(value = "Код страны", example = "643")
    public Integer code;

    public CountryView(String name, Integer code) {
        this.name = name;
        this.code = code;
    }
}
