/*THIS CLASS ALLOW US TO CONVERT FROM AUTHOR DTO TO AUTHOR ENTITY, AND VICEVERSE*/
/*MAP ONE CLASS TO THE OTHER*/

package com.david.database.mappers.impl;

import com.david.database.domain.dto.AuthorDto;
import com.david.database.domain.entities.AuthorEntity;
import com.david.database.mappers.Mapper;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class AuthorMapperImpl implements Mapper<AuthorEntity, AuthorDto> {
    private ModelMapper modelMapper;

    public AuthorMapperImpl(ModelMapper modelMapper)
    {
        this.modelMapper = modelMapper;
    }

    @Override
    public AuthorDto mapTo(AuthorEntity authorEntity) {
        return modelMapper.map(authorEntity, AuthorDto.class);
    }

    @Override
    public AuthorEntity mapFrom(AuthorDto authorDto) {
        return modelMapper.map(authorDto, AuthorEntity.class);
    }
}
