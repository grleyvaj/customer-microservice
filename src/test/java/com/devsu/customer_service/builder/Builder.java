package com.devsu.customer_service.builder;

public abstract class Builder<T> {

	public abstract T build(int index);

	public T build(){
		return this.build(1);
	}

}
