package kz.dorm.api.dorm.util.statement.providers.interfaces;

public interface Insert {

    /**
     * Добавить отчет.
     */
    String insertReport();

    /**
     * Добавить заявление.
     */
    String insertRequest();

    /**
     * Добавить имя.
     */
    String insertNameF();

    /**
     * Добавить фамилию.
     */
    String insertNameL();

    /**
     * Добавить отчество.
     */
    String insertPatronymic();

    /**
     * Добавить родителя.
     */
    String insertParent();

    /**
     * Добавить город.
     */
    String insertCity();

    /**
     * Добавить вид на жительство.
     */
    String insertResidencePermit();
}