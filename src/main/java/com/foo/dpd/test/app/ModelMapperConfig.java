package com.foo.dpd.test.app;

import com.foo.dpd.test.dto.PersonDto;
import com.foo.dpd.test.entity.Person;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {

    @Bean(name = "modelMapperWithoutChild")
    public ModelMapper modelMapperWithoutChild() {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.addMappings(new PropertyMap<PersonDto, Person>() {
            @Override
            protected void configure() {
                skip(destination.getPhoneNumbers());
                skip(destination.getAdresses());
            }
        });

        return modelMapper;
    }

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
}
