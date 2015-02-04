package com.smarthouse.factory;

import com.smarthouse.impl.HttpDao;

public abstract class HttpFactory {

	public abstract HttpDao createFactory();
	
}
