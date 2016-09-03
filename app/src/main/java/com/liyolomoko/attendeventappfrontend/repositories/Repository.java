package com.liyolomoko.attendeventappfrontend.repositories;

import java.sql.SQLException;
import java.util.Set;

/**
 * Created by Leo on 4/24/2016.
 */
public interface Repository<E,ID>{

    E read(ID id);
    Set<E> readAll() throws SQLException;
    E update(E entity) throws SQLException;
    E delete(E entity) throws SQLException;
    int deleteAll() throws SQLException;
    E save(E entity) throws SQLException;
}
