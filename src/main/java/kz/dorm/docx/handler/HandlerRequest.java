package kz.dorm.docx.handler;

import kz.dorm.docx.util.DataConfigRequest;
import kz.dorm.utils.control.ControlWrite;
import kz.dorm.utils.DateText;
import org.docx4j.Docx4J;
import org.docx4j.model.datastorage.migration.VariablePrepare;
import org.docx4j.openpackaging.exceptions.Docx4JException;
import org.docx4j.openpackaging.packages.WordprocessingMLPackage;
import org.docx4j.openpackaging.parts.WordprocessingML.MainDocumentPart;
import org.docx4j.wml.ContentAccessor;
import padeg.lib.Padeg;

import javax.xml.bind.JAXBException;
import java.io.File;
import java.util.*;

public class HandlerRequest {

    /**
     * Создать документ на основе данных.
     */
    public static String create(String name_f, String name_l, String patronymic,
                                String group, int dormId, String date_residence,
                                String children, String phone, String address,
                                String genderId, String number) {
        String outFileName = "docs/request_" + phone.substring(1) + ".docx";
        File fileOut = new File(outFileName);
        File file = new File("docs/patterns/request.docx");

        Student student = new Student(name_f,
                name_l,
                patronymic,
                Integer.parseInt(genderId) == 1);

        try {
            Map<String, String> map = new HashMap<>();
            WordprocessingMLPackage wpMLP = WordprocessingMLPackage.load(file);
            VariablePrepare.prepare(wpMLP);
            MainDocumentPart documentPart = wpMLP.getMainDocumentPart();

            header(map, student, group, phone);
            name(map);
            text(map, dormId, date_residence, children);
            address(map, address);
            rule(map, Integer.parseInt(genderId));
            verification(map, student);
            number(map, number);

            documentPart.variableReplace(map);
            Docx4J.save(wpMLP, fileOut);
        } catch (Docx4JException | JAXBException e) {
            return "docs/null";
        } catch (Exception e) {
            e.printStackTrace();
        }

        return outFileName;
    }

    /**
     * Заполнение анкеты сверху-справа.
     */
    private static void header(Map<String, String> map, Student student, String group, String phone) {
        map.put(DataConfigRequest.DOC_KEY_POSITION, DataConfigRequest.DOC_POSITION);
        map.put(DataConfigRequest.DOC_KEY_WHOM, DataConfigRequest.DOC_WHOM);
        map.put(DataConfigRequest.DOC_KEY_STUDENT, DataConfigRequest.DOC_STUDENT);
        map.put(DataConfigRequest.DOC_KEY_STUDENT_WRITE, student.getAttrName());
        map.put(DataConfigRequest.DOC_KEY_GROUP, DataConfigRequest.DOC_GROUP);
        map.put(DataConfigRequest.DOC_KEY_GROUP_WRITE, group);
        map.put(DataConfigRequest.DOC_KEY_PHONE, DataConfigRequest.DOC_PHONE);
        map.put(DataConfigRequest.DOC_KEY_PHONE_WRITE, phone);
    }

    /**
     * Заполнение названия документа.
     */
    private static void name(Map<String, String> map) {
        map.put(DataConfigRequest.DOC_KEY_NAME, DataConfigRequest.DOC_NAME);
    }

    /**
     * Заполнение текста для заявления.
     */
    private static void text(Map<String, String> map, int dormId, String date_residence, String children) {
        map.put(DataConfigRequest.DOC_KEY_TEXT_ONE, DataConfigRequest.DOC_TEXT_ONE);
        map.put(DataConfigRequest.DOC_KEY_TEXT_ONE_WRITE, String.valueOf(dormId));
        map.put(DataConfigRequest.DOC_KEY_TEXT_TWO, DataConfigRequest.DOC_TEXT_TWO);
        map.put(DataConfigRequest.DOC_KEY_TEXT_TWO_WRITE, DateText.getResidentDate(date_residence));
        map.put(DataConfigRequest.DOC_KEY_TEXT_THREE, DataConfigRequest.DOC_TEXT_THREE);
        map.put(DataConfigRequest.DOC_KEY_TEXT_THREE_WRITE, DateText.getResidentYear(date_residence));
        map.put(DataConfigRequest.DOC_KEY_TEXT_FOUR, DataConfigRequest.DOC_TEXT_FOUR);
        map.put(DataConfigRequest.DOC_KEY_TEXT_CHILD, DataConfigRequest.DOC_TEXT_CHILD);
        map.put(DataConfigRequest.DOC_KEY_TEXT_CHILD_WRITE, children);
        map.put(DataConfigRequest.DOC_KEY_TEXT_CHILDREN, textChildren(children));
    }

    /**
     * Заполнение места проживания.
     */
    private static void address(Map<String, String> map, String address) {
        map.put(DataConfigRequest.DOC_KEY_ADDRESS, DataConfigRequest.DOC_ADDRESS);
        map.put(DataConfigRequest.DOC_KEY_ADDRESS_WRITE, address);
    }

    /**
     * Заполнение пользовательского соглашения.
     */
    private static void rule(Map<String, String> map, int genderId) {
        if (genderId == 1) // ID 1 - Мужской
            map.put(DataConfigRequest.DOC_KEY_RULE,
                    DataConfigRequest.DOC_RULE_MALE);
        else
            map.put(DataConfigRequest.DOC_KEY_RULE,
                    DataConfigRequest.DOC_RULE_FEMALE);
    }

    /**
     * Заполнение подтвердительной информации.
     */
    private static void verification(Map<String, String> map, Student student) {
        map.put(DataConfigRequest.DOC_KEY_DATE, DataConfigRequest.DOC_DATE);
        map.put(DataConfigRequest.DOC_KEY_DATE_WRITE, DateText.getDocCreate());
        map.put(DataConfigRequest.DOC_KEY_VERIFICATION, DataConfigRequest.DOC_VERIFICATION);
        map.put(DataConfigRequest.DOC_KEY_VERIFICATION_WRITE, student.getFullName());
    }

    /**
     * Заполнение номера документа.
     */
    private static void number(Map<String, String> map, String number) {
        map.put(DataConfigRequest.DOC_KEY_REQUEST_NUMBER_WRITE,
                DataConfigRequest.DOC_REQUEST_NUMBER + number);
    }

    /**
     * Определить текст для количество детей в семье.
     */
    private static String textChildren(String children) {
        int child = Integer.parseInt(children);

        if (child >= 10 && child <= 20) {
            return DataConfigRequest.DOC_TEXT_CHILD_THREE;
        } else {
            child %= 10;

            if (child == 1) {
                return DataConfigRequest.DOC_TEXT_CHILD_ONE;
            } else if (child >= 2 && child <= 4) {
                return DataConfigRequest.DOC_TEXT_CHILD_TWO;
            } else {
                return DataConfigRequest.DOC_TEXT_CHILD_THREE;
            }
        }

    }

    /**
     * Класс, для для упрощенной передачи информации, ФИО студента.
     */
    static class Student {

        /* Имя. */
        private String name_f;

        /* Фамилия. */
        private String name_l;

        /* Отчество. */
        private String patronymic;

        /* Гендер. */
        private boolean gender;

        Student(String name_f, String name_l, String patronymic, boolean gender) {
            this.name_f = name_f;
            this.name_l = name_l;
            this.patronymic = patronymic;
            this.gender = gender;
        }

        /**
         * Получить полное ФИО.
         */
        String getFullName() {
            return Padeg.getFIOPadeg(name_f, name_l, patronymic, gender, 1);
        }

        /**
         * Получить ФИО инициалами.
         */
        String getAttrName() {
            return ControlWrite
                    .getAttrName(name_f, Padeg.getIFPadegFS(name_l, gender, 2), patronymic);
        }
    }

    /**
     * Поиск элемента в документе.
     */
    private static List<Object> getAllElementFromObject(Object obj, Class<?> toSearch) {
        List<Object> result = new ArrayList<>();

        if (obj.getClass().equals(toSearch)) {
            result.add(obj);
        } else if (obj instanceof ContentAccessor) {
            List<?> children = ((ContentAccessor) obj).getContent();

            for (Object child : children)
                result.addAll(getAllElementFromObject(child, toSearch));
        }

        return result;
    }

    /**
     * Удаление строки в документе.
     */
    private static void remove(MainDocumentPart documentPart, Class<?> classType, int index) {
        documentPart.getContent()
                .remove((getAllElementFromObject(documentPart, classType).get(index)));
    }
}
