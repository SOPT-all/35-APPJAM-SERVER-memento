package com.official.memento.tag.domain.enums;

public enum TagColor {

    RED("#FF426E"),
    PINK("#EE8AAD"),
    ORANGE("#FF8162"),
    YELLOW("#FFE483"),
    LIGHT_GREEN("#7BD27D"),
    MINT("#149C95"),
    CYAN("#6CA9E1"),
    BLUE("#3867FF"),
    PURPLE("#7B5DFF");

    private String hexCode;

    TagColor(String hexCode) {
        this.hexCode = hexCode;
    }

    public String getHexCode() {
        return hexCode;
    }
}
