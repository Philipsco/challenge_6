package com.philsco.challenge4.module.booking.service;

import com.philsco.challenge4.impl.booking.InvoiceServiceInterface;
import com.philsco.challenge4.module.films.service.FilmServiceImpl;
import com.philsco.challenge4.module.schedules.model.ScheduleModel;
import com.philsco.challenge4.module.seats.model.SeatId;
import com.philsco.challenge4.module.seats.model.SeatModel;
import com.philsco.challenge4.module.seats.service.SeatServiceImpl;
import com.philsco.challenge4.module.users.model.UsersModel;
import com.philsco.challenge4.module.users.service.UserServiceImpl;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class InvoiceService implements InvoiceServiceInterface {
    @Autowired
    private FilmServiceImpl filmService;

    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private SeatServiceImpl seatService;

    @Autowired
    private HttpServletResponse hResponse;

    public void getInvoice(Integer userId, String filmCode) throws IOException, JRException {

        JasperPrint jasperPrint = JasperFillManager.fillReport(giveJasperReport(), giveParameter(), giveBeanCollection(userId, filmCode));
        servletResponse();
        JasperExportManager.exportReportToPdfStream(jasperPrint, hResponse.getOutputStream());
    }

    private void servletResponse() {
        hResponse.setContentType("application/pdf");
        hResponse.addHeader("Content-Disposition", "inline; filename=invoice.pdf;");
    }

    private List<Map<String, String>> giveDataList(Integer userId, String filmCode) {
        List<Map<String, String>> dataList = new ArrayList<>();
        Map<String, String> data = new HashMap<>();

        UsersModel user = userService.getUserById(userId);
        List<ScheduleModel> scheduleL = filmService.scheduleFilm(filmCode);
        ScheduleModel schedule = scheduleL.get(0);

        data.put("username", user.getUsername());
        data.put("seatNumber", "A1");
        data.put("filmName", schedule.getFilmCode().getFilmName());
        data.put("showDate", schedule.getScheduleDate().toString());
        data.put("timeStart", schedule.getTimeStart().toString());
        data.put("timeEnd", schedule.getTimeEnd().toString());
        data.put("filmPrice", schedule.getFilmPrice().toString());

        dataList.add(data);
        return dataList;
    }

    private JasperReport giveJasperReport() throws IOException, JRException {
        return JasperCompileManager.compileReport(ResourceUtils.getFile(ResourceUtils.CLASSPATH_URL_PREFIX + "invoiceTicket.jrxml").getAbsolutePath());
    }

    private HashMap<String, Object> giveParameter() {
        HashMap<String, Object> param = new HashMap<>();
        param.put("createdBy", "PhilscoIDN");
        return param;
    }

    private JRBeanCollectionDataSource giveBeanCollection(Integer userId, String filmCode) {
        return new JRBeanCollectionDataSource(giveDataList(userId, filmCode));
    }

}
