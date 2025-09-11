package com.devsu.customer_service.application.contract;

public interface Mapper<T, F> {

	F map(T input);
}
