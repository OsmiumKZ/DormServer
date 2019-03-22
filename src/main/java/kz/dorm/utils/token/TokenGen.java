package kz.dorm.utils.token;

class TokenGen {

    /* Цифры */
    private static final char[] digit = "0123456789".toCharArray();

    /* Латинские буквы, нижнего регистра */
    private static final char[] lower = "abcdefghijklmnopqrstuvwxyz".toCharArray();

    /* Латинские буквы, верхнего регистра */
    private static final char[] upper = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();

    /**
     * Генерирует токен.
     */
    static String generate(int amount) {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < amount; i++) {
            int chooseType = (int) (Math.random() * 3);

            switch (chooseType) {
                case 0: // lowercase
                    sb.append(lower[randomRange(0, lower.length - 1)]);
                    break;
                case 1: // uppercase
                    sb.append(upper[randomRange(0, upper.length - 1)]);
                    break;
                case 2: // digits
                    sb.append(digit[randomRange(0, digit.length - 1)]);
                    break;
            }
        }

        return sb.toString();
    }

    /**
     * Рандомное число.
     */
    private static int randomRange(int min, int max) {
        int range = (max - min) + 1;

        return (int) (Math.random() * range) + min;
    }
}
