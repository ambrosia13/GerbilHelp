package com.ambrosia.gerbilhelp;

import com.ambrosia.gerbilhelp.internal.RequestManager;
import com.ambrosia.gerbilhelp.internal.RequestResult;
import com.ambrosia.gerbilhelp.search.parse.ParseResult;

public class Main {
	public static void main(String[] args) {
		var result = RequestManager.makeRequest("Derivatives");

		if (result.isSuccess()) {
			var success = (RequestResult.Success) result;

			String json = success.result().unwrap();
			ParseResult.test(json);
		}
	}
}
