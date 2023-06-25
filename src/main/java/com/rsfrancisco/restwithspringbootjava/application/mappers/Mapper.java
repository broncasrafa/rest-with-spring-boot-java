package com.rsfrancisco.restwithspringbootjava.application.mappers;

import com.github.dozermapper.core.DozerBeanMapperBuilder;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;

public class Mapper {
    private static com.github.dozermapper.core.Mapper _mapper = com.github.dozermapper.core.DozerBeanMapperBuilder.buildDefault();
    //private static ModelMapper _mapper = new ModelMapper();


    public static <Source, Destination> Destination mapper(Source source, Class<Destination> destination) {
        return _mapper.map(source, destination);
    }

    public static <Source, Destination> List<Destination> mapper(List<Source> sourceList, Class<Destination> destination) {
        List<Destination> destinationList = new ArrayList<>();
        for(Source source : sourceList) {
            destinationList.add(_mapper.map(source, destination));
        }
        return destinationList;
    }
}
