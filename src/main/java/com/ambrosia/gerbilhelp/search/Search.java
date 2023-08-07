package com.ambrosia.gerbilhelp.search;

public record Search(
	String subject,
	String topic,
	String question
) {
	public String build() {
		return subject + topic + question;
	}
}
