package com.ambrosia.gerbilhelp;

import com.ambrosia.gerbilhelp.internal.RequestResult;
import com.ambrosia.gerbilhelp.search.Search;
import com.ambrosia.gerbilhelp.search.parse.ResultParser;

public class Main {
	public static void main(String[] args) {
		var search = new Search("Math", "Integrals", "How to do u-substitution");
		var requestResult = search.evaluate();

		if (requestResult.isSuccess()) {
			var success = (RequestResult.Success) requestResult;

			String json = success.result().unwrap();
			var results = ResultParser.getResults(json);

			for (var result : results) {
				System.out.println(result.display());
				System.out.println();
			}
		}
	}
}
