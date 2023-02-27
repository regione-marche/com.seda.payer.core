package com.seda.payer.core.bean;

import com.seda.data.spi.PageInfo;

public class BlackBoxPosPagelist extends PageInfo {
	
		private static final long serialVersionUID = 1L;
		PageInfo pageInfo = new PageInfo();
		String blackboxposListXml;
		String blackboxposListXmlRiep;
		String retCode;
		String message;
		public BlackBoxPosPagelist() {
			
		}
		public BlackBoxPosPagelist(PageInfo pageInfo, String retCode,String message, String blackboxposListXml) {
			this.pageInfo = pageInfo;
			this.retCode = retCode;
			this.message = message;
			this.blackboxposListXml = blackboxposListXml;
		}
		public BlackBoxPosPagelist(PageInfo pageInfo, String retCode,String message, String[] blackboxposListXml) {
			this.pageInfo = pageInfo;
			this.retCode = retCode;
			this.message = message;
			this.blackboxposListXml = blackboxposListXml[0];
			this.blackboxposListXmlRiep = blackboxposListXml[1];
		}
		public PageInfo getPageInfo() {
			return pageInfo;
		}
		public void setPageInfo(PageInfo pageInfo) {
			this.pageInfo = pageInfo;
		}
		public String getblackboxposListXml() {
			return blackboxposListXml;
		}
		public void setblackboxposListXml(String blackboxposListXml) {
			this.blackboxposListXml = blackboxposListXml;
		}
		public String getblackboxposListXmlRiep() {
			return blackboxposListXmlRiep;
		}
		public void setblackboxposListXmlRiep(String blackboxposListXmlRiep) {
			this.blackboxposListXmlRiep = blackboxposListXmlRiep;
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


