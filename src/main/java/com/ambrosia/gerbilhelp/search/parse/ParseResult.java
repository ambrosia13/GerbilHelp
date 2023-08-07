package com.ambrosia.gerbilhelp.search.parse;

import com.google.gson.Gson;

import java.util.List;

public class ParseResult {
	record Items(List<Result> items) {}

	public static void test(String json) {
		Gson gson = new Gson();


		var items = gson.fromJson(json, Items.class);

		System.out.println(items);
//		for (var result : items.results) {
//			System.out.println(result);
//		}
		System.out.println(json);
	}
}
