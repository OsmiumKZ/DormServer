-- --------------------------------------------------------

--
-- Структура таблицы "название"
--
-- [id] - ID названия
-- [name_ru] - на русском
-- [name_kz] - на казахском
-- [name_en] - на английском
--

CREATE TABLE [names] 
    ([id] INT IDENTITY (1, 1) PRIMARY KEY,
     [name_ru] NVARCHAR(150) NOT NULL,
     [name_kz] NVARCHAR(150) NULL,
     [name_en] NVARCHAR(150) NULL)
GO
	
-- --------------------------------------------------------

--
-- Структура таблицы "общежитие"
--
-- [id] - ID общаги
-- [name_id] - Название общежития
--

CREATE TABLE [dorms] 
    ([id] INT IDENTITY (1, 1) PRIMARY KEY,
     [name_id] INT NOT NULL)
GO
	
-- --------------------------------------------------------

--
-- Структура таблицы "комната"
--
-- [id] - ID комнаты
-- [number] - Нумерация комнаты
-- [max] - Максимальное количество людей в комнате
-- [symbol] - Символ(-ы) комнаты
-- [floor_id] - ID этажа
--

CREATE TABLE [rooms]
    ([id] INT IDENTITY (1, 1) PRIMARY KEY,
     [number] INT NOT NULL,
     [max] INT NOT NULL,
     [symbol] NVARCHAR(5) NULL,
     [floor_id] INT NOT NULL)
GO
	
-- --------------------------------------------------------

--
-- Структура таблицы "гендер"
--
-- [id] - ID пола
-- [name_id] - Название пола
--
CREATE TABLE [genders]
    ([id] INT IDENTITY (1, 1) PRIMARY KEY,
     [name_id] INT NOT NULL)
GO
	
-- --------------------------------------------------------

--
-- Структура таблицы "отчет"
--
-- [id] - ID отчета
-- [gender_id] - ID пола
-- [room_id] - ID комнаты
-- [status_id] - ID статуса
-- [uin] - ИИН
-- [date_create] - Дата создания отчета
-- [date_update] - Дата обновление отчета
-- [email] - Электронная почта
-- [address] - Место проживания
-- [phone] - Телефон
-- [children] - Сколько в семье детей.
-- [date_residence] - Дата начала проживания.
-- [parent_id_mother] - ID таблицы [parents] - Мама
-- [parent_id_father] - ID таблицы [parents] - Папа
-- [name_f] - Имя
-- [name_l] - Фамилия
-- [patronymic] - Отчество
--
CREATE TABLE [reports]
    ([id] INT IDENTITY (1, 1) PRIMARY KEY,
     [gender_id] INT NOT NULL,
     [room_id] INT NOT NULL,
     [status_id] INT NOT NULL,
     [uin] BIGINT NOT NULL,
     [date_create] DATETIME NOT NULL,
     [date_update] DATETIME NOT NULL,
     [email] NVARCHAR(254) NULL,
     [address] NVARCHAR(60) NOT NULL,
     [phone] NVARCHAR(15) NOT NULL,
     [children] INT NOT NULL,
     [date_residence] DATE NOT NULL,
     [parent_id_mother] INT NULL,
     [parent_id_father] INT NULL,
     [name_f_id] NVARCHAR(40) NOT NULL,
     [name_l_id] NVARCHAR(40) NOT NULL,
     [patronymic_id] NVARCHAR(40) NULL)
GO
	
-- --------------------------------------------------------

--
-- Структура таблицы "этаж"
--
-- [id] - ID этажа
-- [number] - Номер этажа
-- [number] - ID общаги
--

CREATE TABLE [floors]
    ([id] INT IDENTITY (1, 1) PRIMARY KEY,
     [number] INT NOT NULL,
     [dorm_id] INT NOT NULL)
GO
	
-- --------------------------------------------------------

--
-- Структура таблицы "статус"
--
-- [id] - ID статуса
-- [name_id] - Название статуса
-- [active] - (0 - свободно), (1 - занято) место. (-1) отказ (после принятия заявления).
--

CREATE TABLE [status]
    ([id] INT IDENTITY (1, 1) PRIMARY KEY,
     [name_id] INT NOT NULL,
     [active] INT NOT NULL)
GO
	
-- --------------------------------------------------------

--
-- Структура таблицы "аккаунт"
--
-- [id] - ID статуса
-- [login] - Логин
-- [password] - Пароль
--

CREATE TABLE [accounts]
    ([id] INT IDENTITY (1, 1) PRIMARY KEY,
     [login] NVARCHAR(40) NOT NULL,
     [password] NVARCHAR(40) NOT NULL)
GO
	
-- --------------------------------------------------------

--
-- Структура таблицы "заявление"
--
-- [id] - ID заявления
-- [name_f] - Имя
-- [name_l] - Фамилия
-- [patronymic] - Отчество
-- [email] - Электронная почта
-- [address] - Место проживания
-- [phone] - Телефон
-- [group] - Группа
-- [uin] - ИИН
-- [room_id] - ID комнаты
-- [gender_id] - ID пола
-- [parent_id_mother] - ID таблицы [parents] - Мама
-- [parent_id_father] - ID таблицы [parents] - Папа
-- [children] - Сколько в семье детей.
-- [date_residence] - Дата начала проживания.
-- [date_create] - Дата создания.
-- [active] - (0 - открыт) и (1 - закрыт). Или же (0 - не прочтен) и (1 - прочтен).
--

CREATE TABLE [requests]
    ([id] INT IDENTITY (1, 1) PRIMARY KEY,
     [name_f_id] NVARCHAR(40) NOT NULL,
     [name_l_id] NVARCHAR(40) NOT NULL,
     [patronymic_id] NVARCHAR(40) NULL,
     [email] NVARCHAR(254) NULL,
     [address] NVARCHAR(60) NOT NULL,
     [phone] NVARCHAR(15) NOT NULL,
     [group] NVARCHAR(10) NOT NULL,
     [uin] BIGINT NOT NULL,
     [room_id] INT NOT NULL,
     [gender_id] INT NOT NULL,
     [parent_id_mother] INT NULL,
     [parent_id_father] INT NULL,
     [children] INT NOT NULL,
     [date_residence] DATE NOT NULL,
     [date_create] DATETIME NOT NULL,
     [active] INT NOT NULL)
GO
	
-- --------------------------------------------------------

--
-- Структура таблицы "Родитель"
--
-- [id] - ID родителя
-- [name_f_id] - Имя
-- [name_l_id] - Фамилия
-- [patronymic_id] - Отчество
-- [phone] - Телефон
--

CREATE TABLE [parents]
    ([id] INT IDENTITY (1, 1) PRIMARY KEY,
     [name_f_id] INT NOT NULL,
     [name_l_id] INT NOT NULL,
     [patronymic_id] INT NULL,
     [phone] NVARCHAR(15) NOT NULL)
GO
	
-- --------------------------------------------------------

--
-- Структура таблицы "Имя"
--
-- [id] - ID заявления
-- [name] - Имя
--

CREATE TABLE [name_f]
    ([id] INT IDENTITY (1, 1) PRIMARY KEY,
     [name] NVARCHAR(40) NOT NULL)
GO
	
-- --------------------------------------------------------

--
-- Структура таблицы "Фамилия"
--
-- [id] - ID заявления
-- [name] - Имя
--

CREATE TABLE [name_l]
    ([id] INT IDENTITY (1, 1) PRIMARY KEY,
     [name] NVARCHAR(40) NOT NULL)
GO
	
-- --------------------------------------------------------

--
-- Структура таблицы "Отчество"
--
-- [id] - ID заявления
-- [name] - Имя
--

CREATE TABLE [patronymic]
    ([id] INT IDENTITY (1, 1) PRIMARY KEY,
     [name] NVARCHAR(40) NOT NULL)
GO