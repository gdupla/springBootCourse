package web_services.practice;

import com.fasterxml.jackson.annotation.JsonFilter;

@JsonFilter("SomeBeanFilter")
public class SomeBean {
    private String field1;
    private String field2;
    private String field3;

    public SomeBean(String value1, String value2, String value3) {
        super();
        field1 = value1;
        field2 = value2;
        field3 = value3;
    }

    public String getField1() {
        return field1;
    }

    public void setField1(String field) {
        this.field1 = field;
    }

    public String getField2() {
        return field2;
    }

    public void setField2(String field) {
        this.field2 = field;
    }

    public String getField3() {
        return field3;
    }

    public void setField3(String field) {
        this.field3 = field;
    }
}
