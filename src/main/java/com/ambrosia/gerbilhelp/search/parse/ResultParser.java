package com.ambrosia.gerbilhelp.search.parse;

import com.google.gson.Gson;

import java.util.List;

public class ResultParser {
	private static final Gson gson = new Gson();

	// Used to deserialize JSON, and for absolutely nothing else
	private record Items(List<Result> items) {}

	public static List<Result> getResults(String json) {
		return gson.fromJson(json, Items.class).items;
	}
}
