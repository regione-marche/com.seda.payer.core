package com.seda.payer.core.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

import com.seda.data.dao.DAOHelper;
import com.seda.data.helper.HelperException;
import com.seda.payer.core.exception.DaoException;
import com.seda.payer.core.handler.BaseDaoHandler;

public class ProvinciaEnteDao extends BaseDaoHandler {

	public ProvinciaEnteDao(Connection connection, String schema) {
		super(connection, schema);
	}

//	private void closeConnection(CallableStatement callableStatement)
//	{
//		if (callableStatement != null)
//			DAOHelper.closeIgnoringException(callableStatement);
//	}
	
	public void doListProvince() throws DaoException {
		CallableStatement callableStatement = null;
		try	{ 
			callableStatement = prepareCall(Routines.APC_DOLIST2.routine());

			if (callableStatement.execute()) {
				this.loadWebRowSets(callableStatement);	
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
	
	public void doListEnti(String sProvincia, String sCanalePagamento) throws DaoException {
		CallableStatement callableStatement = null;
		try	{ 
			callableStatement = prepareCall(Routines.ANE_DOLIST2.routine());
			callableStatement.setString(1, sProvincia);
			callableStatement.setString(2, sCanalePagamento);

			if (callableStatement.execute()) {
				this.loadWebRowSets(callableStatement);	
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
	
	public void doListEntiPagonet(String sProvincia, String sCanalePagamento) throws DaoException {
		CallableStatement callableStatement = null;
		try	{ 
			callableStatement = prepareCall(Routines.ANE_DOLIST_PG.routine());
			callableStatement.setString(1, sProvincia);
			callableStatement.setString(2, sCanalePagamento);

			if (callableStatement.execute()) {
				this.loadWebRowSets(callableStatement);	
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
	
	//inizio LP PG200060
	//public void doListEntiEstrattoConto(String sProvincia) throws DaoException {
	public void doListEntiEstrattoConto(String sProvincia, String cupFlag) throws DaoException {
	//fine LP PG200060
		CallableStatement callableStatement = null;
		try	{ 
			callableStatement = prepareCall(Routines.ANE_DOLIST_EC.routine());
			callableStatement.setString(1, sProvincia);
			//inizio LP PG200060
			callableStatement.setString(2, cupFlag);
			//fine LP PG200060

			if (callableStatement.execute()) {
				this.loadWebRowSets(callableStatement);	
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
	
	public void doListProvinceConvenzionate() throws DaoException {
		CallableStatement callableStatement = null;
		try	{ 
			callableStatement = prepareCall(Routines.APC_DOLIST3.routine());
			
			if (callableStatement.execute()) {
				this.loadWebRowSets(callableStatement);	
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
	
	public void doListProvinceConvenzionatePagonet(String canalePagamento) throws DaoException {
		CallableStatement callableStatement = null;
		try	{ 
			System.out.println("DAO OK");
			callableStatement = prepareCall(Routines.APC_DOLIST_PG.routine());
			callableStatement.setString(1, canalePagamento);
			
			if (callableStatement.execute()) {
				this.loadWebRowSets(callableStatement);	
			}
			System.out.println("EXECUTE OK");
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
	
	//inizio LP PG200060
	//public void doListProvinceConvenzionateEstrattoConto() throws DaoException {
	public void doListProvinceConvenzionateEstrattoConto(String cupFlag) throws DaoException {		
	//fine LP PG200060
		CallableStatement callableStatement = null;
		try	{ 
			callableStatement = prepareCall(Routines.APC_DOLIST_EC.routine());
			//inizio LP PG200060
			callableStatement.setString(1, cupFlag);
			//fine LP PG200060
			
			if (callableStatement.execute()) {
				this.loadWebRowSets(callableStatement);	
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
	
	
	
	public void doListComuni(String sProvincia) throws DaoException {
		CallableStatement callableStatement = null;
		try	{ 
			callableStatement = prepareCall(Routines.ANE_DOLIST3.routine());
			callableStatement.setString(1, sProvincia);

			if (callableStatement.execute()) {
				this.loadWebRowSets(callableStatement);	
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

	public void doCachedRowSetGetComune(String nomeComune) throws DaoException {
		CallableStatement callableStatement = null;
		try	
		{
			callableStatement = prepareCall(Routines.ANE_DODETAIL_LIKE.routine());			
			callableStatement.setString(1, nomeComune);		
			if (callableStatement.execute()) 
			{
				this.loadWebRowSets(callableStatement);	
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
	
	
	public String[] doCachedRowSetGetEnteBelf(String sCodBelf) throws DaoException {
		
		String[] outRes = new String[2];
		outRes[0] = "";
		outRes[1] = "";
		
		CallableStatement callableStatement = null;
		try	{ 
			callableStatement = prepareCall(Routines.ANE_DODETAIL_BELF.routine());
			callableStatement.setString(1, sCodBelf);
			
			callableStatement.registerOutParameter(2, Types.VARCHAR);
			callableStatement.registerOutParameter(3, Types.VARCHAR);
			
			if (callableStatement.execute()) {
				this.loadWebRowSets(callableStatement);	
			}

			outRes[0] = callableStatement.getString(2).trim();
			outRes[1] = callableStatement.getString(3).trim();
	
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
		
		return outRes;
	}	
	
	
	public String doGetEnteComuneDescr(String chiaveEnte) throws DaoException {
		CallableStatement callableStatement = null;
		//inizio LP PG21XX04 Leak
		ResultSet data = null;
		//fine LP PG21XX04 Leak
		String sDescComuneEnte = "";
		try	
		{
			callableStatement = prepareCall(Routines.ANE_DODETAIL_COMUNE.routine());			
			callableStatement.setString(1, chiaveEnte);		

			if (callableStatement.execute()) {
				//inizio LP PG21XX04 Leak
				//ResultSet data = callableStatement.getResultSet();
				data = callableStatement.getResultSet();
				//fine LP PG21XX04 Leak
				if (data != null)
					if (data.next())
						sDescComuneEnte = data.getString(1);
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
		
		return sDescComuneEnte;
	}
	
	public void doCachedRowSetGetEnte(String codUtente, String codiceEnte, String tipoUfficio, String codiceUffico) throws DaoException 
	{		
		CallableStatement callableStatement = null;
		try	{ 
			callableStatement = prepareCall(Routines.ANE_DODETAIL_ENTE.routine());
			callableStatement.setString(1, codiceEnte);
			callableStatement.setString(2, tipoUfficio);
			callableStatement.setString(3, codiceUffico);
			callableStatement.setString(4, codUtente);
			
			if (callableStatement.execute()) {
				this.loadWebRowSets(callableStatement);	
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
	
	public void doGetEnteUtenteSocieta_Ane(String chiaveEnte) throws DaoException
	{
	//inizio LP 20240912 - PGNTFATT-5
		doGetEnteUtenteSocieta_AneTail(true, true,chiaveEnte);
	}

	public void doGetEnteUtenteSocieta_AneBatch(boolean bUpdateFlagAutocommit, boolean bCloseStat, String chiaveEnte) throws DaoException 
	{
		doGetEnteUtenteSocieta_AneTail(bUpdateFlagAutocommit, bCloseStat, chiaveEnte);
	}

	private void doGetEnteUtenteSocieta_AneTail(boolean bUpdateFlagAutocommit, boolean bCloseStat, String chiaveEnte) throws DaoException 
	{		
	//fine LP 20240912 - PGNTFATT-5
		CallableStatement callableStatement = null;
		try	{
			//inizio LP 20240912 - PGNTFATT-5
			//callableStatement = prepareCall(Routines.ANE_DODETAIL_ENTE2.routine());
			callableStatement = prepareCall(bUpdateFlagAutocommit, Routines.ANE_DODETAIL_ENTE2.routine());
			callableStatement.setString(1, chiaveEnte);
			if (callableStatement.execute()) {
				this.loadWebRowSets(callableStatement);	
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
			//inizio LP 20240912 - PGNTFATT-5
			if(bCloseStat) {
			//fine LP 20240912 - PGNTFATT-5
				if (callableStatement != null) {
					try {
						callableStatement.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
			//inizio LP 20240912 - PGNTFATT-5
				callableStatement = null;
			}
			//fine LP 20240912 - PGNTFATT-5
			//fine LP PG21XX04 Leak
		}
	}	
	
	public void doListEntiAll() throws DaoException {
		CallableStatement callableStatement = null;
		try	{ 
			callableStatement = prepareCall(Routines.ANE_DOLIST_TOT.routine());

			if (callableStatement.execute()) {
				this.loadWebRowSets(callableStatement);	
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
	
	public void doListBelfiore(String sSiglaProvincia) throws DaoException {
		CallableStatement callableStatement = null;
		try	{ 
			callableStatement = prepareCall(Routines.APC_DOLIST_BELFIORE.routine());
			callableStatement.setString(1, sSiglaProvincia);

			if (callableStatement.execute()) {
				this.loadWebRowSets(callableStatement);	
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
	
	public void doListComuni_Sigla(String sSiglaProvincia) throws DaoException {
		CallableStatement callableStatement = null;
		try	{ 
			callableStatement = prepareCall(Routines.APC_DOLIST_SIGLA.routine());
			callableStatement.setString(1, sSiglaProvincia);

			if (callableStatement.execute()) {
				this.loadWebRowSets(callableStatement);	
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
	
	public void doListComuni_Sigla_TN(String sSiglaProvincia) throws DaoException {
		CallableStatement callableStatement = null;
		try	{ 
			callableStatement = prepareCall(Routines.APC_DOLIST_SIGLA_TN.routine());
			callableStatement.setString(1, sSiglaProvincia);

			if (callableStatement.execute()) {
				this.loadWebRowSets(callableStatement);	
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
	
	//PG1800XX_017 GG 18122018 - inizio
	public void doListComuni_Sigla2(String sSiglaProvincia, String flagDescAlternativa) throws DaoException {
		CallableStatement callableStatement = null;
		try	{ 
			callableStatement = prepareCall(Routines.APC_DOLIST_SIGLA2.routine());
			callableStatement.setString(1, sSiglaProvincia);
			callableStatement.setString(2, flagDescAlternativa);

			if (callableStatement.execute()) {
				this.loadWebRowSets(callableStatement);	
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
	//PG1800XX_017 GG 18122018 - fine
}
