package com.liyolomoko.attendeventappfrontend.repositories;
import java.util.*;
/**
 * Created by Leo on 8/31/2016.
 */
public interface RestApi  <E,ID> {
    E create(E entity);

    E readById(ID id);

    Set<E> readAll();

    E update(E entity);

    void delete(E entity);
}
