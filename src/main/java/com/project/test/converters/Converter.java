package com.project.test.converters;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
	
public abstract class Converter<M extends Object, N extends Object, E extends Object> {
	public abstract E modeloReqToEntidad(M m) throws Exception;

	public abstract N entidadToModeloRes(E e);

	protected Log log;

	public Converter() {
		this.log = LogFactory.getLog(getClass());
	}

}
