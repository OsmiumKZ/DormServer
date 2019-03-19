package kz.dorm.api.dorm.util.statement.providers.interfaces;

public interface Select {

    /**
     * Получить все общежития
     */
    String selectDorms();

    /**
     * Получить все этажи
     */
    String selectFloors();

    /**
     * Получить все названия
     */
    String selectNames();

    /**
     * Получить все гендеры
     */
    String selectGenders();

    /**
     * Получить все статусы
     */
    String selectStatus();

    /**
     * Получить номер общежития на основе ID комнаты.
     */
    String selectRoomIdToDormId();

    /**
     * Получить заявление по ID. Ответ в виде расширенно информации.
     */
    String selectRequestIdFull();

    /**
     * Получить отчеты
     */
    String selectReport();

    /**
     * Получить заявления
     */
    String selectRequest();

    /**
     * Получить заявление по UIN.
     */
    String selectRequestUIN();

    /**
     * Получить заявления по ID
     */
    String selectRequestId();

    /**
     * Получить статус по ID.
     */
    String selectStatusId();

    /**
     * Получить гендер по ID.
     */
    String selectGenderId();

    /**
     * Получить аккаунт
     */
    String selectAccount();

    /**
     * Получить список комнат, конкретного этажа, конкретной общаги, с количеством занятых мест
     */
    String selectRooms();

    /**
     * Возвращает совпадение, если есть уже такой UIN в отчетах.
     */
    String selectActiveUINReport();

    /**
     * Проверка комнаты, на допустимый пол студента и свободность самой комнаты.
     */
    String selectCheckRoom();

    /**
     * Возвращает имя.
     */
    String selectNameF();

    /**
     * Возвращает фамилию.
     */
    String selectNameL();

    /**
     * Возвращает отчество.
     */
    String selectPatronymic();

    /**
     * Поиск названий. Подсказка.
     */
    String selectSearchName(String type, String name);

    /**
     * Получить общую статистику.
     */
    String selectStatistic();
}
