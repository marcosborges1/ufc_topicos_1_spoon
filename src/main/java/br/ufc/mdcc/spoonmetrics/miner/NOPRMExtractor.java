package br.ufc.mdcc.spoonmetrics.miner;

import br.ufc.mdcc.spoonmetrics.model.Dataset;
import br.ufc.mdcc.spoonmetrics.model.Measure;
import br.ufc.mdcc.spoonmetrics.model.Metric;
import br.ufc.mdcc.spoonmetrics.util.Util;
import spoon.processing.AbstractProcessor;
import spoon.reflect.declaration.CtClass;
import spoon.reflect.declaration.CtMethod;

public class NOPRMExtractor extends AbstractProcessor<CtClass<?>> {
	
	@Override
	public void process(CtClass<?> element) {

		double numberOfDeclaratedPrivateMethods = 0;

		if (Util.isValid(element)) {
			String qualifiedName = element.getQualifiedName();
			for (CtMethod<?> method : element.getMethods()) {
				if (method.isPrivate())
					numberOfDeclaratedPrivateMethods++;
			}

			Dataset.store(qualifiedName, new Measure(Metric.NOPRM, numberOfDeclaratedPrivateMethods));
		}
	}

}
