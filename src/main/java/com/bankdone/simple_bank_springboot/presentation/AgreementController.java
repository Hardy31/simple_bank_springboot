package com.bankdone.simple_bank_springboot.presentation;

import com.bankdone.simple_bank_springboot.business.AgreementService;
import com.bankdone.simple_bank_springboot.entity.Agreement;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/rest/agreements")
public class AgreementController {

    /**
     * объект AgreementService для получения результата запроса из сервис слоя
     */
    private final AgreementService agreementService;

    /**
     * При отправке Get запроса на  URN rest/agreements/{id}
     *  вернет Agreement по id
     */
    @GetMapping("/{id}")
    public Agreement getBYId(@PathVariable long id) {
        log.info("AgreementController getBYId(@PathVariable long id : {}", id);
        return agreementService.getById(id);
    }

    /**
     * При отправке Get запроса на  URN rest/agreements
     *  вернет список всех Agreement.
     */
    @GetMapping("")
    public List<Agreement> getAll() {
        log.info("AgreementController getAll()");
        return agreementService.getAll();
    }

    /**
     * При отправке Зщые запроса на  URN rest/agreements
     *  создаст в БД  и вернет сохраненный Agreement.
     */
    @PostMapping("")
    public Agreement createAgreement(@RequestBody Agreement agreement) {
        log.info("AgreementController createAgreement(@RequestBody Agreement agreement) : {]", agreement);
        return agreementService.create(agreement);
    }

    /**
     * При отправке Delite запроса на  URN rest/agreements/{id}
     *  удаляет из БД Аргумент по id
     */
    @DeleteMapping("/{id}")
    public void deleteAgreement(@PathVariable Long id) {
        log.info("AgreementController cdeleteAgreement(@PathVariable Long id) : {]", id);
        agreementService.delete(id);
    }

    /**
     * При отправке Put запроса на  URN rest/agreements
     *  изменит его в БД  по id и вернет измененный.
     */
    @PutMapping("")
    public Agreement editAgreement(@RequestBody Agreement agreement) {
        log.info("AgreementController editAgreement(@RequestBody Agreement agreement) : {]", agreement);
        return agreementService.edit(agreement);
    }

}
