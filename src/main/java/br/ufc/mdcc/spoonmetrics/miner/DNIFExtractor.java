package br.ufc.mdcc.spoonmetrics.miner;

import br.ufc.mdcc.spoonmetrics.model.Dataset;
import br.ufc.mdcc.spoonmetrics.model.Measure;
import br.ufc.mdcc.spoonmetrics.model.Metric;
import br.ufc.mdcc.spoonmetrics.util.Util;
import spoon.processing.AbstractProcessor;
import spoon.reflect.code.CtIf;
import spoon.reflect.declaration.CtClass;
import spoon.reflect.visitor.filter.TypeFilter;

public class DNIFExtractor extends AbstractProcessor<CtClass<?>> {

	@Override
	public void process(CtClass<?> element) {

		if (Util.isValid(element)) {
			String qualifiedName = element.getQualifiedName();
			int depthMaximunsIfs = 0;
			for (CtIf currentIf : element.getElements(new TypeFilter<CtIf>(CtIf.class))) {
				if (depthOfNestedIf(currentIf) > depthMaximunsIfs) {
					depthMaximunsIfs = depthOfNestedIf(currentIf);
				}
			}
			Dataset.store(qualifiedName, new Measure(Metric.DNIF, depthMaximunsIfs));
		}
	}

	private int depthOfNestedIf(CtIf ifstmt) {
		int greaterDepthOfNested = 0;
		for (CtIf element : ifstmt.getThenStatement().getElements(new TypeFilter<CtIf>(CtIf.class))) {
			int value = depthOfNestedIf(element) + 1;
			if (value > greaterDepthOfNested) {
				greaterDepthOfNested = value;
			}
		}
		return greaterDepthOfNested;
	}

}
