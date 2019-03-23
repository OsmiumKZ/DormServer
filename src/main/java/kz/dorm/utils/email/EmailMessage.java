package kz.dorm.utils.email;

import kz.dorm.utils.DataConfig;

public enum EmailMessage {
    CREATE_REQUEST { // Создание заявления.
        @Override
        public String getMessage(String name) {
            return EmailMessage.bodyMessage(name, DataConfig.EMAIL_MESSAGE_TEXT_CREATE_REQUEST);
        }
    },
    DELETE_REQUEST { // Удаление заявления.
        @Override
        public String getMessage(String name) {
            return EmailMessage.bodyMessage(name, DataConfig.EMAIL_MESSAGE_TEXT_DELETE_REQUEST);
        }
    },
    ACCEPT_REQUEST { // Подтверждение заявления.
        @Override
        public String getMessage(String name) {
            return EmailMessage.bodyMessage(name, DataConfig.EMAIL_MESSAGE_TEXT_ACCEPT_REQUEST);
        }
    },
    ACCEPT_REPORT { // Подтверждение отчета.
        @Override
        public String getMessage(String name) {
            return EmailMessage.bodyMessage(name, DataConfig.EMAIL_MESSAGE_TEXT_ACCEPT_REPORT);
        }
    },
    DENIED_REPORT { // Отказать отчету.
        @Override
        public String getMessage(String name) {
            return EmailMessage.bodyMessage(name, DataConfig.EMAIL_MESSAGE_TEXT_DENIED_REPORT);
        }
    };

    /**
     * Абстракция отправки сообщения.
     */
    public abstract String getMessage(String name);

    /**
     * Тело электронного сообщения в HTML.
     */
    private static String bodyMessage(String name, String text) {
        return "<table style=\"background:#1e88e5\"\n" +
                "       width=\"770px\" cellspacing=\"0\" cellpadding=\"0\" align=\"center\">\n" +
                "    <tbody>\n" +
                "    <tr>\n" +
                "        <td style=\"padding-top:60px;padding-right:70px;padding-bottom:60px;padding-left:70px\">\n" +
                "            <p style=\"font-family:Arial,sans-serif;color:#ffffff;font-size:24px;font-weight:bold;padding-bottom:14px\">\n" +
                "                " + DataConfig.GLOBAL_NAME + "\n" +
                "            </p>\n" +
                "            <table style=\"border-color:#e6e6e6;border-width:1px;border-style:solid;background-color:#fff;padding-top:25px;padding-right:0;padding-bottom:50px;padding-left:30px\"\n" +
                "                   width=\"100%\" cellspacing=\"0\" cellpadding=\"0\" align=\"center\">\n" +
                "                <tbody>\n" +
                "                <tr>\n" +
                "                    <td style=\"padding:0 30px 30px\">\n" +
                "                        <p style=\"font-family:Arial,sans-serif;color:#000000;font-size:19px;margin-top:14px;margin-bottom:0\">\n" +
                "                            Здравствуйте, " + name + "!\n" +
                "                        </p>\n" +
                "\n" +
                "                        <p style=\"font-family:Arial,sans-serif;color:#000000;font-size:14px;line-height:17px;margin-top:30px;margin-bottom:0\">\n" +
                "                            " + text + ".\n" +
                "                        </p>\n" +
                "\n" +
                "                        <p style=\"font-family:Arial,sans-serif;color:#000000;font-size:15px;font-style:italic;margin-top:30px;margin-bottom:0\">\n" +
                "                            " + DataConfig.EMAIL_MESSAGE_CLOSE + "\n" +
                "                        </p>\n" +
                "                    </td>\n" +
                "                </tr>\n" +
                "                </tbody>\n" +
                "            </table>\n" +
                "            <table width=\"100%\" cellspacing=\"0\" cellpadding=\"0\" align=\"center\">\n" +
                "                <tbody>\n" +
                "                <tr>\n" +
                "                    <td style=\"font-family:Arial,sans-serif;font-size:12px;color:#888888;padding-right:30px;padding-left:30px\">\n" +
                "                        <p style=\"color:#fafafa\">" + DataConfig.EMAIL_MESSAGE_INFO + "</p>\n" +
                "                        <p style=\"color:#fafafa\">" + DataConfig.EMAIL_MESSAGE_PHONE + "</p>\n" +
                "                    </td>\n" +
                "                </tr>\n" +
                "                </tbody>\n" +
                "            </table>\n" +
                "        </td>\n" +
                "    </tr>\n" +
                "    </tbody>\n" +
                "</table>";
    }
}
