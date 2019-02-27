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
import kz.dorm.api.dorm.util.statement.DormSELECT;
-- `name_id` - Название статуса
-- `active` - (0 - свободно) или (1 - занято) место.
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
	
	
	

	
	
	
	
	
	
	Заявки
	
	
SELECT `requests`.`id`,
	`requests`.`uin`,
	`requests`.`address`,
	`requests`.`phone`,
	`requests`.`group`,
	`requests`.`gender_id`,
	`rooms`.`number`,
	`rooms`.`dorm_id`,
	`requests`.`children`,
	`mother`.`parent_id_mother`,
	`mother`.`parent_phone_mother`,
	`mother`.`parent_name_f_mother`,
	`mother`.`parent_name_l_mother`,
	`mother`.`parent_patronymic_mother`,
	`father`.`parent_id_father`,
	`father`.`parent_phone_father`,
	`father`.`parent_name_f_father`,
	`father`.`parent_name_l_father`,
	`father`.`parent_patronymic_father`,
	`requests`.`date_residence`,
	`name_f`.`name`
		AS `name_f`, 
	`name_l`.`name`
		AS `name_l`, 
	`patronymic`.`name`
		AS `patronymic`
FROM `requests`
INNER JOIN `name_f`
ON `requests`.`name_f_id`=`name_f`.`id`
INNER JOIN `name_l`
ON `requests`.`name_l_id`=`name_l`.`id`
LEFT JOIN `patronymic`
ON `requests`.`patronymic_id`=`patronymic`.`id`
INNER JOIN 
	(SELECT `rooms`.`id`, 
		`rooms`.`number`, 
		`floors`.`dorm_id`
	FROM `rooms`
	INNER JOIN `floors`
	ON `rooms`.`floor_id`=`floors`.`id`)
	AS `rooms`
ON `requests`.`room_id`=`rooms`.`id`
LEFT JOIN 
	(SELECT `parents`.`id`
			AS `parent_id_mother`,
		`parents`.`phone`
			AS `parent_phone_mother`,
		`name_f`.`name`
			AS `parent_name_f_mother`,
		`name_l`.`name`
			AS `parent_name_l_mother`,
		`patronymic`.`name`
			AS `parent_patronymic_mother`
	FROM `parents`
	INNER JOIN `name_f`
	ON `parents`.`name_f_id`=`name_f`.`id`
	INNER JOIN `name_l`
	ON `parents`.`name_l_id`=`name_l`.`id`
	LEFT JOIN `patronymic`
	ON `parents`.`patronymic_id`=`patronymic`.`id`)
	AS `mother`
ON `requests`.`parent_id_mother`=`mother`.`parent_id_mother`
LEFT JOIN 
	(SELECT `parents`.`id`
			AS `parent_id_father`,
		`parents`.`phone`
			AS `parent_phone_father`,
		`name_f`.`name`
			AS `parent_name_f_father`,
		`name_l`.`name`
			AS `parent_name_l_father`,
		`patronymic`.`name`
			AS `parent_patronymic_father`
	FROM `parents`
	INNER JOIN `name_f`
	ON `parents`.`name_f_id`=`name_f`.`id`
	INNER JOIN `name_l`
	ON `parents`.`name_l_id`=`name_l`.`id`
	LEFT JOIN `patronymic`
	ON `parents`.`patronymic_id`=`patronymic`.`id`)
	AS `father`
ON `requests`.`parent_id_father`=`father`.`parent_id_father`



Отчеты

SELECT `reports`.`id`,
	`reports`.`uin`,
	`reports`.`address`,
	`reports`.`phone`,
	`reports`.`gender_id`,
	`reports`.`status_id`,
	`rooms`.`number`,
	`rooms`.`dorm_id`,
	`reports`.`date_create`,
	`reports`.`date_update`,
	`reports`.`children`,
	`mother`.`parent_id_mother`,
	`mother`.`parent_phone_mother`,
	`mother`.`parent_name_f_mother`,
	`mother`.`parent_name_l_mother`,
	`mother`.`parent_patronymic_mother`,
	`father`.`parent_id_father`,
	`father`.`parent_phone_father`,
	`father`.`parent_name_f_father`,
	`father`.`parent_name_l_father`,
	`father`.`parent_patronymic_father`,
	`reports`.`date_residence`,
	`name_f`.`name`
		AS `name_f`, 
	`name_l`.`name`
		AS `name_l`, 
	`patronymic`.`name`
		AS `patronymic`
FROM `reports`
INNER JOIN `name_f`
ON `reports`.`name_f_id`=`name_f`.`id`
INNER JOIN `name_l`
ON `reports`.`name_l_id`=`name_l`.`id`
INNER JOIN `patronymic`
ON `reports`.`patronymic_id`=`patronymic`.`id`
INNER JOIN 
	(SELECT `rooms`.`id`, `rooms`.`number`, `floors`.`dorm_id`
	FROM `rooms`
	INNER JOIN `floors`
	ON `rooms`.`floor_id`=`floors`.`id`)
	AS `rooms`
ON `reports`.`room_id`=`rooms`.`id`
LEFT JOIN 
	(SELECT `parents`.`id`
			AS `parent_id_mother`,
		`parents`.`phone`
			AS `parent_phone_mother`,
		`name_f`.`name`
			AS `parent_name_f_mother`,
		`name_l`.`name`
			AS `parent_name_l_mother`,
		`patronymic`.`name`
			AS `parent_patronymic_mother`
	FROM `parents`
	INNER JOIN `name_f`
	ON `parents`.`name_f_id`=`name_f`.`id`
	INNER JOIN `name_l`
	ON `parents`.`name_l_id`=`name_l`.`id`
	LEFT JOIN `patronymic`
	ON `parents`.`patronymic_id`=`patronymic`.`id`)
	AS `mother`
ON `reports`.`parent_id_mother`=`mother`.`parent_id_mother`
LEFT JOIN 
	(SELECT `parents`.`id`
			AS `parent_id_father`,
		`parents`.`phone`
			AS `parent_phone_father`,
		`name_f`.`name`
			AS `parent_name_f_father`,
		`name_l`.`name`
			AS `parent_name_l_father`,
		`patronymic`.`name`
			AS `parent_patronymic_father`
	FROM `parents`
	INNER JOIN `name_f`
	ON `parents`.`name_f_id`=`name_f`.`id`
	INNER JOIN `name_l`
	ON `parents`.`name_l_id`=`name_l`.`id`
	LEFT JOIN `patronymic`
	ON `parents`.`patronymic_id`=`patronymic`.`id`)
	AS `father`
ON `reports`.`parent_id_father`=`father`.`parent_id_father`


































































































SELECT `rooms`.`id`, `rooms`.`number`, `rooms`.`max`, `rooms`.`floor_id`, `rooms`.`amount` 
FROM `floors`
INNER JOIN 
	(SELECT `rooms`.`id`, `rooms`.`number`, `rooms`.`max`, `rooms`.`floor_id`, 
		IF(`reports`.`amount` IS NULL, 0, `reports`.`amount`) 
		AS `amount`
	FROM `rooms`
	LEFT JOIN 
		(SELECT `reports`.`room_id`, COUNT(`status`.`active`) 
		AS `amount` 
		FROM `reports`
		LEFT JOIN 
			(SELECT `status`.`id`, `status`.`active` 
			FROM `status`
			WHERE `status`.`active`=1)
		AS `status` 
		ON `reports`.`status_id`=`status`.`id`
		GROUP BY `reports`.`room_id`)
	AS `reports` 
	ON `rooms`.`id`=`reports`.`room_id`)
AS `rooms` 
ON `floors`.`id`=`rooms`.`floor_id`
WHERE `floors`.`number`=2 AND `floors`.`dorm_id`=1







SELECT `requests`.`uin` 
FROM `requests`
WHERE `requests`.`uin`=?
UNION
SELECT `reports`.`uin` 
FROM `reports`
INNER JOIN 
	(SELECT *
	FROM `status`
	WHERE `status`.`active`=1)
AS `status`
ON `reports`.`status_id`=`status`.`id`
WHERE `reports`.`uin`=?



SELECT * 
FROM 
	(SELECT `rooms`.`id`, `rooms`.`max`,
		IF(`reports`.`gender_id` IS NULL, 0, `reports`.`gender_id`)
			AS `gender_id`,
		IF(`reports`.`amount` IS NULL, 0, `reports`.`amount`) 
			AS `amount`
	FROM `rooms`
	LEFT JOIN 
		(SELECT `reports`.`room_id`, `reports`.`gender_id`,
			COUNT(`status`.`active`) 
				AS `amount` 
		FROM `reports`
		LEFT JOIN 
			(SELECT `status`.`id`, `status`.`active` 
			FROM `status`
			WHERE `status`.`active`=1)
			AS `status` 
		ON `reports`.`status_id`=`status`.`id`
		GROUP BY `reports`.`room_id`)
		AS `reports` 
	ON `rooms`.`id`=`reports`.`room_id`)
	AS `rooms`
WHERE `rooms`.`id`=?
	AND (`rooms`.`gender_id`=? OR `rooms`.`gender_id`=0)
	AND `rooms`.`max`>`rooms`.`amount`
	
	
	
	
	
	
