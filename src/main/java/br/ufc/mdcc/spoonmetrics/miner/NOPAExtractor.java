package br.ufc.mdcc.spoonmetrics.miner;

import br.ufc.mdcc.spoonmetrics.model.Dataset;
import br.ufc.mdcc.spoonmetrics.model.Measure;
import br.ufc.mdcc.spoonmetrics.model.Metric;
import br.ufc.mdcc.spoonmetrics.util.Util;
import spoon.processing.AbstractProcessor;
import spoon.reflect.declaration.CtClass;
import spoon.reflect.declaration.CtField;

public class NOPAExtractor extends AbstractProcessor<CtClass<?>> {

	@Override
	public void process(CtClass<?> element) {

		double numberOfDeclaratedPublicFields = 0;

		if (Util.isValid(element)) {
			String qualifiedName = element.getQualifiedName();
			for (CtField<?> field : element.getFields()) {
				if (field.isPublic())
					numberOfDeclaratedPublicFields++;
			}

			Dataset.store(qualifiedName, new Measure(Metric.NOPA, numberOfDeclaratedPublicFields));
		}
	}

}
