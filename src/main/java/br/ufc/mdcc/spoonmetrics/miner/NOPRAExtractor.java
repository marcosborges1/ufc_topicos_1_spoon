package br.ufc.mdcc.spoonmetrics.miner;

import br.ufc.mdcc.spoonmetrics.model.Dataset;
import br.ufc.mdcc.spoonmetrics.model.Measure;
import br.ufc.mdcc.spoonmetrics.model.Metric;
import br.ufc.mdcc.spoonmetrics.util.Util;
import spoon.processing.AbstractProcessor;
import spoon.reflect.declaration.CtClass;
import spoon.reflect.declaration.CtField;

public class NOPRAExtractor extends AbstractProcessor<CtClass<?>> {

	@Override
	public void process(CtClass<?> element) {

		double numberOfDeclaratedPrivateFields = 0;

		if (Util.isValid(element)) {
			String qualifiedName = element.getQualifiedName();
			for (CtField<?> field : element.getFields()) {
				if (field.isPrivate())
					numberOfDeclaratedPrivateFields++;
			}

			Dataset.store(qualifiedName, new Measure(Metric.NOPRA, numberOfDeclaratedPrivateFields));
		}
	}

}
