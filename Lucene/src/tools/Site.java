package tools;

public enum Site {

	GAMESTAR("http://www.gamestar.de"),
	GAMEPRO("http://www.gamepro.de"),
	FOURPLAYERS("http://feeds.4players.de"),
	CHIP("http://www.chip.de"),
	GIGA("http://www.giga.de"),
	GOLEM("https://rss.golem.de"),
	IGN("http://de.ign.com");
	
	private final String site;
	
	Site(String site) {
		this.site = site;
	}
	
	public String getSite() {
		return this.site;
	}
}
