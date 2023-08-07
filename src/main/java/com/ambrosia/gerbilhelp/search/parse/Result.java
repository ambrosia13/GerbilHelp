package com.ambrosia.gerbilhelp.search.parse;

public record Result(String title, String link, String displayLink) {
	private static String hyperlink(String link) {
		return "<a href=\"" + link + "\">" + link + "</a>";
	}

	public String display() {
		return this.title + " at " + hyperlink(this.displayLink) + "<br>" + hyperlink(this.link);
	}
}
