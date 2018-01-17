package de.moogle.gui.application;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public enum SearchTypes {
	
	FULLTEXT("Volltextsuche"),
	PERSON("Personensuche"),
	ORGANIZATION("Organisationssuche"),
	PERS_ORG("Personen- und Organisationssuche");
	
	private String typeName;
	
	private SearchTypes(String name) {
		typeName = name;
	}
	
	public static ObservableList<String> getAllTypeNames() {
		String full = FULLTEXT.getName();
		String person = PERSON.getName();
		String org = ORGANIZATION.getName();
		String pers_org = PERS_ORG.getName();
		return FXCollections.observableArrayList(full, person, org, pers_org);
	}
	
	public String getName() {
		return typeName;
	}

}
