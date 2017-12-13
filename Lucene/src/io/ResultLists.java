package io;

import java.util.ArrayList;
import java.util.List;

public class ResultLists {

	
	private static ResultLists lists;
	
	private List<LuceneDocument> fullTextList;
	private List<LuceneDocument> orgTextList;
	private List<LuceneDocument> peopleTextList;
	
	public static ResultLists getInstance() {
		if (lists == null) {
			lists = new ResultLists();
		}
		return lists;
	}
	
	private ResultLists() {
		fullTextList = new ArrayList<>();
		orgTextList = new ArrayList<>();
		peopleTextList = new ArrayList<>();
	}
}
