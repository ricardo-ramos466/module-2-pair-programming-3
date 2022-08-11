package com.techelevator.controller;

import com.techelevator.dao.CatCardDao;
import com.techelevator.model.CatCard;
import com.techelevator.services.CatFactService;
import com.techelevator.services.CatPicService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api")
public class CatController {

    private CatCardDao catCardDao;
    private CatFactService catFactService;
    private CatPicService catPicService;

    public CatController(CatCardDao catCardDao, CatFactService catFactService, CatPicService catPicService) {
        this.catCardDao = catCardDao;
        this.catFactService = catFactService;
        this.catPicService = catPicService;
    }
    @GetMapping(path = "/cards")
    public List<CatCard> list(){
        return catCardDao.list();
    }

    @GetMapping(path = "/cards/{id}")
    public CatCard get(@PathVariable int id){
        return catCardDao.get(id);
    }
    @GetMapping(path = "/cards/random")
    public CatCard random(){
        int cardId = 0;
        while(cardId==0 || cardId>catCardDao.list().size()) {
            cardId = (int) (Math.random() * 10);
        }
        return catCardDao.get(cardId);
    }

    @PostMapping(path = "/cards")
    public CatCard save(CatCard card){
        catCardDao.save(card);
        return card;
    }

    @PutMapping(path = "/cards/{id}")
    public CatCard update(@PathVariable int id, CatCard card){
        catCardDao.update(id,card);
        return card;
    }

    @DeleteMapping(path = "/cards/{id}")
    public void delete(@PathVariable int id){
        catCardDao.delete(id);
    }
}
