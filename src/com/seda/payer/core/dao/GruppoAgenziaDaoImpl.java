
package com.seda.payer.core.dao;

import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;
import javax.sql.rowset.WebRowSet;

import com.seda.commons.string.Convert;
import com.seda.data.procedure.reflection.MetaProcedure;
import com.seda.data.procedure.reflection.ProcedureReflectorException;
import com.seda.data.spi.PageInfo;
import com.seda.payer.core.bean.GruppoAgenzia;
import com.seda.payer.core.bean.GruppoAgenziaPageList;
import com.seda.payer.core.exception.DaoException;
import com.seda.payer.core.handler.BaseDaoHandler;
import com.seda.payer.core.wallet.bean.EsitoRisposte;


public class GruppoAgenziaDaoImpl extends BaseDaoHandler implements GruppoAgenziaDao   {
	private static final long serialVersionUID = 1L;
	
	//inizio LP PG21XX04 Leak
	@Deprecated
	//fine LP PG21XX04 Leak
	public GruppoAgenziaDaoImpl(DataSource dataSource, String schema) throws SQLException {
		super(dataSource.getConnection(), schema);
	}
	public GruppoAgenziaDaoImpl(Connection connection, String schema) throws SQLException {
		super(connection, schema);
	}
	
	
	public GruppoAgenzia select(String codiceGruppoAgenzia) throws DaoException {
		CallableStatement callableStatement=null;
		ResultSet resultSet=null;
		Connection connection = null;
		GruppoAgenzia gruppoAgenzia = null;
		try {
			connection = getConnection();
			//inizio LP PGNTCORE-24
			//callableStatement = Helper.prepareCall(connection, getSchema(), Routines.PYGAGSP_SEL.routine());
			callableStatement = MetaProcedure.prepareCall(connection, getSchema(), Routines.PYGAGSP_SEL.routine());
			//fine LP PGNTCORE-24 
			callableStatement.setString(1, codiceGruppoAgenzia);

			callableStatement.execute();

			resultSet=callableStatement.getResultSet();
			if(resultSet.next()) {
				gruppoAgenzia= new GruppoAgenzia(resultSet);
			}

		} catch (SQLException e) {
			throw new DaoException(e);
		} catch (IllegalArgumentException e) {
			throw new DaoException(e);
		//inizio LP PGNTCORE-24
		//} catch (HelperException e) {
		//	throw new DaoException(e);
		} catch (ProcedureReflectorException e) {
			throw new DaoException(e);
		//fine LP PGNTCORE-24 
		} finally {
			//inizio LP PG21XX04 Leak
			//	DAOHelper.closeIgnoringException(callableStatement);
			//	DAOHelper.closeIgnoringException(connection);
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
			//fine LP PG21XX04 Leak
		}
		return gruppoAgenzia;
	}
	
	
	
	public Integer update(GruppoAgenzia gruppoAgenzia)	throws DaoException {
		CallableStatement callableStatement=null;
		Connection connection = null;
		int ret=0;
		try {
			connection = getConnection();
			//inizio LP PGNTCORE-24
			//callableStatement = Helper.prepareCall(connection, getSchema(), Routines.PYGAGSP_UPD.routine());
			callableStatement = MetaProcedure.prepareCall(connection, getSchema(), Routines.PYGAGSP_UPD.routine());
			//fine LP PGNTCORE-24 
			
			callableStatement.setString(1, gruppoAgenzia.getCodiceGruppoAgenzia());
			callableStatement.setString(2, gruppoAgenzia.getDescrizioneGruppoAgenzia());
			
			callableStatement.registerOutParameter(3, Types.INTEGER);						
			callableStatement.execute();
			ret = callableStatement.getInt(3);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DaoException(e);
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
			throw new DaoException(e);
		//inizio LP PGNTCORE-24
		//} catch (HelperException e) {
		//	throw new DaoException(e);
		} catch (ProcedureReflectorException e) {
			throw new DaoException(e);
		//fine LP PGNTCORE-24 
		} finally {
			//inizio LP PG21XX04 Leak
			//	DAOHelper.closeIgnoringException(connection);
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
	
	public EsitoRisposte delete(String codiceGruppoAgenzia)throws DaoException {
		CallableStatement callableStatement=null;
		Connection connection = null;
		EsitoRisposte  esitoRisposte = new EsitoRisposte();
		try {
			connection = getConnection();
			//inizio LP PGNTCORE-24
			//callableStatement = Helper.prepareCall(connection, getSchema(), Routines.PYGAGSP_DEL.routine());	
			callableStatement = MetaProcedure.prepareCall(connection, getSchema(), Routines.PYGAGSP_DEL.routine());
			//fine LP PGNTCORE-24 
			
			callableStatement.setString(1, codiceGruppoAgenzia);
			callableStatement.registerOutParameter(2, Types.VARCHAR);
			callableStatement.registerOutParameter(3, Types.VARCHAR);			
			callableStatement.execute();
			esitoRisposte.setCodiceMessaggio(callableStatement.getString(2));
			esitoRisposte.setDescrizioneMessaggio(callableStatement.getString(3));
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DaoException(e);
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
			throw new DaoException(e);
		//inizio LP PGNTCORE-24
		//} catch (HelperException e) {
		//	throw new DaoException(e);
		} catch (ProcedureReflectorException e) {
			throw new DaoException(e);
		//fine LP PGNTCORE-24 
		} finally {
			//inizio LP PG21XX04 Leak
			//	DAOHelper.closeIgnoringException(connection);
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
	public EsitoRisposte insert(GruppoAgenzia gruppoAgenzia)	throws DaoException {
		CallableStatement callableStatement=null;
		Connection connection = null;
		EsitoRisposte  esitoRisposte = new EsitoRisposte();
		try {
			connection = getConnection();
			//inizio LP PGNTCORE-24
			//callableStatement = Helper.prepareCall(connection, getSchema(), Routines.PYGAGSP_INS.routine());
			callableStatement = MetaProcedure.prepareCall(connection, getSchema(), Routines.PYGAGSP_INS.routine());
			//fine LP PGNTCORE-24 
			
			callableStatement.setString(1, gruppoAgenzia.getCodiceGruppoAgenzia());
			callableStatement.setString(2, gruppoAgenzia.getDescrizioneGruppoAgenzia());
			callableStatement.registerOutParameter(3, Types.VARCHAR);
			callableStatement.registerOutParameter(4, Types.VARCHAR);	
			callableStatement.execute();
			esitoRisposte.setCodiceMessaggio(callableStatement.getString(3));
			esitoRisposte.setDescrizioneMessaggio(callableStatement.getString(4));
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DaoException(e);
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
			throw new DaoException(e);
		//inizio LP PGNTCORE-24
		//} catch (HelperException e) {
		//	throw new DaoException(e);
		} catch (ProcedureReflectorException e) {
			throw new DaoException(e);
		//fine LP PGNTCORE-24 
		} finally {
			//inizio LP PG21XX04 Leak
			//	DAOHelper.closeIgnoringException(connection);
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
	
	
	public GruppoAgenziaPageList gruppoAgenziaList(GruppoAgenzia gruppoAgenzia,int rowsPerPage,
			int pageNumber, String OrderBy) throws DaoException {
		CallableStatement callableStatement=null;

		Connection connection = null;
		ResultSet data = null;
		//inizio LP PG21XX04 Leak
		WebRowSet tmpRowSet = null;
		//fine LP PG21XX04 Leak

		PageInfo pageInfo = null; 
		GruppoAgenziaPageList gruppoAgenziaPagelist = null;
		String gruppoAgenziaLst  = "";
		List<GruppoAgenzia> listGruppoAgenzia = null;
		try {
			connection = getConnection();
			//inizio LP PGNTCORE-24
			//callableStatement = Helper.prepareCall(connection, getSchema(),Routines.PYGAGSP_LST.routine());
			callableStatement = MetaProcedure.prepareCall(connection, getSchema(), Routines.PYGAGSP_LST.routine());
			//fine LP PGNTCORE-24 

			callableStatement.setInt(1,pageNumber);
			callableStatement.setInt(2,rowsPerPage);
			callableStatement.setString(3,gruppoAgenzia.getCodiceGruppoAgenzia());
			callableStatement.setString(4,gruppoAgenzia.getDescrizioneGruppoAgenzia());      
			callableStatement.setString(5,OrderBy); 
			callableStatement.registerOutParameter(6, Types.INTEGER);
			/* we register row end */
			callableStatement.registerOutParameter(7, Types.INTEGER);
			/* we register total rows */
			callableStatement.registerOutParameter(8, Types.INTEGER);
			/* we register total pages */
			callableStatement.registerOutParameter(9, Types.SMALLINT);

			/* we execute procedure */

			if(callableStatement.execute())	{
				pageInfo = new PageInfo();
				pageInfo.setPageNumber(pageNumber);
				pageInfo.setRowsPerPage(rowsPerPage);
				pageInfo.setFirstRow(callableStatement.getInt(6));
				pageInfo.setLastRow(callableStatement.getInt(7));
				pageInfo.setNumRows(callableStatement.getInt(8));
				pageInfo.setNumPages(callableStatement.getInt(9));

				data = callableStatement.getResultSet();
				loadWebRowSet(data);
				gruppoAgenziaLst = getWebRowSetXml();
			

				//inizio LP PG21XX04 Leak
				//WebRowSet tmpRowSet = Convert.stringToWebRowSet(gruppoAgenziaLst);
				tmpRowSet = Convert.stringToWebRowSet(gruppoAgenziaLst);
				//fine LP PG21XX04 Leak
				listGruppoAgenzia = new ArrayList<GruppoAgenzia>();
				while(tmpRowSet.next()) {
					GruppoAgenzia item = new GruppoAgenzia(tmpRowSet);
					listGruppoAgenzia.add(item);
				}
				
				
//				if(callableStatement.getMoreResults()){
//					data=callableStatement.getResultSet();
//					loadWebRowSet(data);
//					gruppoAgenziaLst = getWebRowSetXml();
//				}

				
			}
			gruppoAgenziaPagelist = new GruppoAgenziaPageList(pageInfo, "00","",gruppoAgenziaLst, listGruppoAgenzia);
			return gruppoAgenziaPagelist;

		} catch (SQLException e) {
			e.printStackTrace();
			gruppoAgenziaPagelist = new GruppoAgenziaPageList(pageInfo, "01","Sql-Exception","", null);
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
			gruppoAgenziaPagelist = new GruppoAgenziaPageList(pageInfo, "01","Sql-Exception","", null);
		//inizio LP PGNTCORE-24
		//} catch (HelperException e) {
		//	e.printStackTrace();
		//	gruppoAgenziaPagelist = new GruppoAgenziaPageList(pageInfo, "01","Sql-Exception","", null);
		} catch (ProcedureReflectorException e) {
			e.printStackTrace();
			gruppoAgenziaPagelist = new GruppoAgenziaPageList(pageInfo, "01","Sql-Exception","", null);
		//fine LP PGNTCORE-24
		} catch (IOException e) {
			e.printStackTrace();
			gruppoAgenziaPagelist = new GruppoAgenziaPageList(pageInfo, "01","Sql-Exception","", null);
		} finally {
			//inizio LP PG21XX04 Leak
			//DAOHelper.closeIgnoringException(callableStatement);
			//DAOHelper.closeIgnoringException(connection);
			if (tmpRowSet != null) {
				try {
					tmpRowSet.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
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
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			//fine LP PG21XX04 Leak
		}
		return gruppoAgenziaPagelist;
	}

	
	public GruppoAgenziaPageList gruppoAgenziaListDDL() throws DaoException {
		CallableStatement callableStatement=null;

		Connection connection = null;
		ResultSet data = null;

		GruppoAgenziaPageList gruppoAgenziaPagelist = null;
		String gruppoAgenziaLstXml  = "";
		try {
			connection = getConnection();
			//inizio LP PGNTCORE-24
			//callableStatement = Helper.prepareCall(connection, getSchema(),Routines.PYGAGSP_LST_DDL.routine());
			callableStatement = MetaProcedure.prepareCall(connection, getSchema(), Routines.PYGAGSP_LST_DDL.routine());
			//fine LP PGNTCORE-24 
			
			if(callableStatement.execute()) {
				data = callableStatement.getResultSet();
				loadWebRowSet(data);
				gruppoAgenziaLstXml = getWebRowSetXml();
				gruppoAgenziaPagelist = new GruppoAgenziaPageList(null, "00","",gruppoAgenziaLstXml, null);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			gruppoAgenziaPagelist = new GruppoAgenziaPageList(null, "01","Sql-Exception","", null);
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
			gruppoAgenziaPagelist = new GruppoAgenziaPageList(null, "01","Sql-Exception","", null);
		//inizio LP PGNTCORE-24
		//} catch (HelperException e) {
		//	e.printStackTrace();
		//	gruppoAgenziaPagelist = new GruppoAgenziaPageList(null, "01","Sql-Exception","", null);
		} catch (ProcedureReflectorException e) {
			e.printStackTrace();
			gruppoAgenziaPagelist = new GruppoAgenziaPageList(null, "01","Sql-Exception","", null);
		//fine LP PGNTCORE-24
		} finally {
			//inizio LP PG21XX04 Leak
			//DAOHelper.closeIgnoringException(callableStatement);
			//DAOHelper.closeIgnoringException(connection);
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
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			//fine LP PG21XX04 Leak
		}
		return gruppoAgenziaPagelist;
	}

}
