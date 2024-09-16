package com.seda.payer.core.dao;

import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;

import com.seda.commons.event.Event;
import com.seda.data.helper.HelperException;
import com.seda.payer.core.bean.PgBollIV;
import com.seda.payer.core.exception.DaoException;
import com.seda.payer.core.handler.rest.RestBaseDaoHandler;

public class PgBollIVDao extends RestBaseDaoHandler {
	
	public PgBollIVDao(Connection connection, String schema, boolean isRest, String baseUrl) {
		
		super(connection, schema, isRest, baseUrl);
	}

	public PgBollIVDao(Connection connection, String schema) {
		super(connection, schema);	
	}
	
	public PgBollIV doBollDetailIV(String cuteCute, String bollettino, String tipoDato, String codiceEnte, String codiceIUV, String codiceFiscale) throws DaoException {
		CallableStatement callableStatement = null;
		try	{ 
			callableStatement = prepareCall(Routines.BOL_DODETAIL.routine());
			
			BigDecimal bdBoll = new BigDecimal(bollettino);
			
			//ATTENZIONE: questa parte deve restare così finchè non si gestiscono correttamente gli enti nel passaggio da modello 3	
			//Se la SPEPBOLL è chiamata con codice iuv deve avere anche l'ente ma non è accettato l'ente 00000 di trentino			
//			if(codiceIUV != null && !codiceIUV.equals("") && !codiceIUV.equals("0")){
//				bollettino = "0";
//				tipoDato = "3";		//PG180200
//			}
				
//			if(codiceIUV==null || codiceIUV.equals(""))
			codiceIUV="0";
						
			
			//inizio LP PG200060
			//Commentata la riga sotto come da segnalazione email GG del 25/02/2020 
			//BigDecimal codIUV = codiceIUV==null || codiceIUV.equals("") ? new BigDecimal(0) : BigDecimal.valueOf(Long.parseLong(codiceIUV)); //24022020 GG
			//fine LP PG200060
			System.out.println("PgBollIVDao - doBollDetailIV - SPEPBOLL");
			System.out.println("PgBollIVDao - doBollDetailIV - cuteCute: " + cuteCute);
			System.out.println("PgBollIVDao - doBollDetailIV - codiceEnte: " + codiceEnte);
			System.out.println("PgBollIVDao - doBollDetailIV - bollettino: " + bollettino);
			System.out.println("PgBollIVDao - doBollDetailIV - tipoDato: " + tipoDato);
			System.out.println("PgBollIVDao - doBollDetailIV - codiceIUV: " + codiceIUV);
			System.out.println("PgBollIVDao - doBollDetailIV - codiceFiscale: " + codiceFiscale);
			
			callableStatement.setString(1, cuteCute);
			callableStatement.setString(2, codiceEnte);		//PG180200_000 SB - codente
			callableStatement.setBigDecimal(3, new BigDecimal(bollettino));
			callableStatement.setString(4, tipoDato);
			callableStatement.setBigDecimal(5, new BigDecimal(codiceIUV));		//PG180200_000 SB - bolpagopa
			callableStatement.setString(6, codiceFiscale); 				//PG2000XX_001 SB - codice fiscale			
		    callableStatement.registerOutParameter(7, Types.DECIMAL);
		    callableStatement.registerOutParameter(8, Types.VARCHAR);
		    callableStatement.setString(8, "");		//PG200400
		    callableStatement.registerOutParameter(9, Types.VARCHAR);
		    callableStatement.registerOutParameter(10, Types.VARCHAR);
		    callableStatement.registerOutParameter(11, Types.VARCHAR);
		    callableStatement.registerOutParameter(12, Types.VARCHAR);
		    callableStatement.registerOutParameter(13, Types.VARCHAR);
		    callableStatement.registerOutParameter(14, Types.VARCHAR);
		    callableStatement.registerOutParameter(15, Types.VARCHAR);  //Codice Ente
		    callableStatement.setString(15, "");	//PG200400
		    callableStatement.registerOutParameter(16, Types.VARCHAR);
		    callableStatement.setString(16, "");	//PG200400
		    callableStatement.registerOutParameter(17, Types.VARCHAR);
		    callableStatement.registerOutParameter(18, Types.VARCHAR);
		    callableStatement.registerOutParameter(19, Types.VARCHAR);
		    callableStatement.registerOutParameter(20, Types.DECIMAL);
		    callableStatement.registerOutParameter(21, Types.VARCHAR);
		    callableStatement.registerOutParameter(22, Types.DECIMAL);
		    callableStatement.registerOutParameter(23, Types.VARCHAR);
		    callableStatement.registerOutParameter(24, Types.VARCHAR);
		    callableStatement.registerOutParameter(25, Types.VARCHAR);
		    callableStatement.registerOutParameter(26, Types.VARCHAR);
		    callableStatement.registerOutParameter(27, Types.VARCHAR);
		    callableStatement.registerOutParameter(28, Types.VARCHAR);
		    callableStatement.setString(28, "");	//PG200400
		    callableStatement.registerOutParameter(29, Types.VARCHAR);
		    callableStatement.registerOutParameter(30, Types.VARCHAR);
		    callableStatement.registerOutParameter(31, Types.VARCHAR);
		    callableStatement.registerOutParameter(32, Types.VARCHAR);
		    callableStatement.registerOutParameter(33, Types.VARCHAR);	
		    callableStatement.registerOutParameter(34, Types.VARCHAR);	//PG180200_000 SB - ibanp
		    callableStatement.registerOutParameter(35, Types.VARCHAR);	//PG180200_000 SB - ibanb
		    //inizio LP PG200360
		    callableStatement.registerOutParameter(36, Types.VARCHAR);	//tassonomia
		    //fine LP PG200360
		    //QF CAUSALE:
		    //inizio LP PG200360
		    //callableStatement.registerOutParameter(36, Types.VARCHAR);
		    //inizio LP EP22405X
		    //OU_CAUSALE 
		    //callableStatement.registerOutParameter(37, Types.VARCHAR);
		    callableStatement.registerOutParameter(37, Types.VARCHAR);	//causale per preavvisi BRAV
			callableStatement.registerOutParameter(38, Types.VARCHAR); //Pagamento PagoPA per Pre-verbali ZTL
		    callableStatement.registerOutParameter(39, Types.VARCHAR);
		    //fine LP EP22405X
		    //fine LP PG200360

		    callableStatement.execute();
		    return new PgBollIV(bdBoll, callableStatement, false);
		    
		} catch (SQLException x) {
			throw new DaoException(x);
		} catch (IllegalArgumentException x) {
			throw new DaoException(x);
		} catch (HelperException x) {
			throw new DaoException(x);
		} finally {
			//inizio LP PG21XX04 Leak
			//if (callableStatement != null)
			//	DAOHelper.closeIgnoringException(callableStatement);
			if (callableStatement != null) {
				try {
					callableStatement.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			//fine LP PG21XX04 Leak
		}
	}	
	
	//PAGONET-371 - inizio
	public PgBollIV doBollDetailIVPrec(String cuteCute, String bollettino, String tipoDato, String codiceEnte, String codiceIUV, String codiceFiscale) throws DaoException {
		CallableStatement callableStatement = null;
		try	{ 
			callableStatement = prepareCall(Routines.BOL_DODETAIL.routine());
			
			BigDecimal bdBoll = new BigDecimal(bollettino);
			
			//ATTENZIONE: questa parte deve restare così finchè non si gestiscono correttamente gli enti nel passaggio da modello 3	
			//Se la SPEPBOLL è chiamata con codice iuv deve avere anche l'ente ma non è accettato l'ente 00000 di trentino			
//			if(codiceIUV != null && !codiceIUV.equals("") && !codiceIUV.equals("0")){
//				bollettino = "0";
//				tipoDato = "3";		//PG180200
//			}
				
//			if(codiceIUV==null || codiceIUV.equals(""))
			codiceIUV="0";
						
			
			//inizio LP PG200060
			//Commentata la riga sotto come da segnalazione email GG del 25/02/2020 
			//BigDecimal codIUV = codiceIUV==null || codiceIUV.equals("") ? new BigDecimal(0) : BigDecimal.valueOf(Long.parseLong(codiceIUV)); //24022020 GG
			//fine LP PG200060
			System.out.println("PgBollIVDao - doBollDetailIVPrec - SPEPBOLL");
			System.out.println("PgBollIVDao - doBollDetailIVPrec - cuteCute: " + cuteCute);
			System.out.println("PgBollIVDao - doBollDetailIVPrec - codiceEnte: " + codiceEnte);
			System.out.println("PgBollIVDao - doBollDetailIVPrec - bollettino: " + bollettino);
			System.out.println("PgBollIVDao - doBollDetailIVPrec - tipoDato: " + tipoDato);
			System.out.println("PgBollIVDao - doBollDetailIVPrec - codiceIUV: " + codiceIUV);
			System.out.println("PgBollIVDao - doBollDetailIVPrec - codiceFiscale: " + codiceFiscale);
			
			callableStatement.setString(1, cuteCute);
			callableStatement.setString(2, codiceEnte);		//PG180200_000 SB - codente
			callableStatement.setBigDecimal(3, new BigDecimal(bollettino));
			callableStatement.setString(4, tipoDato);
			callableStatement.setBigDecimal(5, new BigDecimal(codiceIUV));		//PG180200_000 SB - bolpagopa
			callableStatement.setString(6, codiceFiscale); 				//PG2000XX_001 SB - codice fiscale			
		    callableStatement.registerOutParameter(7, Types.DECIMAL);
		    callableStatement.registerOutParameter(8, Types.VARCHAR);
		    callableStatement.setString(8, "");		//PG200400
		    callableStatement.registerOutParameter(9, Types.VARCHAR);
		    callableStatement.registerOutParameter(10, Types.VARCHAR);
		    callableStatement.registerOutParameter(11, Types.VARCHAR);
		    callableStatement.registerOutParameter(12, Types.VARCHAR);
		    callableStatement.registerOutParameter(13, Types.VARCHAR);
		    callableStatement.registerOutParameter(14, Types.VARCHAR);
		    callableStatement.registerOutParameter(15, Types.VARCHAR);  //Codice Ente
		    callableStatement.setString(15, "");	//PG200400
		    callableStatement.registerOutParameter(16, Types.VARCHAR);
		    callableStatement.setString(16, "");	//PG200400
		    callableStatement.registerOutParameter(17, Types.VARCHAR);
		    callableStatement.registerOutParameter(18, Types.VARCHAR);
		    callableStatement.registerOutParameter(19, Types.VARCHAR);
		    callableStatement.registerOutParameter(20, Types.DECIMAL);
		    callableStatement.registerOutParameter(21, Types.VARCHAR);
		    callableStatement.registerOutParameter(22, Types.DECIMAL);
		    callableStatement.registerOutParameter(23, Types.VARCHAR);
		    callableStatement.registerOutParameter(24, Types.VARCHAR);
		    callableStatement.registerOutParameter(25, Types.VARCHAR);
		    callableStatement.registerOutParameter(26, Types.VARCHAR);
		    callableStatement.registerOutParameter(27, Types.VARCHAR);
		    callableStatement.registerOutParameter(28, Types.VARCHAR);
		    callableStatement.setString(28, "");	//PG200400
		    callableStatement.registerOutParameter(29, Types.VARCHAR);
		    callableStatement.registerOutParameter(30, Types.VARCHAR);
		    callableStatement.registerOutParameter(31, Types.VARCHAR);
		    callableStatement.registerOutParameter(32, Types.VARCHAR);
		    callableStatement.registerOutParameter(33, Types.VARCHAR);	
		    callableStatement.registerOutParameter(34, Types.VARCHAR);	//PG180200_000 SB - ibanp
		    callableStatement.registerOutParameter(35, Types.VARCHAR);	//PG180200_000 SB - ibanb
		    //inizio LP PG200360
		    callableStatement.registerOutParameter(36, Types.VARCHAR);	//tassonomia
		    //fine LP PG200360
		    //QF CAUSALE:
		    //inizio LP PG200360
		    //callableStatement.registerOutParameter(36, Types.VARCHAR);
		    callableStatement.registerOutParameter(37, Types.VARCHAR);
		    //fine LP PG200360

		    callableStatement.execute();			
		    return new PgBollIV(bdBoll, callableStatement, true);		    
		    
		} catch (SQLException x) {
			throw new DaoException(x);
		} catch (IllegalArgumentException x) {
			throw new DaoException(x);
		} catch (HelperException x) {
			throw new DaoException(x);
		} finally {
			//inizio LP PG21XX04 Leak
			//if (callableStatement != null)
			//	DAOHelper.closeIgnoringException(callableStatement);
			if (callableStatement != null) {
				try {
					callableStatement.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			//fine LP PG21XX04 Leak
		}
	}	
	
	public PgBollIV doBollDetailIV2Prec(String cuteCute, String bollettino, String tipoDato, String codiceEnte, String codiceIUV, String codiceFiscale,
			String ente, String idBarCode, String codiceImpostaServizio, String numeroDocumento) throws DaoException {
		CallableStatement callableStatement = null;
		try	{ 
			callableStatement = prepareCall(Routines.BOL_DODETAIL.routine());
			
			BigDecimal bdBoll = new BigDecimal(bollettino);
			
			//ATTENZIONE: questa parte deve restare così finchè non si gestiscono correttamente gli enti nel passaggio da modello 3	
			//Se la SPEPBOLL è chiamata con codice iuv deve avere anche l'ente ma non è accettato l'ente 00000 di trentino
//			if(codiceIUV != null && !codiceIUV.equals("") && !codiceIUV.equals("0")){
//				bollettino = "0";
//				tipoDato = "3";		//PG180200
//			}
			
//			if(codiceIUV==null || codiceIUV.equals(""))
			codiceIUV="0";
			
			
			//inizio LP PG200060
			//Commentata la riga sotto come da segnalazione email GG del 25/02/2020 
			//BigDecimal codIUV = codiceIUV==null || codiceIUV.equals("") ? new BigDecimal(0) : BigDecimal.valueOf(Long.parseLong(codiceIUV)); //24022020 GG
			//fine LP PG200060
			System.out.println("- doBollDetailIV2Prec - ");
			
			System.out.println("PgBollIVDao - doBollDetailIV2Prec - param  1 - cuteCute = " + cuteCute);
			System.out.println("PgBollIVDao - doBollDetailIV2Prec - param  3 - bollettino = " + bollettino);
			System.out.println("PgBollIVDao - doBollDetailIV2Prec - param  4 - tipoDato = " + tipoDato);
			System.out.println("PgBollIVDao - doBollDetailIV2Prec - param  2 - codiceEnte = " + codiceEnte);
			System.out.println("PgBollIVDao - doBollDetailIV2Prec - param  5 - codiceIUV = " + codiceIUV);
			System.out.println("PgBollIVDao - doBollDetailIV2Prec - param  6 - codiceFiscale = "+codiceFiscale);
			
			System.out.println("PgBollIVDao - doBollDetailIV2Prec - param  8 - numeroDocumento = "+numeroDocumento);
			System.out.println("PgBollIVDao - doBollDetailIV2Prec - param 15 - ente = " + ente);
			System.out.println("PgBollIVDao - doBollDetailIV2Prec - param 16 - codiceImpostaServizio = " + codiceImpostaServizio);
			System.out.println("PgBollIVDao - doBollDetailIV2Prec - param 28 - idBarCode = " + idBarCode);
					
			
			callableStatement.setString(1, cuteCute);
			callableStatement.setString(2, codiceEnte);		//PG180200_000 SB - codente
			callableStatement.setBigDecimal(3, new BigDecimal(bollettino));
			callableStatement.registerOutParameter(3, Types.DECIMAL);
			callableStatement.setString(4, tipoDato);
			callableStatement.setBigDecimal(5, new BigDecimal(codiceIUV));		//PG180200_000 SB - bolpagopa
			callableStatement.setString(6, codiceFiscale); 				//PG2000XX_001 SB - codice fiscale			
		    callableStatement.registerOutParameter(7, Types.DECIMAL);
		    callableStatement.registerOutParameter(8, Types.VARCHAR);
		    callableStatement.setString(8, ((numeroDocumento.trim().length()>0)?numeroDocumento.trim():""));		//PG200400
		    callableStatement.registerOutParameter(9, Types.VARCHAR);
		    callableStatement.registerOutParameter(10, Types.VARCHAR);
		    callableStatement.registerOutParameter(11, Types.VARCHAR);
		    callableStatement.registerOutParameter(12, Types.VARCHAR);
		    callableStatement.registerOutParameter(13, Types.VARCHAR);
		    callableStatement.registerOutParameter(14, Types.VARCHAR);
		    callableStatement.registerOutParameter(15, Types.VARCHAR);  //Codice Ente
		    callableStatement.setString(15, ((ente.trim().length()>0)?ente.trim():""));		//PG200400
		    callableStatement.registerOutParameter(16, Types.VARCHAR);
		    callableStatement.setString(16, ((codiceImpostaServizio.trim().length()>0)?codiceImpostaServizio.trim():""));		//PG200400
		    callableStatement.registerOutParameter(17, Types.VARCHAR);
		    callableStatement.registerOutParameter(18, Types.VARCHAR);
		    callableStatement.registerOutParameter(19, Types.VARCHAR);
		    callableStatement.registerOutParameter(20, Types.DECIMAL);
		    callableStatement.registerOutParameter(21, Types.VARCHAR);
		    callableStatement.registerOutParameter(22, Types.DECIMAL);
		    callableStatement.registerOutParameter(23, Types.VARCHAR);
		    callableStatement.registerOutParameter(24, Types.VARCHAR);
		    callableStatement.registerOutParameter(25, Types.VARCHAR);
		    callableStatement.registerOutParameter(26, Types.VARCHAR);
		    callableStatement.registerOutParameter(27, Types.VARCHAR);
		    callableStatement.registerOutParameter(28, Types.VARCHAR);
		    callableStatement.setString(28, ((idBarCode.trim().length()>0)?idBarCode.trim():""));	//PG200400
		    callableStatement.registerOutParameter(29, Types.VARCHAR);
		    callableStatement.registerOutParameter(30, Types.VARCHAR);
		    callableStatement.registerOutParameter(31, Types.VARCHAR);
		    callableStatement.registerOutParameter(32, Types.VARCHAR);
		    callableStatement.registerOutParameter(33, Types.VARCHAR);	
		    callableStatement.registerOutParameter(34, Types.VARCHAR);	//PG180200_000 SB - ibanp
		    callableStatement.registerOutParameter(35, Types.VARCHAR);	//PG180200_000 SB - ibanb
		    //inizio LP PG200360
		    callableStatement.registerOutParameter(36, Types.VARCHAR);	//tassonomia
		    //fine LP PG200360
		    //QF CAUSALE:
		    //inizio LP PG200360
		    //callableStatement.registerOutParameter(36, Types.VARCHAR);
		    callableStatement.registerOutParameter(37, Types.VARCHAR);
		    //fine LP PG200360

		    callableStatement.execute();					    
		    
		    if (bollettino.trim().equals("0")) 
		    	bdBoll = callableStatement.getBigDecimal(3);
		    
		    return new PgBollIV(bdBoll, callableStatement, true);		    
		    
		} catch (SQLException x) {
			throw new DaoException(x);
		} catch (IllegalArgumentException x) {
			throw new DaoException(x);
		} catch (HelperException x) {
			throw new DaoException(x);
		} finally {
			//inizio LP PG21XX04 Leak
			//if (callableStatement != null)
			//	DAOHelper.closeIgnoringException(callableStatement);
			if (callableStatement != null) {
				try {
					callableStatement.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			//fine LP PG21XX04 Leak
		}
	}
	//PAGONET-371 - fine
	
	//PG200400
	public PgBollIV doBollDetailIV2(String cuteCute, String bollettino, String tipoDato, String codiceEnte, String codiceIUV, String codiceFiscale,
			String ente, String idBarCode, String codiceImpostaServizio, String numeroDocumento) throws DaoException {
		CallableStatement callableStatement = null;
		try	{ 
			callableStatement = prepareCall(Routines.BOL_DODETAIL.routine());
			
			BigDecimal bdBoll = new BigDecimal(bollettino);
			
			//ATTENZIONE: questa parte deve restare così finchè non si gestiscono correttamente gli enti nel passaggio da modello 3	
			//Se la SPEPBOLL è chiamata con codice iuv deve avere anche l'ente ma non è accettato l'ente 00000 di trentino
//			if(codiceIUV != null && !codiceIUV.equals("") && !codiceIUV.equals("0")){
//				bollettino = "0";
//				tipoDato = "3";		//PG180200
//			}
			
//			if(codiceIUV==null || codiceIUV.equals(""))
			codiceIUV="0";
			
			
			//inizio LP PG200060
			//Commentata la riga sotto come da segnalazione email GG del 25/02/2020 
			//BigDecimal codIUV = codiceIUV==null || codiceIUV.equals("") ? new BigDecimal(0) : BigDecimal.valueOf(Long.parseLong(codiceIUV)); //24022020 GG
			//fine LP PG200060
			System.out.println("- doBollDetailIV2 - ");
			
			System.out.println("PgBollIVDao - doBollDetailIV2 - param  1 - cuteCute = " + cuteCute);
			System.out.println("PgBollIVDao - doBollDetailIV2 - param  3 - bollettino = " + bollettino);
			System.out.println("PgBollIVDao - doBollDetailIV2 - param  4 - tipoDato = " + tipoDato);
			System.out.println("PgBollIVDao - doBollDetailIV2 - param  2 - codiceEnte = " + codiceEnte);
			System.out.println("PgBollIVDao - doBollDetailIV2 - param  5 - codiceIUV = " + codiceIUV);
			System.out.println("PgBollIVDao - doBollDetailIV2 - param  6 - codiceFiscale = "+codiceFiscale);
			
			System.out.println("PgBollIVDao - doBollDetailIV2 - param  8 - numeroDocumento = "+numeroDocumento);
			System.out.println("PgBollIVDao - doBollDetailIV2 - param 15 - ente = " + ente);
			System.out.println("PgBollIVDao - doBollDetailIV2 - param 16 - codiceImpostaServizio = " + codiceImpostaServizio);
			System.out.println("PgBollIVDao - doBollDetailIV2 - param 28 - idBarCode = " + idBarCode);
					
			
			callableStatement.setString(1, cuteCute);
			callableStatement.setString(2, codiceEnte);		//PG180200_000 SB - codente
			callableStatement.setBigDecimal(3, new BigDecimal(bollettino));
			callableStatement.registerOutParameter(3, Types.DECIMAL);
			callableStatement.setString(4, tipoDato);
			callableStatement.setBigDecimal(5, new BigDecimal(codiceIUV));		//PG180200_000 SB - bolpagopa
			callableStatement.setString(6, codiceFiscale); 				//PG2000XX_001 SB - codice fiscale			
		    callableStatement.registerOutParameter(7, Types.DECIMAL);
		    callableStatement.registerOutParameter(8, Types.VARCHAR);
		    callableStatement.setString(8, ((numeroDocumento.trim().length()>0)?numeroDocumento.trim():""));		//PG200400
		    callableStatement.registerOutParameter(9, Types.VARCHAR);
		    callableStatement.registerOutParameter(10, Types.VARCHAR);
		    callableStatement.registerOutParameter(11, Types.VARCHAR);
		    callableStatement.registerOutParameter(12, Types.VARCHAR);
		    callableStatement.registerOutParameter(13, Types.VARCHAR);
		    callableStatement.registerOutParameter(14, Types.VARCHAR);
		    callableStatement.registerOutParameter(15, Types.VARCHAR);  //Codice Ente
		    callableStatement.setString(15, ((ente.trim().length()>0)?ente.trim():""));		//PG200400
		    callableStatement.registerOutParameter(16, Types.VARCHAR);
		    callableStatement.setString(16, ((codiceImpostaServizio.trim().length()>0)?codiceImpostaServizio.trim():""));		//PG200400
		    callableStatement.registerOutParameter(17, Types.VARCHAR);
		    callableStatement.registerOutParameter(18, Types.VARCHAR);
		    callableStatement.registerOutParameter(19, Types.VARCHAR);
		    callableStatement.registerOutParameter(20, Types.DECIMAL);
		    callableStatement.registerOutParameter(21, Types.VARCHAR);
		    callableStatement.registerOutParameter(22, Types.DECIMAL);
		    callableStatement.registerOutParameter(23, Types.VARCHAR);
		    callableStatement.registerOutParameter(24, Types.VARCHAR);
		    callableStatement.registerOutParameter(25, Types.VARCHAR);
		    callableStatement.registerOutParameter(26, Types.VARCHAR);
		    callableStatement.registerOutParameter(27, Types.VARCHAR);
		    callableStatement.registerOutParameter(28, Types.VARCHAR);
		    callableStatement.setString(28, ((idBarCode.trim().length()>0)?idBarCode.trim():""));	//PG200400
		    callableStatement.registerOutParameter(29, Types.VARCHAR);
		    callableStatement.registerOutParameter(30, Types.VARCHAR);
		    callableStatement.registerOutParameter(31, Types.VARCHAR);
		    callableStatement.registerOutParameter(32, Types.VARCHAR);
		    callableStatement.registerOutParameter(33, Types.VARCHAR);	
		    callableStatement.registerOutParameter(34, Types.VARCHAR);	//PG180200_000 SB - ibanp
		    callableStatement.registerOutParameter(35, Types.VARCHAR);	//PG180200_000 SB - ibanb
		    //inizio LP PG200360
		    callableStatement.registerOutParameter(36, Types.VARCHAR);	//tassonomia
		    //fine LP PG200360
		    //QF CAUSALE:
		    //inizio LP PG200360
		    //callableStatement.registerOutParameter(36, Types.VARCHAR);
		    //inizio LP EP22405X
		    //OU_CAUSALE 
		    //callableStatement.registerOutParameter(37, Types.VARCHAR);
		    //callableStatement.registerOutParameter(37, Types.VARCHAR);
		    callableStatement.registerOutParameter(37, Types.VARCHAR);	//causale per preavvisi BRAV
		    callableStatement.registerOutParameter(38, Types.VARCHAR);
		    //fine LP PG200360
		    //fine LP EP22405X

		    callableStatement.execute();					    
		    
		    if (bollettino.trim().equals("0")) 
		    	bdBoll = callableStatement.getBigDecimal(3);
		    
		    return new PgBollIV(bdBoll, callableStatement, false);		    
		    
		} catch (SQLException x) {
			throw new DaoException(x);
		} catch (IllegalArgumentException x) {
			throw new DaoException(x);
		} catch (HelperException x) {
			throw new DaoException(x);
		} finally {
			//inizio LP PG21XX04 Leak
			//if (callableStatement != null)
			//	DAOHelper.closeIgnoringException(callableStatement);
			if (callableStatement != null) {
				try {
					callableStatement.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			//fine LP PG21XX04 Leak
		}
	}
	//FINE PG200400
}
