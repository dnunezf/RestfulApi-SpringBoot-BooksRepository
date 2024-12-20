package com.david.database.config;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MapperConfig
{
    // METHOD THAT ALLOW US TO HAVE ACCESS TO OUR MODEL MAPPER

    // THIS METHOD ALLOW US TO CREATE NESTED OBJECTS (FOR EXAMPLE,
    // WE CREATE A BOOK, AND INSIDE THE BOOK, CREATING AN AUTHOR RELATED
    // TO THE BOOK, SO THE AUTHOR WOULDN'T BE NULL)

    /*EXAMPLE IN POSTMAN ABOUT NESTED OBJECTS (IT CREATES THE BOOK AND THE AUTHOR)
    * {
        "isbn": "1-B",
        "title": "BOOK 2",
        "author": {
            "name": "David",
            "age": 20
        }
      }
    * */

    @Bean
    public ModelMapper modelMapper()
    {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
        return modelMapper;
    }
}
