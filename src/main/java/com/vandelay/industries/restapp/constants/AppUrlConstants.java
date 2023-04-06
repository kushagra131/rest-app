/**
 * 
 */
package com.vandelay.industries.restapp.constants;

/**
 * 	@author Kushagra Sharma
 *	Class contains Application URL Constants
 */
public class AppUrlConstants {
	
	
	/**
	 * Base API Endpoint
	 */
	public static final String BASE_API = "/api/v1";
	
	
	
	/*
	 * --------------------------------------------------------------------------
	 * --------------------- API Endpoints for Factory --------------------------
	 */
	
	
	/**
	 * factories API Endpoint
	 */
	public static final String FACTORY_API = BASE_API + "/factory";
	
	
	/**
	 * List factories in the ERP system
	 */
	public static final String LIST_FACTORIES = "/factories";
	
	
	/**
	 * Add a factory
	 */
	public static final String ADD_FACTORY = "/";
	
	
	/**
	 * Add a machine
	 */
	public static final String ADD_MACHINE_AT_FACTORY = "/{factoryId}/machine";
	
	
	/**
	 * List machines at a given factory
	 */
	public static final String LIST_MACHINES_AT_FACTORY = "/{factoryId}/machines";
	
	
	
	/*
	 * --------------------------------------------------------------------------
	 * --------------------- API Endpoints for Warehouse ------------------------
	 */
	
	
	/**
	 * warehouses API Endpoint
	 */
	public static final String WAREHOUSE_API = BASE_API + "/warehouse";
	
	
	/**
	 * List warehouses in the ERP system
	 */
	public static final String LIST_WAREHOUSES = "/warehouses";
	
	
	/**
	 * Add a warehouse
	 */
	public static final String ADD_WAREHOUSE = "";
	
	
	/**
	 * Add a warehouse
	 */
	public static final String GET_WAREHOUSE = "/{warehouseId}";
	
	
	/**
	 * List inventory at a single warehouse
	 */
	public static final String LIST_INVENTORY_AT_WAREHOUSE = "/{warehouseId}/inventory";
	
	
	/**
	 * Add an inventory at a single warehouse
	 */
	public static final String ADD_INVENTORY_AT_WAREHOUSE = "/{warehouseId}/inventory";
	
	
	/**
	 * Update an existing inventory at a single warehouse
	 */
	public static final String UPDATE_INVENTORY_AT_WAREHOUSE = "/{warehouseId}/inventory";
	

}
