package kz.dorm.docx.handler;

import kz.dorm.docx.util.DataConfigDirection;
import org.docx4j.Docx4J;
import org.docx4j.openpackaging.exceptions.Docx4JException;
import org.docx4j.openpackaging.packages.WordprocessingMLPackage;
import org.docx4j.openpackaging.parts.WordprocessingML.MainDocumentPart;
import padeg.lib.Padeg;

import javax.xml.bind.JAXBException;
import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class HandlerDirection {

    /**
     * Создать документ на основе данных.
     */
    public static String create(String name_f, String name_l, String patronymic,
                                 String genderId, int dormId, String address,
                                 String phone) {
        String rootDir = System.getProperty("user.dir");
        String outFileName = "/docs/direction_" + phone.substring(1) + ".docx";
        File fileOut = new File(rootDir + outFileName);
        File file = new File(Objects
                .requireNonNull(HandlerRequest
                        .class
                        .getClassLoader()
                        .getResource("docx/direction.docx"))
                .getFile());

        try {
            Map<String, String> map = new HashMap<>();
            WordprocessingMLPackage wpMLP = WordprocessingMLPackage.load(file);
            MainDocumentPart documentPart = wpMLP.getMainDocumentPart();

            name(map);
            header(map);
            citizen(map, name_f, name_l, patronymic, Integer.parseInt(genderId));
            dorm(map, dormId, address);
            rector(map);

            documentPart.variableReplace(map);
            Docx4J.save(wpMLP, fileOut);
        } catch (Docx4JException | JAXBException e) {
            System.out.println(e);
            return "";
        }

        return outFileName;
    }

    /**
     * Заполнение названия документа.
     */
    private static void name(Map<String, String> map) {
        map.put(DataConfigDirection.DOC_KEY_NAME, DataConfigDirection.DOC_NAME);
    }

    /**
     * Заполнение информации о том, чье направление.
     */
    private static void header(Map<String, String> map) {
        map.put(DataConfigDirection.DOC_KEY_TYPE, DataConfigDirection.DOC_TYPE);
        map.put(DataConfigDirection.DOC_KEY_UNIVERSITY, DataConfigDirection.DOC_UNIVERSITY);
    }

    /**
     * Заполнить поле с ФИО гражданина.
     */
    private static void citizen(Map<String, String> map, String name_f, String name_l, String patronymic, int genderId) {
        if (genderId == 1) { // ID 1 - Мужской
            map.put(DataConfigDirection.DOC_KEY_CITIZEN, DataConfigDirection.DOC_CITIZEN_MALE);
            map.put(DataConfigDirection.DOC_KEY_CITIZEN_WRITE, Padeg.getFIOPadeg(name_l, name_f, patronymic, true, 3));
        }else {
            map.put(DataConfigDirection.DOC_KEY_CITIZEN, DataConfigDirection.DOC_CITIZEN_FEMALE);
            map.put(DataConfigDirection.DOC_KEY_CITIZEN_WRITE, Padeg.getFIOPadeg(name_l, name_f, patronymic, false, 3));
        }
    }

    /**
     * Заполнение номера и адрес общежития.
     */
    private static void dorm(Map<String, String> map, int dormId, String address) {
        map.put(DataConfigDirection.DOC_KEY_DORM_NUMBER, DataConfigDirection.DOC_DORM_NUMBER);
        map.put(DataConfigDirection.DOC_KEY_DORM_NUMBER_WRITE, String.valueOf(dormId));
        map.put(DataConfigDirection.DOC_KEY_DORM_ADDRESS, DataConfigDirection.DOC_DORM_ADDRESS);
        map.put(DataConfigDirection.DOC_KEY_DORM_ADDRESS_WRITE, address);
    }

    /**
     * Заполнение ректора
     */
    private static void rector(Map<String, String> map) {
        map.put(DataConfigDirection.DOC_KEY_RECTOR, DataConfigDirection.DOC_RECTOR);
    }
}
