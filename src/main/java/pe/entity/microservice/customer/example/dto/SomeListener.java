package pe.entity.microservice.customer.example.dto;

import java.util.concurrent.atomic.AtomicInteger;

public abstract class SomeListener {
	private static final AtomicInteger counter = new AtomicInteger(1);
	private final int id;

	public SomeListener() {
		id = counter.getAndIncrement();
	}

	public abstract void priceTick(PriceTick event);

	public abstract void error(Throwable throwable);

	@Override
	public String toString() {
		return String.format("Listener ID:%d:%s", id, super.toString());
	}
}
