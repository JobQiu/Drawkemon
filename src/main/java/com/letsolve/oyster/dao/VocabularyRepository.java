package com.letsolve.oyster.dao;

import com.letsolve.oyster.entity.Vocabulary;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

/**
 * @author xavier.qiu
 * 8/1/18 7:04 PM
 */
public interface VocabularyRepository extends CrudRepository<Vocabulary, Long> {

    Optional<Vocabulary> findByUserId(Long userId);
}
