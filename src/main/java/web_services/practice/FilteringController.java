package web_services.practice;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
public class FilteringController {

    //field1, field3
    @GetMapping("/filtering")
    public MappingJacksonValue retrieveSomeBean () {
        SomeBean someBean = new SomeBean ("value1", "value2", "value3");

        SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("field1","field3");

        FilterProvider filters = new SimpleFilterProvider().addFilter("SomeBeanFilter", filter);

        MappingJacksonValue mapping = new MappingJacksonValue((someBean));
        mapping.setFilters(filters);

        return mapping;
    }

    //field2, field3
    @GetMapping("/filtering-list")
    public MappingJacksonValue retrieveListOfSomeBean () {
        List someBeanList =  Arrays.asList(new SomeBean ("value1", "value2", "value3"), new SomeBean ("value11", "value21", "value31"));

        SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("field2","field3");

        FilterProvider filters = new SimpleFilterProvider().addFilter("SomeBeanFilter", filter);

        MappingJacksonValue mapping = new MappingJacksonValue((someBeanList));
        mapping.setFilters(filters);

        return mapping;
    }
}
