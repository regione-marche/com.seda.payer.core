package com.seda.payer.core.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import com.seda.payer.core.bean.ConfigSessioneCarrello;
import com.seda.payer.core.bean.Item;
import com.seda.payer.core.bean.ItemAttribute;
import com.seda.payer.core.bean.CarrelloVirtuale;
import com.seda.payer.core.messages.Messages;
import com.seda.data.helper.HelperException;
import com.seda.data.spi.DaoHandler;
import com.seda.payer.core.exception.DaoException;


public class CarrelloVirtualeDao extends DaoHandler {
	
	public CarrelloVirtualeDao(Connection connection, String schema) {
		super(connection, schema);
	}
	
	public String addItem(Item item, String securityToken) throws DaoException {
		CallableStatement callableStatement = null;
		try {
			callableStatement = prepareCall(Routines.ITE_DOINSERT_BUFFER.routine());
			item.onSave(callableStatement);
			callableStatement.setString(12, securityToken);
			callableStatement.registerOutParameter(13, Types.CHAR);
			callableStatement.execute();
			String itemId = callableStatement.getString(13);
			return itemId;
		} 
		catch (Exception x) {
			throw new DaoException(x);
		} 
		finally {
			//inizio LP PG21XX04 Leak
			//DaoUtil.closeStatement(callableStatement);
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

	public List<Item> getAllItems(String shoppingCartId) throws DaoException {
		List<Item> items = new ArrayList<Item>();
		CallableStatement callableStatement = null;
		//inizio LP PG21XX04 Leak
		ResultSet data = null;
		//fine LP PG21XX04 Leak
		try {
			callableStatement = prepareCall(Routines.ITE_DOLIST.routine());
			callableStatement.setString(1, shoppingCartId);
			if (callableStatement.execute()) {
				//inizio LP PG21XX04 Leak
				//ResultSet data = callableStatement.getResultSet();
				data = callableStatement.getResultSet();
				//fine LP PG21XX04 Leak
				while (data.next()) {
					items.add(new Item(data));
				}
			}
			return items;
		} 
		catch (Exception x) {
			throw new DaoException(x);
		} 
		finally {
			//inizio LP PG21XX04 Leak
			//DaoUtil.closeStatement(callableStatement);
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
	
	public void	updateItem(Item item, String securityToken) throws DaoException {
		CallableStatement callableStatement = null;
		try {
			callableStatement = prepareCall(Routines.ITE_DOUPDATE_BUFFER.routine());
			item.onUpdate(callableStatement);
			callableStatement.setString(7, securityToken);
			callableStatement.execute();
		} 
		catch (Exception x) {
			throw new DaoException(x);
		} 
		finally {
			//inizio LP PG21XX04 Leak
			//DaoUtil.closeStatement(callableStatement);
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

	public void	deleteItem(Item item, String securityToken) throws DaoException {
		CallableStatement callableStatement = null;
		try {
			callableStatement = prepareCall(Routines.ITE_DODELETE.routine());
			item.onDelete(callableStatement);
			callableStatement.setString(3, securityToken);
			callableStatement.execute();
		} 
		catch (Exception x) {
			throw new DaoException(x);
		} 
		finally {
			//inizio LP PG21XX04 Leak
			//DaoUtil.closeStatement(callableStatement);
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

	public void	deleteAllItems(String shoppingCartId, String securityToken) throws DaoException {
		CallableStatement callableStatement = null;
		try {
			callableStatement = prepareCall(Routines.ITE_DODELETE_CART.routine());
			callableStatement.setString(1, shoppingCartId);
			callableStatement.setString(2, securityToken);
			callableStatement.execute();
		} 
		catch (Exception x) {
			throw new DaoException(x);
		} 
		finally {
			//inizio LP PG21XX04 Leak
			//DaoUtil.closeStatement(callableStatement);
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

	public void	deleteItemsForSession(String shoppingCartId, String codiceSocieta, String codiceUtente, String securityToken) throws DaoException {
		CallableStatement callableStatement = null;
		try {
			callableStatement = prepareCall(Routines.ITE_DODELETE_SESSION.routine());
			callableStatement.setString(1, shoppingCartId);
			callableStatement.setString(2, codiceSocieta);
			callableStatement.setString(3, codiceUtente);
			callableStatement.setString(4, securityToken);
			callableStatement.execute();
		} 
		catch (Exception x) {
			throw new DaoException(x);
		} 
		finally {
			//inizio LP PG21XX04 Leak
			//DaoUtil.closeStatement(callableStatement);
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
	
	public ConfigSessioneCarrello getCartSessionConfig(String codiceSocieta, String canalePagamento) throws DaoException {
		CallableStatement callableStatement = null;
		//inizio LP PG21XX04 Leak
		ResultSet data = null;
		//fine LP PG21XX04 Leak
		try {
			if (codiceSocieta == null || codiceSocieta.length() == 0)
				throw new IllegalArgumentException(Messages.INVALID_PARAMETER.format("cart.codiceSocieta"));
			if (canalePagamento == null || canalePagamento.length() == 0)
				throw new IllegalArgumentException(Messages.INVALID_PARAMETER.format("cart.canalePagamento"));
			callableStatement = prepareCall(Routines.CAS_DODETAIL.routine());
			callableStatement.setString(1, codiceSocieta);
			callableStatement.setString(2, canalePagamento);
			if (callableStatement.execute()) {
				//inizio LP PG21XX04 Leak
				//ResultSet data = callableStatement.getResultSet();
				data = callableStatement.getResultSet();
				//fine LP PG21XX04 Leak
				if (data.next())
					return new ConfigSessioneCarrello(data);
			}
			return null;
		} 
		catch (Exception x) {
			throw new DaoException(x);
		} 
		finally {
			//inizio LP PG21XX04 Leak
			//DaoUtil.closeStatement(callableStatement);
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

	public void	purgeCart() throws DaoException {
		CallableStatement callableStatement = null;
		try {
			callableStatement = prepareCall(Routines.CAV_DOPURGE.routine());
			callableStatement.setInt(1, 7200);
			callableStatement.execute();
		}
		catch (Exception x) {
			throw new DaoException(x);
		} 
		finally {
			//inizio LP PG21XX04 Leak
			//DaoUtil.closeStatement(callableStatement);
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

	public List<ItemAttribute>	getAllAttributes(String itemId) throws DaoException {
		List<ItemAttribute> items = new ArrayList<ItemAttribute>();
		CallableStatement callableStatement = null;
		//inizio LP PG21XX04 Leak
		ResultSet data = null;
		//fine LP PG21XX04 Leak
		try {
			callableStatement = prepareCall(Routines.ITA_DOLIST.routine());
			callableStatement.setString(1, itemId);
			if (callableStatement.execute()) {
				//inizio LP PG21XX04 Leak
				//ResultSet data = callableStatement.getResultSet();
				data = callableStatement.getResultSet();
				//fine LP PG21XX04 Leak
				while (data.next()) {
					items.add(new ItemAttribute(data));
				}
			}
			return items;
		} 
		catch (Exception x) {
			throw new DaoException(x);
		} 
		finally {
			//inizio LP PG21XX04 Leak
			//DaoUtil.closeStatement(callableStatement);
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
	
	public String doSave(CarrelloVirtuale shoppingCart) throws DaoException {
		CallableStatement callableStatement = null;
		try	{
			callableStatement = prepareCall(Routines.CAV_DOINSERT.routine());
			shoppingCart.onSave(callableStatement);
			callableStatement.registerOutParameter(6, Types.CHAR);
			callableStatement.execute();
			String cartId = callableStatement.getString(6);
			return cartId;
		} 
		catch (Exception x) {
			throw new DaoException(x);
		} 
		finally {
			//inizio LP PG21XX04 Leak
			//DaoUtil.closeStatement(callableStatement);
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

	public CarrelloVirtuale doDetail(String shoppingCartId) throws DaoException {
		CallableStatement callableStatement = null;
		//inizio LP PG21XX04 Leak
		ResultSet data = null;
		//fine LP PG21XX04 Leak
		try	{
			if (shoppingCartId == null || shoppingCartId.length() == 0)
				throw new IllegalArgumentException(Messages.INVALID_PARAMETER.format("cart.shoppingCartId"));

			callableStatement = prepareCall(Routines.CAV_DODETAIL.routine());
			callableStatement.setString(1, shoppingCartId);
			if (callableStatement.execute()) {
				//inizio LP PG21XX04 Leak
				//ResultSet data = callableStatement.getResultSet();
				data = callableStatement.getResultSet();
				//fine LP PG21XX04 Leak
				if (data.next())
					return new CarrelloVirtuale(data);
			}
			return null;
		} 
		catch (Exception x) {
			throw new DaoException(x);
		} 
		finally {
			//inizio LP PG21XX04 Leak
			//DaoUtil.closeStatement(callableStatement);
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
	
	public void doDelete(String shoppingCartId) throws DaoException {
		CallableStatement callableStatement = null;
		try	{
			if (shoppingCartId == null || shoppingCartId.length() == 0)
				throw new IllegalArgumentException(Messages.INVALID_PARAMETER.format("cart.shoppingCartId"));

			callableStatement = prepareCall(Routines.CAV_DODELETE.routine());
			callableStatement.setString(1, shoppingCartId);
			callableStatement.execute();
			//commit();
			
								
		} catch (SQLException x) {
			throw new DaoException(x.getErrorCode(),x.getMessage(),x);
		} catch (IllegalArgumentException x) {
			throw new DaoException(Integer.parseInt(Messages.ILL_ARG_ERR_CODE.format()),x.getMessage(),x);
		} catch (HelperException x) {
			throw new DaoException(Integer.parseInt(Messages.HELPER_ERR_CODE.format()),x.getMessage(),x);
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

	public void updateSecurityToken(String cartId, String securityToken) throws DaoException {
		CallableStatement callableStatement = null;
		try	{
			if (cartId == null || cartId.length() == 0)
				throw new IllegalArgumentException(Messages.INVALID_PARAMETER.format("cart.shoppingCartId"));
			if (securityToken == null || securityToken.length() == 0)
				throw new IllegalArgumentException(Messages.INVALID_PARAMETER.format("cart.securityToken"));
			callableStatement = prepareCall(Routines.CAV_DOUPDATE.routine());
			callableStatement.setString(1, cartId);
			callableStatement.setString(2, securityToken);
			//lo stato del carrello non deve cambiare
			callableStatement.setString(3, null);
			callableStatement.execute();
		} 
		catch (Exception x) {
			throw new DaoException(x);
		} 
		finally {
			//inizio LP PG21XX04 Leak
			//DaoUtil.closeStatement(callableStatement);
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

}
