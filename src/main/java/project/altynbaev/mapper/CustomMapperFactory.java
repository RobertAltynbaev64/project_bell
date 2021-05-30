package project.altynbaev.mapper;

import project.altynbaev.dto.user.UserGetDto;
import project.altynbaev.dto.user.UserSaveDto;
import project.altynbaev.dto.user.UserUpdateDto;
import project.altynbaev.model.User;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.FactoryBean;

@Service
public class CustomMapperFactory implements FactoryBean<MapperFactory> {
    @Override
    public MapperFactory getObject() {
        MapperFactory mapperFactory = new DefaultMapperFactory.Builder().mapNulls(false)
                .constructorResolverStrategy(null)
                .build();
        mapperFactory.classMap(UserUpdateDto.class, User.class)
                .field("docNumber", "document.documentNumber")
                .field("docDate", "document.documentDate").byDefault().register();

        mapperFactory.classMap(User.class, UserGetDto.class)
                .field("document.documentNumber", "docNumber")
                .field("document.documentDate", "docDate").byDefault().register();

        mapperFactory.classMap(UserSaveDto.class, User.class)
                .field("docNumber", "document.documentNumber")
                .field("docDate", "document.documentDate").byDefault().register();

        return mapperFactory;
    }

    @Override
    public Class<?> getObjectType() {
        return DefaultMapperFactory.class;
    }

}
