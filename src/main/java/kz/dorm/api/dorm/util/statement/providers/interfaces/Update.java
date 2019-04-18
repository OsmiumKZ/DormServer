package kz.dorm.api.dorm.util.statement.providers.interfaces;

public interface Update {

    /**
     * Обновление статуса в отчете, через ID отчета.
     */
    String updateStatus();

    /**
     * Сделать, как прочитанное заявление.
     */
    String updateRequestActive();
}
