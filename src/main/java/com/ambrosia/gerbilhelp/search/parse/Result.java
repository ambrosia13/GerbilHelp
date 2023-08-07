package com.ambrosia.gerbilhelp.search.parse;

public record Result(String title, String link, String displayLink) {
	public String display() {
		return this.title + " at " + this.displayLink + "\n" + this.link;
	}
}
