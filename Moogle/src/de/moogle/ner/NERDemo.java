package de.moogle.ner;

import java.io.IOException;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.logging.Logger;

import edu.stanford.nlp.ie.AbstractSequenceClassifier;
import edu.stanford.nlp.ie.crf.CRFClassifier;
import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.util.RuntimeInterruptedException;
import edu.stanford.nlp.util.Triple;

/**
 * InitSets einmalig für jede Datei ausführen, damit die Sets gefüllt werden.
 * Danach kann auf die Getter Funktionen zugegriffen werden. Bei Hauptprogramm
 * als VM Parameter folgendes mitgeben: -mx400m -cp "*"
 */

public class NERDemo {

	private static final Logger log = Logger.getLogger(NERDemo.class.getName());
	
	private static NERDemo ner;
	
	private String serializedClassifierDE;
	private AbstractSequenceClassifier<CoreLabel> classifierDE;
	private String serializedClassifierEN;
	private AbstractSequenceClassifier<CoreLabel> classifierEN;
	
	private LinkedHashSet<String> orgs = new LinkedHashSet<String>();

	private LinkedHashSet<String> pers = new LinkedHashSet<String>();

	public static NERDemo getInstance() throws ClassCastException, ClassNotFoundException, IOException {
		if (ner == null) {
			ner = new NERDemo();
		}
		return ner;
	}
	
	
	private NERDemo() throws ClassCastException, ClassNotFoundException, IOException {
		serializedClassifierDE = "edu/stanford/nlp/models/ner/german.conll.hgc_175m_600.crf.ser.gz";
		classifierDE = CRFClassifier.getClassifier(serializedClassifierDE);
		serializedClassifierEN = "english.all.3class.distsim.crf.ser.gz";
		classifierEN = CRFClassifier.getClassifier(serializedClassifierEN);
	}
	/**
	 * 
	 * @param file
	 *            Dateiname inkl. Pfad
	 */
	public void fillSets(String fileContents) throws ClassCastException, ClassNotFoundException, IOException, RuntimeInterruptedException {


		/*
		 * For either a file to annotate or for the hardcoded text example, this demo
		 * file shows several ways to process the input, for teaching purposes.
		 */

		// String fileContents = IOUtils.slurpFile(file);
		List<Triple<String, Integer, Integer>> listDE = classifierDE.classifyToCharacterOffsets(fileContents);
		for (Triple<String, Integer, Integer> item : listDE) {
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
		List<Triple<String, Integer, Integer>> listEN = classifierEN.classifyToCharacterOffsets(fileContents);
		for (Triple<String, Integer, Integer> item : listEN) {
			String identifier = item.first();
			String name = fileContents.substring(item.second(), item.third());
			switch (identifier) {
			case "ORGANIZATION":
				orgs.add(name);
				break;
			case "PERSON":
				pers.add(name);
				break;
			}
			// log.info(item.first() + ": " + fileContents.substring(item.second(),
			// item.third()));
		}
		log.info("Organisationen: " + orgs.toString().replace("[", "").replace("]", ""));
		log.info("Personen: " + pers.toString().replace("[", "").replace("]", ""));

	}
	
	public void clearSets() {
		orgs.clear();
		pers.clear();
	}

	public String getOrgs() {
		String orgsString = orgs.toString().replace("[", "").replace("]", "");
		return orgsString;
	}

	public String getPers() {
		String persString = pers.toString().replace("[", "").replace("]", "");
		return persString;
	}

}
