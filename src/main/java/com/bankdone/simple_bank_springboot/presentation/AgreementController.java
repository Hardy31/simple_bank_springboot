package com.bankdone.simple_bank_springboot.presentation;

import com.bankdone.simple_bank_springboot.business.AgreementService;
import com.bankdone.simple_bank_springboot.entity.Agreement;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("/rest")
public class AgreementController {

    /**
     * объект AgreementService для получения результата запроса из сервис слоя
     */
    private final AgreementService agreementService;

    /**
     * При отправке Get запроса на  URN rest/agreement/{id}
     *  вернет Agreement по id
     */
    @GetMapping("agreement/{id}")
    public Agreement getBYId(@PathVariable long id) {
        return agreementService.getById(id);
    }

    /**
     * При отправке Get запроса на  URN rest/agreements/all
     *  вернет список всех Agreement.
     */
    @GetMapping("agreements/all")
    public List<Agreement> getAll() {
        return agreementService.getAll();
    }

    /**
     * При отправке Зщые запроса на  URN rest/createAgreement
     *  создаст в БД  и вернет сохраненный Agreement.
     */
    @PostMapping("createAgreement")
    public Agreement createAgreement(@RequestBody Agreement agreement) {
        return agreementService.create(agreement);
    }

    /**
     * При отправке Delite запроса на  URN rest/deleteArgument/{id}
     *  удаляет из БД Аргумент по id
     */
    @DeleteMapping("deleteArgument/{id}")
    public void deleteAgreement(@PathVariable Long id) {
        agreementService.delete(id);
    }

    /**
     * При отправке Put запроса на  URN rest/editAgreement
     *  изменит его в БД  по id и вернет измененный.
     */
    @PutMapping("editAgreement")
    public Agreement editAgreement(@RequestBody Agreement agreement) {
        return agreementService.edit(agreement);
    }

}
