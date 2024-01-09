package com.seda.payer.commons.utility;


import java.io.StringWriter;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Calendar;
import java.util.Locale;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.seda.commons.logger.LoggerWrapper;

public class NodoSpcHelper {

	public static String generaRPTXml(LoggerWrapper log, String codSocieta, String identificativoDominio, String identificativoStazioneRichiedente, String tipoIdUnivocoPagatore, String cfPagatore,
			String nomePagatore, String emailPagatore, String cfEnteCreditore, String nomeEnteCreditore, BigDecimal importo, String iuv,
			String ibanAccredito, String datiSpecificiRiscossione, String tipoVersamento, String codContestoPagamento, String causaleVersamento, String tipoIdUnivocoVersante, String cfVersante,
			String nomeVersante, String emailVersante) {  

	//inizio LP PG21XX005 - Quadratura Off Pgec
		String out = generaRPTXml(codSocieta, identificativoDominio, identificativoStazioneRichiedente, tipoIdUnivocoPagatore, cfPagatore, nomePagatore, emailPagatore, cfEnteCreditore, nomeEnteCreditore, importo, iuv, ibanAccredito, datiSpecificiRiscossione, tipoVersamento, codContestoPagamento, causaleVersamento, tipoIdUnivocoVersante, cfVersante, nomeVersante, emailVersante);
		if(out.length() > 0) {
			log.debug("RPT XML: \n" + out);
		}
		return out;
	}
	
	public static String generaRPTXml(String codSocieta, String identificativoDominio, String identificativoStazioneRichiedente, String tipoIdUnivocoPagatore, String cfPagatore,
			String nomePagatore, String emailPagatore, String cfEnteCreditore, String nomeEnteCreditore, BigDecimal importo, String iuv,
			String ibanAccredito, String datiSpecificiRiscossione, String tipoVersamento, String codContestoPagamento, String causaleVersamento, String tipoIdUnivocoVersante, String cfVersante,
			String nomeVersante, String emailVersante) {  
	//fine LP PG21XX005 - Quadratura Off Pgec
		
		

		try {

			DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

			// RPT element
			Document doc = docBuilder.newDocument();
			Element rootElement = doc.createElement("RPT");
			rootElement.setAttribute("xmlns", "http://www.digitpa.gov.it/schemas/2011/Pagamenti/");
			doc.appendChild(rootElement);

			Element el = null;

			// versioneOggetto elements
			String versioneOggetto = "6.2.0";
			Element el_versioneOggetto = doc.createElement("versioneOggetto");
			el_versioneOggetto.setTextContent(versioneOggetto);
			rootElement.appendChild(el_versioneOggetto);

			// dominio elements
			Element dominio = doc.createElement("dominio");
			rootElement.appendChild(dominio);
			// dominio/identificativoDominio elements
			el = doc.createElement("identificativoDominio");
			el.setTextContent(identificativoDominio.trim());  //Codice fiscale della struttura che invia la richiesta di pagamento
			dominio.appendChild(el);
			// dominio/identificativoStazioneRichiedente elements
			el = doc.createElement("identificativoStazioneRichiedente");
			el.setTextContent(identificativoStazioneRichiedente);
			dominio.appendChild(el);

			// identificativoMessaggioRichiesta elements
			el = doc.createElement("identificativoMessaggioRichiesta");
			el.setTextContent(DateUtility.frmtTimeByPattern("yyyyMMddHHmmss",Calendar.getInstance().getTime())); //identificativo univoco, utilizzo data ora
			rootElement.appendChild(el);

			// dataOraMessaggioRichiesta elements
			String dataEsitoSingoloPagamento="";
			System.out.println("tipoIdUnivocoPagatore: "+ tipoIdUnivocoPagatore);
			if(tipoIdUnivocoPagatore.contains("||QUADRATURANODO||")){
				dataEsitoSingoloPagamento = tipoIdUnivocoPagatore.substring(18,tipoIdUnivocoPagatore.length());
				System.out.println("dataEsitoSingoloPagamento: " + dataEsitoSingoloPagamento);
				tipoIdUnivocoPagatore="";
			}
			el = doc.createElement("dataOraMessaggioRichiesta");
			el.setTextContent(dataEsitoSingoloPagamento.equals("") ? DateUtility.frmtTimeByPattern("yyyy-MM-dd'T'HH:mm:ss",Calendar.getInstance().getTime()) :  (dataEsitoSingoloPagamento+"T01:00:00")); //ISO 8601
			rootElement.appendChild(el);

			// autenticazioneSoggetto elements
			el = doc.createElement("autenticazioneSoggetto");
			el.setTextContent("N/A"); //CNS; USR; OTH; N/A;
			rootElement.appendChild(el);
			
			if (!cfVersante.trim().equals("") && !nomeVersante.trim().equals("")) {
				// soggettoVersante elements
				Element soggettoVersante = doc.createElement("soggettoVersante");
				rootElement.appendChild(soggettoVersante);
				
				// soggettoVersante/identificativoUnivocoVersante elements 
				Element identificativoUnivocoVersante = doc.createElement("identificativoUnivocoVersante");
				soggettoVersante.appendChild(identificativoUnivocoVersante);
				
				// soggettoVersante/identificativoUnivocoVersante/tipoIdentificativoUnivoco elements
				el = doc.createElement("tipoIdentificativoUnivoco");
				if (tipoIdUnivocoVersante.trim().length() > 0) {
					el.setTextContent(tipoIdUnivocoVersante);
				} else {
					if(cfVersante.matches("\\d+")) //se numerico allora partita iva
						el.setTextContent("G"); //F=persona fisica; G=persona giuridica
					else
						el.setTextContent("F"); //F=persona fisica; G=persona giuridica
				}
				identificativoUnivocoVersante.appendChild(el);
				
				// soggettoVersante/identificativoUnivocoVersante/codiceIdentificativoUnivoco elements
				el = doc.createElement("codiceIdentificativoUnivoco");
				el.setTextContent(cfVersante.trim()); //CF  o PIVA
				identificativoUnivocoVersante.appendChild(el);
				
				// soggettoVersante/anagraficaVersante elements
				el = doc.createElement("anagraficaVersante");
				el.setTextContent(nomeVersante);
				soggettoVersante.appendChild(el);
				
				if(!emailVersante.trim().equals("")) 
				{
					// soggettoVersante/e-mailVersante elements
					el = doc.createElement("e-mailVersante");
					el.setTextContent(emailVersante);
					soggettoVersante.appendChild(el);
				}
			}
			
			// soggettoPagatore elements 
			Element soggettoPagatore = doc.createElement("soggettoPagatore");
			rootElement.appendChild(soggettoPagatore);

			// soggettoPagatore/identificativoUnivocoPagatore elements 
			Element identificativoUnivocoPagatore = doc.createElement("identificativoUnivocoPagatore");
			soggettoPagatore.appendChild(identificativoUnivocoPagatore);

			// soggettoPagatore/identificativoUnivocoPagatore/tipoIdentificativoUnivoco elements
			el = doc.createElement("tipoIdentificativoUnivoco");
			if (tipoIdUnivocoPagatore.trim().length() > 0) {
				el.setTextContent(tipoIdUnivocoPagatore);
			} else {
				if(cfPagatore.matches("\\d+")) //se numerico allora partita iva
					el.setTextContent("G"); //F=persona fisica; G=persona giuridica
				else
					el.setTextContent("F"); //F=persona fisica; G=persona giuridica
			}
			identificativoUnivocoPagatore.appendChild(el);

			// soggettoPagatore/identificativoUnivocoPagatore/codiceIdentificativoUnivoco elements
			el = doc.createElement("codiceIdentificativoUnivoco");
			//if(cfPagatore.trim().length()==0)
			//	cfPagatore = "PAGAMENTO ANONIMO";// "RSSMRA00A01H501C"; //Fisso per pagamenti fatti dal payer perch� il dato non � gestito dal payer
			el.setTextContent(cfPagatore.trim()); //CF  o PIVA
			identificativoUnivocoPagatore.appendChild(el);

			// soggettoPagatore/anagraficaPagatore elements
			el = doc.createElement("anagraficaPagatore");
			//if(nomePagatore.trim().length()==0)
			//	nomePagatore = "PAGAMENTO ANONIMO Web Payer"; //Fisso per pagamenti fatti dal payer perch� il dato non � gestito dal payer
			el.setTextContent(nomePagatore);
			soggettoPagatore.appendChild(el);

			if(!emailPagatore.trim().equals("")) 
			{
				// soggettoPagatore/e-mailPagatore elements
				el = doc.createElement("e-mailPagatore");
				el.setTextContent(emailPagatore);
				soggettoPagatore.appendChild(el);
			}

			// enteBeneficiario elements 
			Element enteBeneficiario = doc.createElement("enteBeneficiario");
			rootElement.appendChild(enteBeneficiario);

			// enteBeneficiario/identificativoUnivocoPagatore elements 
			Element identificativoUnivocoBeneficiario = doc.createElement("identificativoUnivocoBeneficiario");
			enteBeneficiario.appendChild(identificativoUnivocoBeneficiario);

			// enteBeneficiario/identificativoUnivocoBeneficiario/tipoIdentificativoUnivoco elements
			el = doc.createElement("tipoIdentificativoUnivoco");
			el.setTextContent("G"); //G=persona giuridica fisso
			identificativoUnivocoBeneficiario.appendChild(el);

			// enteBeneficiario/identificativoUnivocoBeneficiario/codiceIdentificativoUnivoco elements
			el = doc.createElement("codiceIdentificativoUnivoco");
			el.setTextContent(cfEnteCreditore.trim()); //CF ente creditore
			identificativoUnivocoBeneficiario.appendChild(el);


			// enteBeneficiario/denominazioneBeneficiario elements
			el = doc.createElement("denominazioneBeneficiario");
			//30052018 - inizio
			//el.setTextContent(nomeEnteCreditore.length()>70?nomeEnteCreditore.substring(0, 70):nomeEnteCreditore); //CF ente creditore
			String denominazioneBeneficario = getDenominazioneBeneficiario(nomeEnteCreditore);
			el.setTextContent(denominazioneBeneficario.length()>70?denominazioneBeneficario.substring(0, 70):denominazioneBeneficario); //CF ente creditore
			//30052018 - fine
			enteBeneficiario.appendChild(el);


			// datiVersamento elements 
			Element datiVersamento = doc.createElement("datiVersamento");
			rootElement.appendChild(datiVersamento);

			// datiVersamento/dataEsecuzionePagamento elements
			el = doc.createElement("dataEsecuzionePagamento");
			el.setTextContent(dataEsitoSingoloPagamento.equals("") ? DateUtility.frmtTimeByPattern("yyyy-MM-dd",Calendar.getInstance().getTime()) :  dataEsitoSingoloPagamento); //
			datiVersamento.appendChild(el);

			// datiVersamento/importoTotaleDaVersare elements
			el = doc.createElement("importoTotaleDaVersare");
			DecimalFormat df = new DecimalFormat("0.00");
			df.setDecimalFormatSymbols(new DecimalFormatSymbols(Locale.ENGLISH));
			el.setTextContent(df.format(importo)); //
			datiVersamento.appendChild(el);

			// datiVersamento/tipoVersamento elements
			el = doc.createElement("tipoVersamento");
			//el.setTextContent("BBT"); //BBT; BP; AD; CP; PO;
			el.setTextContent(tipoVersamento); //BBT; BP; AD; CP; PO;
			datiVersamento.appendChild(el);

			// datiVersamento/identificativoUnivocoVersamento elements
			el = doc.createElement("identificativoUnivocoVersamento");
			el.setTextContent(iuv);
			datiVersamento.appendChild(el);

			// datiVersamento/codiceContestoPagamento elements
			el = doc.createElement("codiceContestoPagamento");
			el.setTextContent(codContestoPagamento); //"n/a" fisso perch� valorizzato se pagamento attivato da psp 
			datiVersamento.appendChild(el);

			// datiVersamento/ibanAddebito elements
			//			el = doc.createElement("ibanAddebito");
			//			el.setTextContent(""); //Valorizzato solo se tipoVersamento = AD
			//			datiVersamento.appendChild(el);

			// datiVersamento/bicAddebito elements
			//			el = doc.createElement("bicAddebito");
			//			el.setTextContent(""); //Valorizzato solo se tipoVersamento = AD
			//			datiVersamento.appendChild(el);

			// datiVersamento/firmaRicevuta elements
			el = doc.createElement("firmaRicevuta");
			el.setTextContent("0"); //0=non richiesta; 1=CaDes; 3=XaDes; 4=Elettronica Avanzata
			datiVersamento.appendChild(el);

			//1 per ogni bollettino (massimo 5)
			//for (int i = 0; i < 5; i++) {
			// datiVersamento/datiSingoloVersamento elements
			Element datiSingoloVersamento = doc.createElement("datiSingoloVersamento");
			datiVersamento.appendChild(datiSingoloVersamento);

			// datiVersamento/datiSingoloVersamento/importoSingoloVersamento elements
			el = doc.createElement("importoSingoloVersamento");
			el.setTextContent(df.format(importo)); 
			datiSingoloVersamento.appendChild(el);

			// datiVersamento/datiSingoloVersamento/ibanAccredito elements
			el = doc.createElement("ibanAccredito");
			el.setTextContent(ibanAccredito); 
			datiSingoloVersamento.appendChild(el);

			// datiVersamento/datiSingoloVersamento/causaleVersamento elements
			el = doc.createElement("causaleVersamento");
			el.setTextContent(causaleVersamento); 
			datiSingoloVersamento.appendChild(el);

			// datiVersamento/datiSingoloVersamento/datiSpecificiRiscossione elements
			el = doc.createElement("datiSpecificiRiscossione");
			el.setTextContent(datiSpecificiRiscossione); //<tipo contabilit�: 0;1;2;9;>/<codice contabilit�>
			datiSingoloVersamento.appendChild(el);

			//}


			// write the content into xml file
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource source = new DOMSource(doc);

			//StreamResult result = new StreamResult(new File("C:\\file.xml"));
			StringWriter writer = new StringWriter();
			StreamResult result = new StreamResult(writer);

			// Output to console for testing
			// StreamResult result = new StreamResult(System.out);

			transformer.transform(source, result);

			//inizio LP PG21XX005 - Quadratura Off Pgec
			//log.debug("RPT XML: \n" + writer.toString());
			//System.out.println("RPT XML: \n" + writer.toString());
			//return writer.toString();
			String out = writer.toString();
			out = out.replaceFirst(" standalone=\"no\"", "");
			System.out.println("RPT XML: \n" + out);
			return out;
			//fine LP PG21XX005 - Quadratura Off Pgec


		} catch (ParserConfigurationException pce) {
			pce.printStackTrace();
		} catch (TransformerException tfe) {
			tfe.printStackTrace();
		}		
		return "";
	}

	public static String generaRPTXmlPoste(LoggerWrapper log, String codSocieta, String identificativoDominio, String identificativoStazioneRichiedente, String tipoIdUnivocoPagatore, String cfPagatore,
			String nomePagatore, String emailPagatore, String cfEnteCreditore, String nomeEnteCreditore, BigDecimal importo, String iuv,
			String ibanAccredito, String datiSpecificiRiscossione, String tipoVersamento, String codContestoPagamento, String causaleVersamento, String tipoIdUnivocoVersante, String cfVersante,
			String nomeVersante, String emailVersante) {
	//inizio LP PG21XX005 - Quadratura Off Pgec
		String out = generaRPTXmlPoste(codSocieta, identificativoDominio, identificativoStazioneRichiedente, tipoIdUnivocoPagatore, cfPagatore, nomePagatore, emailPagatore, cfEnteCreditore, nomeEnteCreditore, importo, iuv, ibanAccredito, datiSpecificiRiscossione, tipoVersamento, codContestoPagamento, causaleVersamento, tipoIdUnivocoVersante, cfVersante, nomeVersante, emailVersante);
		if(out.length() > 0) {
			log.debug("RPT XML: \n" + out);
		}
		return out;

	}
	
	public static String generaRPTXmlPoste(String codSocieta, String identificativoDominio, String identificativoStazioneRichiedente, String tipoIdUnivocoPagatore, String cfPagatore,
			String nomePagatore, String emailPagatore, String cfEnteCreditore, String nomeEnteCreditore, BigDecimal importo, String iuv,
			String ibanAccredito, String datiSpecificiRiscossione, String tipoVersamento, String codContestoPagamento, String causaleVersamento, String tipoIdUnivocoVersante, String cfVersante,
			String nomeVersante, String emailVersante) {
	//fine LP PG21XX005 - Quadratura Off Pgec

		try {

			DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

			// RPT element
			Document doc = docBuilder.newDocument();
//			Element rootElement = doc.createElement("pag:RPT");
			Element rootElement = doc.createElement("RPT");
//			rootElement.setAttribute("xmlns:pag", "http://www.digitpa.gov.it/schemas/2011/Pagamenti/");
			rootElement.setAttribute("xmlns", "http://www.digitpa.gov.it/schemas/2011/Pagamenti/");
			doc.appendChild(rootElement);

			Element el = null;

			//			// versioneOggetto elements
			String versioneOggetto = "6.2.0";
//			Element el_versioneOggetto = doc.createElement("pag:versioneOggetto");
			Element el_versioneOggetto = doc.createElement("versioneOggetto");
			el_versioneOggetto.setTextContent(versioneOggetto);
			rootElement.appendChild(el_versioneOggetto);

			// dominio elements
//			Element dominio = doc.createElement("pag:dominio");
			Element dominio = doc.createElement("dominio");
			rootElement.appendChild(dominio);
			// dominio/identificativoDominio elements
//			el = doc.createElement("pag:identificativoDominio");
			el = doc.createElement("identificativoDominio");
			el.setTextContent(identificativoDominio.trim());  //Codice fiscale della struttura che invia la richiesta di pagamento
			dominio.appendChild(el);
			// dominio/identificativoStazioneRichiedente elements
//			el = doc.createElement("pag:identificativoStazioneRichiedente");
			el = doc.createElement("identificativoStazioneRichiedente");
			el.setTextContent(identificativoStazioneRichiedente);
			dominio.appendChild(el);

			// identificativoMessaggioRichiesta elements
//			el = doc.createElement("pag:identificativoMessaggioRichiesta");
			el = doc.createElement("identificativoMessaggioRichiesta");
			el.setTextContent(DateUtility.frmtTimeByPattern("yyyyMMddHHmmss",Calendar.getInstance().getTime())); //identificativo univoco, utilizzo data ora
			rootElement.appendChild(el);

			// dataOraMessaggioRichiesta elements
//			el = doc.createElement("pag:dataOraMessaggioRichiesta");
			String dataEsitoSingoloPagamento="";
			System.out.println("tipoIdUnivocoPagatore: "+ tipoIdUnivocoPagatore);
			if(tipoIdUnivocoPagatore.contains("||QUADRATURANODO||")){
				dataEsitoSingoloPagamento = tipoIdUnivocoPagatore.substring(18,tipoIdUnivocoPagatore.length());
				System.out.println("dataEsitoSingoloPagamento: " + dataEsitoSingoloPagamento);
				tipoIdUnivocoPagatore="";
			}
			el = doc.createElement("dataOraMessaggioRichiesta");
			el.setTextContent(dataEsitoSingoloPagamento.equals("") ? DateUtility.frmtTimeByPattern("yyyy-MM-dd'T'HH:mm:ss",Calendar.getInstance().getTime()) : (dataEsitoSingoloPagamento+"T01:00:00")); //ISO 8601
			rootElement.appendChild(el);

			// autenticazioneSoggetto elements
//			el = doc.createElement("pag:autenticazioneSoggetto");
			el = doc.createElement("autenticazioneSoggetto");
			el.setTextContent("N/A"); //CNS; USR; OTH; N/A;
			rootElement.appendChild(el);
			
			if (!cfVersante.trim().equals("") && !nomeVersante.trim().equals("")) {
				// soggettoVersante elements
				Element soggettoVersante = doc.createElement("soggettoVersante");
				rootElement.appendChild(soggettoVersante);
				
				// soggettoVersante/identificativoUnivocoVersante elements 
				Element identificativoUnivocoVersante = doc.createElement("identificativoUnivocoVersante");
				soggettoVersante.appendChild(identificativoUnivocoVersante);
				
				// soggettoVersante/identificativoUnivocoVersante/tipoIdentificativoUnivoco elements
				el = doc.createElement("tipoIdentificativoUnivoco");
				if (tipoIdUnivocoVersante.trim().length() > 0) {
					el.setTextContent(tipoIdUnivocoVersante);
				} else {
					if(cfVersante.matches("\\d+")) //se numerico allora partita iva
						el.setTextContent("G"); //F=persona fisica; G=persona giuridica
					else
						el.setTextContent("F"); //F=persona fisica; G=persona giuridica
				}
				identificativoUnivocoVersante.appendChild(el);
				
				// soggettoVersante/identificativoUnivocoVersante/codiceIdentificativoUnivoco elements
				el = doc.createElement("codiceIdentificativoUnivoco");
				el.setTextContent(cfVersante.trim()); //CF  o PIVA
				identificativoUnivocoVersante.appendChild(el);
				
				// soggettoVersante/anagraficaVersante elements
				el = doc.createElement("anagraficaVersante");
				el.setTextContent(nomeVersante);
				soggettoVersante.appendChild(el);
				
				if(!emailVersante.trim().equals("")) 
				{
					// soggettoVersante/e-mailVersante elements
					el = doc.createElement("e-mailVersante");
					el.setTextContent(emailVersante);
					soggettoVersante.appendChild(el);
				}
			}
			
			// soggettoPagatore elements 
//			Element soggettoPagatore = doc.createElement("pag:soggettoPagatore");
			Element soggettoPagatore = doc.createElement("soggettoPagatore");
			rootElement.appendChild(soggettoPagatore);

			// soggettoPagatore/identificativoUnivocoPagatore elements 
//			Element identificativoUnivocoPagatore = doc.createElement("pag:identificativoUnivocoPagatore");
			Element identificativoUnivocoPagatore = doc.createElement("identificativoUnivocoPagatore");
			soggettoPagatore.appendChild(identificativoUnivocoPagatore);

			// soggettoPagatore/identificativoUnivocoPagatore/tipoIdentificativoUnivoco elements
//			el = doc.createElement("pag:tipoIdentificativoUnivoco");
			el = doc.createElement("tipoIdentificativoUnivoco");
			if (tipoIdUnivocoPagatore.trim().length()>0) {
				el.setTextContent(tipoIdUnivocoPagatore);
			} else {
				if(cfPagatore.matches("\\d+")) //se numerico allora partita iva
					el.setTextContent("G"); //F=persona fisica; G=persona giuridica
				else
					el.setTextContent("F"); //F=persona fisica; G=persona giuridica
			}
			identificativoUnivocoPagatore.appendChild(el);

			// soggettoPagatore/identificativoUnivocoPagatore/codiceIdentificativoUnivoco elements
//			el = doc.createElement("pag:codiceIdentificativoUnivoco");
			el = doc.createElement("codiceIdentificativoUnivoco");
			//if(cfPagatore.trim().length()==0)
			//	cfPagatore = "PAGAMENTO ANONIMO";// "RSSMRA00A01H501C"; //Fisso per pagamenti fatti dal payer perch� il dato non � gestito dal payer
			el.setTextContent(cfPagatore.trim()); //CF  o PIVA
			identificativoUnivocoPagatore.appendChild(el);

			// soggettoPagatore/anagraficaPagatore elements
//			el = doc.createElement("pag:anagraficaPagatore");
			el = doc.createElement("anagraficaPagatore");
			//if(nomePagatore.trim().length()==0)
			//	nomePagatore = "PAGAMENTO ANONIMO Web Payer"; //Fisso per pagamenti fatti dal payer perch� il dato non � gestito dal payer
			el.setTextContent(nomePagatore);
			soggettoPagatore.appendChild(el);

/*     Dati asteriscati in data 19/11/2015 Poste sembra aver utilizzato, per il modello 1
 *              la versione della RPT utilizzata da tutti gli altri
 * 
			// soggettoPagatore/indirizzoPagatore elements
			el = doc.createElement("pag:indirizzoPagatore");
//			el.setTextContent(indirizzoPagatore); MATTEO
			el.setTextContent(" ");
			soggettoPagatore.appendChild(el);

			// soggettoPagatore/civicoPagatore elements
			el = doc.createElement("pag:civicoPagatore");
//			el.setTextContent(civicoPagatore); MATTEO
			el.setTextContent("");
			soggettoPagatore.appendChild(el);

			// soggettoPagatore/capPagatore elements
			el = doc.createElement("pag:capPagatore");
//			el.setTextContent(capPagatore); MATTEO
			el.setTextContent("");
			soggettoPagatore.appendChild(el);

			// soggettoPagatore/localitaPagatore elements
			el = doc.createElement("pag:localitaPagatore");
//			el.setTextContent(localitaPagatore); MATTEO
			el.setTextContent("");
			soggettoPagatore.appendChild(el);

			// soggettoPagatore/provinciaPagatore elements
			el = doc.createElement("pag:provinciaPagatore");
//			el.setTextContent(provinciaPagatore); MATTEO
			el.setTextContent("");
			soggettoPagatore.appendChild(el);

			// soggettoPagatore/nazionePagatore elements
			el = doc.createElement("pag:nazionePagatore");
//			el.setTextContent(nazionePagatore); MATTEO
			el.setTextContent("");
			soggettoPagatore.appendChild(el);
*/
			if(!emailPagatore.trim().equals("")) 
			{
				// soggettoPagatore/e-mailPagatore elements
	//			el = doc.createElement("pag:e-mailPagatore");
				el = doc.createElement("e-mailPagatore");
				el.setTextContent(emailPagatore);
				soggettoPagatore.appendChild(el);
			}

			// enteBeneficiario elements 
//			Element enteBeneficiario = doc.createElement("pag:enteBeneficiario");
			Element enteBeneficiario = doc.createElement("enteBeneficiario");
			rootElement.appendChild(enteBeneficiario);

			// enteBeneficiario/identificativoUnivocoPagatore elements 
//			Element identificativoUnivocoBeneficiario = doc.createElement("pag:identificativoUnivocoBeneficiario");
			Element identificativoUnivocoBeneficiario = doc.createElement("identificativoUnivocoBeneficiario");
			enteBeneficiario.appendChild(identificativoUnivocoBeneficiario);

			// enteBeneficiario/identificativoUnivocoBeneficiario/tipoIdentificativoUnivoco elements
//			el = doc.createElement("pag:tipoIdentificativoUnivoco");
			el = doc.createElement("tipoIdentificativoUnivoco");
			el.setTextContent("G"); //G=persona giuridica fisso
			identificativoUnivocoBeneficiario.appendChild(el);

			// enteBeneficiario/identificativoUnivocoBeneficiario/codiceIdentificativoUnivoco elements
//			el = doc.createElement("pag:codiceIdentificativoUnivoco");
			el = doc.createElement("codiceIdentificativoUnivoco");
			el.setTextContent(cfEnteCreditore.trim()); //CF ente creditore
			identificativoUnivocoBeneficiario.appendChild(el);


			// enteBeneficiario/denominazioneBeneficiario elements
//			el = doc.createElement("pag:denominazioneBeneficiario");
			el = doc.createElement("denominazioneBeneficiario");
			//30052018 - inizio
			//el.setTextContent(nomeEnteCreditore.length()>70?nomeEnteCreditore.substring(0, 70):nomeEnteCreditore); //CF ente creditore
			String denominazioneBeneficiario = getDenominazioneBeneficiario(nomeEnteCreditore);
			System.out.println("denominazioneBeneficiario = " + denominazioneBeneficiario);
			el.setTextContent(denominazioneBeneficiario.length()>70?denominazioneBeneficiario.substring(0, 70):denominazioneBeneficiario); //CF ente creditore
			//30052018 - fine
			enteBeneficiario.appendChild(el);

/*     Dati asteriscati in data 19/11/2015 Poste sembra aver utilizzato, per il modello 1
 *              la versione della RPT utilizzata da tutti gli altri
 * 
			// enteBeneficiario/codiceUnitOperBeneficiarioBeneficiario elements
			el = doc.createElement("pag:codiceUnitOperBeneficiario");
//			el.setTextContent(codiceUnitOperBeneficiario); MATTEO
			el.setTextContent("");
			enteBeneficiario.appendChild(el);

			// enteBeneficiario/codiceUnitOperBeneficiarioBeneficiario elements
			el = doc.createElement("pag:denomUnitOperBeneficiario");
//			el.setTextContent(denomUnitOperBeneficiario); MATTEO
			el.setTextContent("");
			enteBeneficiario.appendChild(el);

			// enteBeneficiario/indirizzoBeneficiarioBeneficiario elements
			el = doc.createElement("pag:indirizzoBeneficiario");
//			el.setTextContent(indirizzoBeneficiario); MATTEO
			el.setTextContent("");
			enteBeneficiario.appendChild(el);

			// enteBeneficiario/civicoBeneficiarioBeneficiario elements
			el = doc.createElement("pag:civicoBeneficiario");
//			el.setTextContent(civicoBeneficiario); MATTEO
			el.setTextContent("");
			enteBeneficiario.appendChild(el);

			// enteBeneficiario/capBeneficiarioBeneficiario elements
			el = doc.createElement("pag:capBeneficiario");
//			el.setTextContent(capBeneficiario); MATTEO
			el.setTextContent("");
			enteBeneficiario.appendChild(el);

			// enteBeneficiario/localitaBeneficiarioBeneficiario elements
			el = doc.createElement("pag:localitaBeneficiario");
//			el.setTextContent(localitaBeneficiario); MATTEO
			el.setTextContent("");
			enteBeneficiario.appendChild(el);

			// enteBeneficiario/provinciaBeneficiarioBeneficiario elements
			el = doc.createElement("pag:provinciaBeneficiario");
//			el.setTextContent(provinciaBeneficiario); MATTEO
			el.setTextContent("");
			enteBeneficiario.appendChild(el);

			// enteBeneficiario/nazioneBeneficiarioBeneficiario elements
			el = doc.createElement("pag:nazioneBeneficiario");
//			el.setTextContent(nazioneBeneficiario);
			el.setTextContent("");
			enteBeneficiario.appendChild(el);
*/
			// datiVersamento elements 
//			Element datiVersamento = doc.createElement("pag:datiVersamento");
			Element datiVersamento = doc.createElement("datiVersamento");
			rootElement.appendChild(datiVersamento);


			// datiVersamento/dataEsecuzionePagamento elements
//			el = doc.createElement("pag:dataEsecuzionePagamento");
			el = doc.createElement("dataEsecuzionePagamento");
			el.setTextContent(dataEsitoSingoloPagamento.equals("") ? DateUtility.frmtTimeByPattern("yyyy-MM-dd",Calendar.getInstance().getTime()) :  dataEsitoSingoloPagamento); //
			datiVersamento.appendChild(el);

			// datiVersamento/importoTotaleDaVersare elements
//			el = doc.createElement("pag:importoTotaleDaVersare");
			el = doc.createElement("importoTotaleDaVersare");
			DecimalFormat df = new DecimalFormat("0.00");
			df.setDecimalFormatSymbols(new DecimalFormatSymbols(Locale.ENGLISH));
			el.setTextContent(df.format(importo)); //
			datiVersamento.appendChild(el);

			// datiVersamento/tipoVersamento elements
//			el = doc.createElement("pag:tipoVersamento");
			el = doc.createElement("tipoVersamento");
			//el.setTextContent("BBT"); //BBT; BP; AD; CP; PO;
			el.setTextContent(tipoVersamento); //BBT; BP; AD; CP; PO;
			datiVersamento.appendChild(el);

			// datiVersamento/identificativoUnivocoVersamento elements
//			el = doc.createElement("pag:identificativoUnivocoVersamento");
			el = doc.createElement("identificativoUnivocoVersamento");
			el.setTextContent(iuv);
			datiVersamento.appendChild(el);

			// datiVersamento/codiceContestoPagamento elements
//			el = doc.createElement("pag:codiceContestoPagamento");
			el = doc.createElement("codiceContestoPagamento");
			el.setTextContent(codContestoPagamento); //"n/a" fisso perch� valorizzato se pagamento attivato da psp 
			datiVersamento.appendChild(el);

/*     Dati asteriscati in data 19/11/2015 Poste sembra aver utilizzato, per il modello 1
 *              la versione della RPT utilizzata da tutti gli altri
 * 
			// datiVersamento/ibanAddebito elements
			el = doc.createElement("pag:ibanAddebito");
			el.setTextContent(""); //Valorizzato solo se tipoVersamento = AD
			datiVersamento.appendChild(el);

			// datiVersamento/bicAddebito elements
			el = doc.createElement("pag:bicAddebito");
			el.setTextContent(""); //Valorizzato solo se tipoVersamento = AD
			datiVersamento.appendChild(el);
*/
			// datiVersamento/firmaRicevuta elements
//			el = doc.createElement("pag:firmaRicevuta");
//          Per Poste, come da documento del 26/06/2015, la firma deve sempre essere impostata ad "1"
//			el.setTextContent("0"); //0=non richiesta; 1=CaDes; 3=XaDes; 4=Elettronica Avanzata
//			if (tipoVersamento.equals("BP"))
//				el.setTextContent("1");
			el = doc.createElement("firmaRicevuta");
			//Per Poste non serviva la firma ad 1. Pu� anche non essere richiesta 
			//el.setTextContent("1"); //0=non richiesta; 1=CaDes; 3=XaDes; 4=Elettronica Avanzata
			el.setTextContent("0"); //0=non richiesta; 1=CaDes; 3=XaDes; 4=Elettronica Avanzata
			datiVersamento.appendChild(el);

			//1 per ogni bollettino (massimo 5)
			//for (int i = 0; i < 5; i++) {
			// datiVersamento/datiSingoloVersamento elements
//			Element datiSingoloVersamento = doc.createElement("pag:datiSingoloVersamento");
			Element datiSingoloVersamento = doc.createElement("datiSingoloVersamento");
			datiVersamento.appendChild(datiSingoloVersamento);

			// datiVersamento/datiSingoloVersamento/importoSingoloVersamento elements
//			el = doc.createElement("pag:importoSingoloVersamento");
			el = doc.createElement("importoSingoloVersamento");
			el.setTextContent(df.format(importo)); 
			datiSingoloVersamento.appendChild(el);

/*     Dati asteriscati in data 19/11/2015 Poste sembra aver utilizzato, per il modello 1
 *              la versione della RPT utilizzata da tutti gli altri
 * 
			// datiVersamento/datiSingoloVersamento/commissioneCaricoPA elements
			el = doc.createElement("pag:commissioneCaricoPA"); 
//			el.setTextContent(df.format(commissioneCaricoPA)); MATTEO
			el.setTextContent("");
			datiSingoloVersamento.appendChild(el);
*/			
			// datiVersamento/datiSingoloVersamento/ibanAccredito elements
//			el = doc.createElement("pag:ibanAccredito");
			el = doc.createElement("ibanAccredito");
			el.setTextContent(ibanAccredito); 
			datiSingoloVersamento.appendChild(el);

/*     Dati asteriscati in data 19/11/2015 Poste sembra aver utilizzato, per il modello 1
 *              la versione della RPT utilizzata da tutti gli altri
 * 			
			// datiVersamento/datiSingoloVersamento/bicAccredito elements
			el = doc.createElement("pag:bicAccredito");
//			el.setTextContent(bicAccredito); MATTEO 
			datiSingoloVersamento.appendChild(el);
			
			// datiVersamento/datiSingoloVersamento/ibanAppoggio elements
			el = doc.createElement("pag:ibanAppoggio");
//			el.setTextContent(ibanAppoggio); MATTEO 
			datiSingoloVersamento.appendChild(el);
			
			// datiVersamento/datiSingoloVersamento/bicAppoggio elements
			el = doc.createElement("pag:bicAppoggio");
//			el.setTextContent(bicAppoggio); MATTEO
			datiSingoloVersamento.appendChild(el);
			
			// datiVersamento/datiSingoloVersamento/credenzialiPagatore elements
			el = doc.createElement("pag:credenzialiPagatore");
//			el.setTextContent(credenzialiPagatore); MATTEO 
			datiSingoloVersamento.appendChild(el);
*/
			// datiVersamento/datiSingoloVersamento/causaleVersamento elements
//			el = doc.createElement("pag:causaleVersamento");
			el = doc.createElement("causaleVersamento");
			//Causale = /RFB/IUV/Importo
			el.setTextContent(causaleVersamento); 
			datiSingoloVersamento.appendChild(el);

			// datiVersamento/datiSingoloVersamento/datiSpecificiRiscossione elements
//			el = doc.createElement("pag:datiSpecificiRiscossione");
			el = doc.createElement("datiSpecificiRiscossione");
			el.setTextContent(datiSpecificiRiscossione); //<tipo contabilit�: 0;1;2;9;>/<codice contabilit�>
			datiSingoloVersamento.appendChild(el);

			//}


			// write the content into xml file
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource source = new DOMSource(doc);

			//StreamResult result = new StreamResult(new File("C:\\file.xml"));
			StringWriter writer = new StringWriter();
			StreamResult result = new StreamResult(writer);

			// Output to console for testing
			// StreamResult result = new StreamResult(System.out);

			transformer.transform(source, result);
			//inizio LP PG21XX005 - Quadratura Off Pgec
			//log.debug("RPT XML: \n" + writer.toString());
			//System.out.println("RPT XML: \n" + writer.toString());
			//return writer.toString();
			String out = writer.toString();
			out = out.replaceFirst(" standalone=\"no\"", "");
			System.out.println("RPT XML: \n" + out);
			return out;
			//fine LP PG21XX005 - Quadratura Off Pgec


		} catch (ParserConfigurationException pce) {
			pce.printStackTrace();
		} catch (TransformerException tfe) {
			tfe.printStackTrace();
		}		
		return "";
	}
	
	//30052018 - inizio
	public static String getDenominazioneBeneficiario (String nomeEnteCreditore) {
		String denominazioneBeneficiario = "";
		String denominazioneBeneficiarioFull = nomeEnteCreditore;
		
		int init = denominazioneBeneficiarioFull.indexOf("[");
		int end = denominazioneBeneficiarioFull.indexOf("]");
		if (init > 0)
			denominazioneBeneficiario = denominazioneBeneficiarioFull.substring(0,init);
		if (end > 0 && end < denominazioneBeneficiarioFull.length())
			denominazioneBeneficiario = denominazioneBeneficiario.concat(denominazioneBeneficiarioFull.substring(end + 1));
		
		denominazioneBeneficiario = denominazioneBeneficiario.trim();
		return denominazioneBeneficiario;
	}
	//30052018 - fine
	
}
