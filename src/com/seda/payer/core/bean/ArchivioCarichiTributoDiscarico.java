package com.seda.payer.core.bean;

import java.math.BigDecimal;
import java.util.Date;

public class ArchivioCarichiTributoDiscarico {

  private int progressivoTributo;
  private String codiceTributo;
  private String annoTributo;
  private BigDecimal impTributo;
  private BigDecimal impPagatoCompresiSgravi;
  private BigDecimal impResiduo;
  private BigDecimal impDiscaric;
  
  public int getProgressivoTributo() {
    return progressivoTributo;
  }
  public void setProgressivoTributo(int progressivoTributo) {
    this.progressivoTributo = progressivoTributo;
  }
  public String getCodiceTributo() {
    return codiceTributo;
  }
  public void setCodiceTributo(String codiceTributo) {
    this.codiceTributo = codiceTributo;
  }
  public String getAnnoTributo() {
    return annoTributo;
  }
  public void setAnnoTributo(String annoTributo) {
    this.annoTributo = annoTributo;
  }
  public BigDecimal getImpTributo() {
    return impTributo;
  }
  public void setImpTributo(BigDecimal impTributo) {
    this.impTributo = impTributo;
  }
  public BigDecimal getImpPagatoCompresiSgravi() {
    return impPagatoCompresiSgravi;
  }
  public void setImpPagatoCompresiSgravi(BigDecimal impPagatoCompresiSgravi) {
    this.impPagatoCompresiSgravi = impPagatoCompresiSgravi;
  }
  public BigDecimal getImpDiscaric() {
    return impDiscaric;
  }
  public void setImpDiscaric(BigDecimal impDiscaric) {
    this.impDiscaric = impDiscaric;
  }
  public BigDecimal getImpResiduo() {
    return impResiduo;
  }
  public void setImpResiduo(BigDecimal impResiduo) {
    this.impResiduo = impResiduo;
  }

  
}
