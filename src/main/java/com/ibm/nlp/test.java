package com.ibm.nlp;

import java.util.ArrayList;
import java.util.List;

public class test {
	public static void main(String[] args) {
		List list = new ArrayList();
		list.add("hello");
		list.add(2);
		System.out.print(list.get(0) instanceof Object);
		System.out.print(list.get(1) instanceof Integer);
	}

}
