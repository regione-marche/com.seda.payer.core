package com.seda.payer.core.dao;

import com.seda.data.helper.HelperException;
import com.seda.payer.commons.bean.TypeRequest;
import com.seda.payer.core.bean.PrenotazioneFatturazione;
import com.seda.payer.core.bean.RangeAbiUtenteEnte;
import com.seda.payer.core.exception.DaoException;
import com.seda.payer.core.handler.BaseDaoHandler;
import com.seda.payer.core.messages.Messages;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

public class PrenotazioneFatturazioneDao extends BaseDaoHandler {
    public PrenotazioneFatturazioneDao(Connection connection, String schema) {
        super(connection, schema);
    }

    public void doSave(PrenotazioneFatturazione prenotazioneFatturazione, String codOp) throws DaoException {
        CallableStatement callableStatement = null;
        try {
            if (prenotazioneFatturazione != null && codOp != null) { // TODO controllo su codOp
                callableStatement = this.prepareCall(Routines.FAT_DOSAVE.routine());
                prenotazioneFatturazione.onSave(callableStatement);
                callableStatement.execute();
            } else {
                throw new IllegalArgumentException(Messages.UNIQUE_CONSTRAINT_VIOLATION.toString());
            }
        } catch (SQLException ex) {
            throw new DaoException(ex);
        } catch (HelperException ex) {
            throw new DaoException(ex);
        } finally {
            if (callableStatement != null) {
                try {
                    callableStatement.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }

        }

    }
}
