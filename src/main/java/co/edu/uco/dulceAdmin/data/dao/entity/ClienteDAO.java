package co.edu.uco.dulceAdmin.data.dao.entity;

import java.util.UUID;

import co.edu.uco.dulceAdmin.data.dao.CreateDAO;
import co.edu.uco.dulceAdmin.data.dao.DeleteDAO;
import co.edu.uco.dulceAdmin.data.dao.RetrieveDAO;
import co.edu.uco.dulceAdmin.data.dao.UpdateDAO;
import co.edu.uco.dulceAdmin.entity.ClienteEntity;

public interface ClienteDAO extends CreateDAO<ClienteEntity>, RetrieveDAO<ClienteEntity, UUID>, UpdateDAO<ClienteEntity>, DeleteDAO <UUID>{

}
