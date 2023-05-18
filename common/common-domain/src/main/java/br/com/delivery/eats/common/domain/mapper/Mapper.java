package br.com.delivery.eats.common.domain.mapper;

public interface Mapper<I,O> {
    O map(I i);
}
