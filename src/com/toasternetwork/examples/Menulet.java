package com.toasternetwork.examples;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Function;

public abstract class Menulet<T> {
	protected Map<String, Function<Menulet<?>,T>> menu;
	protected boolean isBuilt;

	public Menulet() {
		menu = new HashMap<>();
	}

	void display(){
		AtomicInteger i = new AtomicInteger(1);
		menu.forEach((c,m) -> System.out.printf("%d) %s\n", i.getAndIncrement(),c));
	}

	public int choose() {
		Scanner sc = new Scanner(System.in);

		display();

		System.out.print("Option: ");
		int result = sc.nextInt();
		if(result < 1 || result > menu.size())
			return 0;
		return result-1;
	}

	public T execute(int option) {
		String key = (String)menu.keySet().toArray()[option];
		return menu.get(key).apply(this);
	}

	abstract void build();
}
