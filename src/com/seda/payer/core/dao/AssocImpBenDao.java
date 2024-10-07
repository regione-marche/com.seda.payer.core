package com.seda.payer.core.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import com.seda.payer.commons.bean.TypeRequest;
import com.seda.payer.core.bean.AssocImpBen;
import com.seda.payer.core.bean.TipologiaServizio;
import com.seda.payer.core.exception.DaoException;
import com.seda.payer.core.handler.BaseDaoHandler;
import com.seda.payer.core.messages.Messages;
import com.seda.data.helper.HelperException;

import java.util.Vector;

// 5/9/2011 aggiunta combo PYAPCSP_LST_CEB_DDL e PYANESP_LST_CEB_DDL

public class AssocImpBenDao extends BaseDaoHandler {

	/*
	public static final String CEB_DODETAIL = "PYCEBSP_SEL";
	public static final String CEB_DOLIST = "PYCEBSP_LST";
	public static final String CEB_DOINSERT = "PYCEBSP_INS";
	public static final String CEB_DOUPDATE = "PYCEBSP_UPD";
	public static final String CEB_DODELETE = "PYCEBSP_DEL";
	public static final String CEB_DODDLIST = "PYCEBSP_LST_DDL";
	*/
/*
	public static final String APCBEN_CEBDODDLIST = "PYAPCBENSP_LST_CEB_DDL";
	public static final String ANEBEN_CEBDODDLIST = "PYANEBENSP_LST_CEB_DDL";
*/
	
	public AssocImpBenDao(Connection connection, String schema) {
		super(connection, schema);
	}

	//lista dei province dai beneficiari presi per convenzione 
	public String doDDListProvinceByConv(String codSocieta) throws DaoException {
		CallableStatement callableStatement = null;
		try	{ 
//			callableStatement = prepareCall(APCBEN_CEBDODDLIST);
			callableStatement = prepareCall(Routines.APCBEN_CEBDODDLIST.routine());
			callableStatement.setString(1, codSocieta == null ? "" : codSocieta);
			if (callableStatement.execute()) {
				this.loadWebRowSets(callableStatement);	
			}
			return this.getWebRowSetXml(BeneficiarioDao.IDX_DOLIST_LISTA);
		} catch (SQLException x) {
			throw new DaoException(x);
		} catch (IllegalArgumentException x) {
			throw new DaoException(x);
		} catch (HelperException x) {
			throw new DaoException(x);
		} finally {
			//inizio LP PG21XX04 Leak
			//closeConnection(callableStatement);
			if(callableStatement != null) {
				try {
					callableStatement.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			//fine LP PG21XX04 Leak
		}
	}

	//lista dei beneficiari dalle convenzioni
	public String doDDListBeneficiariByConv(String codSocieta, String codProvincia, String codUtente, String codEnte) throws DaoException {
		CallableStatement callableStatement = null;
		try	{ 
//			callableStatement = prepareCall(ANEBEN_CEBDODDLIST);
			callableStatement = prepareCall(Routines.ANEBEN_CEBDODDLIST.routine());
			callableStatement.setString(1, codSocieta == null ? "" : codSocieta);
			callableStatement.setString(2, codProvincia == null ? "" : codProvincia);
			callableStatement.setString(3, codUtente == null ? "" : codUtente);
			callableStatement.setString(4, codEnte == null ? "" : codEnte);
			if (callableStatement.execute()) {
				this.loadWebRowSets(callableStatement);	
			}
			return this.getWebRowSetXml(BeneficiarioDao.IDX_DOLIST_LISTA);
		} catch (SQLException x) {
			throw new DaoException(x);
		} catch (IllegalArgumentException x) {
			throw new DaoException(x);
		} catch (HelperException x) {
			throw new DaoException(x);
		} finally {
			//inizio LP PG21XX04 Leak
			//closeConnection(callableStatement);
			if(callableStatement != null) {
				try {
					callableStatement.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			//fine LP PG21XX04 Leak
		}
	}

	public AssocImpBen doDetail(AssocImpBen associazione) throws DaoException {
		CallableStatement callableStatement = null;
		//inizio LP PG21XX04 Leak
		ResultSet data = null;
		//fine LP PG21XX04 Leak
		try	{
			callableStatement = prepareCall(Routines.CEB_DODETAIL.routine());
			callableStatement.setString(1, associazione.getUser().getCompany().getCompanyCode());
			callableStatement.setString(2, associazione.getUser().getUserCode());
			callableStatement.setString(3, associazione.getAnagImpositore().getChiaveEnte());		
			callableStatement.setString(4, associazione.getTipologiaServizio().getCodiceTipologiaServizio());		
			callableStatement.setString(5, associazione.getAnnoRifDa());		
			callableStatement.setString(6, associazione.getAnnoRifA());		
			callableStatement.setString(7, associazione.getDataValidita());		
			callableStatement.setString(8, associazione.getAnagBeneficiario().getChiaveEnte());
			callableStatement.setString(9, associazione.getUserBeneficiario().getCompany().getCompanyCode());
			callableStatement.setString(10, associazione.getUserBeneficiario().getUserCode());
			if (callableStatement.execute()) {
				//inizio LP PG21XX04 Leak
				//ResultSet data = callableStatement.getResultSet();
				data = callableStatement.getResultSet();
				//fine LP PG21XX04 Leak
				if (data.next())
					return new AssocImpBen(data);
			}
			return null;
		} catch (SQLException x) {
			throw new DaoException(x);
		} catch (IllegalArgumentException x) {
			throw new DaoException(x);
		} catch (HelperException x) {
			throw new DaoException(x);
		} finally {
			//inizio LP PG21XX04 Leak
			//closeConnection(callableStatement);
			if(data != null) {
				try {
					data.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(callableStatement != null) {
				try {
					callableStatement.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			//fine LP PG21XX04 Leak
		}
	}

	private AssocImpBen doVincolo(AssocImpBen associazione) throws DaoException {
		CallableStatement callableStatement = null;
		//inizio LP PG21XX04 Leak
		ResultSet data = null;
		//fine LP PG21XX04 Leak
		try	{
			callableStatement = prepareCall(Routines.CEB_TESTVINCOLO.routine());
			callableStatement.setString(1, associazione.getUser().getCompany().getCompanyCode());
			callableStatement.setString(2, associazione.getUser().getUserCode());
			callableStatement.setString(3, associazione.getAnagImpositore().getChiaveEnte());		
			callableStatement.setString(4, associazione.getTipologiaServizio().getCodiceTipologiaServizio());		
			callableStatement.setString(5, associazione.getDataValidita());		
			if (callableStatement.execute()) {
				//inizio LP PG21XX04 Leak
				//ResultSet data = callableStatement.getResultSet();
				data = callableStatement.getResultSet();
				//fine LP PG21XX04 Leak
				if (data.next())
					return new AssocImpBen(data);
			}
			return null;
		} catch (SQLException x) {
			throw new DaoException(x);
		} catch (IllegalArgumentException x) {
			throw new DaoException(x);
		} catch (HelperException x) {
			throw new DaoException(x);
		} finally {
			//inizio LP PG21XX04 Leak
			//closeConnection(callableStatement);
			if(data != null) {
				try {
					data.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(callableStatement != null) {
				try {
					callableStatement.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			//fine LP PG21XX04 Leak
		}
	}

	public void doRowSets(AssocImpBen associazione, String ordine) throws DaoException {
		doRowSets(associazione, ordine, 0, 0);
	}

	public void doRowSets(AssocImpBen associazione, String ordine, int rowsPerPage, int pageNumber) throws DaoException {
		if (rowsPerPage <= 0)
			throw new IllegalArgumentException(Messages.INVALID_PARAMETER.format("rowsPerPage"));
		if (pageNumber <= 0)
			throw new IllegalArgumentException(Messages.INVALID_PARAMETER.format("pageNumber"));
		CallableStatement callableStatement = null;
		try	{
/*
         IN I_SOC_CSOCCSOC CHAR(5),
  	    IN I_APC_CANEBELF CHAR(4), 
  	    IN I_UTE_CUTECUTE CHAR(5),
  	    IN I_CEB_KANEKENT_IMP CHAR(10),
  	    IN I_CEB_KANEKENT_BEN CHAR(10),
  	    IN I_CEB_GCEBGDAT CHAR(10),
  	    IN I_CEB_NCEBANNO_DA CHAR(4),
  	    IN I_CEB_NCEBANNO_A CHAR(4),
  	    IN I_TSE_CTSECTSE CHAR(3),
			
 */
			callableStatement = prepareCall(Routines.CEB_DOLIST.routine());
			callableStatement.setString(1, associazione.getUser().getCompany().getCompanyCode());
			callableStatement.setString(2, associazione.getAnagImpositore().getAnagProvCom().getCodiceBelfiore());
			callableStatement.setString(3, associazione.getUser().getUserCode());
			callableStatement.setString(4, associazione.getAnagImpositore().getChiaveEnte());		
			callableStatement.setString(5, associazione.getAnagBeneficiario().getChiaveEnte());		
			callableStatement.setString(6, associazione.getDataValidita());		
			callableStatement.setString(7, associazione.getAnnoRifDa());		
			callableStatement.setString(8, associazione.getAnnoRifA());		
			callableStatement.setString(9, associazione.getTipologiaServizio().getCodiceTipologiaServizio());		
			callableStatement.setString(10, associazione.getUserBeneficiario().getCompany().getCompanyCode());
			callableStatement.setString(11, associazione.getAnagBeneficiario().getAnagProvCom().getCodiceBelfiore());
			callableStatement.setString(12, associazione.getUserBeneficiario().getUserCode());
			callableStatement.setString(13, ordine);
			callableStatement.setInt(14, rowsPerPage);
			callableStatement.setInt(15, pageNumber);
			/* we register row start */
			callableStatement.registerOutParameter(16, Types.INTEGER);
			/* we register row end */
			callableStatement.registerOutParameter(17, Types.INTEGER);
			/* we register total rows */
			callableStatement.registerOutParameter(18, Types.INTEGER);
			/* we register total pages */
			callableStatement.registerOutParameter(19, Types.SMALLINT);
			/* we execute procedure */
			if (callableStatement.execute()) {
				this.loadWebRowSets(callableStatement);
				/* we register page info */
				registerPageInfo(rowsPerPage, pageNumber, callableStatement.getInt(16), callableStatement.getInt(17), 
								 callableStatement.getInt(18), callableStatement.getInt(19));
			}
		} catch (SQLException x) {
			throw new DaoException(x);
		} catch (IllegalArgumentException x) {
			throw new DaoException(x);
		} catch (HelperException x) {
			throw new DaoException(x);
		} finally {
			//inizio LP PG21XX04 Leak
			//closeConnection(callableStatement);
			if(callableStatement != null) {
				try {
					callableStatement.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			//fine LP PG21XX04 Leak
		}
	}

/*	
	public int doSave(AssocImpBen associazione, String codOp) throws DaoException {
		CallableStatement callableStatement = null;
		int code = 0;
		
		try	{

			if (codOp != null && codOp.compareTo(TypeRequest.ADD_SCOPE.scope())==0)
			{
				
				//sono in aggiunta 
				//controllo chiave
				AssocImpBen data = doDetail(associazione);
				if (data != null) 
					throw new IllegalArgumentException(Messages.UNIQUE_CONSTRAINT_VIOLATION.format("Associazione"));

				//controllo associazione impositore / beneficiario
				AssocImpBen appoggio = new AssocImpBen();
				appoggio.getUser().getCompany().setCompanyCode(associazione.getUser().getCompany().getCompanyCode());
				appoggio.getUser().setUserCode(associazione.getUser().getUserCode());
				appoggio.getAnagImpositore().setChiaveEnte(associazione.getAnagImpositore().getChiaveEnte());
				appoggio.getAnagBeneficiario().setChiaveEnte(associazione.getAnagBeneficiario().getChiaveEnte());
				doRowSets(appoggio, "", 2, 1);
				if (getPageInfo().getNumRows()>0)
					throw new IllegalArgumentException(Messages.UNIQUE_CONSTRAINT_VIOLATION.format("Associazione Impositore/Beneficiario"));

				callableStatement = prepareCall(Routines.CEB_DOINSERT.routine());
			}
			else
				callableStatement = prepareCall(Routines.CEB_DOUPDATE.routine());

			
			associazione.save(callableStatement);
			callableStatement.execute();
			//commit();

			code = callableStatement.getInt(14);
			
		} catch (SQLException x) {
			throw new DaoException(x);
		} catch (IllegalArgumentException x) {
			System.out.println("doSave failed generic error due to: " + x.getMessage());
			throw new DaoException(101, x.getMessage());
		} catch (HelperException x) {
			throw new DaoException(x);
		} finally {
			closeConnection(callableStatement);
		}
		
		return code;
	}
*/
	
	public int doSave(AssocImpBen associazione, String codOp) throws DaoException {
		CallableStatement callableStatement = null;
		int code = 0;
		try {
			if (codOp != null && codOp.compareTo(TypeRequest.ADD_SCOPE.scope()) == 0) {
	            boolean risultato = true;
				callableStatement = prepareCall(Routines.CEB_DOINSERT.routine());
				//controllo immissione
				if (associazione.getTipologiaServizio().getCodiceTipologiaServizio().equals("000")) {
					//ricerca servizi per società
					String codSocieta = associazione.getUser().getCompany().getCompanyCode();
					String codUtente = associazione.getUser().getUserCode();
					String chiaveEnte = associazione.getAnagImpositore().getChiaveEnte();
					Vector<String> tipSer = getTipologieServizi(codSocieta,codUtente,chiaveEnte);
					for(String codTipologiaServizio: tipSer) {
						TipologiaServizio tipologiaServizio = new TipologiaServizio();
				    	tipologiaServizio.getCompany().setCompanyCode(codSocieta);	    	
				    	tipologiaServizio.setCodiceTipologiaServizio(codTipologiaServizio);
						associazione.setTipologiaServizio(tipologiaServizio);
						try {
						 code = code + addAssociazione(associazione, callableStatement);
						} catch(Exception e) {
							//inserimento non effettuato a seguito di violazione vincoli
							risultato = false;
						}
					}
					if (!risultato) {
						if (code == 0)
							code = -2; // nessun inserimento a causa di vincoli violati
						else
							code = -1; // alcuni inserimenti non eseguiti a causa di vincoli violati
					} else {
						if (code>0)
							code = 1; // tutti gli inserimenti sono riusciti
						else
							code = -3; // il ciclo non ha eseguito iterazioni per mancanza di tipologia servizi							 
						}
				} else {
					//singolo inserimento
					code = addAssociazione(associazione, callableStatement);	//0 nessun inseirmento 1 inserimento riuscito
				}
			} else {
				callableStatement = prepareCall(Routines.CEB_DOUPDATE.routine());
				code = saveAssociazione(associazione, callableStatement);
			}
		} catch (SQLException x) {
			throw new DaoException(x);
		} catch (IllegalArgumentException x) {
			System.out.println("doSave failed generic error due to: " + x.getMessage());
			throw new DaoException(101, x.getMessage());
		} catch (HelperException x) {
			throw new DaoException(x);
		} catch (Exception e) {
			throw new DaoException(e);
		}  finally {
			//inizio LP PG21XX04 Leak
			//closeConnection(callableStatement);
			if(callableStatement != null) {
				try {
					callableStatement.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			//fine LP PG21XX04 Leak
		}
		return code;
	}

    private int addAssociazione(AssocImpBen associazione, CallableStatement callableStatement) throws Exception
    {
		//sono in aggiunta 
		//controllo chiave
		AssocImpBen data = doDetail(associazione);
		if (data != null) 
			throw new IllegalArgumentException(Messages.UNIQUE_CONSTRAINT_VIOLATION.format("Associazione"));
		//controllo associazione impositore / beneficiario
		AssocImpBen appoggio = new AssocImpBen();
/*
 //cambio vincolo
		appoggio.getUser().getCompany().setCompanyCode(associazione.getUser().getCompany().getCompanyCode());
		appoggio.getUser().setUserCode(associazione.getUser().getUserCode());
		appoggio.getAnagImpositore().setChiaveEnte(associazione.getAnagImpositore().getChiaveEnte());
		appoggio.getAnagBeneficiario().setChiaveEnte(associazione.getAnagBeneficiario().getChiaveEnte());
		doRowSets(appoggio, "", 2, 1);
		if (getPageInfo().getNumRows()>0)
			throw new IllegalArgumentException(Messages.UNIQUE_CONSTRAINT_VIOLATION.format("Associazione Impositore/Beneficiario"));
*/
		String codSocieta = associazione.getUser().getCompany().getCompanyCode();
		appoggio.getUser().getCompany().setCompanyCode(codSocieta);
		appoggio.getUser().setUserCode(associazione.getUser().getUserCode());
		appoggio.getAnagImpositore().setChiaveEnte(associazione.getAnagImpositore().getChiaveEnte());
        appoggio.getTipologiaServizio().getCompany().setCompanyCode(codSocieta);
        appoggio.getTipologiaServizio().setCodiceTipologiaServizio(associazione.getTipologiaServizio().getCodiceTipologiaServizio());
        appoggio.setDataValidita(associazione.getDataValidita());        
		if (doVincolo(appoggio)!=null)
			throw new IllegalArgumentException(Messages.UNIQUE_CONSTRAINT_VIOLATION.format("Associazione Impositore/Servizio/Data Validita"));
// fine mod        
		return saveAssociazione(associazione, callableStatement);    	
    }

	private int saveAssociazione(AssocImpBen associazione, CallableStatement callableStatement) throws Exception
	{
		associazione.save(callableStatement);
		callableStatement.execute();
		return callableStatement.getInt(16);
		
	}

	public int doDelete(AssocImpBen associazione) throws DaoException {
		int code = 0;
		CallableStatement callableStatement = null;
		try	{
			callableStatement = prepareCall(Routines.CEB_DODELETE.routine());
			/*
			if (beneficiario.getUser().getUserCode() == null || beneficiario.getUser().getUserCode().length() == 0)
				throw new IllegalArgumentException(Messages.INVALID_PARAMETER.format("beneficiario.user.userCode"));

			if (beneficiario.getUser().getCompany() == null || beneficiario.getUser().getCompany().getCompanyCode() == null || beneficiario.getUser().getCompany().getCompanyCode().length() == 0)
				throw new IllegalArgumentException(Messages.INVALID_PARAMETER.format("beneficiario.user.company.companyCode"));
*/
			
			/*
			 		IN I_CEB_CSOCCSOC CHAR(5),
		IN I_CEB_CUTECUTE CHAR(5),
		IN I_CEB_KANEKENT_IMP CHAR(10),
		IN I_CEB_CTSECTSE CHAR(3),
		IN I_CEB_NCEBANNO_DA CHAR(4),
		IN I_CEB_NCEBANNO_A CHAR(4),
		IN I_CEB_GCEBGDAT CHAR(10)

			 */
			associazione.delete(callableStatement);
			callableStatement.execute();
			//commit();
			code = callableStatement.getInt(11);
		} catch (SQLException x) {
			throw new DaoException(x.getErrorCode(),x.getMessage(),x);
		} catch (IllegalArgumentException x) {
			throw new DaoException(Integer.parseInt(Messages.ILL_ARG_ERR_CODE.format()),x.getMessage(),x);
		} catch (HelperException x) {
			throw new DaoException(Integer.parseInt(Messages.HELPER_ERR_CODE.format()),x.getMessage(),x);
		} finally {
			//inizio LP PG21XX04 Leak
			//closeConnection(callableStatement);
			if(callableStatement != null) {
				try {
					callableStatement.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			//fine LP PG21XX04 Leak
		}
		return code;
	}

//mod
	
	private Vector<String> getTipologieServizi(String codSocieta, String codUtente, String chiaveEnte) throws DaoException {
		CallableStatement callableStatement = null;
		//inizio LP PG21XX04 Leak
		ResultSet data = null;
		//fine LP PG21XX04 Leak
		Vector<String> risultato = new Vector<String>();
		try	{
			callableStatement = prepareCall(Routines.PYTSESP_DDL_2.routine());
			callableStatement.setString(1, codSocieta);
			callableStatement.setString(2, codUtente);
			callableStatement.setString(3, chiaveEnte);
			callableStatement.setString(4, "");
			//callableStatement = prepareCall(Routines.TMG_TIPOLOGIA_SERVIZIO_GET_DDL.routine());
			//callableStatement.setString(1, codSocieta == null ? "" : codSocieta);
			if (callableStatement.execute()) {
				//inizio LP PG21XX04 Leak
				//ResultSet data = callableStatement.getResultSet();
				data = callableStatement.getResultSet();
				//fine LP PG21XX04 Leak
				while (data.next()) {
					risultato.add(data.getString("TSE_CTSECTSE"));
				}
			}
		} catch (SQLException x) {
			throw new DaoException(x);
		} catch (IllegalArgumentException x) {
			throw new DaoException(x);
		} catch (HelperException x) {
			throw new DaoException(x);
		} finally {
			//inizio LP PG21XX04 Leak
			//closeConnection(callableStatement);
			if(data != null) {
				try {
					data.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(callableStatement != null) {
				try {
					callableStatement.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			//fine LP PG21XX04 Leak
		}
		return risultato;
	}
	
// fine mod	
	
//	private void closeConnection(CallableStatement callableStatement)
//	{
//		if (callableStatement != null)
//			DAOHelper.closeIgnoringException(callableStatement);
//	}

}