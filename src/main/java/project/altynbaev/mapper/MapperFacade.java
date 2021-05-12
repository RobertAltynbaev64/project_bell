package project.altynbaev.mapper;

import java.util.List;

public interface MapperFacade {

    <S, D> D map(S sourceObject, Class<D> destinationClass);

    <S, D> List<D> mapAsList(Iterable<S> source, Class<D> destinationClass);

}
