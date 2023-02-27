package com.seda.payer.core.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Vector;

import com.seda.payer.commons.bean.TypeRequest;
import com.seda.payer.core.bean.AssocImpBen;
import com.seda.payer.core.bean.ConvenzioneImp;
import com.seda.payer.core.bean.GatewayPagamento;
import com.seda.payer.core.bean.TipologiaServizio;
//import com.seda.payer.core.bean.Ente;
import com.seda.payer.core.exception.DaoException;
import com.seda.payer.core.handler.BaseDaoHandler;
import com.seda.payer.core.messages.Messages;
//inizio LP PG21XX04 Leak
//import com.seda.data.dao.DAOHelper;
//fine LP PG21XX04 Leak
import com.seda.data.helper.HelperException;

public class ConvenzioneImpDao extends BaseDaoHandler {
	
/*	
	public static final String CEN_DODETAIL = "PYCENSP_SEL";
	public static final String CEN_DOLIST = "PYCENSP_LST";
	public static final String CEN_DOINSERT = "PYCENSP_INS";
	public static final String CEN_DOUPDATE = "PYCENSP_UPD";
	public static final String CEN_DODELETE = "PYCENSP_DEL";
	*/
	
	public ConvenzioneImpDao(Connection connection, String schema) {
		super(connection, schema);
	}

	
	public ConvenzioneImp doDetail(ConvenzioneImp convenzione) throws DaoException {
		CallableStatement callableStatement = null;
		//inizio LP PG21XX04 Leak
		ResultSet data = null;
		//fine LP PG21XX04 Leak
		try	{
			
			callableStatement = prepareCall(Routines.CEN_DODETAIL.routine());	
			callableStatement.setString(1, convenzione.getUser().getCompany().getCompanyCode());	//CEN_CSOCCSOC
			callableStatement.setString(2, convenzione.getUser().getUserCode());					//CEN_CUTECUTE
			callableStatement.setString(3, convenzione.getAnagImpositore().getChiaveEnte());		//CEN_KANEKENT_IMP	

			callableStatement.setString(4, convenzione.getGatewayPag().getChiaveGateway());			//CEN_KGTWKGTW
			callableStatement.setString(5, convenzione.getDataValidita());							//CEN_GCENGDAT

			if (callableStatement.execute()) {
				//inizio LP PG21XX04 Leak
				//ResultSet data = callableStatement.getResultSet();
				data = callableStatement.getResultSet();
				//fine LP PG21XX04 Leak
				if (data.next())
					return new ConvenzioneImp(data);
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
			if (data != null) {
				try {
					data.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
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


	
	public void doRowSets(ConvenzioneImp convenzione, String ordine) throws DaoException {
		doRowSets(convenzione, ordine, 0, 0);
	}

	public void doRowSets(ConvenzioneImp convenzione, String ordine, int rowsPerPage, int pageNumber) throws DaoException {
		CallableStatement callableStatement = null;

		if (rowsPerPage <= 0)
			throw new IllegalArgumentException(Messages.INVALID_PARAMETER.format("rowsPerPage"));

		if (pageNumber <= 0)
			throw new IllegalArgumentException(Messages.INVALID_PARAMETER.format("pageNumber"));

		try	{
			
/*
		IN I_CEN_CSOCCSOC CHAR(5),
  	    IN I_APC_CANEBELF CHAR(4), 
		IN I_CEN_CUTECUTE CHAR(5),
		IN I_CEN_KANEKENT_IMP CHAR(10),
		IN CAN_KCANKCAN CHAR(3),
		IN I_CEN_KGTWKGTW CHAR(10),
		IN I_CEN_GCENGDAT CHAR(10),			
 */
			callableStatement = prepareCall(Routines.CEN_DOLIST.routine());
			callableStatement.setString(1, convenzione.getUser().getCompany().getCompanyCode());
			callableStatement.setString(2, convenzione.getAnagImpositore().getAnagProvCom().getCodiceBelfiore());
			callableStatement.setString(3, convenzione.getUser().getUserCode());
			callableStatement.setString(4, convenzione.getAnagImpositore().getChiaveEnte());		
			callableStatement.setString(5, convenzione.getGatewayPag().getCanale().getChiaveCanalePagamento());		
			callableStatement.setString(6, convenzione.getGatewayPag().getChiaveGateway());		
			callableStatement.setString(7, convenzione.getDataValidita());		

			callableStatement.setString(8, ordine);
			callableStatement.setInt(9, rowsPerPage);
			callableStatement.setInt(10, pageNumber);
			/* we register row start */
			callableStatement.registerOutParameter(11, Types.INTEGER);
			/* we register row end */
			callableStatement.registerOutParameter(12, Types.INTEGER);
			/* we register total rows */
			callableStatement.registerOutParameter(13, Types.INTEGER);
			/* we register total pages */
			callableStatement.registerOutParameter(14, Types.SMALLINT);
			/* we execute procedure */
			if (callableStatement.execute()) {
				this.loadWebRowSets(callableStatement);
				/* we register page info */
				registerPageInfo(rowsPerPage, pageNumber, callableStatement.getInt(11), callableStatement.getInt(12), 
								 callableStatement.getInt(13), callableStatement.getInt(14));
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

/*	
	public int doSave(ConvenzioneImp convenzione, String codOp) throws DaoException {
		int code = 0;
		CallableStatement callableStatement = null;
		try	{
			

			if (codOp != null && codOp.compareTo(TypeRequest.ADD_SCOPE.scope())==0)
			{
				//sono in aggiunta 
				//controllo chiave
				ConvenzioneImp data = doDetail(convenzione);
				if (data != null) 
					throw new IllegalArgumentException(Messages.UNIQUE_CONSTRAINT_VIOLATION.format("Convenzione"));

				callableStatement = prepareCall(Routines.CEN_DOINSERT.routine());
			}
			else
				callableStatement = prepareCall(Routines.CEN_DOUPDATE.routine());

			convenzione.save(callableStatement);
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
	
	public int doSave(ConvenzioneImp convenzione, String codOp) throws DaoException {
		int code = 0;
		CallableStatement callableStatement = null;
		try	{
			

			if (codOp != null && codOp.compareTo(TypeRequest.ADD_SCOPE.scope())==0)
			{
	            boolean risultato = true;
				callableStatement = prepareCall(Routines.CEN_DOINSERT.routine());

				if (convenzione.getGatewayPag().getChiaveGateway().equals("0000000000"))
				{
					//ricerca tipologia carta (gateway) 
					String codSocieta = convenzione.getUser().getCompany().getCompanyCode();
					String codUtente = convenzione.getUser().getUserCode();
					String codImpositore = convenzione.getAnagImpositore().getChiaveEnte();
					Vector<String> tipCarte = getTipiCarte(codSocieta, codUtente, codImpositore);
					
					for(String codTipoCarta: tipCarte)
					{
						GatewayPagamento tipoCarta = new GatewayPagamento();
				    	tipoCarta.setChiaveGateway(codTipoCarta);

						convenzione.setGatewayPag(tipoCarta);
						try
						{
						 code = code + addConvenzione(convenzione, callableStatement);
						}
						catch(Exception e)
						{
							//inserimento non effettuato a seguito di violazione vincoli
							risultato = false;
						}
					}
//					if (!risultato)
//						code = -1; 
					if (!risultato)
					{
					 if (code==0)
						 code = -2; // nessun inserimento a causa di vincoli violati
					 else
						 code = -1; // alcuni inserimenti non eseguiti a causa di vincoli violati
					}
					else
					{
						if (code>0)
							code = 1; // tutti gli inserimenti sono riusciti
						else
							code = -3; // il ciclo non ha eseguito iterazioni per mancanza di gateway
					}
				}
				else
				{
					//singolo inserimento
					code = addConvenzione(convenzione, callableStatement);
				}
			}
			else
			{
				callableStatement = prepareCall(Routines.CEN_DOUPDATE.routine());
				code = saveConvenzione(convenzione, callableStatement);
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
		} 
		finally {
			//inizio LP PG21XX04 Leak
			//closeConnection(callableStatement);
			if (callableStatement != null) {
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

	
    private int addConvenzione(ConvenzioneImp convenzione, CallableStatement callableStatement) throws Exception
    {
		//sono in aggiunta 
		//controllo chiave
		ConvenzioneImp data = doDetail(convenzione);
		if (data != null) 
			throw new IllegalArgumentException(Messages.UNIQUE_CONSTRAINT_VIOLATION.format("Convenzione"));
		
		return saveConvenzione(convenzione, callableStatement);    	
    }

    
	private int saveConvenzione(ConvenzioneImp convenzione, CallableStatement callableStatement) throws Exception
	{

		convenzione.save(callableStatement);
		callableStatement.execute();

		return callableStatement.getInt(14);
		
	}

	
	
	
// --------------------------------------------	
	
	public int doDelete(ConvenzioneImp convenzione) throws DaoException {
		int code = 0;
		CallableStatement callableStatement = null;

		try	{
			callableStatement = prepareCall(Routines.CEN_DODELETE.routine());
			/*
			if (beneficiario.getUser().getUserCode() == null || beneficiario.getUser().getUserCode().length() == 0)
				throw new IllegalArgumentException(Messages.INVALID_PARAMETER.format("beneficiario.user.userCode"));

			if (beneficiario.getUser().getCompany() == null || beneficiario.getUser().getCompany().getCompanyCode() == null || beneficiario.getUser().getCompany().getCompanyCode().length() == 0)
				throw new IllegalArgumentException(Messages.INVALID_PARAMETER.format("beneficiario.user.company.companyCode"));
*/
			
			convenzione.delete(callableStatement);
			callableStatement.execute();
			//commit();
			code = callableStatement.getInt(6);
			
		} catch (SQLException x) {
			throw new DaoException(x.getErrorCode(),x.getMessage(),x);
		} catch (IllegalArgumentException x) {
			throw new DaoException(Integer.parseInt(Messages.ILL_ARG_ERR_CODE.format()),x.getMessage(),x);
		} catch (HelperException x) {
			throw new DaoException(Integer.parseInt(Messages.HELPER_ERR_CODE.format()),x.getMessage(),x);
		} finally {
			//inizio LP PG21XX04 Leak
			//closeConnection(callableStatement);
			if (callableStatement != null) {
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
		
	private Vector<String> getTipiCarte(String codSocieta, String codUtente, String codImpositore) throws DaoException {
		CallableStatement callableStatement = null;
		//inizio LP PG21XX04 Leak
		ResultSet data = null;
		//fine LP PG21XX04 Leak
		Vector<String> risultato = new Vector<String>();
		try	{
			callableStatement = prepareCall(Routines.GTWIMP_DODDLIST.routine());
			callableStatement.setString(1, codSocieta == null ? "" : codSocieta);
			callableStatement.setString(2, "");
			callableStatement.setString(3, codUtente == null ? "" : codUtente);
			callableStatement.setString(4, codImpositore == null ? "" : codImpositore);
			callableStatement.setString(5, "");

			if (callableStatement.execute()) 
			{
				//inizio LP PG21XX04 Leak
				//ResultSet data = callableStatement.getResultSet();
				data = callableStatement.getResultSet();
				//fine LP PG21XX04 Leak
				while (data.next())
				{
					risultato.add(data.getString("GTW_KGTWKGTW"));
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
			if (data != null) {
				try {
					data.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (callableStatement != null) {
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
	
	//inizio LP PG21XX04 Leak
	//private void closeConnection(CallableStatement callableStatement)
	//{
	//	if (callableStatement != null)
	//		DAOHelper.closeIgnoringException(callableStatement);
	//}
	//fine LP PG21XX04 Leak
}