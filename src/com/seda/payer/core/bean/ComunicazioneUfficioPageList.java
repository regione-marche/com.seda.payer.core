package com.seda.payer.core.bean;

import com.seda.data.spi.PageInfo;

import java.util.Arrays;
import java.util.Objects;

public class ComunicazioneUfficioPageList extends PageInfo {

    private static final long serialVersionUID = 1L;
    PageInfo pageInfo = new PageInfo();
    String comunicazioneUfficioListXml;
    String comunicazioneUfficioListXmlRiep;
    String retCode;
    String message;
    String[] ufficioList;

    public ComunicazioneUfficioPageList(PageInfo pageInfo, String number, String s, String[] ufficioList) {
        this.ufficioList=ufficioList;
        this.pageInfo=pageInfo;
        this.retCode=number;
        this.message=s;
        comunicazioneUfficioListXml = this.ufficioList[0];
        comunicazioneUfficioListXmlRiep = this.ufficioList[1];
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ComunicazioneUfficioPageList that = (ComunicazioneUfficioPageList) o;
        return Objects.equals(pageInfo, that.pageInfo) && Objects.equals(comunicazioneUfficioListXml, that.comunicazioneUfficioListXml) && Objects.equals(comunicazioneUfficioListXmlRiep, that.comunicazioneUfficioListXmlRiep) && Objects.equals(retCode, that.retCode) && Objects.equals(message, that.message) && Objects.deepEquals(ufficioList, that.ufficioList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pageInfo, comunicazioneUfficioListXml, comunicazioneUfficioListXmlRiep, retCode, message, Arrays.hashCode(ufficioList));
    }

    @Override
    public String toString() {
        return "ComunicazioneUfficioPageList{" +
                "pageInfo=" + pageInfo +
                ", comunicazioneUfficioListXml='" + comunicazioneUfficioListXml + '\'' +
                ", comunicazioneUfficioListXmlRiep='" + comunicazioneUfficioListXmlRiep + '\'' +
                ", retCode='" + retCode + '\'' +
                ", message='" + message + '\'' +
                ", ufficioList=" + Arrays.toString(ufficioList) +
                '}';
    }


    public String[] getUfficioList() {
        return ufficioList;
    }

    public void setUfficioList(String[] ufficioList) {
        this.ufficioList = ufficioList;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getRetCode() {
        return retCode;
    }

    public void setRetCode(String retCode) {
        this.retCode = retCode;
    }

    public String getComunicazioneUfficioListXmlRiep() {
        return comunicazioneUfficioListXmlRiep;
    }

    public void setComunicazioneUfficioListXmlRiep(String comunicazioneUfficioListXmlRiep) {
        this.comunicazioneUfficioListXmlRiep = comunicazioneUfficioListXmlRiep;
    }

    public String getComunicazioneUfficioListXml() {
        return comunicazioneUfficioListXml;
    }

    public void setComunicazioneUfficioListXml(String comunicazioneUfficioListXml) {
        this.comunicazioneUfficioListXml = comunicazioneUfficioListXml;
    }

    public PageInfo getPageInfo() {
        return pageInfo;
    }

    public void setPageInfo(PageInfo pageInfo) {
        this.pageInfo = pageInfo;
    }
}
