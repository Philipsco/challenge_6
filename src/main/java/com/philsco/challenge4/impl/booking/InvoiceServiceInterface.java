package com.philsco.challenge4.impl.booking;

import net.sf.jasperreports.engine.JRException;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public interface InvoiceServiceInterface {
    void getInvoice(Integer userId, String filmCode) throws IOException, JRException;
}
