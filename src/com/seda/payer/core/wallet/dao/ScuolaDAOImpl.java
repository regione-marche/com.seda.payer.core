package com.seda.payer.core.wallet.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;

import javax.sql.DataSource;

import com.seda.data.dao.DAOHelper;

import com.seda.data.helper.Helper;
import com.seda.data.helper.HelperException;
import com.seda.data.procedure.reflection.MetaProcedure;
import com.seda.data.procedure.reflection.ProcedureReflectorException;
import com.seda.payer.core.dao.Routines;
import com.seda.payer.core.exception.DaoException;
import com.seda.payer.core.handler.BaseDaoHandler;
import com.seda.payer.core.wallet.bean.Scuola;

public class ScuolaDAOImpl  extends  BaseDaoHandler  implements ScuolaDAO  {
	private static final long serialVersionUID = 1L;
	//inizio LP PG21XX04 Leak
	@Deprecated
	//fine LP PG21XX04 Leak
	public ScuolaDAOImpl(DataSource dataSource, String schema) throws SQLException {
		super(dataSource.getConnection(), schema);
	}
	public ScuolaDAOImpl(Connection connection, String schema) throws SQLException {
		super(connection, schema);
	}
	//inizio LP PG21XX04
	//Nota. La chiusura della connection è affidata al chiamante.
	//fine LP PG21XX04
	public String insertBatch(Scuola scuola)  {
		CallableStatement callableStatement=null;
		Connection connection = null; 
		String message = "";
		try {
			connection = getConnection();

			//PGNTCORE-24 - inizio
			//callableStatement = Helper.prepareCall(connection, getSchema(), Routines.PYSCUSP_INS.routine());
			callableStatement = MetaProcedure.prepareCall(connection, getSchema(), Routines.PYSCUSP_INS.routine());
			//PGNTCORE-24 - fine
//		1	I_SCU_CSOCCSOC VARCHAR(5),
//		2	I_SCU_CUTECUTE VARCHAR(5),
//		3	I_SCU_KANEKENT CHAR(10),
//		4	I_SCU_CSCUSCOD VARCHAR(6),
//		5	I_SCU_TSCUTIPO VARCHAR(1),
//		6	I_SCU_DSCUDENO VARCHAR(50),
//		7	I_SCU_DSCUINDI VARCHAR(50),
// 		8	I_SCU_DSCUCOMU VARCHAR(50),			
//		9	I_SCU_CSCUCCAP VARCHAR(6),
//		10	I_SCU_CSCUCOMP VARCHAR(1),
//		11	I_SCU_DSCUDORD VARCHAR(30),
//		12	I_SCU_CSRVCODI CHAR(2)
			
			callableStatement.setString(1, scuola.getCuteCute().trim());
			callableStatement.setString(2, scuola.getChiaveEnte().trim());
			callableStatement.setString(3, scuola.getCodiceScuola().trim());
			callableStatement.setString(4, scuola.getTipoScuola().trim());
			callableStatement.setString(5, scuola.getDescrizioneScuola().trim());
			callableStatement.setString(6, scuola.getIndirizzoScuola().trim());
			callableStatement.setString(7, scuola.getComuneScuola().trim());
			callableStatement.setString(8, scuola.getCapScuola().trim());
			callableStatement.setString(9, scuola.getCompenzaScuola().trim());
			callableStatement.setString(10, scuola.getDescrizioneOrdineScuola().trim());
			callableStatement.registerOutParameter(11, Types.VARCHAR);
			callableStatement.execute();
			message = callableStatement.getString(11);
		} catch (SQLException e) {
			System.out.println(e);
			message = e.getMessage();
		} catch (IllegalArgumentException e) {
			System.out.println(e);
			message = e.getMessage();
		} catch (ProcedureReflectorException e) {
			System.out.println(e);
			message = e.getMessage();
		} finally {
//			DAOHelper.closeIgnoringException(connection);
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
		
		return message;
	}
}