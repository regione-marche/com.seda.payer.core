package com.seda.payer.core.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import com.seda.data.procedure.reflection.MetaProcedure;
import com.seda.data.procedure.reflection.ProcedureReflectorException;
import com.seda.data.spi.DaoHandler;
import com.seda.payer.core.bean.FlussiPageList;
import com.seda.payer.core.bean.Flusso;
import com.seda.payer.core.bean.FlussoDettagliPageList;
import com.seda.payer.core.exception.DaoException;

public class IntegrazioniFlussiDao extends DaoHandler {

	public IntegrazioniFlussiDao(Connection connection, String schema) {
		super(connection, schema);
	}

	public FlussiPageList flussiList(String tipoFlusso, String codFiscAgg, String nomeFile, String dataCreazioneDa, String dataCreazioneA, int pageNumber, int rowsPerPage, String orderBy) throws DaoException {
		
		FlussiPageList flussiPageList = null;
		
		Connection connection = getConnection();
		CallableStatement cs = null;
		ResultSet data = null;
		
		try {
			//inizio LP PGNTCORE-24 
			//cs = Helper.prepareCall(connection, getSchema(), Routines.CNPSTSP_MAN_LST.routine());
            cs = MetaProcedure.prepareCall(connection, getSchema(), Routines.CNPSTSP_MAN_LST.routine());
			//fine LP PGNTCORE-24 
			int p = 1;
			cs.setString(p++, tipoFlusso);
			cs.setString(p++, codFiscAgg);
			cs.setString(p++, nomeFile);
			try {
				cs.setDate(p++, Date.valueOf(LocalDate.parse(dataCreazioneDa, DateTimeFormatter.ISO_LOCAL_DATE)));
			} catch(Exception e) {
				p--;
				cs.setDate(p++, null);
			}
			try {
				cs.setDate(p++, Date.valueOf(LocalDate.parse(dataCreazioneA, DateTimeFormatter.ISO_LOCAL_DATE)));
			} catch(Exception e) {
				p--;
				cs.setDate(p++, null);
			}
			cs.setInt(p++, pageNumber);
			cs.setInt(p++, rowsPerPage);
			cs.setString(p++, orderBy);
			
			if (cs.execute()) {
				
				flussiPageList = new FlussiPageList();
				
				flussiPageList.setPageNumber(pageNumber);
				flussiPageList.setRowsPerPage(rowsPerPage);
				flussiPageList.setFirstRow(cs.getInt(p++));
				flussiPageList.setLastRow(cs.getInt(p++));
				flussiPageList.setNumRows(cs.getInt(p++));
				flussiPageList.setNumPages(cs.getInt(p++));
				
				data = cs.getResultSet();
				loadWebRowSet(data);
				flussiPageList.setFlussiListXml(getWebRowSetXml());
			}
		} catch (SQLException e) {
			throw new DaoException(e);
		//inizio LP PGNTCORE-24 
		//} catch (HelperException e) {
		//	throw new DaoException(e);
		} catch (ProcedureReflectorException e) {
			throw new DaoException(e);
		//fine LP PGNTCORE-24 
		} finally {
			if (data != null) {
				try {
					data.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (cs != null) {
				try {
					cs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		
		return flussiPageList;
	}

	public FlussoDettagliPageList flussoDettagliList(int idFlusso, String idDominio, String codiceEnte,	String codiceIuv, Boolean flagPagato, int idFlussoRT, int pageNumber, int rowsPerPage, String orderBy) throws DaoException {
		
		FlussoDettagliPageList flussoDettagliPageList = null;
		
		Connection connection = getConnection();
		CallableStatement cs = null;
		ResultSet data = null;
		
		try {
			//inizio LP PGNTCORE-24 
			//cs = Helper.prepareCall(connection, getSchema(), Routines.CNDOCSP_PST_MAN_LST.routine());
            cs = MetaProcedure.prepareCall(connection, getSchema(), Routines.CNDOCSP_PST_MAN_LST.routine());
			//fine LP PGNTCORE-24 
			int p = 1;
			cs.setInt(p++, idFlusso);
			cs.setString(p++, idDominio);
			cs.setString(p++, codiceEnte);
			cs.setString(p++, codiceIuv);
			cs.setString(p++, flagPagato == null ? null : flagPagato.booleanValue() ? "X" : "");
			cs.setInt(p++, idFlussoRT);
			cs.setInt(p++, pageNumber);
			cs.setInt(p++, rowsPerPage);
			cs.setString(p++, orderBy);
			
			if (cs.execute()) {
				
				flussoDettagliPageList = new FlussoDettagliPageList();
				
				flussoDettagliPageList.setPageNumber(pageNumber);
				flussoDettagliPageList.setRowsPerPage(rowsPerPage);
				flussoDettagliPageList.setFirstRow(cs.getInt(p++));
				flussoDettagliPageList.setLastRow(cs.getInt(p++));
				flussoDettagliPageList.setNumRows(cs.getInt(p++));
				flussoDettagliPageList.setNumPages(cs.getInt(p++));
				
				data = cs.getResultSet();
				loadWebRowSet(data);
				flussoDettagliPageList.setFlussoDettagliListXml(getWebRowSetXml());
			}
		} catch (SQLException e) {
			throw new DaoException(e);
		//inizio LP PGNTCORE-24 
		//} catch (HelperException e) {
		//	throw new DaoException(e);
		} catch (ProcedureReflectorException e) {
			throw new DaoException(e);
		//fine LP PGNTCORE-24 
		} finally {
			if (data != null) {
				try {
					data.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (cs != null) {
				try {
					cs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		
		return flussoDettagliPageList;
	}
	
	public Flusso select(int idFlusso) throws DaoException {
		
		Flusso flusso = null;
		
		Connection connection = getConnection();
		CallableStatement cs = null;
		ResultSet rs = null;
		try {
			//inizio LP PGNTCORE-24 
			//cs = Helper.prepareCall(connection, getSchema(), Routines.CNPSTSP_SEL.routine());
            cs = MetaProcedure.prepareCall(connection, getSchema(), Routines.CNPSTSP_SEL.routine());
			//fine LP PGNTCORE-24 
			int p = 1;
			cs.setInt(p++, idFlusso);
			//inizio LP 20240811 PGNTCORE-24
			//rs = cs.executeQuery();
			if(cs.execute()) {
				rs = cs.getResultSet();
				if(rs!= null) {
			//fine LP 20240811 PGNTCORE-24
				if (rs.next())
					flusso = new Flusso(rs);
			//inizio LP 20240811 PGNTCORE-24
				}
			}
			//fine LP 20240811 PGNTCORE-24
		} catch (SQLException e) {
			throw new DaoException(e);
		//inizio LP PGNTCORE-24 
		//} catch (HelperException e) {
		//	throw new DaoException(e);
		} catch (ProcedureReflectorException e) {
			throw new DaoException(e);
		//fine LP PGNTCORE-24 
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (cs != null) {
				try {
					cs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return flusso;
	}
}
