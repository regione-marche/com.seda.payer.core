package com.seda.payer.core.dao;

import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.seda.data.helper.HelperException;
import com.seda.payer.core.bean.IoItaliaPagamentoInAttesa;
import com.seda.payer.core.exception.DaoException;
import com.seda.payer.core.handler.BaseDaoHandler;

public class IoItaliaBBDao extends BaseDaoHandler {

	public IoItaliaBBDao(Connection connection, String schema) {
		super(connection, schema);
	}

	public List<IoItaliaPagamentoInAttesa> getArchivioPagamentiBlackBox(String idDominio, String chiaveEnte5, String tipologiaServizio, BigDecimal importoDa, BigDecimal importoA) throws DaoException {
		List<IoItaliaPagamentoInAttesa> lista = new ArrayList<IoItaliaPagamentoInAttesa>();
		CallableStatement cs = null;
		ResultSet rs = null;
		try {
			cs = prepareCall(Routines.CNDOCSP_IOIT.routine());
			cs.setString(1, idDominio);
			cs.setString(2, chiaveEnte5);
			cs.setString(3, tipologiaServizio);
			LocalDate now = LocalDate.now();
			cs.setDate(4, Date.valueOf(now));
			cs.setDate(5, Date.valueOf(now.plusDays(30)));
			if (importoDa == null) importoDa = BigDecimal.ZERO;
			if (importoA == null) importoA = new BigDecimal(999999999);
			cs.setBigDecimal(6, importoDa);
			cs.setBigDecimal(7, importoA);
			//inizio LP 20240811 PGNTCORE-24
			//rs = cs.executeQuery();
			if(cs.execute()) {
				rs = cs.getResultSet();
				if(rs != null) {
			//fin LP 20240811 PGNTCORE-24
					while (rs.next())
						lista.add(new IoItaliaPagamentoInAttesa(rs, 1));
			//inizio LP 20240811 PGNTCORE-24
				}
			}
			//fine LP 20240811 PGNTCORE-24
		} catch (SQLException e) {
			throw new DaoException(e);
		} catch (HelperException e) {
			throw new DaoException(e);
		} finally {
			if (rs != null) {
				try { rs.close(); } catch (SQLException e) { }
			}
			if (cs != null) {
				try { cs.close(); } catch (SQLException e) { }
			}
		}
		return lista;
	}
}
