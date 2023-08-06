package com.ambrosia.gerbilhelp;

import com.ambrosia.gerbilhelp.internal.RequestManager;

public class Main {
	public static void main(String[] args) {
		var result = RequestManager.makeRequest("Derivatives");
		System.out.println(result);
	}
}
