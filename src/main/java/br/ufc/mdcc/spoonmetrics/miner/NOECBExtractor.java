package br.ufc.mdcc.spoonmetrics.miner;

import br.ufc.mdcc.spoonmetrics.model.Dataset;
import br.ufc.mdcc.spoonmetrics.model.Measure;
import br.ufc.mdcc.spoonmetrics.model.Metric;
import br.ufc.mdcc.spoonmetrics.util.Util;
import spoon.processing.AbstractProcessor;
import spoon.reflect.code.CtCatch;
import spoon.reflect.declaration.CtClass;
import spoon.reflect.visitor.filter.TypeFilter;

public class NOECBExtractor extends AbstractProcessor<CtClass<?>> {

	@Override
	public void process(CtClass<?> element) {

		if (Util.isValid(element)) {
			double numberOfEmptyCatchBlocks = 0;
			String qualifiedName = element.getQualifiedName();
			for (CtCatch currentCatch : element.getElements(new TypeFilter<CtCatch>(CtCatch.class))) {
				if (currentCatch.getBody().getStatements().size() == 0) {
					numberOfEmptyCatchBlocks++;
				}
			}
			Dataset.store(qualifiedName, new Measure(Metric.NOECB, numberOfEmptyCatchBlocks));
		}
	}

}
