package com.seda.payer.core.bean;

import java.io.Serializable;


/**
 * 
 * @author ddiemdio
 *
 */

public class Paginazione implements Serializable {

	private static final long serialVersionUID = 1L;

    private int rowsPerPage;

    private int pageNumber;

    private java.lang.String order;


    
    public Paginazione() { 

        this.rowsPerPage = 5;
        this.pageNumber = 1;
        this.order = "";
            
    }

    public Paginazione(
            int rowsPerPage,
            int pageNumber,
            java.lang.String order) {
            this.rowsPerPage = rowsPerPage;
            this.pageNumber = pageNumber;
            this.order = order;
     }

	public int getRowsPerPage() {
		return rowsPerPage;
	}

	public void setRowsPerPage(int rowsPerPage) {
		this.rowsPerPage = rowsPerPage;
	}

	public int getPageNumber() {
		return pageNumber;
	}

	public void setPageNumber(int pageNumber) {
		this.pageNumber = pageNumber;
	}

	public java.lang.String getOrder() {
		return order;
	}

	public void setOrder(java.lang.String order) {
		this.order = order;
	}

    
}