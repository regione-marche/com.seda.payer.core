//da com.seda.payer.core.dao;
package com.seda.payer.core.dao;

//import com.seda.payer.riversamento.facade.logger.LogWriter;

//import java.sql.Array;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Date;

//import javax.sql.rowset.WebRowSet;

import com.seda.data.helper.HelperException;
import com.seda.payer.core.bean.EccedenzaDettaglioBean;
import com.seda.payer.core.bean.EccedenzaTestaBean;
import com.seda.payer.core.bean.Ente;
import com.seda.payer.core.bean.EccedenzaDetailPage;
import com.seda.payer.core.bean.EccedenzePage;
import com.seda.payer.core.exception.DaoException;
import com.seda.payer.core.handler.BaseDaoHandler;
import com.seda.payer.core.messages.Messages;

@SuppressWarnings("unchecked")
public class EccedenzeDao extends BaseDaoHandler {

/*	
	private final String PYENTSP_LST_ALL = "PYENTSP_LST_ALL";	//OK
	private final String PYECTSP_CHKNAME = "PYECTSP_CHKNAME";	//OK
	private final String PYANESP_CHKNAME = "PYANESP_CHKNAME";	//OK
	private final String PYECTSP_INS = "PYECTSP_INS";
	private final String PYECTSP_DEL = "PYECTSP_DEL";
	private final String PYECTSP_SEL = "PYECTSP_SEL"; 
	private final String PYECCSP_INS = "PYECCSP_INS";
	private final String PYECCSP_LST_FLUSSOCBI = "PYECCSP_LST_FLUSSOCBI";
	private final String PYECCSP_LST_NOTIFY = "PYECCSP_LST_NOTIFY";
	private final String PYECCSP_LST_RENDENTE = "PYECCSP_LST_RENDENTE";
	private final String PYECCSP_SEL_ESITOCBI = "PYECCSP_SEL_ESITOCBI"; 
	private final String PYECCSP_UPD_ESITOCBI = "PYECCSP_UPD_ESITOCBI";	//OK upd dopo arrivo esito CBI
	private final String PYECCSP_UPD_RENDENTE = "PYECCSP_UPD_RENDENTE"; //OK upd dopo sped rendicontazione ente
	private final String PYECCSP_UPD_FLUSSOCBI = "PYECCSP_UPD_FLUSSOCBI"; //OK upd dopo flusso CBI
	private final String PYECCSP_UPD_NOTIFY = "PYECCSP_UPD_NOTIFY";

	private final String PYECTSP_LST = "PYECTSP_LST";
	private final String PYECTSP_STAT = "PYECTSP_STAT";
	private final String PYECCSP_SEL = "PYECCSP_SEL";
*/	
	
	public EccedenzeDao(Connection connection, String schema) {
		super(connection, schema);
	}
	
	public ArrayList getListaEnti() throws DaoException 
	{
		//inizio LP PG21XX04 Leak
		CallableStatement callableStatement = null;
		ResultSet data = null;
		//fine LP PG21XX04 Leak
		ArrayList result = new ArrayList();
		try	{
//			CallableStatement callableStatement = prepareCall(PYENTSP_LST_ALL);
			//inizio LP PG21XX04 Leak
			//CallableStatement callableStatement = prepareCall(Routines.PYENTSP_LST_ALL.routine());
			callableStatement = prepareCall(Routines.PYENTSP_LST_ALL.routine());
			//fine LP PG21XX04 Leak
			if (callableStatement.execute()) {
				//inizio LP PG21XX04 Leak
				//ResultSet data = callableStatement.getResultSet();
				data = callableStatement.getResultSet();
				//fine LP PG21XX04 Leak
				while (data.next())
					result.add(new Ente(data));
			}
			return result;
		} catch (SQLException x) {
			throw new DaoException(x);
		} catch (IllegalArgumentException x) {
			throw new DaoException(x);
		} catch (HelperException x) {
			throw new DaoException(x);
		}
		//inizio LP PG21XX04 Leak
		finally {
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
		}
		//fine LP PG21XX04 Leak
	}
	
	public ArrayList getListaDettagli() throws DaoException 
	{
		//inizio LP PG21XX04 Leak
		CallableStatement callableStatement = null;
		ResultSet data = null;
		//fine LP PG21XX04 Leak
		ArrayList result = new ArrayList();
		try	{
//			CallableStatement callableStatement = prepareCall(PYECCSP_LST_FLUSSOCBI);
			//inizio LP PG21XX04 Leak
			//CallableStatement callableStatement = prepareCall(Routines.PYECCSP_LST_FLUSSOCBI.routine());
			callableStatement = prepareCall(Routines.PYECCSP_LST_FLUSSOCBI.routine());
			//fine LP PG21XX04 Leak
			if (callableStatement.execute()) {
				//inizio LP PG21XX04 Leak
				//ResultSet data = callableStatement.getResultSet();
				data = callableStatement.getResultSet();
				//fine LP PG21XX04 Leak
				while (data.next())
				{
					result.add(new EccedenzaDettaglioBean(data));
				}
			}
			return result;
		} catch (SQLException x) {
			throw new DaoException(x);
		} catch (IllegalArgumentException x) {
			throw new DaoException(x);
		} catch (HelperException x) {
			throw new DaoException(x);
		}
		//inizio LP PG21XX04 Leak
		finally {
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
		}
		//fine LP PG21XX04 Leak
	}
	
	public ArrayList getListaDettagliRendicontazione() throws DaoException 
	{
		//inizio LP PG21XX04 Leak
		CallableStatement callableStatement = null;
		ResultSet data = null;
		//fine LP PG21XX04 Leak
		ArrayList result = new ArrayList();
		try	{
			//CallableStatement callableStatement = prepareCall(PYECCSP_LST_RENDENTE);
			//inizio LP PG21XX04 Leak
			//CallableStatement callableStatement = prepareCall(Routines.PYECCSP_LST_RENDENTE.routine());
			callableStatement = prepareCall(Routines.PYECCSP_LST_RENDENTE.routine());
			//fine LP PG21XX04 Leak
			if (callableStatement.execute()) {
				//inizio LP PG21XX04 Leak
				//ResultSet data = callableStatement.getResultSet();
				data = callableStatement.getResultSet();
				//fine LP PG21XX04 Leak
				while (data.next())
				{
					result.add(new EccedenzaDettaglioBean(data));
				}
			}
			return result;
		} catch (SQLException x) {
			throw new DaoException(x);
		} catch (IllegalArgumentException x) {
			throw new DaoException(x);
		} catch (HelperException x) {
			throw new DaoException(x);
		}
		//inizio LP PG21XX04 Leak
		finally {
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
		}
		//fine LP PG21XX04 Leak
	}
	
	public ArrayList getListaDaNotificare() throws DaoException 
	{
		//inizio LP PG21XX04 Leak
		CallableStatement callableStatement = null;
		ResultSet data = null;
		//fine LP PG21XX04 Leak
		ArrayList result = new ArrayList();
		try	{
			//CallableStatement callableStatement = prepareCall(PYECCSP_LST_NOTIFY);
			//inizio LP PG21XX04 Leak
			//CallableStatement callableStatement = prepareCall(Routines.PYECCSP_LST_NOTIFY.routine());
			callableStatement = prepareCall(Routines.PYECCSP_LST_NOTIFY.routine());
			//fine LP PG21XX04 Leak
			if (callableStatement.execute()) {
				//inizio LP PG21XX04 Leak
				//ResultSet data = callableStatement.getResultSet();
				data = callableStatement.getResultSet();
				//fine LP PG21XX04 Leak
				while (data.next())
				{
					result.add(new EccedenzaDettaglioBean(data));
				}
			}
			return result;
		} catch (SQLException x) {
			throw new DaoException(x);
		} catch (IllegalArgumentException x) {
			throw new DaoException(x);
		} catch (HelperException x) {
			throw new DaoException(x);
		}
		//inizio LP PG21XX04 Leak
		finally {
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
		}
		//fine LP PG21XX04 Leak
	}
	
	public EccedenzaDettaglioBean getEsitoCBI(String codiceSocieta, String codiceUtente, String codiceUnivoco) throws DaoException 
	{
		//inizio LP PG21XX04 Leak
		CallableStatement callableStatement = null;
		ResultSet data = null;
		//fine LP PG21XX04 Leak
		EccedenzaDettaglioBean result = null;
		try	{
			//CallableStatement callableStatement = prepareCall(PYECCSP_SEL_ESITOCBI);
			//inizio LP PG21XX04 Leak
			//CallableStatement callableStatement = prepareCall(Routines.PYECCSP_SEL_ESITOCBI.routine());
			callableStatement = prepareCall(Routines.PYECCSP_SEL_ESITOCBI.routine());
			//fine LP PG21XX04 Leak
			callableStatement.setString(1, codiceSocieta);
			callableStatement.setString(2, codiceUtente);
			callableStatement.setString(3, codiceUnivoco);
			if (callableStatement.execute()) {
				//inizio LP PG21XX04 Leak
				//ResultSet data = callableStatement.getResultSet();
				data = callableStatement.getResultSet();
				//fine LP PG21XX04 Leak
				if (data.next())
				{
					result = new EccedenzaDettaglioBean(data);
				}
			}
			return result;
		} catch (SQLException x) {
			throw new DaoException(x);
		} catch (IllegalArgumentException x) {
			throw new DaoException(x);
		} catch (HelperException x) {
			throw new DaoException(x);
		}
		//inizio LP PG21XX04 Leak
		finally {
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
		}
		//fine LP PG21XX04 Leak
	}
	
	public EccedenzaTestaBean getTesta(String key) throws DaoException 
	{
		//inizio LP PG21XX04 Leak
		CallableStatement callableStatement = null;
		ResultSet data = null;
		//fine LP PG21XX04 Leak
		EccedenzaTestaBean result = new EccedenzaTestaBean();
		try	{
			//CallableStatement callableStatement = prepareCall(PYECTSP_SEL);
			//inizio LP PG21XX04 Leak
			//CallableStatement callableStatement = prepareCall(Routines.PYECTSP_SEL.routine());
			callableStatement = prepareCall(Routines.PYECTSP_SEL.routine());
			//fine LP PG21XX04 Leak
			callableStatement.setString(1, key);
			
			if (callableStatement.execute()) {
				//inizio LP PG21XX04 Leak
				//ResultSet data = callableStatement.getResultSet();
				data = callableStatement.getResultSet();
				//fine LP PG21XX04 Leak
				if (data.next())
				{
					result = new EccedenzaTestaBean(data);
				}
			}
			return result;
		} catch (SQLException x) {
			throw new DaoException(x);
		} catch (IllegalArgumentException x) {
			throw new DaoException(x);
		} catch (HelperException x) {
			throw new DaoException(x);
		}
		//inizio LP PG21XX04 Leak
		finally {
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
		}
		//fine LP PG21XX04 Leak
	}
	
	public boolean checkNomeFlusso(String nomeFlusso)throws DaoException 
	{
//		boolean result = false;
//		int nRec = 0;
		//inizio LP PG21XX04 Leak
		CallableStatement callableStatement = null;
		//fine LP PG21XX04 Leak
		try	{
			//CallableStatement callableStatement = prepareCall(PYECTSP_CHKNAME);
			//inizio LP PG21XX04 Leak
			//CallableStatement callableStatement = prepareCall(Routines.PYECTSP_CHKNAME.routine());
			callableStatement = prepareCall(Routines.PYECTSP_CHKNAME.routine());
			//fine LP PG21XX04 Leak
			callableStatement.setString(1, nomeFlusso);
			callableStatement.registerOutParameter(2, Types.INTEGER);
			callableStatement.execute();
			return callableStatement.getInt(2)>0;
		} catch (SQLException x) {
			throw new DaoException(x);
		} catch (IllegalArgumentException x) {
			throw new DaoException(x);
		} catch (HelperException x) {
			throw new DaoException(x);
		}
		//inizio LP PG21XX04 Leak
		finally {
			if (callableStatement != null) {
				try {
					callableStatement.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		//fine LP PG21XX04 Leak
	}
	
	public boolean checkEnte(String codiceEnte,String tipoUfficio, String codiceUfficio)throws DaoException 
	{
//		boolean result = false;
//		String nRec = "";
		//inizio LP PG21XX04 Leak
		CallableStatement callableStatement = null;
		//fine LP PG21XX04 Leak
		try	{
			//CallableStatement callableStatement = prepareCall(PYANESP_CHKNAME);
			//inizio LP PG21XX04 Leak
			//CallableStatement callableStatement = prepareCall(Routines.PYANESP_CHKNAME.routine());
			callableStatement = prepareCall(Routines.PYANESP_CHKNAME.routine());
			//fine LP PG21XX04 Leak
			callableStatement.setString(1, codiceEnte);
			callableStatement.setString(2, tipoUfficio);
			callableStatement.setString(3, codiceUfficio);
			callableStatement.registerOutParameter(4, Types.VARCHAR);
			callableStatement.execute();
			
			return callableStatement.getInt(4)>0;
		} catch (SQLException x) {
			throw new DaoException(x);
		} catch (IllegalArgumentException x) {
			throw new DaoException(x);
		} catch (HelperException x) {
			throw new DaoException(x);
		}
		//inizio LP PG21XX04 Leak
		finally {
			if (callableStatement != null) {
				try {
					callableStatement.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		//fine LP PG21XX04 Leak
	}
	
	public int updateFlussoCBI(String chiave, String codiceUnivoco, java.sql.Date dataDisposizione, String flagEsito, String username) throws DaoException
	{
//		CallableStatement callableStatement = null;
		//inizio LP PG21XX04 Leak
		CallableStatement callableStatement = null;
		//fine LP PG21XX04 Leak
		try	{
			//callableStatement = prepareCall(PYECCSP_UPD_FLUSSOCBI);
			//inizio LP PG21XX04 Leak
			//CallableStatement callableStatement = prepareCall(Routines.PYECCSP_UPD_FLUSSOCBI.routine());
			callableStatement = prepareCall(Routines.PYECCSP_UPD_FLUSSOCBI.routine());
			//fine LP PG21XX04 Leak
			callableStatement.setString(1, chiave);
			callableStatement.setString(2, codiceUnivoco);
			callableStatement.setDate(3, dataDisposizione);
			callableStatement.setString(4, flagEsito);
			callableStatement.setString(5, username);
			callableStatement.registerOutParameter(6, Types.INTEGER);
			callableStatement.execute();

			int risultato = callableStatement.getInt(6);
			return risultato;

			//commit();
		} catch (SQLException x) {
			throw new DaoException(x);
		} catch (IllegalArgumentException x) {
			System.out.println("insertDettaglio failed generic error due to: " + x.getMessage());
			throw new DaoException(101, x.getMessage());
		} catch (HelperException x) {
			throw new DaoException(x);
		}
		//inizio LP PG21XX04 Leak
		finally {
			if (callableStatement != null) {
				try {
					callableStatement.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		//fine LP PG21XX04 Leak
	}
	
	public int updateEsitoCBI(String chiave, String nomeSupporto, Date dataCreazione, String flagEsito, String username) throws DaoException
	{
		//CallableStatement callableStatement = null;
		//inizio LP PG21XX04 Leak
		CallableStatement callableStatement = null;
		//fine LP PG21XX04 Leak
		try	{
			//callableStatement = prepareCall(PYECCSP_UPD_ESITOCBI);
			//inizio LP PG21XX04 Leak
			//CallableStatement callableStatement = prepareCall(Routines.PYECCSP_UPD_ESITOCBI.routine());
			callableStatement = prepareCall(Routines.PYECCSP_UPD_ESITOCBI.routine());
			//fine LP PG21XX04 Leak
			callableStatement.setString(1, chiave);
			callableStatement.setString(2, nomeSupporto);
			callableStatement.setDate(3, new java.sql.Date(dataCreazione.getTime()));
			callableStatement.setString(4, flagEsito);
			callableStatement.setString(5, username);
			callableStatement.registerOutParameter(6, Types.INTEGER);
			callableStatement.execute();
			
			int risultato = callableStatement.getInt(6);
			return risultato;
			
			//commit();
		} catch (SQLException x) {
			throw new DaoException(x);
		} catch (IllegalArgumentException x) {
			System.out.println("insertDettaglio failed generic error due to: " + x.getMessage());
			throw new DaoException(101, x.getMessage());
		} catch (HelperException x) {
			throw new DaoException(x);
		}
		//inizio LP PG21XX04 Leak
		finally {
			if (callableStatement != null) {
				try {
					callableStatement.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		//fine LP PG21XX04 Leak
	}
	
	public int updateRendEnte(String chiave, String flagInvioEnte, String nomeFile, String username) throws DaoException
	{
		//CallableStatement callableStatement = null;
		//inizio LP PG21XX04 Leak
		CallableStatement callableStatement = null;
		//fine LP PG21XX04 Leak
		try	{
			//callableStatement = prepareCall(PYECCSP_UPD_RENDENTE);
			//inizio LP PG21XX04 Leak
			//CallableStatement callableStatement = prepareCall(Routines.PYECCSP_UPD_RENDENTE.routine());
			callableStatement = prepareCall(Routines.PYECCSP_UPD_RENDENTE.routine());
			//fine LP PG21XX04 Leak
			callableStatement.setString(1, chiave);
			callableStatement.setString(2, flagInvioEnte);
			callableStatement.setString(3, nomeFile);
			callableStatement.setString(4, username);
			callableStatement.registerOutParameter(5, Types.INTEGER);
			callableStatement.execute();
			
			int risultato = callableStatement.getInt(5);
			return risultato;
		} catch (SQLException x) {
			throw new DaoException(x);
		} catch (IllegalArgumentException x) {
			System.out.println("insertDettaglio failed generic error due to: " + x.getMessage());
			throw new DaoException(101, x.getMessage());
		} catch (HelperException x) {
			throw new DaoException(x);
		}
		//inizio LP PG21XX04 Leak
		finally {
			if (callableStatement != null) {
				try {
					callableStatement.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		//fine LP PG21XX04 Leak
	}
	
	
	
	public int updateStatoNotifica(String chiave, String flagEmail, String flagSms, String username) throws DaoException
	{
//		CallableStatement callableStatement = null;
		//inizio LP PG21XX04 Leak
		CallableStatement callableStatement = null;
		//fine LP PG21XX04 Leak
		try	{
//			callableStatement = prepareCall(PYECCSP_UPD_NOTIFY);
			//inizio LP PG21XX04 Leak
			//CallableStatement callableStatement = prepareCall(Routines.PYECCSP_UPD_NOTIFY.routine());
			callableStatement = prepareCall(Routines.PYECCSP_UPD_NOTIFY.routine());
			//fine LP PG21XX04 Leak
			callableStatement.setString(1, chiave);
			callableStatement.setString(2, flagEmail);
			callableStatement.setString(3, flagSms);
			callableStatement.setString(4, username);
			callableStatement.registerOutParameter(5, Types.INTEGER);
			
			callableStatement.execute();
			int risultato = callableStatement.getInt(5);
			return risultato;

			//commit();
		} catch (SQLException x) {
			throw new DaoException(x);
		} catch (IllegalArgumentException x) {
			System.out.println("insertDettaglio failed generic error due to: " + x.getMessage());
			throw new DaoException(101, x.getMessage());
		} catch (HelperException x) {
			throw new DaoException(x);
		}
		//inizio LP PG21XX04 Leak
		finally {
			if (callableStatement != null) {
				try {
					callableStatement.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		//fine LP PG21XX04 Leak
	}
	
	public void insertTesta(EccedenzaTestaBean testa) throws DaoException {
		//CallableStatement callableStatement = null;
		//inizio LP PG21XX04 Leak
		CallableStatement callableStatement = null;
		//fine LP PG21XX04 Leak
		try	{
			//callableStatement = prepareCall(PYECTSP_INS);
			//inizio LP PG21XX04 Leak
			//CallableStatement callableStatement = prepareCall(Routines.PYECTSP_INS.routine());
			callableStatement = prepareCall(Routines.PYECTSP_INS.routine());
			//fine LP PG21XX04 Leak
			testa.save(callableStatement);
			
			callableStatement.execute();
			//commit();
		} catch (SQLException x) {
			throw new DaoException(x);
		} catch (IllegalArgumentException x) {
			System.out.println("insertTesta failed generic error due to: " + x.getMessage());
			throw new DaoException(101, x.getMessage());
		} catch (HelperException x) {
			throw new DaoException(x);
		}
		//inizio LP PG21XX04 Leak
		finally {
			if (callableStatement != null) {
				try {
					callableStatement.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		//fine LP PG21XX04 Leak
	}
	
	public void insertDettaglio(EccedenzaDettaglioBean dettaglio) throws DaoException {
//		CallableStatement callableStatement = null;
		//inizio LP PG21XX04 Leak
		CallableStatement callableStatement = null;
		//fine LP PG21XX04 Leak
		try	{
			//callableStatement = prepareCall(PYECCSP_INS);
			//inizio LP PG21XX04 Leak
			//CallableStatement callableStatement = prepareCall(Routines.PYECCSP_INS.routine());
			callableStatement = prepareCall(Routines.PYECCSP_INS.routine());
			//fine LP PG21XX04 Leak
			dettaglio.save(callableStatement);
			callableStatement.execute();
			//commit();
		} catch (SQLException x) {
			throw new DaoException(x);
		} catch (IllegalArgumentException x) {
			System.out.println("insertDettaglio failed generic error due to: " + x.getMessage());
			throw new DaoException(101, x.getMessage());
		} catch (HelperException x) {
			throw new DaoException(x);
		}
		//inizio LP PG21XX04 Leak
		finally {
			if (callableStatement != null) {
				try {
					callableStatement.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		//fine LP PG21XX04 Leak
	}
	
	public void deleteTesta(EccedenzaTestaBean testa) throws DaoException {
		//CallableStatement callableStatement = null;
		//inizio LP PG21XX04 Leak
		CallableStatement callableStatement = null;
		//fine LP PG21XX04 Leak
		try	{
			//callableStatement = prepareCall(PYECTSP_DEL);
			//inizio LP PG21XX04 Leak
			//CallableStatement callableStatement = prepareCall(Routines.PYECTSP_DEL.routine());
			callableStatement = prepareCall(Routines.PYECTSP_DEL.routine());
			//fine LP PG21XX04 Leak
			testa.delete(callableStatement);
			callableStatement.execute();
			
			//commit();
		} catch (SQLException x) {
			throw new DaoException(x);
		} catch (IllegalArgumentException x) {
			System.out.println("insertDettaglio failed generic error due to: " + x.getMessage());
			throw new DaoException(101, x.getMessage());
		} catch (HelperException x) {
			throw new DaoException(x);
		}
		//inizio LP PG21XX04 Leak
		finally {
			if (callableStatement != null) {
				try {
					callableStatement.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		//fine LP PG21XX04 Leak
	}
	
	
	// ----------------------------- DOM
	
	public EccedenzePage getEccedenze(EccedenzePage dto, String ordine) throws DaoException {
		
		return getEccedenze(dto, ordine, 0, 0);
	}

	public EccedenzePage getEccedenze(EccedenzePage dto, String ordine, int rowsPerPage, int pageNumber) throws DaoException {

			if (rowsPerPage <= 0)
				throw new IllegalArgumentException(Messages.INVALID_PARAMETER.format("rowsPerPage"));

			if (pageNumber <= 0)
				throw new IllegalArgumentException(Messages.INVALID_PARAMETER.format("pageNumber"));
 
			dto = getListaEccedenze(dto, ordine, rowsPerPage, pageNumber);
			dto = getStatisticheEccedenze(dto);
			return dto;

	}

	public EccedenzaDetailPage getDettaglioEccedenza(EccedenzaDetailPage dto) throws DaoException 
	{
		//CallableStatement callableStatement = null;
		//inizio LP PG21XX04 Leak
		CallableStatement callableStatement = null;
		ResultSet ris = null;
		//fine LP PG21XX04 Leak
		try	{
			//lista riversamenti
			//callableStatement = prepareCall(PYECCSP_SEL);
			//inizio LP PG21XX04 Leak
			//CallableStatement callableStatement = prepareCall(Routines.PYECCSP_SEL.routine());
			callableStatement = prepareCall(Routines.PYECCSP_SEL.routine());
			//fine LP PG21XX04 Leak
			callableStatement.setString(1, dto.getChiaveEccedenza());

			if (callableStatement.execute()) 
			{
				this.loadWebRowSets(callableStatement);
				//dto.setListXml(getWebRowSetXml(RiversamentoDao.IDX_DOLIST_LISTA));  
				dto.setListXml(getWebRowSetXml(EccedenzeDao.IDX_DOLIST_LISTA));
			}
			
			if (callableStatement.execute()) 
			{
				//inizio LP PG21XX04 Leak
				//ResultSet ris = callableStatement.getResultSet();
				ris = callableStatement.getResultSet();
				//fine LP PG21XX04 Leak
				ris.next();
				dto.setDettaglio(new EccedenzaDettaglioBean(ris));				
			}

			
		} catch (SQLException x) {
			throw new DaoException(x);
		} catch (IllegalArgumentException x) {
			throw new DaoException(x);
		} catch (HelperException x) {
			throw new DaoException(x);
		} finally {
			//inizio LP PG21XX04 Leak
			if (ris != null) {
				try {
					ris.close();
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
			
		return dto;

}

	
	public EccedenzePage getListaEccedenze(EccedenzePage dto, String ordine, int rowsPerPage, int pageNumber) throws DaoException 
	{
		//CallableStatement callableStatement = null;
		//inizio LP PG21XX04 Leak
		CallableStatement callableStatement = null;
		//fine LP PG21XX04 Leak
		try	{

			//callableStatement = prepareCall(PYECTSP_LST);		
			//inizio LP PG21XX04 Leak
			//CallableStatement callableStatement = prepareCall(Routines.PYECTSP_LST.routine());
			callableStatement = prepareCall(Routines.PYECTSP_LST.routine());
			//fine LP PG21XX04 Leak
			callableStatement.setString(1, dto.getCodiceSocieta());
			callableStatement.setString(2, dto.getSiglaProvincia());
			callableStatement.setString(3, dto.getCodiceUtente());
			callableStatement.setString(4, dto.getCodiceEnte());
			callableStatement.setString(5, dto.getCodiceFiscale());

			callableStatement.setString(6, dto.getDataRicezioneDa());
			callableStatement.setString(7, dto.getDataRicezioneA());
			callableStatement.setString(8, dto.getEsitoRimborso());
			callableStatement.setString(9, dto.getNomeDoc());
		  
			callableStatement.setString(10, dto.getStrumento());
			callableStatement.setString(11, dto.getDataOrdineDa());
			callableStatement.setString(12, dto.getDataOrdineA());

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
			if (callableStatement.execute()) 
			{
				this.loadWebRowSets(callableStatement);
				/* we register page info */
				registerPageInfo(rowsPerPage, pageNumber, callableStatement.getInt(16), callableStatement.getInt(17), 
								 callableStatement.getInt(18), callableStatement.getInt(19));
				dto.setPageInfo(getPageInfo());
				dto.setListXml(getWebRowSetXml(EccedenzeDao.IDX_DOLIST_LISTA));
			}
		} catch (SQLException x) {
			throw new DaoException(x);
		} catch (IllegalArgumentException x) {
			throw new DaoException(x);
		} catch (HelperException x) {
			throw new DaoException(x);
		} finally {
			//inizio LP PG21XX04 Leak
			if (callableStatement != null) {
				try {
					callableStatement.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			//fine LP PG21XX04 Leak
		}
		
		return dto;
	}

	
	public EccedenzePage getStatisticheEccedenze(EccedenzePage dto) throws DaoException {
		//CallableStatement callableStatement = null;
		//inizio LP PG21XX04 Leak
		CallableStatement callableStatement = null;
		//fine LP PG21XX04 Leak
		try	{

			//callableStatement = prepareCall(PYECTSP_STAT);
			//inizio LP PG21XX04 Leak
			//CallableStatement callableStatement = prepareCall(Routines.PYECTSP_STAT.routine());
			callableStatement = prepareCall(Routines.PYECTSP_STAT.routine());
			//fine LP PG21XX04 Leak

			callableStatement.setString(1, dto.getCodiceSocieta());
			callableStatement.setString(2, dto.getSiglaProvincia());
			callableStatement.setString(3, dto.getCodiceUtente());
			callableStatement.setString(4, dto.getCodiceEnte());
			callableStatement.setString(5, dto.getCodiceFiscale());

			callableStatement.setString(6, dto.getDataRicezioneDa());
			callableStatement.setString(7, dto.getDataRicezioneA());
			callableStatement.setString(8, dto.getEsitoRimborso());
			callableStatement.setString(9, dto.getNomeDoc());
			callableStatement.setString(10, dto.getStrumento());
			callableStatement.setString(11, dto.getDataOrdineDa());
			callableStatement.setString(12, dto.getDataOrdineA());

			
			callableStatement.registerOutParameter(13, Types.DECIMAL);
			callableStatement.registerOutParameter(14, Types.DECIMAL);
			callableStatement.registerOutParameter(15, Types.DECIMAL);
			callableStatement.registerOutParameter(16, Types.DECIMAL);
			callableStatement.registerOutParameter(17, Types.DECIMAL);
			callableStatement.registerOutParameter(18, Types.DECIMAL);

			callableStatement.execute(); 

			dto.setTotImportoRimborsiPos(callableStatement.getBigDecimal(13));
			dto.setTotImportoRimborsiNeg(callableStatement.getBigDecimal(14));
			dto.setTotImportoAssegniPos(callableStatement.getBigDecimal(15));
			dto.setTotImportoAssegniNeg(callableStatement.getBigDecimal(16));
			dto.setTotImportoBonificiPos(callableStatement.getBigDecimal(17));
			dto.setTotImportoBonificiNeg(callableStatement.getBigDecimal(18));
				
		} catch (SQLException x) {
			throw new DaoException(x);
		} catch (IllegalArgumentException x) {
			throw new DaoException(x);
		} catch (HelperException x) {
			throw new DaoException(x);
		} finally {
			//inizio LP PG21XX04 Leak
			if (callableStatement != null) {
				try {
					callableStatement.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			//fine LP PG21XX04 Leak
		}
		
		return dto;
	}

	
}