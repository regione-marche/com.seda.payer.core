package com.seda.payer.core.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import com.seda.data.dao.DAOHelper;
import com.seda.data.helper.HelperException;
import com.seda.payer.core.exception.DaoException;
import com.seda.payer.core.handler.BaseDaoHandler;

public class RisconetIciImuDao extends BaseDaoHandler {
	
	public RisconetIciImuDao(Connection connection, String schema) {
		super(connection, schema);
	}

	//inizio LP PG21XX04 Leak
	//Nota. La connection è chiusa dal chiamante.
	//fine LP PG21XX04 Leak
	public void recuperaPagamentiIciImu(String codiceProvinciaComune, String codiceFiscaleContribuente, String dbName, String codiceConcessione) throws DaoException
	{
		PreparedStatement statement = null;
		String sql = "" +
		"SELECT EPMIC_CUTECUTE," +
				"convert(varchar(6),EPMIC_CENTE) as EPMIC_CENTE," +
				"EPMIC_NROREGI," +
				"EPMIC_DTMOV, " +
				"COALESCE(CONVERT(VARCHAR(1),EPMIC_LUOMOV),'') AS EPMIC_LUOMOV," + 
				"COALESCE(CONVERT(VARCHAR(1),EPMIC_FBOL),'') AS EPMIC_FBOL, " +
				"COALESCE(CONVERT(VARCHAR(1),EPMIC_FSAN),'N') AS EPMIC_FSAN, " +
				"COALESCE(CONVERT(VARCHAR(6),EPMIC_CENTE_UBIC),'') AS EPMIC_CENTE_UBIC," + 
				"COALESCE(CONVERT(VARCHAR(50),EPMIC_COMU_UBIC),'') AS EPMIC_COMU_UBIC, " +
				"COALESCE(CONVERT(VARCHAR(5),EPMIC_CAP),'') AS EPMIC_CAP, " +
				"COALESCE(CONVERT(VARCHAR(4),EPMIC_ANNO_IMP),'') AS EPMIC_ANNO_IMP," + 
				"COALESCE(EPMIC_NRO_FAB,0) AS EPMIC_NRO_FAB, " +
				"COALESCE(CONVERT(VARCHAR(1),EPMIC_FRAT),'') AS EPMIC_FRAT," + 
				"COALESCE(EPMIC_IMPMOVI_EURO,0) AS EPMIC_IMPMOVI_EURO, " +
				"COALESCE(EPMIC_IMP_TER_AG_E,0) AS EPMIC_IMP_TER_AG_E, " +
				"COALESCE(EPMIC_IMP_ARE_FA_E,0) AS EPMIC_IMP_ARE_FA_E, " +
				"COALESCE(EPMIC_IMP_ABI_PR_E,0) AS EPMIC_IMP_ABI_PR_E,  " +
				"COALESCE(EPMIC_IMP_ALT_FA_E,0) AS EPMIC_IMP_ALT_FA_E, " +
				"COALESCE(EPMIC_IMP_DET_PR_E,0) AS EPMIC_IMP_DET_PR_E,  " +
				"COALESCE(EPMIC_IMP_COMP_E,0) AS EPMIC_IMP_COMP_E, " +
				" CONVERT(VARCHAR(16),EPMIC_CODFISC)AS EPMIC_CODFISC,  " +
				"COALESCE(CONVERT(VARCHAR(1),EPMIC_RAVVEDIMENTO),'0') AS EPMIC_RAVVEDIMENTO," +  
				"COALESCE(EPMIC_IMP_DET_ST_E,0) AS EPMIC_IMP_DET_ST_E,  " +
				"COALESCE(CONVERT(VARCHAR(1),EPMIC_FTIPO_IMPOSTA),'') AS EPMIC_FTIPO_IMPOSTA," +  
				"COALESCE(EPMIC_IMP_FAB_RU_E,0) AS EPMIC_IMP_FAB_RU_E," + 
				"COALESCE(CONVERT(VARCHAR(120),EPANA_DENOM),'') AS EPANA_DENOM," +  
				"COALESCE(CONVERT(VARCHAR(70),EPANA_INDIFISC),'') AS EPANA_INDIFISC," +  
				"COALESCE(CONVERT(VARCHAR(6),EPANA_CAPFISC),'') AS EPANA_CAPFISC,  " +
				"COALESCE(CONVERT(VARCHAR(35),EPANA_COMUFISC),'') AS EPANA_COMUFISC, " +
				"COALESCE(CONVERT(VARCHAR(2),EPANA_PROVFISC),'') AS EPANA_PROVFISC, " +
				"COALESCE(EPMIC_IMP_IMPO_E,0) AS EPMIC_IMP_IMPO_E, " +
				"COALESCE(EPMIC_IMP_SOPR_E,0) AS EPMIC_IMP_SOPR_E, " +
				"COALESCE(EPMIC_IMP_PENA_E,0) AS EPMIC_IMP_PENA_E, " +
				"COALESCE(EPMIC_IMP_INTE_E,0) AS EPMIC_IMP_INTE_E, " +
				"COALESCE(EPMIC_IMP_COMP_E,0) AS EPMIC_IMP_COMP_E, " +
				"COALESCE(EPMIC_DTPROV,'1900-01-01') AS EPMIC_DTPROV, " +
				"COALESCE(CONVERT(VARCHAR(10),EPMIC_NRO_PROV),'') AS EPMIC_NRO_PROV," +  
				"COALESCE(EPMIC_IMP_IMM_PROD,0) AS EPMIC_IMP_IMM_PROD   " +
		"FROM " + dbName + "..TB_ICI_MOV_ESA_" + codiceConcessione + " " +
			"INNER JOIN " + dbName + "..TB_ICI_ANA_ESA_" + codiceConcessione + " " +
			"ON EPMIC_CUTECUTE = EPANA_CUTECUTE AND EPMIC_CENTE = EPANA_CENTE AND EPMIC_CODFISC = EPANA_CODFISC " +
		"WHERE EPMIC_CUTECUTE = ? AND EPMIC_CENTE = ? AND EPMIC_CODFISC = ?";

		try	{
			Connection conn = getConnection();
			//statement = conn.prepareStatement("SELECT * FROM RNET_000TO..TB_ICI_MOV_ESA_503 WHERE EPMIC_CODFISC='TCCTRS42B52B573L'");
			
			statement = conn.prepareStatement(sql);
			statement.setString(1, codiceConcessione);
			statement.setString(2, codiceProvinciaComune);
			statement.setString(3, codiceFiscaleContribuente);
			
			if (statement.execute()) 
				this.loadWebRowSets(statement);
			
		} catch (SQLException x) {
			throw new DaoException(x);
		} catch (IllegalArgumentException x) {
			throw new DaoException(x);
		} finally {
			//inizio LP PG21XX04 Leak
			//if (statement != null)
			//	DAOHelper.closeIgnoringException(statement);
			if (statement != null) {
				try {
					statement.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			//fine LP PG21XX04 Leak
		}
		
//		callablestatement callablestatement = null ;
//		connection connection = getconnection();
//		try	{
//
//			callablestatement = connection.preparecall("{call " + getschema() + ".dbo.visualizza_statistiche_cdg}");
//
//			callablestatement.setstring(1, codiceconcessione); 
//			callablestatement.setstring(2, codiceprovinciacomune);
//			callablestatement.setstring(3, codicefiscalecontribuente); 
//	
//			/* we execute procedure */
//			if (callablestatement.execute() ) {
//				
//					this.loadwebrowsets(callablestatement);
//			
//			}
//		} catch (sqlexception x) {
//			throw new daoexception(x);
//		} catch (illegalargumentexception x) {
//			throw new daoexception(x);
//		} finally {
//			if (callablestatement != null)
//				daohelper.closeignoringexception(callablestatement);
//		}
//		
	}
}
