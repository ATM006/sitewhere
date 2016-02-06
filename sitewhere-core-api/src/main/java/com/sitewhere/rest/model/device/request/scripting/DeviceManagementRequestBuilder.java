/*
 * Copyright (c) SiteWhere, LLC. All rights reserved. http://www.sitewhere.com
 *
 * The software in this package is published under the terms of the CPAL v1.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE.txt file.
 */
package com.sitewhere.rest.model.device.request.scripting;

import com.sitewhere.rest.model.device.request.DeviceCommandCreateRequest;
import com.sitewhere.rest.model.device.request.DeviceCreateRequest;
import com.sitewhere.rest.model.device.request.DeviceSpecificationCreateRequest;
import com.sitewhere.rest.model.device.request.SiteCreateRequest;
import com.sitewhere.spi.SiteWhereException;
import com.sitewhere.spi.device.IDeviceManagement;
import com.sitewhere.spi.device.IDeviceSpecification;
import com.sitewhere.spi.device.ISite;
import com.sitewhere.spi.device.command.IDeviceCommand;

/**
 * Builder that supports creating device management entities.
 * 
 * @author Derek
 */
public class DeviceManagementRequestBuilder {

	/** Device management implementation */
	private IDeviceManagement deviceManagement;

	public DeviceManagementRequestBuilder(IDeviceManagement deviceManagement) {
		this.deviceManagement = deviceManagement;
	}

	public SiteCreateRequest.Builder newSite(String token, String name) {
		return new SiteCreateRequest.Builder(token, name);
	}

	public ISite persist(SiteCreateRequest.Builder builder) throws SiteWhereException {
		return getDeviceManagement().createSite(builder.build());
	}

	public DeviceSpecificationCreateRequest.Builder newSpecification(String token, String name,
			String assetModuleId, String assetId) {
		return new DeviceSpecificationCreateRequest.Builder(token, name, assetModuleId, assetId);
	}

	public IDeviceSpecification persist(DeviceSpecificationCreateRequest.Builder builder)
			throws SiteWhereException {
		return getDeviceManagement().createDeviceSpecification(builder.build());
	}

	public DeviceCommandCreateRequest.Builder newCommand(String token, String namespace, String name) {
		return new DeviceCommandCreateRequest.Builder(token, namespace, name);
	}

	public IDeviceCommand persist(IDeviceSpecification specification,
			DeviceCommandCreateRequest.Builder builder) throws SiteWhereException {
		return getDeviceManagement().createDeviceCommand(specification, builder.build());
	}

	public DeviceCreateRequest.Builder newDevice(String siteToken, String specificationToken,
			String hardwareId) {
		return new DeviceCreateRequest.Builder(siteToken, specificationToken, hardwareId);
	}

	public void persist(DeviceCreateRequest.Builder builder) throws SiteWhereException {
		getDeviceManagement().createDevice(builder.build());
	}

	public IDeviceManagement getDeviceManagement() {
		return deviceManagement;
	}

	public void setDeviceManagement(IDeviceManagement deviceManagement) {
		this.deviceManagement = deviceManagement;
	}
}