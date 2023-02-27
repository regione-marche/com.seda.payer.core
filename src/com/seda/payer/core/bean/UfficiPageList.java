package com.seda.payer.core.bean;

import com.seda.data.spi.PageInfo;

public class UfficiPageList extends PageInfo {
	
		private static final long serialVersionUID = 1L;
		PageInfo pageInfo = new PageInfo();
		String ufficiListXml;
		String retCode;
		String message;
		
		public UfficiPageList() {}
		
		public UfficiPageList(
				PageInfo pageInfo, 
				String retCode,
				String message, 
				String ufficiListXml) {
			this.pageInfo = pageInfo;
			this.retCode = retCode;
			this.message = message;
			this.ufficiListXml = ufficiListXml;
		}
		
		public PageInfo getPageInfo() {
			return pageInfo;
		}
		
		public void setPageInfo(PageInfo pageInfo) {
			this.pageInfo = pageInfo;
		}
		
		public String getUfficiListXml() {
			return ufficiListXml;
		}
		
		public void setUfficiListXml(String ufficiListXml) {
			this.ufficiListXml = ufficiListXml;
		}
		
		public String getRetCode() {
			return retCode;
		}
		
		public void setRetCode(String retCode) {
			this.retCode = retCode;
		}
		
		public String getMessage() {
			return message;
		}
		
		public void setMessage(String message) {
			this.message = message;
		}
		
	}


