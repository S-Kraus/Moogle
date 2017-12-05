package de.moogle.ner;

import java.io.IOException;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.logging.Logger;

import edu.stanford.nlp.ie.AbstractSequenceClassifier;
import edu.stanford.nlp.ie.crf.CRFClassifier;
import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.util.Triple;

/**
 * InitSets einmalig für jede Datei ausführen, damit die Sets gefüllt werden.
 * Danach kann auf die Getter Funktionen zugegriffen werden. Bei Hauptprogramm
 * als VM Parameter folgendes mitgeben: -mx400m -cp "*"
 */

public class NERDemo {

	private static final Logger log = Logger.getLogger(NERDemo.class.getName());
	private LinkedHashSet<String> orgs = new LinkedHashSet<String>();

	private LinkedHashSet<String> pers = new LinkedHashSet<String>();

	/**
	 * 
	 * @param file
	 *            Dateiname inkl. Pfad
	 */
	public void initSets(String fileContents) throws ClassCastException, ClassNotFoundException, IOException {

		String serializedClassifier = "edu/stanford/nlp/models/ner/german.conll.hgc_175m_600.crf.ser.gz";

		AbstractSequenceClassifier<CoreLabel> classifier = CRFClassifier.getClassifier(serializedClassifier);

		/*
		 * For either a file to annotate or for the hardcoded text example, this demo
		 * file shows several ways to process the input, for teaching purposes.
		 */

		// String fileContents = IOUtils.slurpFile(file);
		List<Triple<String, Integer, Integer>> list = classifier.classifyToCharacterOffsets(fileContents);
		for (Triple<String, Integer, Integer> item : list) {
			String identifier = item.first();
			String name = fileContents.substring(item.second(), item.third());
			switch (identifier) {
			case "I-ORG":
				orgs.add(name);
				break;
			case "I-PER":
				pers.add(name);
				break;
			}
			// log.info(item.first() + ": " + fileContents.substring(item.second(),
			// item.third()));
		}
		log.info("Organisationen: " + orgs.toString().replace("[", "").replace("]", ""));
		log.info("Personen: " + pers.toString().replace("[", "").replace("]", ""));

	}

	public LinkedHashSet<String> getOrgs() {
		return orgs;
	}

	public LinkedHashSet<String> getPers() {
		return pers;
	}

}
