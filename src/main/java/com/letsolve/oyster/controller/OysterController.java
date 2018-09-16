package com.letsolve.oyster.controller;

import com.letsolve.oyster.dao.ItemRepository;
import com.letsolve.oyster.dao.VocabularyRepository;
import com.letsolve.oyster.entity.Item;
import com.letsolve.oyster.entity.Vocabulary;
import com.letsolve.oyster.redis.RedisHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static com.letsolve.oyster.Constant.OysterConstant.TYPES;
import static com.letsolve.oyster.Constant.OysterConstant.VOCABULARY;
import static org.springframework.web.bind.annotation.RequestMethod.GET;


/**
 * @author xavier.qiu
 * 8/1/18 6:30 PM
 */
//@RestController
//@Slf4j
public class OysterController {

    @RequestMapping("reviewWord")
    public boolean reviewWord(@RequestParam long userId,
                              @RequestParam String wordId) {
        Optional<Vocabulary> o = vocabularyRepository.findByUserId(userId);
        if (o.isPresent()) {
            Vocabulary v = o.get();
            v.reviewWord(wordId);
            v = vocabularyRepository.save(v);
            return v != null;
        }
        return false;
    }

    @RequestMapping("update")
    public boolean update(@RequestParam long itemId,
                          @RequestParam String key,
                          @RequestParam int value,
                          String content,
                          float valid) {
        //key = public, archive, status, valid, content,imageUrl,tag
        Optional<Item> o = itemRepository.findById(itemId);
        if (o.isPresent()) {
            Item i = o.get();
            if (key.contains("public")) {
                boolean b = value != 0;
                if (i.isOpenView() == b) {
                    return true;
                } else {
                    i.setOpenView(b);
                }
            }
            if (key.contains("archive")) {
                boolean b = value != 0;
                if (i.isArchived() == b) {
                    return true;
                } else {
                    i.setArchived(b);
                }
            }
            if (key.contains("status")) {
                if (i.getStatus() == value) {
                    return true;
                } else {
                    i.setStatus(value);
                }
            }
            if (key.contains("valid")) {
                i.setValid(valid);
            }
            if (key.contains("content")) {
                if (i.getContent().equals(content)) {
                    return true;
                } else {
                    i.setContent(content);
                }
            }
            if (key.contains("imageUrl")) {
                if (i.getImageUrl().equals(content)) {
                    return true;
                } else {
                    i.setImageUrl(content);
                }
            }
            if (key.contains("tag")) {
                if (i.getTagId().equals(content)) {
                    return true;
                } else {
                    i.setTagId(content);
                }
            }

            i = itemRepository.save(i);
            return i != null;
        }


        return false;
    }

    @RequestMapping("getI")
    public List<Item> getI(@RequestParam long userId,
                           @RequestParam String type,
                           @RequestParam boolean archived,
                           @RequestParam boolean public_) {
        type = type.trim();
        if (TYPES.contains(type)) {
            List<Item> items = itemRepository.findAllByTypeAndUserIdAndArchivedAndOpenView(type, userId, archived, public_);
            return items;
        }
        return new ArrayList<>();
    }

    @RequestMapping("getV")
    public Vocabulary getV(@RequestParam long userId) {
        Optional<Vocabulary> v = vocabularyRepository.findByUserId(userId);
        if (v.isPresent()) {
            return v.get();
        } else {
            Vocabulary vv = new Vocabulary(userId);
            vv = vocabularyRepository.save(vv);
            return vv;
        }

    }

    @RequestMapping("add")
    public boolean add(@RequestParam String type,
                       @RequestParam String content,
                       @RequestParam long userId,
                       Float valid,
                       Integer status,
                       String imageUrl) {

        if (redisHelper.isExisted(userId + content + type)) {
            return false;
        } else {
            redisHelper.save(userId + content + type, "", 600);
        }

        type = type.trim();
        content = content.trim();
        imageUrl = StringUtils.isEmpty(imageUrl) || "null".equals(imageUrl) ? "" : imageUrl.trim();

        if (VOCABULARY.equals(type) || type.contains(VOCABULARY)) {
            return addWord(userId, content);
        }

        if (TYPES.contains(type)) {
            List<Item> items = itemRepository.findAllByTypeAndUserIdAndContent(type, userId, content);
            Item i;
            if (items.size() > 0) {
                i = items.get(0);
                i.setFrequency(i.getFrequency() + 1);
                i.setLastModifiedTime(new Date());
            } else {
                i = new Item(type, userId, content, status, valid, imageUrl);
            }
            i = itemRepository.save(i);
            return i != null;
        }

        return false;
    }


    private boolean addWord(long userId, String wordId) {
        Optional<Vocabulary> o = vocabularyRepository.findByUserId(userId);
        Vocabulary v;
        if (o.isPresent()) {
            v = o.get();
        } else {
            v = new Vocabulary();
            v.setUserId(userId);
        }
        v.addNewWord(wordId);
        v = vocabularyRepository.save(v);
        return v != null;
    }

    @RequestMapping(value = "del", method = GET)
    public boolean del(@RequestParam long itemId) {
        itemRepository.deleteById(itemId);
        return true;
    }

    @Autowired
    private VocabularyRepository vocabularyRepository;

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private RedisHelper redisHelper;
}
