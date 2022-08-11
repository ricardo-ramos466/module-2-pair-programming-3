package com.techelevator.controller;

import com.techelevator.dao.CatCardDao;
import com.techelevator.model.CatCard;
import com.techelevator.services.CatFactService;
import com.techelevator.services.CatPicService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(path = "/api/cards")
public class CatController {

    private CatCardDao catCardDao;
    private CatFactService catFactService;
    private CatPicService catPicService;
    private CatCard card = new CatCard();

    public CatController(CatCardDao catCardDao, CatFactService catFactService, CatPicService catPicService) {
        this.catCardDao = catCardDao;
        this.catFactService = catFactService;
        this.catPicService = catPicService;
    }
    @GetMapping()
    public List<CatCard> list(){
        return catCardDao.list();
    }

    @GetMapping(path = "/{id}")
    public CatCard get(@PathVariable int id){
        return catCardDao.get(id);
    }
    @GetMapping(path = "/random")
    public CatCard random(){
//        int cardId = 0;
//        while(cardId==0 || cardId>catCardDao.list().size()) {
//            cardId = (int) (Math.random() * 10);
//        }
//        return catCardDao.get(cardId);
        card.setCatFact(catFactService.getFact().getText());
        card.setImgUrl(catPicService.getPic().getFile());
        return card;
    }

    @PostMapping()
    public CatCard save(@Valid @RequestBody CatCard card){
        catCardDao.save(card);
        return card;
    }

    @PutMapping(path = "/{id}")
    public CatCard update(@PathVariable int id, @Valid @RequestBody CatCard card){
        catCardDao.update(id,card);
        return card;
    }

    @DeleteMapping(path = "/{id}")
    public void delete(@PathVariable int id){
        catCardDao.delete(id);
    }
}
