/**
 * 
 */
package com.qa.listeners;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

import org.testng.IAnnotationTransformer;
import org.testng.annotations.IDataProviderAnnotation;
import org.testng.annotations.IFactoryAnnotation;
import org.testng.annotations.IListenersAnnotation;
import org.testng.annotations.ITestAnnotation;

import com.qa.retryAnalyzer.RetryAnalyzer;

/**
 * @author sharmaa11
 *
 */
public class AnnotationTransformer implements IAnnotationTransformer {

	@Override
	public void transform (final ITestAnnotation annotation, @SuppressWarnings("rawtypes") final Class testClass, @SuppressWarnings("rawtypes") final Constructor testConstructor, final Method testMethod) {
	annotation.setRetryAnalyzer (RetryAnalyzer.class);
	}
	
	@Override
	public void transform(IDataProviderAnnotation annotation, Method method) {
		IAnnotationTransformer.super.transform(annotation, method);
	}

	@Override
	public void transform(IFactoryAnnotation annotation, Method method) {
		IAnnotationTransformer.super.transform(annotation, method);
	}

	@Override
	public void transform(IListenersAnnotation annotation, Class<?> testClass) {
		IAnnotationTransformer.super.transform(annotation, testClass);
	}
}
