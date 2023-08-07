package com.ambrosia.gerbilhelp.search.parse;

import com.google.gson.Gson;

import java.util.List;

public class ResultParser {
	record Items(List<Result> items) {}

	public static List<Result> getResults(String json) {
		var gson = new Gson();
		return gson.fromJson(json, Items.class).items;
	}
}
