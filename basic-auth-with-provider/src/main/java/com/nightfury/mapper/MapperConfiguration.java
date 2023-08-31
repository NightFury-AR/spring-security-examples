package com.nightfury.mapper;

import org.mapstruct.CollectionMappingStrategy;
import org.mapstruct.MapperConfig;
import org.mapstruct.NullValueCheckStrategy;

@MapperConfig(componentModel = "spring",collectionMappingStrategy = CollectionMappingStrategy.TARGET_IMMUTABLE,nullValueCheckStrategy = NullValueCheckStrategy.ON_IMPLICIT_CONVERSION)
public interface MapperConfiguration {
}
