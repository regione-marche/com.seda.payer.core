package com.seda.payer.core.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

import javax.sql.DataSource;
import com.seda.data.procedure.reflection.MetaProcedure;
import com.seda.data.procedure.reflection.ProcedureReflectorException;
import com.seda.payer.core.bean.ScadenzaUrlNotifica;
import com.seda.payer.core.exception.DaoException;
import com.seda.payer.core.handler.BaseDaoHandler;
import com.seda.payer.core.wallet.bean.EsitoRisposte;

public class ScadenzaUrlNotificaDaoImpl extends BaseDaoHandler implements ScadenzaUrlNotificaDao   {
	private static final long serialVersionUID = 1L;

	//inizio LP PG21XX04 Leak
	@Deprecated
	//fine LP PG21XX04 Leak
	public ScadenzaUrlNotificaDaoImpl(DataSource dataSource, String schema) throws SQLException {
		super(dataSource.getConnection(), schema);
	}

	public ScadenzaUrlNotificaDaoImpl(Connection connection, String schema) throws SQLException {
		super(connection, schema);
	}


	public ScadenzaUrlNotifica select(String codEnte, String codiceFiscale, String numeroDocumento ) throws DaoException {
		CallableStatement callableStatement = null;
		ResultSet resultSet = null;
		Connection connection = null;
		ScadenzaUrlNotifica scadenzaUrlNotifica = null;
		try {
			connection = getConnection();
			//inizio LP PGNTCORE-24 
			//callableStatement = Helper.prepareCall(connection, getSchema(), "PYUNPSP_SEL");
            callableStatement = MetaProcedure.prepareCall(connection, getSchema(), "PYUNPSP_SEL");
			//fine LP PGNTCORE-24 
			System.out.println("PYEUNPSP_SEL - codEnte: " + codEnte);
			System.out.println("PYEUNPSP_SEL - codiceFiscale: " + codiceFiscale);
			System.out.println("PYEUNPSP_SEL - numeroDocumento: " + numeroDocumento);
			callableStatement.setString(1, codEnte);
			callableStatement.setString(2, codiceFiscale);
			callableStatement.setString(3, numeroDocumento);

			callableStatement.execute();

			resultSet=callableStatement.getResultSet();
			if(resultSet.next()) {
				scadenzaUrlNotifica = new ScadenzaUrlNotifica(resultSet);
			}
			
		} catch (SQLException e) {
			throw new DaoException(e);
		} catch (IllegalArgumentException e) {
			throw new DaoException(e);
		//inizio LP PGNTCORE-24 
		//} catch (HelperException e) {
		//	throw new DaoException(e);
		//} 
		} catch (ProcedureReflectorException e) {
			throw new DaoException(e);
		} 
		//inizio LP PG21XX04 Leak
		finally {
			if (resultSet != null) {
				try {
					resultSet.close();
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
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		//fine LP PG21XX04 Leak
		return scadenzaUrlNotifica;
	}
	
	public Integer update(ScadenzaUrlNotifica scadenzaUrl )	throws DaoException {
		CallableStatement callableStatement=null;
		Connection connection = null;
		int ret=0;
		try {
			connection = getConnection();
			//inizio LP PGNTCORE-24 
			//callableStatement = Helper.prepareCall(connection, getSchema(), "PYUNPSP_UPD");
            callableStatement = MetaProcedure.prepareCall(connection, getSchema(), "PYUNPSP_UPD");
			//fine LP PGNTCORE-24 
			callableStatement.setString(1, scadenzaUrl.getCodiceEnte());
			if(scadenzaUrl.getCodiceUtente()==null || scadenzaUrl.getCodiceUtente().equals(""))
				callableStatement.setNull(2, Types.VARCHAR);
			else
				callableStatement.setString(2, scadenzaUrl.getCodiceUtente());
			if(scadenzaUrl.getCodiceSocieta() ==null || scadenzaUrl.getCodiceSocieta().equals(""))
				callableStatement.setNull(3, Types.VARCHAR);
			else
				callableStatement.setString(3, scadenzaUrl.getCodiceSocieta());
			callableStatement.setString(4, scadenzaUrl.getCodiceFiscale());
			callableStatement.setString(5, scadenzaUrl.getNumeroDocumento());
			if(scadenzaUrl.getUrlDownload() ==null || scadenzaUrl.getUrlDownload().equals(""))
				callableStatement.setNull(6, Types.VARCHAR);
			else
				callableStatement.setString(6, scadenzaUrl.getUrlDownload());
			if(scadenzaUrl.getChiaveNotifica() ==null || scadenzaUrl.getChiaveNotifica().equals(""))
				callableStatement.setNull(7, Types.VARCHAR);
			else
				callableStatement.setString(7, scadenzaUrl.getChiaveNotifica());
			if(scadenzaUrl.getDataScadenza() ==null || scadenzaUrl.getDataScadenza().equals(""))
				callableStatement.setNull(8, Types.TIMESTAMP);
			else
				callableStatement.setTimestamp(8,  new java.sql.Timestamp(scadenzaUrl.getDataScadenza().getTimeInMillis()));
			callableStatement.registerOutParameter(9, Types.INTEGER);						
			callableStatement.execute();
			ret = callableStatement.getInt(9);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DaoException(e);
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
			throw new DaoException(e);
		//inizio LP PGNTCORE-24 
		//} catch (HelperException e) {
		//	e.printStackTrace();
		//	throw new DaoException(e);
		//} 
		} catch (ProcedureReflectorException e) {
			e.printStackTrace();
			throw new DaoException(e);
		//fine LP PGNTCORE-24 
		//inizio LP PG21XX04 Leak
		} finally {
			//inizio LP PG21XX04 Leak
			//DAOHelper.closeIgnoringException(connection);
			if (callableStatement != null) {
				try {
					callableStatement.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			//fine LP PG21XX04 Leak
		}
		return ret;
	}
	
	public EsitoRisposte insert(ScadenzaUrlNotifica scadenzaUrlNotifica )	throws DaoException {
		CallableStatement callableStatement=null;
		Connection connection = null;
		EsitoRisposte  esitoRisposte = new EsitoRisposte();
		try {
			connection = getConnection();
			//inizio LP PGNTCORE-24 
			//callableStatement = Helper.prepareCall(connection, getSchema(), "PYUNPSP_INS");
            callableStatement = MetaProcedure.prepareCall(connection, getSchema(), "PYUNPSP_INS");
			//fine LP PGNTCORE-24 
			callableStatement.setString(1, scadenzaUrlNotifica.getCodiceEnte());
			callableStatement.setString(2, scadenzaUrlNotifica.getCodiceUtente());
			callableStatement.setString(3, scadenzaUrlNotifica.getCodiceSocieta());
			callableStatement.setString(4, scadenzaUrlNotifica.getCodiceFiscale());
			callableStatement.setString(5, scadenzaUrlNotifica.getNumeroDocumento());
			callableStatement.setString(6, scadenzaUrlNotifica.getUrlDownload());
			callableStatement.setString(7, scadenzaUrlNotifica.getChiaveNotifica());
			callableStatement.setTimestamp(8, new java.sql.Timestamp(scadenzaUrlNotifica.getDataScadenza().getTimeInMillis()));
			callableStatement.registerOutParameter(9, Types.VARCHAR);		
			callableStatement.registerOutParameter(10, Types.VARCHAR);
			callableStatement.execute();
			esitoRisposte.setCodiceMessaggio(callableStatement.getString(9));
			esitoRisposte.setDescrizioneMessaggio(callableStatement.getString(10));
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DaoException(e);
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
			throw new DaoException(e);
		//inizio LP PGNTCORE-24 
		//} catch (HelperException e) {
		//	e.printStackTrace();
		//	throw new DaoException(e);
		//} 
		} catch (ProcedureReflectorException e) {
			e.printStackTrace();
			throw new DaoException(e);
		//fine LP PGNTCORE-24 
		} finally {
			//inizio LP PG21XX04 Leak
			//DAOHelper.closeIgnoringException(connection);
			if (callableStatement != null) {
				try {
					callableStatement.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			//fine LP PG21XX04 Leak
		}
		return esitoRisposte;
	}
	
	public String getCodiceSocietaByEnteUtente(String cutecute, String codEnte) throws DaoException {
		CallableStatement callableStatement=null;
		Connection connection = null;
		ResultSet listInfoCodEnteImpositore = null;
	
		String codiceSocieta = "";
		
		try {	
			connection = getConnection();
			//inizio LP PGNTCORE-24 
			//callableStatement = Helper.prepareCall(connection, getSchema(), "PYENTSP_SEL_INFO_CENT");
            callableStatement = MetaProcedure.prepareCall(connection, getSchema(), "PYENTSP_SEL_INFO_CENT");
			//fine LP PGNTCORE-24 
			callableStatement.setString(1, cutecute);
			callableStatement.setString(2, codEnte);
			callableStatement.execute();
			listInfoCodEnteImpositore = callableStatement.getResultSet();
			if (listInfoCodEnteImpositore.next()){
				codiceSocieta = listInfoCodEnteImpositore.getString("ENT_CSOCCSOC");
			}
			
		}   catch (SQLException e) {
			e.printStackTrace();
			throw new DaoException(e);
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
			throw new DaoException(e);
		//inizio LP PGNTCORE-24 
		//} catch (HelperException e) {
		//	e.printStackTrace();
		//	throw new DaoException(e);
		//} 
		} catch (ProcedureReflectorException e) {
			e.printStackTrace();
			throw new DaoException(e);
		//fine LP PGNTCORE-24 
		} finally {
			//inizio LP PG21XX04 Leak
			//DAOHelper.closeIgnoringException(connection);
			if (listInfoCodEnteImpositore != null) {
				try {
					listInfoCodEnteImpositore.close();
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
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			//fine LP PG21XX04 Leak
		}
		return codiceSocieta;
	}

}
