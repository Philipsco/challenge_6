package com.philsco.challenge4.module.booking.controller;

import com.philsco.challenge4.module.booking.service.InvoiceService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import net.sf.jasperreports.engine.JRException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/api/v1/cinema/invoice")
public class InvoiceController {
    @Autowired
    private InvoiceService invoiceService;

    private static final Logger LOG = LoggerFactory.getLogger(InvoiceController.class);

    // Pesen Tiket
    @Operation(summary = "Order film ticket, require userId and filmCode in path variable")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "202", description = "Ticket generated!",
                    content = {@Content(schema = @Schema(example = "Ticket generated!"))})
    })
    @GetMapping("/customer/{userId}/{filmCode}")
    public ResponseEntity generateInvoice(@PathVariable("userId") Integer userId, @PathVariable("filmCode") String filmCode) throws JRException, IOException {
        invoiceService.getInvoice(userId, filmCode);
        LOG.info("Ticket Generated");
        return new ResponseEntity(HttpStatus.ACCEPTED);
    }

}
