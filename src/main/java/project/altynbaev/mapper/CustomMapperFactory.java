package project.altynbaev.mapper;

import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.FactoryBean;

@Service
public class CustomMapperFactory implements FactoryBean<MapperFactory> {

    @Override
    public MapperFactory getObject() {
        return new DefaultMapperFactory.Builder()
                .build();
    }

    @Override
    public Class<?> getObjectType() {
        return DefaultMapperFactory.class;
    }

}
