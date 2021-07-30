package br.ufc.mdcc.spoonmetrics.miner;

import br.ufc.mdcc.spoonmetrics.model.Dataset;
import br.ufc.mdcc.spoonmetrics.model.Measure;
import br.ufc.mdcc.spoonmetrics.model.Metric;
import br.ufc.mdcc.spoonmetrics.util.Util;
import spoon.processing.AbstractProcessor;
import spoon.reflect.declaration.CtClass;
import spoon.reflect.declaration.CtMethod;

public class NOSEExtractor extends AbstractProcessor<CtClass<?>> {

	@Override
	public void process(CtClass<?> element) {

		if (Util.isValid(element)) {
			double numberOfThrowsExceptions = 0;
			String qualifiedName = element.getQualifiedName();

			for (CtMethod<?> method : element.getMethods()) {
				if (method.getThrownTypes().size() > 0) {
					numberOfThrowsExceptions++;
				}
			}
			Dataset.store(qualifiedName, new Measure(Metric.NOSE, numberOfThrowsExceptions));
		}
	}

}
