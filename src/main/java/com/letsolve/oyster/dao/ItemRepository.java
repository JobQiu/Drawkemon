package com.letsolve.oyster.dao;

import com.letsolve.oyster.entity.Item;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * @author xavier.qiu
 * 7/28/18 11:28 PM
 */
public interface ItemRepository extends CrudRepository<Item, Long> {
//
//    @Transactional
//    @Modifying
//    @Query(" UPDATE Item t SET t.archived = ?2 WHERE t.id = ?1")
//    int setArchivedFor(long id, int archived);
//
//    @Transactional
//    @Modifying
//    @Query(" UPDATE Item t SET t.public_ = ?2 WHERE t.id = ?1")
//    int setPublic_For(long id, int public_);

    List<Item> findAllByTypeAndUserIdAndArchivedAndOpenView(String type, long userId, boolean archived, boolean openView);

    List<Item> findAllByTypeAndUserIdAndContent(String type, long userId, String content);
}
