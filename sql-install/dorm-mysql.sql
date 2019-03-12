-- --------------------------------------------------------

--
-- Структура таблицы "название"
--
-- `id` - ID названия
-- `name_ru` - на русском
-- `name_kz` - на казахском
-- `name_en` - на английском
--

CREATE TABLE `names` (
	`id` INT NOT NULL AUTO_INCREMENT,
	`name_ru` VARCHAR(150) NOT NULL,
	`name_kz` VARCHAR(150) NULL,
	`name_en` VARCHAR(150) NULL,
	PRIMARY KEY (`id`)) 
	ENGINE = InnoDB
	DEFAULT CHARACTER SET = utf8;
	
-- --------------------------------------------------------

--
-- Структура таблицы "общежитие"
--
-- `id` - ID общаги
-- `name_id` - Название общежития
--

CREATE TABLE `dorms` (
	`id` INT NOT NULL AUTO_INCREMENT,
	`name_id` INT NOT NULL,
	PRIMARY KEY (`id`)) 
	ENGINE = InnoDB
	DEFAULT CHARACTER SET = utf8;
	
-- --------------------------------------------------------

--
-- Структура таблицы "комната"
--
-- `id` - ID комнаты
-- `number` - Нумерация комнаты
-- `max` - Максимальное количество людей в комнате
-- `floor_id` - ID этажа
--

CREATE TABLE `rooms` (
	`id` INT NOT NULL AUTO_INCREMENT,
	`number` INT NOT NULL,
	`max` INT NOT NULL,
	`floor_id` INT NOT NULL,
	PRIMARY KEY (`id`)) 
	ENGINE = InnoDB
	DEFAULT CHARACTER SET = utf8;
	
-- --------------------------------------------------------

--
-- Структура таблицы "гендер"
--
-- `id` - ID пола
-- `name_id` - Название пола
--
CREATE TABLE `genders` (
	`id` INT NOT NULL AUTO_INCREMENT,
	`name_id` INT NOT NULL,
	PRIMARY KEY (`id`)) 
	ENGINE = InnoDB
	DEFAULT CHARACTER SET = utf8;
	
-- --------------------------------------------------------

--
-- Структура таблицы "отчет"
--
-- `id` - ID отчета
-- `gender_id` - ID пола
-- `room_id` - ID комнаты
-- `status_id` - ID статуса
-- `uin` - ИИН
-- `date_create` - Дата создания отчета
-- `date_update` - Дата обновление отчета
-- `address` - Место проживания
-- `phone` - Телефон
-- `children` - Сколько в семье детей.
-- `date_residence` - Дата начала проживания.
-- `parent_id_mother` - ID таблицы `parents` - Мама
-- `parent_id_father` - ID таблицы `parents` - Папа
-- `name_f` - Имя
-- `name_l` - Фамилия
-- `patronymic` - Отчество
--
CREATE TABLE `reports` (
	`id` INT NOT NULL AUTO_INCREMENT,
	`gender_id` INT NOT NULL,
	`room_id` INT NOT NULL,
	`status_id` INT NOT NULL,
	`uin` BIGINT NOT NULL,
	`date_create` DATETIME NOT NULL,
	`date_update` DATETIME NOT NULL,
	`address` VARCHAR(60) NOT NULL,
	`phone` VARCHAR(15) NOT NULL,
	`children` INT NOT NULL,
	`date_residence` DATE NOT NULL,
	`parent_id_mother` INT NULL,
	`parent_id_father` INT NULL,
	`name_f_id` VARCHAR(40) NOT NULL,
	`name_l_id` VARCHAR(40) NOT NULL,
	`patronymic_id` VARCHAR(40) NULL,
	PRIMARY KEY (`id`)) 
	ENGINE = InnoDB
	DEFAULT CHARACTER SET = utf8;
	
-- --------------------------------------------------------

--
-- Структура таблицы "этаж"
--
-- `id` - ID этажа
-- `number` - Номер этажа
-- `number` - ID общаги
--

CREATE TABLE `floors` (
	`id` INT NOT NULL AUTO_INCREMENT,
	`number` INT NOT NULL,
	`dorm_id` INT NOT NULL,
	PRIMARY KEY (`id`)) 
	ENGINE = InnoDB
	DEFAULT CHARACTER SET = utf8;
	
-- --------------------------------------------------------

--
-- Структура таблицы "статус"
--
-- `id` - ID статуса
-- `name_id` - Название статуса
-- `active` - (0 - свободно), (1 - занято) место. (-1) отказ (после принятия заявления).
--

CREATE TABLE `status` (
	`id` INT NOT NULL AUTO_INCREMENT,
	`name_id` INT NOT NULL,
	`active` INT NOT NULL,
	PRIMARY KEY (`id`)) 
	ENGINE = InnoDB
	DEFAULT CHARACTER SET = utf8;
	
-- --------------------------------------------------------

--
-- Структура таблицы "аккаунт"
--
-- `id` - ID статуса
-- `login` - Логин
-- `password` - Пароль
--

CREATE TABLE `accounts` (
	`id` INT NOT NULL AUTO_INCREMENT,
	`login` VARCHAR(40) NOT NULL,
	`password` VARCHAR(40) NOT NULL,
	PRIMARY KEY (`id`)) 
	ENGINE = InnoDB
	DEFAULT CHARACTER SET = utf8;
	
-- --------------------------------------------------------

--
-- Структура таблицы "заявление"
--
-- `id` - ID заявления
-- `name_f` - Имя
-- `name_l` - Фамилия
-- `patronymic` - Отчество
-- `address` - Место проживания
-- `phone` - Телефон
-- `group` - Группа
-- `uin` - ИИН
-- `room_id` - ID комнаты
-- `gender_id` - ID пола
-- `parent_id_mother` - ID таблицы `parents` - Мама
-- `parent_id_father` - ID таблицы `parents` - Папа
-- `children` - Сколько в семье детей.
-- `date_residence` - Дата начала проживания.
-- `active` - (0 - открыт) и (1 - закрыт). Или же (0 - не прочтен) и (1 - прочтен).
--

CREATE TABLE `requests` (
	`id` INT NOT NULL AUTO_INCREMENT,
	`name_f_id` VARCHAR(40) NOT NULL,
	`name_l_id` VARCHAR(40) NOT NULL,
	`patronymic_id` VARCHAR(40) NULL,
	`address` VARCHAR(60) NOT NULL,
	`phone` VARCHAR(15) NOT NULL,
	`group` VARCHAR(10) NOT NULL,
	`uin` BIGINT NOT NULL,
	`room_id` INT NOT NULL,
	`gender_id` INT NOT NULL,
	`parent_id_mother` INT NULL,
	`parent_id_father` INT NULL,
	`children` INT NOT NULL,
	`date_residence` DATE NOT NULL,
	`active` INT NOT NULL,
	PRIMARY KEY (`id`)) 
	ENGINE = InnoDB
	DEFAULT CHARACTER SET = utf8;
	
-- --------------------------------------------------------

--
-- Структура таблицы "Родитель"
--
-- `id` - ID родителя
-- `name_f_id` - Имя
-- `name_l_id` - Фамилия
-- `patronymic_id` - Отчество
-- `phone` - Телефон
--

CREATE TABLE `parents` (
	`id` INT NOT NULL AUTO_INCREMENT,
	`name_f_id` INT NOT NULL,
	`name_l_id` INT NOT NULL,
	`patronymic_id` INT NULL,
	`phone` VARCHAR(15) NOT NULL,
	PRIMARY KEY (`id`)) 
	ENGINE = InnoDB
	DEFAULT CHARACTER SET = utf8;
	
-- --------------------------------------------------------

--
-- Структура таблицы "Имя"
--
-- `id` - ID заявления
-- `name` - Имя
--

CREATE TABLE `name_f` (
	`id` INT NOT NULL AUTO_INCREMENT,
	`name` VARCHAR(40) NOT NULL,
	PRIMARY KEY (`id`)) 
	ENGINE = InnoDB
	DEFAULT CHARACTER SET = utf8;
	
-- --------------------------------------------------------

--
-- Структура таблицы "Фамилия"
--
-- `id` - ID заявления
-- `name` - Имя
--

CREATE TABLE `name_l` (
	`id` INT NOT NULL AUTO_INCREMENT,
	`name` VARCHAR(40) NOT NULL,
	PRIMARY KEY (`id`)) 
	ENGINE = InnoDB
	DEFAULT CHARACTER SET = utf8;
	
-- --------------------------------------------------------

--
-- Структура таблицы "Отчество"
--
-- `id` - ID заявления
-- `name` - Имя
--

CREATE TABLE `patronymic` (
	`id` INT NOT NULL AUTO_INCREMENT,
	`name` VARCHAR(40) NOT NULL,
	PRIMARY KEY (`id`)) 
	ENGINE = InnoDB
	DEFAULT CHARACTER SET = utf8;