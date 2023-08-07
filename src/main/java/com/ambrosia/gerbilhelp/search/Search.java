package com.ambrosia.gerbilhelp.search;

import com.ambrosia.gerbilhelp.internal.RequestManager;
import com.ambrosia.gerbilhelp.internal.RequestResult;

public record Search(String subject, String topic, String question) {
	private String build() {
		return subject + " " + topic + " " + question;
	}

	public RequestResult evaluate() {
		return RequestManager.makeRequest(this.build());
	}
}
