import java.io.IOException;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.logging.Logger;

import edu.stanford.nlp.ie.AbstractSequenceClassifier;
import edu.stanford.nlp.ie.crf.CRFClassifier;
import edu.stanford.nlp.io.IOUtils;
import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.util.Triple;

/**
 * This is a demo of calling CRFClassifier programmatically.
 * <p>
 * Usage:
 * {@code java -mx400m -cp "*" NERDemo [serializedClassifier [fileName]] }
 * <p>
 * If arguments aren't specified, they default to
 * classifiers/english.all.3class.distsim.crf.ser.gz and some hardcoded sample
 * text. If run with arguments, it shows some of the ways to get k-best
 * labelings and probabilities out with CRFClassifier. If run without arguments,
 * it shows some of the alternative output formats that you can get.
 * <p>
 * To use CRFClassifier from the command line:
 * </p>
 * <blockquote>
 * {@code java -mx400m edu.stanford.nlp.ie.crf.CRFClassifier -loadClassifier [classifier] -textFile [file] }
 * </blockquote>
 * <p>
 * Or if the file is already tokenized and one word per line, perhaps in a
 * tab-separated value format with extra columns for part-of-speech tag, etc.,
 * use the version below (note the 's' instead of the 'x'):
 * </p>
 * <blockquote>
 * {@code java -mx400m edu.stanford.nlp.ie.crf.CRFClassifier -loadClassifier [classifier] -testFile [file] }
 * </blockquote>
 *
 * @author Jenny Finkel
 * @author Christopher Manning
 */

public class NERDemo {
	
	private static final Logger log = Logger.getLogger(NERDemo.class.getName());
	private LinkedHashSet<String> orgs = new LinkedHashSet<String>();

	private LinkedHashSet<String> pers = new LinkedHashSet<String>();

	public void initSets(String file) throws ClassCastException, ClassNotFoundException, IOException {

		String serializedClassifier = "edu/stanford/nlp/models/ner/german.conll.hgc_175m_600.crf.ser.gz";

		AbstractSequenceClassifier<CoreLabel> classifier = CRFClassifier.getClassifier(serializedClassifier);

		/*
		 * For either a file to annotate or for the hardcoded text example, this demo
		 * file shows several ways to process the input, for teaching purposes.
		 */
		
		String fileContents = IOUtils.slurpFile(file);
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
			log.info(item.first() + ": " + fileContents.substring(item.second(), item.third()));
		}
		log.info("Organisationen: " + orgs.toString());
		log.info("Personen: " + pers.toString());

	}
	
	public LinkedHashSet<String> getOrgs() {
		return orgs;
	}

	public LinkedHashSet<String> getPers() {
		return pers;
	}

}
